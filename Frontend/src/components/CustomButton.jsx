import { Button, colors } from "@mui/material";
import Colors from "../utils/Colors";
import { LoginRounded } from "@mui/icons-material";

function CustomButton({ 
    title = "Button", 
    width={},
    height={},
    onPress, 
    disabled=false,
    bgColor=Colors.white,
    textColor=Colors.background,
    hover={},
    type = 'button', // button, submit, reset
    startIcon = null,
    endIcon = null,
    fullWidth=true,
    mb=0,
}) {
    return(
        <Button 
            variant="contained"
            onClick={onPress}
            disabled={disabled}
            type={type}
            startIcon={startIcon}
            endIcon={endIcon}
            fullWidth={fullWidth}
            sx={{ 
                height: {height},
                width: {width},
                mb: mb,
                backgroundColor: bgColor,
                color: textColor,
                '&.Mui-disabled': {
                    color: Colors.grey,
                    border: `1px solid ${Colors.lightGrey}`,
                    backgroundColor: textColor,
                },
            }}
        >
            {title}
        </Button>
    );
}

export default CustomButton;