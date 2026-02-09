import { getResponseError } from "../types/ResponseType";
import ApiCaller from "./ApiCaller";

function handleChangePasswordFormSubmit({ setIsLoading, setErrorMsg, setShowError }) {
    
    /**
     * @param {Event} event
     */
    return async (event) => {
        setIsLoading(true);
        event.preventDefault();

        const form = event.target;
        const formData = Object.fromEntries(new FormData(form).entries());
        console.log("Form Data : ", formData);

        try {
            const response = await ApiCaller.changePassword(formData);

            setErrorMsg(response.data.message);
        } catch(error) {
            const resError = getResponseError(error);

            setErrorMsg(resError.errorMessage);
        }
        setShowError(true);

        setIsLoading(false);
    }
}

export default handleChangePasswordFormSubmit;