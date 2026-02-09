import { Box } from "@mui/material";

function Row({ 
    children, 
    height="auto", width="auto",
    justifyContent = 'center', alignItems = 'center', 
    marginLeft = "0px", marginRight = "0px", marginTop = "0px", marginBottom = "0px",
    paddingLeft = "0px", paddingRight = "0px", paddingTop = "0px", paddingBottom = "0px",
}) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    return (
        <Box 
            height={height}
            width={width}
            display={"flex"}
            flexDirection={"row"}
            justifyContent={justifyContent}
            alignItems={alignItems}
            marginTop={marginTop} marginRight={marginRight} marginBottom={marginBottom} marginLeft={marginLeft}
            paddingTop={paddingTop} paddingRight={paddingRight} paddingBottom={paddingBottom} paddingLeft={paddingLeft}
        >
            {children}
        </Box>
    );
}

export default Row;
