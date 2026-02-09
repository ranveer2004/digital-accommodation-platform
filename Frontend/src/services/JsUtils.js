/**
 * 
 * @param {Object} obj 
 * @returns {boolean} true is object is empty, else false
 */
const isObjEmpty = function (obj) {
    if(obj instanceof File) {
        return !(obj.name && obj.size > 0);
    }
    return (Object.keys(obj).length === 0);
}


export { isObjEmpty };