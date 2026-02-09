import React from "react";

const GlobalContext = React.createContext();

function GlobalProvider({ children }) {
    const [ errorDialogProp, setErrorDialogProp ] = React.useState({ showDialog: false });

    return (
        <GlobalContext.Provider value={{ errorDialogProp, setErrorDialogProp }}>
            { children }
        </GlobalContext.Provider>
    );
}

function useGlobalState() {
    return React.useContext(GlobalContext);
}

const GlobalVariable = {
    errorDialogProp: "errorDialogProp",
    setErrorDialogProp: "setErrorDialogProp",
}

export { GlobalProvider, useGlobalState, GlobalVariable };