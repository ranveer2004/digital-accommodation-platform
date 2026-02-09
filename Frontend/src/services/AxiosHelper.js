import axios from "axios";
import LocalStorageHelper from "../utils/LocalStorageHelper";

class AxiosHelper {

    static #getAuthHeader() {
        const jwtToken = LocalStorageHelper.getJwtAccessToken();

        return {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${jwtToken}`,
        };
    }
    
    static #getNoAuthHeader() {
        return {
            "Content-Type": "application/json",
        };
    }
    
    static GET({ url, isAuthHeader = true }) {
        const header = isAuthHeader ? this.#getAuthHeader() : this.#getNoAuthHeader();

        return axios.get(url,
        {
            headers: header,
        });
    }

    static POST({ url, body = {}, isAuthHeader = true, withCredentials = false }) {
        const header = isAuthHeader ? this.#getAuthHeader() : this.#getNoAuthHeader();
        
        return axios.post(url, body, 
        {
            headers: header,
            withCredentials: withCredentials,
        });
    }

    static PUT({ url, body = {}, isAuthHeader = true, withCredentials = false }) {
        const header = isAuthHeader ? this.#getAuthHeader() : this.#getNoAuthHeader();
        
        return axios.put(url, body, 
        {
            headers: header,
            withCredentials: withCredentials
        });
    }

    static PATCH({ url, body = {}, isAuthHeader = true }) {
        const header = isAuthHeader ? this.#getAuthHeader() : this.#getNoAuthHeader();
        return axios.patch(url, body, {
            headers: header,
        });
    }

}

export default AxiosHelper;