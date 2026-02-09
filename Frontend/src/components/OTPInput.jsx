import React from 'react';
import { Box, TextField } from '@mui/material';
import Colors from '../utils/Colors';

const OTPInput = ({ otp, setOtp }) => {
  const inputsRef = React.useRef([]);

  const handleChange = (index, value) => {
    if (!/^[0-9]?$/.test(value)) return;

    const otpArr = otp.split('');
    otpArr[index] = value;
    setOtp(otpArr.join(''));

    // Move focus
    if (value && index < 5) {
      inputsRef.current[index + 1]?.focus();
    }
  };

  const handleKeyDown = (e, index) => {
    if (e.key === 'Backspace') {
      if (!otp[index] && index > 0) {
        inputsRef.current[index - 1]?.focus();
      }
    }
  };

  const handlePaste = (event) => {
    event.preventDefault();
    const paste = event.clipboardData.getData('text').slice(0, 6);

    if (!/^\d{6}$/.test(paste)) { 
      return;
    }
    
    setOtp(paste);

    paste.split('').forEach((char, i) => {
      inputsRef.current[i].value = char;
    });

    inputsRef.current[5]?.focus();
  };

  return (
    <Box
      onPaste={ handlePaste }
      sx={{
        display: 'flex',
        justifyContent: 'center',
        gap: 1,
        my: 2,
      }}
    >
      {[...Array(6)].map((_, index) => (
        <TextField
            key={index}
            inputRef={(ref) => (inputsRef.current[index] = ref)}
            value={otp.charAt(index) || ''}
            required
            placeholder={"*"}
            onChange={(e) => handleChange(index, e.target.value)}
            onKeyDown={(e) => handleKeyDown(e, index)}
            slotProps={{
                input: {
                  maxLength: 1,
                },
            }}
            sx={{
                input: {
                  width: 40,
                  height: 40,
                  color: Colors.primary,
                  textAlign: 'center',
                  fontSize: 18,
                  p: 1,
                },
            }}
        />
      ))}
    </Box>
  );
};

export default OTPInput;