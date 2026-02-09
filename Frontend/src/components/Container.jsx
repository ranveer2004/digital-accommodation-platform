import { Box, Paper } from "@mui/material";
import Colors from "../utils/Colors";
import Column from "./Column.jsx";

function Container({ 
    children, 
    height="auto", width="auto", maxWidth="auto",
    marginLeft = "10px", marginRight = "10px", marginTop = "10px", marginBottom = "10px",
    paddingLeft = "30px", paddingRight = "30px", paddingTop = "30px", paddingBottom = "30px",
}) {
    return (
        <Paper
            variant="elevation"
            elevation={10}
            sx={{ 
                border: '1px solid',
                borderColor: Colors.grey,
                borderRadius: 10
                // bgcolor: Colors.foreground 
            }}
        >
            <Column
                height={height}
                width={width}
                maxWidth={maxWidth}
                marginLeft={marginLeft} marginRight={marginRight} marginTop={marginTop} marginBottom={marginBottom}
                paddingLeft={paddingLeft} paddingRight={paddingRight} paddingTop={paddingTop} paddingBottom={paddingBottom}
            >
                {children}
            </Column>
        </Paper>
    );
}

export default Container;