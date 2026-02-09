import ApiCaller from "./ApiCaller";
import AppRoutes from "../utils/AppRoutes";
import Navigate from "./NavigationService";
import { getResponseError } from "../types/ResponseType";

function handleGenerateSignupOtp(setIsLoading, setShowSignup, setErrorMsg) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        setIsLoading(true);
        event.preventDefault();

        const form = event.target;
        const formData = Object.fromEntries(new FormData(form));

        try {
            const response = await ApiCaller.generateSignupOtp(formData);

            if(response.status === 200) {
                setErrorMsg(response.data.message);
                setShowSignup(true);
            }
        } 
        catch(error) {
            const resError = getResponseError(error);
            setErrorMsg(resError.errorMessage);
        } 
        finally {
            setIsLoading(false);
        }
    }
}

function handleSignupFormSubmit(setIsLoading, setErrorMsg, setShowError) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        setIsLoading(true);
        event.preventDefault();

        const form = event.target;
        const formData = Object.fromEntries(new FormData(form));
        console.log("Form Data : ", formData);

        const { password, confirmPassword } = formData;

        /**
         * TODO:
         * - if signup fail
         * - if signup is successful
         * -- create profile 
         */
        if(password === confirmPassword) {
            await ApiCaller.signup(formData)
                .then((_) => {
                    setShowError(true);
                    // navigate(AppRoutes.editProfile);
                    Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
                })
                .catch((error) => {
                    console.log(error);
                    
                    const resError = getResponseError(error);

                    setErrorMsg(resError.errorMessage);
                    setShowError(true);
                });
        } else {
            setErrorMsg("Password is not matching. Please confirm your password.");
        }

        setIsLoading(false);
    };
}

export { handleGenerateSignupOtp, handleSignupFormSubmit };