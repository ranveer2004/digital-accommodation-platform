import Navigate from '../services/NavigationService';
import AppRoutes from './AppRoutes';
import LocalStorageHelper from './LocalStorageHelper';
import UserContext from './UserContext';

class ApiActionHelper {

    static logoutHelper() {
        LocalStorageHelper.clearUid();
        LocalStorageHelper.clearUserProfile();
        LocalStorageHelper.clearJwtAccessToken();
    }

    /**
     * @param {Object} response
     */
    static loginSignupResponseHelper(response) {
        LocalStorageHelper.setUserProfile(response);
    }
    
    /**
     * @param {import('axios').AxiosResponse} response
     */
    static tokenResponseHelper(response) {
        console.log(`response FROM axios response helper : ${response}`);
        const { uid, accessToken } = response.data;

        LocalStorageHelper.setUid(uid);
        LocalStorageHelper.setJwtAccessToken(accessToken);
    }

    /**
     * @param {import('axios').AxiosResponse} response
     */
    static profileResponseHelper(response) {
        console.log(`response FROM axios response helper : ${response}`);
        
        LocalStorageHelper.setUserProfile(response.data);
        UserContext.setLoggedInUser(response.data);
    }


    static Error = class {
        /**
         * Profile related Error Codes :: 
            1301  :  NO_PROFILE_FOUND(1301, ""We could not find a profile matching the given user id. Please try to log in and create a profile.""),
            1302  :  PROFILE_ALREADY_CREATED(1302, "Profile already created. If you like to make changes, please update your existing profile.");
         */

        /**
         * 
         * @param {number} errorCode 
         */
        static profileErrorAction(errorCode) {
            if(errorCode == 1301) {
                Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
            } else {
                Navigate.to({ path: AppRoutes.home, clearBrowserStack: true });
            }
        }


        /**
         * Token related Error Codes :: 
            1001  :	 EMPTY(1001, "Authorization header missing or invalid."),
            1002  :  EXPIRED(1002, "Token has expired. Please log in again to continue."),
            1003  :  BLACKLISTED(1003, "This token is invalid or has already been invalidated."),
            1004  :  INVALID(1004, "Invalid authorization token."),
            1005  :  INVALID_USER(1005, "Invalid user details provided in authorization token."),
            1006  :  MALFORMED(1006, "Malformed authorization. The authorization token format is invalid or corrupted."),
            1007  :  UNSUPPORTED(1007, "The algorithm used in the authorization token is not supported."),
            1010  :  BLOCKED(1010, "Access denied. The token is no longer valid. Please log in again to continue. !!!");
        */

        /**
         * 
         * @param {number} errorCode 
         * @param {Function | null} callback
         */
        static accessTokenErrorAction(errorCode, callback) {
            if(errorCode == 1001 || errorCode == 1003 || errorCode == 1005 || errorCode == 1006 || errorCode == 1007 || errorCode == 1010) {
                // logout --> login page
                
            } else if(errorCode == 1002) {
                // refresh token --> send request again
            } else if(errorCode == 1004) {
                // Do nothing, just show error
            }
        }

        /**
         * 
         * @param {number} errorCode 
         * @param {Function | null} callback
         */
        static refreshTokenErrorAction(errorCode) {
            if(errorCode == 1001 || errorCode == 1002 || errorCode == 1003 || errorCode == 1005 || errorCode == 1006 || errorCode == 1007 || errorCode == 1010) {
                // logout --> login page
            } else if(errorCode == 1004) {
                // Do nothing, just show error
            }
        }

    }

}

export default ApiActionHelper;