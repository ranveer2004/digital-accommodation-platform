import React from "react";
import { Box, IconButton, Link, TextField } from "@mui/material";
import Colors from "../utils/Colors";
import AppRoutes from "../utils/AppRoutes";
import { AccountBox, AccountCircle, Search, VerifiedUser } from "@mui/icons-material";
import AssetHelper from "../utils/AssetHelper";
import LoggedInUserMenu from "./LoggedInUserMenu";
import Row from "./Row";
import LoggedOutUserMenu from "./LoggedOutUserMenu";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import SizedBox from "./SizedBox";

const Navbar = () => {
  const [ anchorEl, setAnchorEl ] = React.useState(null); 

  const navBarStyle = { 
    position: "fixed", 
    top: 0, 
    left: 0, 
    width: "100%",  
    height: "50px",
    zIndex: 1000,
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    background: `linear-gradient(90deg, ${Colors.background} 70%)`,
    boxShadow: `0 5px 10px rgba(255, 255, 255, 0.07)` 
  };

  const handleMenuOnOpen = (event) => {
    console.log("clicked " + event.target);
    setAnchorEl(event.target);
  }

  /**
   * @return {boolean}
   */
  function isUserLoggedIn() {
    return (LocalStorageHelper.getJwtAccessToken() != null)
  }

  return (

    <>
      <nav style={ navBarStyle } >
        <Link 
          href={AppRoutes.home}
          style={{ marginRight: 8, display: "flex", justifyContent: "center", alignItems: "center" }}
        >
          <img src={AssetHelper.appIcon} alt="app-logo" style={{ height: "25px", margin: 5 }} />
          StayEn.Casa
        </Link>
        
        <Box marginRight={1} >
          {
            isUserLoggedIn() 
            ? (<IconButton onClick={ handleMenuOnOpen } >
                <AccountCircle />
              </IconButton>) 
            : (<LoggedOutUserMenu />)
          }
        </Box>

        {/* <Row justifyContent="end" > */}
          {/* <IconButton onClick={ handleMenuOnOpen } >
            <AccountCircle />
          </IconButton> */}
        {/* </Row> */}
      </nav>

      <LoggedInUserMenu 
        anchorEl={ anchorEl } 
        setAnchorEl={ setAnchorEl } 
      />
    </>
  );
};

export default Navbar;
