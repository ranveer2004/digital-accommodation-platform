// // Property Service
// // This service handles API calls related to properties.
// import { getResponseError } from "../types/ResponseType";
// import Endpoints from "../utils/ApiEndpoints";
// import LocalStorageHelper from "../utils/LocalStorageHelper";
// import ApiCaller from "./ApiCaller";
// import AxiosHelper from "./AxiosHelper";

// const BASE_URL = import.meta.env.VITE_PROPERTY_API_URL;

// /**
//  * Fetches all properties from the server.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * If the response is not an array, it returns an empty array.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const getAllProperties = async () => {
//     try {
//       const response = await AxiosHelper.GET({ url: Endpoints.getAllProperty, isAuthHeader: false });

//       const allProperties = Array.isArray(response.data) ? response.data : [];

//       LocalStorageHelper.setAllProperty(allProperties);

//       return allProperties;
//     } 
//     catch (error) {
//       if (error.response && error.response.data && error.response.data.message) {
//         throw new Error(error.response.data.message);
//       }
//       throw new Error(error.message || "Error fetching all properties");
//     }
// }

// /**
//  * Creates a new property.
//  * @param {Object} propertyDetails - The details of the property to be created.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const createProperty = async (propertyDetails, setError) => {
//     try {
//         const response = await AxiosHelper.POST({ url: Endpoints.postProperty, body: propertyDetails, isAuthHeader: true });
//         return response.data;
//     } 
//     catch (error) {

//       return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
//         const retryRes = await AxiosHelper.POST({ url: Endpoints.postProperty, body: propertyDetails, isAuthHeader: true });
//         return retryRes.data;
//       });

//       // const resError = getResponseError(error);
//       // try {
//       //   if(resError.errorCode && resError.errorCode === 1002) {
          
//       //     return await ApiCaller.callRefreshAndRepeatProcess(async () => {
//       //       const response = await AxiosHelper.POST({ url: Endpoints.postProperty, body: propertyDetails, isAuthHeader: true });
          
//       //       return response.data;
//       //     });
//       //   }
//       // } catch(error) {
//       //   setError(error.response.data?.message || "Oops !!! Something went wrong");
//       // }

//       // if (error.response && error.response.data && error.response.data.message) {
//       //    throw new Error(error.response.data.message);
//       // }
//       // throw new Error(error.message || "Something went wrong while creating property");
//     }
// }

// /**
//  * Update property images.
//  * @param {string} propertyId - The ID of the property to update.
//  * @param {Array} imageUrls - Array of image URLs to be updated.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const updatePropertyImages = async (propertyId, imageUrls, setError) => {
//   const url = Endpoints.updatePropertyImages(propertyId);

//   try {
//     const response = await AxiosHelper.PUT({ url: url, body: imageUrls, isAuthHeader: true });

//     return response.data; // APIResponse with message
//   } catch (error) {
    
//     return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
//       const retryRes = await AxiosHelper.PUT({ url: url, body: imageUrls, isAuthHeader: true });

//       return retryRes.data;
//     });

//     // if (error.response && error.response.data && error.response.data.message) {
//     //   throw new Error(error.response.data.message);
//     // }
//     // throw new Error(error.message || "Error updating property images");
//   }
// };

// /**
//  * Fetches property details by ID.
//  * @param {string} propertyId - The ID of the property to be fetched.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const getPropertyDetailsById = async (propertyId) => {
//     try {
//         const response = await AxiosHelper.GET({ url: Endpoints.getPropertyById(propertyId), isAuthHeader: false });
//         return response.data;
//     } 
//     catch (error) {
//         // ApiCaller.onErrorCallRefreshAndRepeatProcess(error, );



//         if (error.response && error.response.data && error.response.data.message) {
//           throw new Error(error.response.data.message);
//         }
//     throw new Error(error.message || "Error fetching property details by ID");
//     }
// }

// /**
//  * Fetches properties owned by a specific user.
//  * @param {string} ownerId - The ID of the owner whose properties are to be fetched.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const getMyProperties = async (ownerId) => {
//   // const url = `${Endpoints.getMyProperties}${Endpoints.myPropertiesEnd}/${ownerId}`;
//   const url = `${Endpoints.getMyProperties}`; 
//   try {
//     const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });

//     const myProperties = Array.isArray(response.data) ? response.data : [];

//     return myProperties;
//   } catch (error) {
//     return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, ()=>{}, async () => {
//       const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });

//       const myProperties = Array.isArray(response.data) ? response.data : [];

//       return myProperties;
//     });
//     // console.error("Error fetching user properties:", error);
//     // throw error;
//   }
// };

// /**
//  * Deletes a property by its ID.
//  * @param {string} propertyId - The ID of the property to be deleted.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const deleteProperty = async (propertyId) => {
//   try {
//     const response = await AxiosHelper.DELETE({url: Endpoints.deleteProperty(propertyId), isAuthHeader: true
//     });
//     return response.data; 
//   } catch (error) {
//     console.error("Error deleting property:", error);
//     throw error;
//   }
// };

// /**
//  * Updates a property by its ID.
//  * @param {string} propertyId - The ID of the property to be updated.
//  * @param {Object} propertyData - The data to update the property with.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const updateProperty = async (propertyId, propertyData) => {
//   try {
//     const response = await AxiosHelper.PUT({
//       url: Endpoints.updateProperty(propertyId),
//       body: propertyData,
//       isAuthHeader: true
//     });
//     return response.data;
//   } catch (error) {
//     console.error("Error updating property:", error);
//     throw error;
//   }
// };


// /** 
//  * Toggles the availability status of a property.
//  * @param {string} propertyId - The ID of the property whose availability is to be toggled.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  * @throws {Error} - Throws an error if the API call fails.
//  */
// export const toggleAvailability = async (propertyId) => {
//   try {
//     const response = await AxiosHelper.PATCH({
//       url: Endpoints.toggleAvailability(propertyId),
//       isAuthHeader: true
//     });
//     return response.data;
//   } catch (error) {
//     console.error("Error toggling availability:", error);
//     throw error;
//   }
// };


import { getResponseError } from "../types/ResponseType";
import Endpoints from "../utils/ApiEndpoints";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import ApiCaller from "./ApiCaller";
import AxiosHelper from "./AxiosHelper";

const BASE_URL = import.meta.env.VITE_PROPERTY_API_URL;

export const getAllProperties = async () => {
    try {
      const response = await AxiosHelper.GET({ url: Endpoints.getAllProperty, isAuthHeader: false });
      const allProperties = Array.isArray(response.data) ? response.data : [];
      LocalStorageHelper.setAllProperty(allProperties);
      return allProperties;
    } 
    catch (error) {
      if (error.response && error.response.data && error.response.data.message) {
        throw new Error(error.response.data.message);
      }
      throw new Error(error.message || "Error fetching all properties");
    }
}

export const createProperty = async (propertyDetails, setError) => {
    try {
        const response = await AxiosHelper.POST({ url: Endpoints.postProperty, body: propertyDetails, isAuthHeader: true });
        return response.data;
    } 
    catch (error) {
      return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
        const retryRes = await AxiosHelper.POST({ url: Endpoints.postProperty, body: propertyDetails, isAuthHeader: true });
        return retryRes.data;
      });
    }
}

export const updatePropertyImages = async (propertyId, imageUrls, setError) => {
  const url = Endpoints.updatePropertyImages(propertyId);
  try {
    const response = await AxiosHelper.PUT({ url: url, body: imageUrls, isAuthHeader: true });
    return response.data;
  } catch (error) {
    return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
      const retryRes = await AxiosHelper.PUT({ url: url, body: imageUrls, isAuthHeader: true });
      return retryRes.data;
    });
  }
};

/**
 * Fetches property details by ID.
 * Set to isAuthHeader: true so the Gateway accepts the request.
 */
export const getPropertyDetailsById = async (propertyId, setError = () => {}) => {
    const url = Endpoints.getPropertyById(propertyId);
    try {
        const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });
        return response.data;
    } 
    catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.GET({ url: url, isAuthHeader: true });
            return retryRes.data;
        });
    }
}

export const getMyProperties = async (ownerId) => {
  const url = `${Endpoints.getMyProperties}`; 
  try {
    const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });
    const myProperties = Array.isArray(response.data) ? response.data : [];
    return myProperties;
  } catch (error) {
    return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, ()=>{}, async () => {
      const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });
      const myProperties = Array.isArray(response.data) ? response.data : [];
      return myProperties;
    });
  }
};

export const deleteProperty = async (propertyId) => {
  try {
    const response = await AxiosHelper.DELETE({url: Endpoints.deleteProperty(propertyId), isAuthHeader: true
    });
    return response.data; 
  } catch (error) {
    console.error("Error deleting property:", error);
    throw error;
  }
};

export const updateProperty = async (propertyId, propertyData) => {
  try {
    const response = await AxiosHelper.PUT({
      url: Endpoints.updateProperty(propertyId),
      body: propertyData,
      isAuthHeader: true
    });
    return response.data;
  } catch (error) {
    console.error("Error updating property:", error);
    throw error;
  }
};

export const toggleAvailability = async (propertyId) => {
  try {
    const response = await AxiosHelper.PATCH({
      url: Endpoints.toggleAvailability(propertyId),
      isAuthHeader: true
    });
    return response.data;
  } catch (error) {
    console.error("Error toggling availability:", error);
    throw error;
  }
};