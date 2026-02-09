import { Box } from "@mui/material";

function SizedBox({ width, height }) {
    return (
        <>
            <Box sx={{
                width: width,
                height: height,
            }} />
        </>
    );
}

export default SizedBox;