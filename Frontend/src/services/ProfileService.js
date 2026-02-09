import ApiCaller from "./ApiCaller";
import ApiActionHelper from "../utils/ApiActionHelper";
import { getResponseError } from "../types/ResponseType";
import SupabaseHelper from "./SupabaseHelper";
import { isObjEmpty } from "./JsUtils";

function handleUpdateProfile(file, profile, setProfile, setProfileUpdateError, setIsLoading) {

    /**
     * @param {Event} event
     */
    return async (event) => {
        setIsLoading(true);
        event.preventDefault();
        
        if(!profile.name || !profile.phoneNo || !profile.address) {
            setProfileUpdateError("Invalid Name, Phone Number, Address.");
            setIsLoading(false);
            return;
        }

        console.log("file : ", file);
        console.log("isObjEmpty : ", isObjEmpty(file));
        console.log("profile :", profile);

        if(!isObjEmpty(file)) {
            const photoUrl = await SupabaseHelper.uploadProfilePhotoFile(file);

            console.log(`photoUrl : ${photoUrl}`);
            profile["photoUrl"] = photoUrl;

            if(photoUrl) {
                setProfileUpdateError("");
                
                setProfile(function(prev) {
                    return {
                        ...prev,
                        photoUrl
                    };
                });

                await updateUserProfile(profile, setProfileUpdateError);
            } else {
                setProfileUpdateError("Oops !!! Something went wrong. Please refresh and try again later.");
            }
        } else {
            await updateUserProfile(profile, setProfileUpdateError);
        }
        
        setIsLoading(false);
    }
}

async function updateUserProfile(profile, setProfileUpdateError) {
    try {
        var response = await ApiCaller.updateProfile(profile);
        console.log("success [1] : ", response);
        // window.location.reload();
    } catch(error) {
        var resError = getResponseError(error);
        
        if(resError.errorCode == 1002) {
            try {
                console.log("Token expired - REFRESHING token");
                await ApiCaller.refreshToken();
                
                response = await ApiCaller.updateProfile(profile);
                console.log("success [2] : ", response);
                
                setProfileUpdateError("");
                // window.location.reload();
            } catch(error) {
                console.log("error [1] : ", error);
                
                resError = getResponseError(error);

                setProfileUpdateError(resError.message);
            }
        } else {
            setProfileUpdateError(resError.errorMessage);
        }
    }
}

export { handleUpdateProfile };