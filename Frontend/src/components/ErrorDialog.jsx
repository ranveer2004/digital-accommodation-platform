import CustomDialog from "./CustomDialog";

function ErrorDialog({ msg, showDialog, setShowDialog, successCallback }) {
    return (
        <CustomDialog
            title="Oops!!! Something went wrong."
            msg={msg}
            showDialog={showDialog}
            setShowDialog={setShowDialog}
            successCallback={successCallback}
        />
    );
}

export default ErrorDialog;