import Container from '../components/Container.jsx';
import CustomButton from '../components/CustomButton.jsx';
import { Box, TextField, Typography, Link, Divider, Backdrop, CircularProgress, Collapse } from '@mui/material';
import DividerWithText from '../components/DividerWithText.jsx';
import AppConstant from '../utils/AppConstant.js';
import AppRoutes from '../utils/AppRoutes.js';
import IdHelper from '../utils/IdHelper.js';
import Colors from '../utils/Colors.js';
import { useNavigate } from 'react-router-dom';
import { handleGenerateSignupOtp, handleSignupFormSubmit } from '../services/SignupService.js';
import React from 'react';
import AssetHelper from '../utils/AssetHelper.js';
import { HorizontalRule, Key, Lock } from '@mui/icons-material';
import OTPInput from '../components/OTPInput.jsx';
import SizedBox from '../components/SizedBox.jsx';

function Signup() {
    const [ isLoading, setIsLoading ] = React.useState(false);
    const [ email, setEmail ] = React.useState("");
    const [ showSignup, setShowSignup ] = React.useState(false);
    const [ otp, setOtp ] = React.useState("");
    
    const [ showError, setShowError ] = React.useState(true);
    const [ errorMsg, setErrorMsg ] = React.useState("We will send an OTP to your email to verify your identity");

    const [ showPasswordComparison, setShowPasswordComparison ] = React.useState(false);
    const [ isPasswordMatched, setPasswordMatched ] = React.useState(false);


    const passwordMsg = {
        matched: "Yup! Password matched.",
        unMatched: "Oops! Password do not match.",
    }

    /**
     * @param {event} event 
     */
    const handleConfirmPassword = (event) => {
        if(!showPasswordComparison) {
            setShowPasswordComparison(true);
        }
        const password = document.getElementById(IdHelper.signupPassword).value;
        
        if(password === event.target.value) {
            setPasswordMatched(true);
        } else {
            setPasswordMatched(false);
        }
    }

    return (
        <>
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
                
                <Typography sx={{mb: 5, fontSize: '1.35em'}}>
                    {AppConstant.signupTagline}
                </Typography>

                {/* <Typography sx={{mb: 1, color: Colors.error, fontSize: '0.75em'}}>
                    We will send an OTP to your email to verify you identity
                </Typography> */}

                <Typography 
                    id={IdHelper.signupErrorDiv}
                    style={{marginBottom: "15px", fontSize: '0.8em', color: Colors.error}}
                    hidden={showError === false}
                >
                    {errorMsg}
                </Typography>
                
                <form onSubmit={ handleGenerateSignupOtp(setIsLoading, setShowSignup, setErrorMsg) } style={{ width: "100%" }} >
                    <TextField
                        id={IdHelper.signupEmail}
                        variant='outlined'
                        label='Email'
                        name='email'
                        placeholder='your@gmail.com'
                        type='email'
                        sx={{ mb: 2 }}
                        onChange={(e) => { setEmail(e.target.value); }}
                        autoComplete='username'
                        required={true}
                    />

                    <Collapse in={!showSignup} >
                        <CustomButton
                            title='Generate OTP'
                            disabled={showSignup}
                            endIcon={<Lock />}
                            type='submit'
                            mb={2}
                        />
                    </Collapse>

                    <CustomButton
                        title={ showSignup ? "Generate OTP" : "Verify OTP" }
                        endIcon={<Key />}
                        type='button'
                        bgColor={Colors.success}
                        mb={2}
                        onPress={ () => setShowSignup(!showSignup) }
                    />
                </form>

                <Collapse in={showSignup} >
                    <form 
                        id={IdHelper.signupForm}
                        onSubmit={ handleSignupFormSubmit(setIsLoading, setErrorMsg, setShowError) }
                    >
                        <input 
                            name='email'
                            value={ email }
                            required
                            readOnly
                            hidden
                        />

                        <OTPInput otp={otp} setOtp={setOtp} />
                        <SizedBox height={10} />
                        <input 
                            name='otp'
                            value={otp}
                            hidden
                            readOnly
                        />

                        <TextField
                            id={IdHelper.signupPassword}
                            variant='outlined'
                            label='Password'
                            name='password'
                            placeholder='********'
                            type='password'
                            autoComplete='new-password'
                            required={true}
                            sx={{ mb: 2 }}
                        />
                        
                        <TextField
                            // id={IdHelper.signupConfirmPassword}
                            variant='outlined'
                            label='Confirm Password'
                            placeholder='********'
                            type='password'
                            name='confirmPassword'
                            autoComplete='new-password'
                            required={true}
                            onChange={handleConfirmPassword}
                            sx={{ mb: 1 }}
                        />

                        <Typography
                            style={{ 
                                fontSize: "0.8em",
                                marginBottom: "1.4em", 
                                textAlign: "start",
                                color: `${isPasswordMatched ? Colors.success : Colors.error}`,
                            }}
                            visibility={ showPasswordComparison ? 'visible' : 'hidden'}
                        >
                            {isPasswordMatched ? passwordMsg.matched : passwordMsg.unMatched}
                        </Typography>
                        
                        <CustomButton 
                            title="Signup"
                            disabled={!isPasswordMatched} 
                            type='submit' 
                            mb={2} 
                        />
                    </form>
                </Collapse>

                <DividerWithText text={'or'} />

                <Typography sx={{ color: Colors.white }}>
                    Already have an account ? <Link href={AppRoutes.login} style={{ fontSize: "1em" }}> Login</Link>
                </Typography>

                <Backdrop open={isLoading} >
                    <CircularProgress />
                </Backdrop>
            </Container>
        </>
    );
}

export default Signup;