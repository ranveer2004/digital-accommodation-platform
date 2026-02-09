import { Box } from "@mui/material";

function Column({ 
    children, 
    height="auto", width="auto", maxWidth=400, 
    marginLeft = 0, marginRight = 0, marginTop = 0, marginBottom = 0,
    paddingLeft = 0, paddingRight = 0, paddingTop = 0, paddingBottom = 0,
    justifyContent = 'center', 
    alignItems = 'center' }
) {
    if(!children) {
        throw new Error('Children required !!!');
    }

    return (
        <Box 
            height={height}
            width={width}
            maxWidth={maxWidth}
            display={"flex"}
            flexDirection={"column"}
            justifyContent={justifyContent}
            alignItems={alignItems}
            marginLeft={marginLeft} marginRight={marginRight} marginTop={marginTop} marginBottom={marginBottom}
            paddingLeft={paddingLeft} paddingRight={paddingRight} paddingTop={paddingTop} paddingBottom={paddingBottom}
            // sx={{
            //     // display: 'flex',
            //     // flexDirection: 'column',
            //     // justifyContent: justifyContent,
            //     // alignItems: alignItems,
            //     marginLeft: marginLeft,
            //     marginRight: marginRight,
            // }}
        >
            {children}
        </Box>
    );
}

export default Column;