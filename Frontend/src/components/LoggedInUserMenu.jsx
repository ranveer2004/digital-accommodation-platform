import { AccountCircle, Book, Dashboard, DashboardCustomize, Home, Logout, Note } from "@mui/icons-material";
import { Box, colors, Menu, MenuItem, MenuList, Typography } from "@mui/material";
import Row from "./Row";
import SizedBox from "./SizedBox";
import React from "react";
import Colors from "../utils/Colors";
import Navigate from "../services/NavigationService";
import AppRoutes from "../utils/AppRoutes";
import ApiCaller from "../services/ApiCaller";
import LocalStorageHelper from "../utils/LocalStorageHelper";

function LoggedInUserMenu({ anchorEl, setAnchorEl }) {
    const toOpen = Boolean(anchorEl);

    const menuStyle = {
        border: '1px solid',
        borderColor: Colors.white,
    }
    const iconStyle = {
        color: Colors.white,
    }
    const textStyle = {
        color: Colors.white,
    }

    const onDashboard = () => {
        onClose();
        Navigate.to({ path: AppRoutes.dashboard });
    }
    const onEditProfile = () => {
        onClose();
        Navigate.to({ path: AppRoutes.editProfile });
    }
    const onMyBooking = () => {
        onClose();
    }
    const onLogout = () => {
        onClose();
        ApiCaller.logout();
    }
    const onClose = () => {
        setAnchorEl(null);
    }

    return (
        <Menu
            anchorEl={anchorEl}
            open={ toOpen }
            onClose={ onClose }
            slotProps={{
                paper: {
                    sx: {
                        border: '1px solid #fff',
                        borderRadius: '8px',
                        boxShadow: '0 2px 8px rgba(0,0,0,0.15)'
                    }
                }
            }}
        >
            <MenuItem onClick={ onEditProfile } >
                <React.Fragment>
                    <AccountCircle style={iconStyle} />
                    <SizedBox width={10} />
                    <Typography style={textStyle} >{ LocalStorageHelper.getUserProfile()?.email }</Typography>
                </React.Fragment>
            </MenuItem>

            <MenuItem onClick={ onDashboard } >
                <React.Fragment>
                    <DashboardCustomize style={iconStyle} />
                    <SizedBox width={10} />
                    <Typography style={textStyle} >Dashboard</Typography>
                </React.Fragment>
            </MenuItem>

            {/* <MenuItem onClick={ onEditProfile } >
                <React.Fragment>
                    <AccountCircle style={iconStyle} />
                    <SizedBox width={10} />
                    <Typography style={textStyle} >Profile</Typography>
                </React.Fragment>
            </MenuItem> */}

            <MenuItem onClick={ onMyBooking } >
                <React.Fragment>
                    <Note style={iconStyle} />
                    <SizedBox width={10} />
                    <Typography style={textStyle}>My Booking</Typography>
                </React.Fragment>
            </MenuItem>

            <MenuItem onClick={ onLogout } >
                <React.Fragment>
                    <Logout style={iconStyle} />
                    <SizedBox width={10} />
                    <Typography style={textStyle}>Logout</Typography>
                </React.Fragment>
            </MenuItem>
        </Menu>
    );
}

export default LoggedInUserMenu;