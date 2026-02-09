/**
 * @typedef {Object} LocationData
 * @property {string} locality
 * @property {string} city
 * @property {string} state
 * @property {string} country
 * @property {string} pincode
 * @property {string} address
 */

/**
 * 
 * @param {Object} locationApiResult 
 * @returns {LocationData}
 */
function getLocationData(locationApiResult) {
    return {
        locality: locationApiResult?.suburb || '',
        city: locationApiResult?.city || '',
        state: locationApiResult?.state || '',
        country: locationApiResult?.country || '',
        pincode: locationApiResult?.postcode || '',
    }
}

export { getLocationData };