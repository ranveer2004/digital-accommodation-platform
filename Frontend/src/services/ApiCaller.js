import axios, { AxiosError } from "axios";
import Endpoints from "../utils/ApiEndpoints";
import PayloadHelper from "../utils/PayloadHelper";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import AxiosHelper from "./AxiosHelper";
import ApiActionHelper from "../utils/ApiActionHelper";
import Navigate from "./NavigationService";
import AppRoutes from "../utils/AppRoutes";
import { getResponseError } from "../types/ResponseType";

class ApiCaller {

    static async getProfile() {
        const url = Endpoints.profile;
        
        return AxiosHelper
            .GET({ url, isAuthHeader: true })
            .then((response) => {
                ApiActionHelper.profileResponseHelper(response);
                    
                return response;
            });
    }

    static async forgotPassword(resetEmail) {
        const url = Endpoints.forgotPassword;
        console.log("calling : " + url);
        console.log("payload ", resetEmail);

        return AxiosHelper.POST({ url, body: resetEmail, isAuthHeader: false, withCredentials: false });
    }

    static async changePassword(newPasswordData) {
        const url = Endpoints.changePassword;
        console.log("calling : " + url);
        console.log("payload ", newPasswordData);

        return AxiosHelper.PUT({ url, body: newPasswordData, isAuthHeader: false });
    }

    /**
     * 
     * @param {import("../utils/UserContext").User} profile 
     */
    static async updateProfile(profile) {
        const url = Endpoints.profile;
        console.log("calling : " + url);

        return AxiosHelper
            .PUT({ url, body: profile, isAuthHeader: true })
            .then((response) => {
                ApiActionHelper.profileResponseHelper(response);

                return response;
            });
    }

    static async login({email, password}) {
        if(!email || !password) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }

        const url = Endpoints.login;
        const body = PayloadHelper.forLogin({ email, password, deviceId: LocalStorageHelper.getDeviceId() });
        
        console.log("calling : " + url);
        console.log("with : " + body);

        return AxiosHelper
            .POST({ url, body, isAuthHeader: false, withCredentials: true })
            .then((response) => {
                ApiActionHelper.tokenResponseHelper(response);

                const { uid } = response.data;
                ApiActionHelper.loginSignupResponseHelper({ uid, email });

                return response;
            });
    }

    /**
     * Never call this method from 
     * 'ApiActionHelper.Error'
     * @returns void
     */
    static async logout() {
        const logoutURL = Endpoints.logout;

        try {
            await AxiosHelper.POST({ url: logoutURL, isAuthHeader: true, withCredentials: false });

            ApiActionHelper.logoutHelper();
        } 
        catch(error) {
            await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, ()=>{}, async () => {
                await AxiosHelper.POST({ url: logoutURL, isAuthHeader: true, withCredentials: false });

                ApiActionHelper.logoutHelper();
            });
        }
        finally {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        }
        // await AxiosHelper
        //     .POST({ url: logoutURL, isAuthHeader: true, withCredentials: false })
        //     .then((_) => {
        //         ApiActionHelper.logoutHelper();
        //     })
        //     .catch( async (_) => {
        //         await this.refreshToken();
        //         await AxiosHelper.POST({ url: logoutURL, isAuthHeader: true, withCredentials: false });
        //     });
        //     // .finally(() => {
        //     // });
        // Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
    }

    static async generateSignupOtp({ email }) {
        if(!email) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }

        const url = Endpoints.signupOtp;

        return AxiosHelper.POST({ url, body: { email }, isAuthHeader: false, withCredentials: false });
    }

    static async signup(formData) {
        if(!formData.email || !formData.password) {
            throw new Error("Invalid email / password while calling Axios Helper");
        }

        const url = Endpoints.signup;
        const body = PayloadHelper.forSignup(formData);

        console.log("calling : " + url);
        console.log("with : " + body);

        return await AxiosHelper
            .POST({ url, body, isAuthHeader: false, withCredentials: true })
            .then((response) => {
                ApiActionHelper.tokenResponseHelper(response);

                const { uid } = response.data;
                ApiActionHelper.loginSignupResponseHelper({ uid, email: formData.email });

                return response;
            });
    }

    static async refreshToken() {
        const url = Endpoints.refreshToken;

        try {
            const response = await AxiosHelper.POST({ url, isAuthHeader: false, withCredentials: true });

            ApiActionHelper.tokenResponseHelper(response);

            return response;
        } catch(error) {
            ApiActionHelper.logoutHelper();
        }

        // await AxiosHelper
        //     .POST({ url, isAuthHeader: false, withCredentials: true })
        //     .then((response) => {
        //         ApiActionHelper.tokenResponseHelper(response);

        //         return response;
        //     })
        //     .catch((error) => {
        //         ApiActionHelper.logoutHelper();
        //     });
    }

   
    /**
     * 
     * @param {Error} error 
     * @param {function} callback 
     * @param {function} setError 
     * @returns 
     */
    static async onErrorCallRefreshAndRepeatProcess(error, setError, callback) {
        console.log(error);
        try {
            const resError = getResponseError(error);
            console.log("resError : ", resError);

            if(resError.errorCode && resError.errorCode === 1002) {
                await this.refreshToken();

                return await callback();
                // console.log("true");
            }
        } 
        catch(error) {
            setError(error.response.data?.message || "Oops !!! Something went wrong");
        }
        // try {
        //     await this.refreshToken();
        //     return callback();
        // } catch(error) {
        //     return error;
        // }
    }

}

export default ApiCaller;