import { Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";

function CustomDialog({ title, msg, showDialog, setShowDialog, successCallback }) {
    return (
        <Dialog open={ showDialog } >
            <DialogTitle>{ title }</DialogTitle>
            <DialogContent>
                { msg }
            </DialogContent>
            <DialogActions>
                <Row justifyContent="space-evenly" marginTop={3} >
                    <CustomButton 
                        title="Close" 
                        bgColor={Colors.error}
                        textColor={Colors.white}
                        fullWidth={false}
                        onPress={ () => {
                            setShowDialog(false);
                        }} 
                    />
                    <CustomButton 
                        title="Upload"
                        disabled={showPhotoError} 
                        bgColor={Colors.success}
                        textColor={Colors.white}
                        fullWidth={false} 
                        onPress={() => {
                            successCallback();

                            setShowDialog(false);
                        }}
                    />
                </Row>
            </DialogActions>
        </Dialog>
    );
}

export default CustomDialog;