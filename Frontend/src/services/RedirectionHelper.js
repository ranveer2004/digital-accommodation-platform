import AppRoutes from "../utils/AppRoutes";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import UserContext from "../utils/UserContext";
import Navigate from "./NavigationService";

class RedirectionHelper {
    
    static fromLogin() {
        // const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if(UserContext.isUserLoggedIn()) {
            Navigate.to({ path: AppRoutes.dashboard, clearBrowserStack: true });
        }
    }

    static fromHome() {
        const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if( !isLoggedIn ) {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        } 
        else {
            const loggedInUser = UserContext.getLoggedInUser();
    
            if( loggedInUser.name == null ) {
                Navigate.to({ path: AppRoutes.editProfile, clearBrowserStack: true });
            }
        }
    }

    static fromEditProfile() {
        // const isLoggedIn = LocalStorageHelper.getJwtAccessToken() !== null;

        if( !UserContext.isUserLoggedIn() ) {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        }
    }

    static fromAddPropertyPage() {
        if( !UserContext.isUserLoggedIn() ) {
            Navigate.to({ path: AppRoutes.login, clearBrowserStack: true });
        }
    }

    static fromSpecificPropertyPage(propertyId) {
        if( !propertyId ) {
            Navigate.to({ path: AppRoutes.showAllProperties, clearBrowserStack: true });
        }
    }

}

export default RedirectionHelper;