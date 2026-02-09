
class Navigate {
    
    /**
     * @type {Function} navigate 
     */
    static #navigate = null;

    /**
     * Set the navigate function from useNavigate
     * @param {Function} navigate
     */
    static set(navigate) {
        if(this.#navigate == null) {
            this.#navigate = navigate;
        }
    }

    /**
     * Navigate programmatically from anywhere
     */
    static to({ path, state = {}, clearBrowserStack = false }) {
        if(this.#navigate == null) {
            // throw new Error("Navigate function not set");
            window.location.replace(path);
        }
        
        // TODO: navigating via window obj
        window.location.replace(path);
        // this.#navigate(
        //     path, {
        //         state: state,
        //         replace: clearBrowserStack
        //     }
        // );
    }

    static goBack() {
        if(this.#navigate == null) {
            throw new Error("Navigate function not set");
        }
        this.#navigate(-1);
    }

    static goForward() {
        if(this.#navigate == null) {
            throw new Error("Navigate function not set");
        }
        this.#navigate(1);
    }
}

export default Navigate;