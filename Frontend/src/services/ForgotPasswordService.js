import { getResponseError } from "../types/ResponseType";
import ApiCaller from "./ApiCaller";

/**
 * @returns {function(event): void}
 * 
 * @author Aadarsh Yadav
 */
function handleResetPassword({ setIsLoading, setErrorMsg, setShowError }) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        event.preventDefault();
        setIsLoading(true);

        const form = event.target;
        const formData = Object.fromEntries(new FormData(form).entries());
        console.log("Form Data : ", formData);

        try {
            const response = await ApiCaller.forgotPassword(formData);
            
            setErrorMsg(response.data.message);
        } catch(error) {
            const resError = getResponseError(error);

            setErrorMsg(resError.errorMessage);
        }
        setShowError(true);

        setIsLoading(false);
    }
}

export default handleResetPassword;