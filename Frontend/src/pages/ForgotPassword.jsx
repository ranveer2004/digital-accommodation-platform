import React from 'react';
import { Box, TextField, Button, Typography, Paper, Backdrop, CircularProgress } from '@mui/material';
import IdHelper from '../utils/IdHelper';
import CustomButton from '../components/CustomButton';
import { Key, Lock } from '@mui/icons-material';
import AssetHelper from '../utils/AssetHelper';
import Column from '../components/Column';
import Colors from '../utils/Colors';
import Container from '../components/Container';
import handleResetPassword from '../services/ForgotPasswordService';
import Row from '../components/Row';
import SizedBox from '../components/SizedBox';
import Navigate from '../services/NavigationService';
import AppRoutes from '../utils/AppRoutes';

function ForgotPassword () {
    const [ isLoading, setIsLoading ] = React.useState(false);
    const [ errorMsg, setErrorMsg ] = React.useState("");
    const [ showError, setShowError ] = React.useState(false);

    return (
        <Container>
            <Box
                component="img"
                src={AssetHelper.appIconWithName}
                alt="Box Image"
                sx={{ 
                    width: 200, 
                    height: 'auto', 
                    borderRadius: 2,
                    }}
            />
            
            <Typography variant="body2" sx={{ mb: 3 }}>
                An OTP will be send to registered Email ID
            </Typography>

            <Typography 
                style={{marginBottom: "25px", fontSize: '0.8em', color: Colors.error}}
                hidden={showError === false}
            >
                {errorMsg}
            </Typography>

            <form onSubmit={ handleResetPassword({ setIsLoading, setErrorMsg, setShowError }) } >
                <TextField
                    id={IdHelper.resetPasswordEmail}
                    name='email'
                    variant='outlined'
                    label='Email'
                    placeholder='your@gmail.com'
                    type='email'
                    autoComplete='username'
                    required={true}
                    sx={{ mb: 3 }}
                />

                <CustomButton
                    title='Generate OTP'
                    endIcon={<Lock />}
                    type='submit'
                    mb={2}
                />
            </form>

            <CustomButton
                title='Verify OTP'
                endIcon={<Key />}
                type='button'
                bgColor={Colors.success}
                mb={2}
                onPress={ () => Navigate.to({ path: AppRoutes.changePassword }) }
            />

            <Backdrop open={isLoading} >
                <CircularProgress />
            </Backdrop>
        </Container>
    );
}

export default ForgotPassword;