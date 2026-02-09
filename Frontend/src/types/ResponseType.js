/**
 * @typedef {Object} ResponseError
 * @property {number} errorCode
 * @property {string} errorMessage
 * @property {Date} timestamp
 */

/**
 * 
 * @param {import('axios').AxiosError} error  
 * @returns {ResponseError}
 */
function getResponseError(error) {
    return error.response.data;
};

/**
 * 
 * @param {number} errorCode 
 * @param {string} errorMessage 
 * @returns {ResponseError}
 */
function sendResponseError(errorCode, errorMessage) {
    return {
        errorCode,
        errorMessage,
        // timestamp: new Date.now(),
    };
}


/**
 * @typedef {Object} LocationCoords
 * @property {number} latitude
 * @property {number} longitude
 */

/**
 * 
 * @param {number} latitude 
 * @param {number} longitude
 * @returns {LocationCoords}
 */
function createLocationCoords(latitude, longitude) {
    return {
        latitude,
        longitude
    };
}


export { getResponseError, sendResponseError, createLocationCoords };