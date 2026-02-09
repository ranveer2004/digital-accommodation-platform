import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import '../index.css';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import './App.css';
import App from './App.jsx';
import Colors from './utils/Colors.js';
import { colors } from '@mui/material';
import { BrowserRouter } from 'react-router-dom';
import { GlobalProvider } from './pages/GlobalProvider.jsx';

const rootElement = document.getElementById('root');

const root = createRoot(rootElement);

const theme = createTheme({
  palette: {
    background: {
      default: Colors.background,
    },
  },
  typography: {
    fontFamily: 'Nunito, sans-serif',
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          backgroundColor: Colors.background,
        },
      }
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundColor: Colors.background,
        }
      }
    },
    MuiTypography: {
      styleOverrides: {
        root: {
          fontSize: "0.9em",
          color: Colors.primary,
          fontFamily: 'Nunito, sans-serif',
        },
      },
    },
    MuiCircularProgress: {
      styleOverrides: {
        root: {
          color: Colors.primary,
        }
      }
    },
    MuiLink: {
      styleOverrides: {
        root: {
          color: Colors.white,
          fontSize: "0.9em",
          textDecoration: 'none',
          ':hover': {
            color: Colors.primary
          }
        }
      }
    },
    MuiButton: {
      styleOverrides: {
        root: {
          color: Colors.background,
          backgroundColor: Colors.white,
          ':hover': {
              backgroundColor: Colors.primary,
              color: Colors.white,
          }
        }
      }
    },
    MuiIconButton: {
      styleOverrides: {
        root: {
          color: Colors.white,
          backgroundColor: Colors.background,
          "&:hover": {
            color: Colors.primary,
            // backgroundColor: Colors.white,
          },
        },
      }
    },
    MuiMenuItem: {
      styleOverrides: {
        root: {
          color: Colors.background,
          ":hover": {
            backgroundColor: Colors.primary,
            color: Colors.white,
          }
        }
      }
    },
    MuiDialog: {
      styleOverrides: {
        paper: {
          border: '1px solid #fff', // Change as needed
          borderRadius: '30px',     // Optional
          boxShadow: '0px 4px 20px rgba(0,0,0,0.2)' // Optional
        }
      }
    },
    MuiTextField: {
      defaultProps: {
        fullWidth: true,
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        root: {
          fontSize: '0.9em',
          color: '#ccc', // default label color
          '&.Mui-focused': {
            color: Colors.primary, // label color when focused
          },
        },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          backgroundColor: 'transparent',
          // borderRadius: '8px',
          '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
            borderColor: Colors.primary, // outline border color when focused
          },
        },
        notchedOutline: {
          borderColor: '#ccc', // default border color
          
        },
        input: {
          padding: '12px',
          fontSize: '0.9em',
          color: Colors.primary,
        },
      },
    },
  }
});

root.render(
  <StrictMode>
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <GlobalProvider>
          <App /> 
        </GlobalProvider>
      </BrowserRouter>
    </ThemeProvider>
  </StrictMode>,
);
