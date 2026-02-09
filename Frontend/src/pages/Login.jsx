import Container from '../components/Container.jsx';
import CustomButton from '../components/CustomButton.jsx';
import SizedBox from '../components/SizedBox.jsx';
import { Box, TextField, Typography, Link, Divider, Backdrop, CircularProgress } from '@mui/material';
import { LoginOutlined } from '@mui/icons-material';
import DividerWithText from '../components/DividerWithText.jsx';
import AppConstant from '../utils/AppConstant.js';
import AppRoutes from '../utils/AppRoutes.js';
import IdHelper from '../utils/IdHelper.js';
import { handleLoginFormSubmit } from '../services/LoginService.js';
import { useNavigate } from 'react-router-dom';
import React from 'react';
import Colors from '../utils/Colors.js';
import AssetHelper from '../utils/AssetHelper.js';
import UserContext from '../utils/UserContext.js';
import LocalStorageHelper from '../utils/LocalStorageHelper.js';
import Navigate from '../services/NavigationService.js';
import RedirectionHelper from '../services/RedirectionHelper.js';

function Login() {
    RedirectionHelper.fromLogin();

    const [ isLoading, setIsLoading ] = React.useState(false);
    const [ errorMsg, setErrorMsg ] = React.useState("");
    const [ showError, setShowError ] = React.useState(false);

    return (
        <div style={{
            height: '100vh',
            width: '100vw',
            maxWidth: 400,
        }}>
            <Container style={{width: '100%'}}>
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
                
                <Typography sx={{mb: 4, fontSize: '1.35em'}}>
                    {AppConstant.loginTagline}
                </Typography>

                <Typography 
                    id={IdHelper.loginErrorDiv}
                    style={{marginBottom: "15px", fontSize: '0.8em', color: Colors.error}}
                    hidden={showError === false}
                >
                    {errorMsg}
                </Typography>
                
                <form 
                    id={IdHelper.loginForm}
                    onSubmit={ handleLoginFormSubmit(setIsLoading, setErrorMsg, setShowError) }
                >
                    <TextField
                        id={IdHelper.loginEmail}
                        name='email'
                        variant='outlined'
                        label='Email'
                        placeholder='your@gmail.com'
                        type='email'
                        autoComplete='username'
                        required={true}
                        sx={{ mb: 2 }}
                    />

                    <TextField
                        id={IdHelper.loginPassword}
                        name='password'
                        variant='outlined'
                        label='Password'
                        placeholder='********'
                        type='password'
                        autoComplete='current-password'
                        sx={{ mb: 3 }}
                        required={true}
                    />

                    <CustomButton title="Login" type='submit' mb={2} />
                </form>

                <Link href={AppRoutes.forgotPassword}>Forgot your password ?</Link>

                <DividerWithText text={'or'} />
                
                <Typography sx={{ color: Colors.white }}>
                    Don't have an account ? <Link href={AppRoutes.signup} style={{ fontSize: "1em" }}> Sign up</Link>
                </Typography>
            </Container>

            <Backdrop open={isLoading} >
                <CircularProgress />
            </Backdrop>
        </div>
    );
}

export default Login;