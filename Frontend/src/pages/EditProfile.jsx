import React from "react";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import { Avatar, Backdrop, Box, Button, CircularProgress, Dialog, DialogContent, DialogTitle, Divider, IconButton, Input, Tab, Tabs, TextField, Typography } from "@mui/material";
import Login from "./Login";
import Signup from "./Signup";
import Container from "../components/Container";
import UserContext from "../utils/UserContext";
import AssetHelper from "../utils/AssetHelper";
import Row from "../components/Row"; 
import SizedBox from "../components/SizedBox";
import Colors from "../utils/Colors";
import CustomButton from "../components/CustomButton";
import { CloudDone, Done, DoneAll, Edit } from "@mui/icons-material";
import { handleUpdateProfile } from "../services/ProfileService";
import Navigate from "../services/NavigationService";
import AppRoutes from "../utils/AppRoutes";
import RedirectionHelper from "../services/RedirectionHelper";
import IdHelper from "../utils/IdHelper";
import getFileObject from "../types/InputFile";
import UploadProfilePhotoDialog from "../components/UploadPhotoDialog";

function EditProfile() {
    RedirectionHelper.fromEditProfile();

    let loggedInUser = UserContext.getLoggedInUser();

    if(loggedInUser.name === undefined) {
        loggedInUser = {
            ...loggedInUser,
            name: "",
            phoneNo: "",
            address: "",
            photoUrl: "",
        };
    }

    const [ isLoading, setIsLoading ] = React.useState(false);
    const [ profile, setProfile ] = React.useState(loggedInUser ?? {});
    const [ file, setFile ] = React.useState({});
    const [ profileUpdateError, setProfileUpdateError ] = React.useState("");

    const [ showDialog, setShowDialog ] = React.useState(false);

    const titleStyle = {
        fontSize: "1.2em",
    };

    const valueStyle = {
        fontSize: "0.9em",
        color: Colors.white
    };

    /**
     * @param {Event} event 
     */
    function handleTextFieldUpdate(event) {
        const fieldName = event.target.name;
        const value = event.target.value;

        switch(fieldName) {
            case 'name':
                setProfile({...profile, name: value});
                break;
                case 'phoneNo':
                setProfile({...profile, phoneNo: value});
                break;
                case 'address':
                setProfile({...profile, address: value});
                break;
        }
    }

    function getPhotoUrl() {
        if(loggedInUser.photoUrl) {
            return loggedInUser.photoUrl;
        }
        return AssetHelper.defaultUserPhoto;
    }

    return (
        <React.Fragment>
            <Container  maxWidth={500} >
                <Box position="relative" display="inline-block">
                    <Avatar 
                        src={getPhotoUrl()} 
                        alt="profile picture" 
                        onError={(event) => {
                            event.target.onerror = null;
                            event.target.src = AssetHelper.defaultUserPhoto;
                        }}
                        referrerPolicy="no-referrer"
                        sx={{
                            height: "120px",
                            width: "120px",
                            mb: 4,
                        }}
                    />
                    
                    <IconButton
                        size="small"
                        sx={{
                            position: 'absolute',
                            top: 0,
                            right: -20,
                            boxShadow: 3,
                            backgroundColor: Colors.white,
                            color: Colors.background,
                            '&:hover': {
                                backgroundColor: Colors.primary,
                                color: Colors.white,
                            },
                        }}
                        onClick={() => { setShowDialog(true) }}
                    >
                        <Edit fontSize="" />
                    </IconButton>
                </Box>

                
                {/* 
                    User Uid
                */}
                <Row
                    width="100%"
                    justifyContent="space-between" 
                >
                    <Typography sx={ titleStyle }>User ID : </Typography>
                    <SizedBox width={50} />
                    <Typography sx={ valueStyle }>{ loggedInUser.uid }</Typography>
                </Row>
                
                {/* 
                    User Email
                */}
                <Row 
                    width="100%"
                    marginTop={2}
                    justifyContent="space-between"
                >
                    <Typography sx={ titleStyle }>Email : </Typography>
                    <Typography sx={ valueStyle }>{ loggedInUser.email }</Typography>
                </Row>

                {/* <DividerWithText text={""} /> */}

                <hr width="100%" color={Colors.white} style={{ marginTop: 30, marginBottom: 20 }} />

                {/* 
                    User Name
                */}
                <Box sx={{ width: "100%", textAlign: "start", mb: 3 }}>
                    <Typography>Name</Typography>
                    <TextField 
                        variant="outlined"
                        type="text"
                        name="name"
                        value={profile.name ?? ""}
                        placeholder="Your name"
                        onChange={handleTextFieldUpdate}
                        required={true}
                        sx={{ mt: 0.5 }}
                    />
                </Box>

                {/* 
                    User PhoneNo
                */}
                <Box sx={{ width: "100%", textAlign: "start", mb:3 }}>
                    <Typography>Phone number</Typography>
                    <TextField 
                        variant="outlined"
                        type="tel"
                        name="phoneNo"
                        value={profile.phoneNo ?? ""}
                        placeholder="+91 ----- -----"
                        onChange={handleTextFieldUpdate}
                        required={true}
                        sx={{ mt: 0.5 }}
                    />
                </Box>

                {/* 
                    User Address
                */}
                <Box sx={{ width: "100%", textAlign: "start", mb: 3}}>
                    <Typography>Address</Typography>
                    <TextField 
                        variant="outlined"
                        type="text"
                        name="address"
                        value={profile.address ?? ""}
                        placeholder="Your full address with pincode"
                        multiline={true}
                        rows={4}
                        onChange={handleTextFieldUpdate}
                        required={true}
                        sx={{ mt: 0.5 }}
                    />
                </Box>

                <Typography sx={{ width: "100%", mb: 1.5, color: Colors.error }} >{ profileUpdateError }</Typography>
                
                <CustomButton title="Update" startIcon={ <CloudDone /> } type="button" mb={2} onPress={ handleUpdateProfile(file, profile, setProfile, setProfileUpdateError, setIsLoading) } />
            </Container>

            {
                showDialog && 
                (<UploadProfilePhotoDialog  
                    showDialog={showDialog}
                    setShowDialog={setShowDialog}
                    saveFileOnUpload={setFile}
                />) 
            }

            <Backdrop open={isLoading} >
                <CircularProgress color="" />
            </Backdrop>

        </React.Fragment>
    );
}

export default EditProfile;