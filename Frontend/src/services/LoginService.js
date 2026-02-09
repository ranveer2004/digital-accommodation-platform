import IdHelper from "../utils/IdHelper";
import ApiCaller from "./ApiCaller";
import AppRoutes from "../utils/AppRoutes";
import ApiActionHelper from "../utils/ApiActionHelper";
import { getResponseError as getResponseErrorData } from "../types/ResponseType";
import Navigate from "./NavigationService";

function handleLoginFormSubmit(setIsLoading, setErrorMsg, setShowError) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        // return async function startLogin(event) {
        setIsLoading(true);

        setShowError(false);
        event.preventDefault();

        const form = event.target;
        const formData = new FormData(form);

        const { email, password } = Object.fromEntries(formData.entries());

        console.log(`email : ${email}`);
        console.log(`password : ${password}`);

        await ApiCaller.login({email, password})
            .then( async (_) => {
                setShowError(false);

                await ApiCaller.getProfile()
                    .then((_) => {
                        // navigate(AppRoutes.home);
                        Navigate.to({ path: AppRoutes.home, clearBrowserStack: true });
                    })
                    /**
                     * Error in profile fetching
                     */
                    .catch((error) => {
                        const resError = getResponseErrorData(error);

                        ApiActionHelper.Error.profileErrorAction(resError.errorCode);
                    });
            })
            /**
             * Error in login process
             */
            .catch((error) => {
                const { errorCode, errorMessage } = error.response.data;
                console.log('Error during login : ' + errorCode + " : " + errorMessage);

                setErrorMsg(errorMessage);
                setShowError(true);
            });

        setIsLoading(false);
    }
}

export { handleLoginFormSubmit };