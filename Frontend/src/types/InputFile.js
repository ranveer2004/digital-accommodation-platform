/**
 * @typedef {File} file
 * @property {string} name
 * @property {number} size
 * @property {string} type
 */

/**
 * 
 * @param {Event} event 
 * @returns {File}
 */
function getFileObject(event) {
    return event.target.files[0];
}

export default getFileObject;