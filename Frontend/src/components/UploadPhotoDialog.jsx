import { Box, Dialog, DialogContent, DialogTitle, Input, Typography } from "@mui/material";
import Row from "./Row";
import CustomButton from "./CustomButton";
import React from "react";
import Colors from "../utils/Colors";
import getFileObject from "../types/InputFile";

/**
 * 
 * @param {Object} props 
 * @param {boolean} props.showDialog
 * @param {function(boolean):void} props.setShowDialog
 * @param {function(Object):void} props.saveFileOnUpload
 * @returns {JSX.Element}
 */
function UploadProfilePhotoDialog({ showDialog, setShowDialog, saveFileOnUpload }) {
    const [ selectedFile, setSelectedFile ] = React.useState({});
    const [ showPhotoError, setShowPhotoError ] = React.useState(false);

    const handleSelectedPhoto = (event) => {
        const file = getFileObject(event);
        console.log(file);

        if(file) {
            if(file.type.startsWith("image/")) {
                setShowPhotoError(false);
                const localUrl = URL.createObjectURL(file);
                
                setSelectedFile({ ...selectedFile, localUrl, file });
            } else {
                setShowPhotoError(true);

                setSelectedFile({});
            }
        } else {
            setSelectedFile({});
        }
    }

    return (
        <Dialog open={ showDialog } fullWidth={true} >
            <DialogTitle textAlign={"center"} fontSize={"1.4em"} >
                Choose photo
            </DialogTitle>

            <DialogContent>
                {
                    showPhotoError  
                    ?   <Typography color={Colors.error} >
                            Please select a valid photo [ .png | .jpg | .webp ]
                        </Typography> 
                    :   (
                            selectedFile.localUrl 
                            ?   <Box
                                    component="img"
                                    src={ selectedFile.localUrl }
                                    sx={{ 
                                        width: "200px",
                                        height: "auto" 
                                    }} 
                                /> 
                            :   null
                        )
                }
                
                <Input
                    type="file" 
                    accept="image/*"
                    fullWidth={true}
                    sx={{
                        color: Colors.white,
                        mt: 2,
                        mb: 1,
                    }}
                    onChange={ handleSelectedPhoto }
                />
                
                <Row justifyContent="space-evenly" marginTop={3} >
                    <CustomButton 
                        title="Cancel" 
                        bgColor={Colors.error}
                        textColor={Colors.white}
                        fullWidth={false}
                        mb={2}
                        onPress={ () => {
                            setShowDialog(false);
                            setSelectedFile({});
                        }} 
                    />
                    <CustomButton 
                        title="Upload"
                        disabled={showPhotoError} 
                        bgColor={Colors.success}
                        textColor={Colors.white}
                        fullWidth={false} 
                        mb={2}
                        onPress={() => {
                            saveFileOnUpload(selectedFile.file);

                            URL.revokeObjectURL(selectedFile.localUrl);
                            setShowDialog(false);
                        }}
                    />
                </Row>
            </DialogContent>
        </Dialog>
    );
}

export default UploadProfilePhotoDialog;