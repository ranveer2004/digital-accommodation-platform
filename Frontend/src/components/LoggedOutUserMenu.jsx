import { LogoutRounded } from "@mui/icons-material";
import Navigate from "../services/NavigationService";
import AppRoutes from "../utils/AppRoutes";
import Colors from "../utils/Colors";
import CustomButton from "./CustomButton";
import Row from "./Row";
import SizedBox from "./SizedBox";

function LoggedOutUserMenu() {
    return (
        <Row>
            <CustomButton 
                title="Login"
                bgColor={Colors.primary}
                fullWidth={false}
                onPress={() => Navigate.to({ path: AppRoutes.login, clearBrowserStack: false })}
                />
            <SizedBox width={5} />
            <CustomButton 
                title="Signup"
                width={"0px"}
                // endIcon={<LogoutRounded />}
                bgColor={Colors.primary}
                fullWidth={false}
                onPress={() => Navigate.to({ path: AppRoutes.signup, clearBrowserStack: false })}
            />
        </Row>
    );
}

export default LoggedOutUserMenu;