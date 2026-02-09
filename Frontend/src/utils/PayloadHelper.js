import LocalStorageHelper from "./LocalStorageHelper";

class PayloadHelper {
    static forLogin({email, password, deviceId}) {
        return {
            "email": email,
            "password": password,
            "deviceId": deviceId,
        };
    }

    static forSignup(formData) {
        const { confirmPassword, ...restData } = formData;

        const signupBody = { 
            ...restData, 
            deviceId: LocalStorageHelper.getDeviceId(), 
        };

        return signupBody;
    }
}

export default PayloadHelper;