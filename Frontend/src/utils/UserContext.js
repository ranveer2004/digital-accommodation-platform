import LocalStorageHelper from "./LocalStorageHelper";

class UserContext {

    /**
     * @type {import('../types/User').User}
     */
    static #user = null;

    static isUserLoggedIn() {
        const token = LocalStorageHelper.getJwtAccessToken();
        return (token != null);
    }

    /**
     * @returns {import('../types/User').User | null}
     */
    static getLoggedInUser() {
        if(this.#user == null) {
            this.#user = LocalStorageHelper.getUserProfile();
        }
        return this.#user;
    }

    /**
     * 
     * @param {import('../types/User').User | null} profile 
     */
    static setLoggedInUser(profile) {
        this.#user = profile;
    }
}

export default UserContext;