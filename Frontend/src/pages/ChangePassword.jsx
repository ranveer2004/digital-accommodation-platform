import React from 'react';
import { Box, TextField, Button, Typography, Paper, Backdrop, CircularProgress } from '@mui/material';
import OTPInput from '../components/OTPInput';
import AssetHelper from '../utils/AssetHelper';
import Container from '../components/Container';
import IdHelper from '../utils/IdHelper';
import Colors from '../utils/Colors';
import CustomButton from '../components/CustomButton';
import handleChangePasswordFormSubmit from '../services/ChangePasswordService';
import { Key, Lock } from '@mui/icons-material';
import AppRoutes from '../utils/AppRoutes';
import Navigate from '../services/NavigationService';

const textFieldStyle = {
  mb: 2,
  input: { color: 'white' },
  label: { color: '#bbb' },
  '& .MuiOutlinedInput-root': {
    '& fieldset': { borderColor: '#555' },
    '&:hover fieldset': { borderColor: '#777' },
    '&.Mui-focused fieldset': { borderColor: '#ff7a00' },
  },
};

function ChangePassword () {
    const [ isLoading, setIsLoading ] = React.useState(false);
    // const [email, setEmail] = React.useState('');
    const [otp, setOtp] = React.useState('');
    // const [newPassword, setNewPassword] = React.useState('');
    // const [confirmPassword, setConfirmPassword] = React.useState('');
    const [ errorMsg, setErrorMsg ] = React.useState("");
    const [ showError, setShowError ] = React.useState(false);

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
        const password = document.getElementById(IdHelper.otpChangePasswordNew).value;
        
        if(password === event.target.value) {
            setPasswordMatched(true);
        } else {
            setPasswordMatched(false);
        }
    }

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

            <Typography sx={{ mt: -2, mb: 3, fontSize: "1.3em" }}>
                Reset your password
            </Typography>

            <Typography 
                style={{marginBottom: "25px", fontSize: '0.8em', color: Colors.error}}
                hidden={showError === false}
            >
                {errorMsg}
            </Typography>

            <form 
              id={IdHelper.otpChangePasswordForm}
              onSubmit={ handleChangePasswordFormSubmit({ setIsLoading, setErrorMsg, setShowError }) }
            >
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
                  // onChange={(e) => setEmail(e.target.value)}
              />
              
              <Typography sx={{ textAlign: 'left', mb: -1, width: '100%', fontSize: "0.8em", color: Colors.primary }} >
                  Enter the OTP received on registered Email ID
              </Typography>
              <OTPInput 
                  otp={otp} 
                  setOtp={setOtp} 
              />
              <input
                name='otp'
                value={otp}
                type='text' 
                readOnly
                hidden
              />

              <TextField
                  id={IdHelper.otpChangePasswordNew}
                  variant='outlined'
                  label='New Password'
                  name='newPassword'
                  placeholder='********'
                  type='password'
                  autoComplete='new-password'
                  required={true}
                  sx={{ my: 3 }}
                  // value={newPassword}
                  // onChange={(e) => setNewPassword(e.target.value)}
              />

              <TextField
                  // id={IdHelper.signupConfirmPassword}
                  variant='outlined'
                  label='Confirm Password'
                  placeholder='********'
                  type='password'
                  // name='confirmPassword'
                  autoComplete='new-password'
                  required={true}
                  sx={{ mb: 1, color: Colors.error }}
                  onChange={ handleConfirmPassword }
                  // value={confirmPassword}
                  // onChange={(e) => setConfirmPassword(e.target.value)}
              />

              <Typography
                  style={{ 
                      fontSize: "0.8em",
                      marginBottom: "1.4em", 
                      textAlign: "start",
                      width: "100%",
                      color: `${isPasswordMatched ? Colors.success : Colors.error}`,
                  }}
                  visibility={ showPasswordComparison ? 'visible' : 'hidden'}
              >
                  {isPasswordMatched ? passwordMsg.matched : passwordMsg.unMatched}
              </Typography>

              <CustomButton 
                title='Reset Password' 
                type='submit'
                mb={2}
                disabled={showPasswordComparison && !isPasswordMatched} 
              />
            </form>

            <CustomButton
                title='Generate OTP'
                endIcon={<Lock />}
                type='button'
                bgColor={Colors.success}
                mb={2}
                onPress={ () => Navigate.to({ path: AppRoutes.forgotPassword }) }
            />

            <Backdrop open={isLoading} >
                <CircularProgress />
            </Backdrop>
        </Container>
    );
}

export default ChangePassword;