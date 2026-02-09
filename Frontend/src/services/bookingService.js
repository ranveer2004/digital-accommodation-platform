// // Booking Service
// //Service for handling booking-related API calls

// import AxiosHelper from "./AxiosHelper";


// const BASE_URL = import.meta.env.VITE_BOOKING_API_URL;

// /** 
//  * Fetches all bookings from the server.
//  * @returns {Promise} - Promise resolving to the response of the API call.
// */
// export const getAllBookings = async () => {
//     try {
//         const response = await AxiosHelper.GET({ url: BASE_URL });
//         return response.data;
//     } catch (error) {
//         console.error("Error fetching bookings:", error);
//         throw error;
//     }
// };

// /**
//  * Function to create a new booking.
//  * @param {Object} bookingDetails - The details of the booking to be submitted.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  */
// export const createBooking = async (bookingDetails) => {
//     try {
//         const response = await AxiosHelper.POST({ url: BASE_URL, body: bookingDetails });
//         return response.data;
//     } catch (error) {
//         console.error("Error creating Booking:", error);
//         throw error;
//     }
// };

// /**
//  * Get specific Booking details by Booking ID.
//  * @param {string} bookingId - The ID of the booking to be fetched.
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  */
// export const getBookingDetailsById = async (bookingId) => {
//     try {
//         const response = await AxiosHelper.GET({ url: `${BASE_URL}/${bookingId}` });
//         return response.data;
//     } catch (error) {
//         console.error("Error fetching Booking details by ID: ", error);
//         throw error;
//     }
// };

// /**
//  * Get all the bookings made by a specific buyer.
//  * @param {string} buyerId - The ID of the buyer whose bookings are to be fetched.
//  * @returns {Promise} - Promise resolving to the response of the API call. 
//  */
// export const getBookingDetailsByBuyerId = async (buyerId) => {
//     try {
//         const response = await AxiosHelper.GET({ url: `${BASE_URL}/buyer/${buyerId}` });
//         return response.data;
//     } catch (error) {
//         console.error("Error fetching all the Bookings made by Buyer: ", error);
//         throw error;
//     }
// };

// /** 
//  * Get all bookings for a specific property.
//  * @param {string} propertyId - The ID of the property whose bookings are to be fetched.
//  * @returns {Promise} - Promise resolving to the response of the API call.
// */
// export const getBookingDetailsByPropertyId = async (propertyId) => {
//     try {
//         const response = await AxiosHelper.GET({ url: `${BASE_URL}/property/${propertyId}` });
//         return response.data;
//     } catch (error) {
//         console.error("Error fetching Bookings made for a Property: ", error);
//         throw error;
//     }
// };    

// /**
//  * Update the status of a booking by its ID.
//  * @param {string} bookingId - The ID of the booking to be updated.
//  * @param {string} status - The new status to be set for the booking (PENDING, APPROVED, CANCELLED).
//  * @returns {Promise} - Promise resolving to the response of the API call.
//  */
// export const updateBookingStatusByBookingId = async (bookingId, status) => {
//     try {
//         const response = await AxiosHelper.PATCH({
//             url: `${BASE_URL}/${bookingId}/status`,
//             body: { status }
//         });
//         return response.data;
//     } catch (error) {
//         console.error("Error updating Booking Status of a Property: ", error);
//         throw error;
//     }
// };
import AxiosHelper from "./AxiosHelper";
import ApiCaller from "./ApiCaller";

const BASE_URL = import.meta.env.VITE_BOOKING_API_URL;

/**
 * Fetches all bookings (Admin use)
 */
export const getAllBookings = async (setError = () => {}) => {
    try {
        const response = await AxiosHelper.GET({ url: BASE_URL, isAuthHeader: true });
        return response.data;
    } catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.GET({ url: BASE_URL, isAuthHeader: true });
            return retryRes.data;
        });
    }
};

/**
 * Creates a new booking with payment details
 */
export const createBooking = async (bookingDetails, setError = () => {}) => {
    try {
        const response = await AxiosHelper.POST({ url: BASE_URL, body: bookingDetails, isAuthHeader: true });
        return response.data;
    } catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.POST({ url: BASE_URL, body: bookingDetails, isAuthHeader: true });
            return retryRes.data;
        });
    }
};

/**
 * Fetches specific booking by ID (Required by BookingDetailsPage)
 */
export const getBookingDetailsById = async (bookingId, setError = () => {}) => {
    const url = `${BASE_URL}/${bookingId}`;
    try {
        const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });
        return response.data;
    } catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.GET({ url: url, isAuthHeader: true });
            return retryRes.data;
        });
    }
};

/**
 * Fetches all bookings for a specific buyer (Required by MyBookingsPage)
 */
export const getBookingDetailsByBuyerId = async (buyerId, setError = () => {}) => {
    const url = `${BASE_URL}/buyer/${buyerId}`;
    try {
        const response = await AxiosHelper.GET({ url: url, isAuthHeader: true });
        return response.data;
    } catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.GET({ url: url, isAuthHeader: true });
            return retryRes.data;
        });
    }
};

/**
 * Updates booking status (Required by UpdateBookingStatus)
 */
export const updateBookingStatusByBookingId = async (bookingId, status, setError = () => {}) => {
    const url = `${BASE_URL}/${bookingId}/status`;
    try {
        const response = await AxiosHelper.PATCH({
            url: url,
            body: { status },
            isAuthHeader: true
        });
        return response.data;
    } catch (error) {
        return await ApiCaller.onErrorCallRefreshAndRepeatProcess(error, setError, async () => {
            const retryRes = await AxiosHelper.PATCH({
                url: url,
                body: { status },
                isAuthHeader: true
            });
            return retryRes.data;
        });
    }
};

/**
 * Alias for canceling a booking (Uses the update status logic)
 */
export const cancelBooking = async (bookingId, setError = () => {}) => {
    return await updateBookingStatusByBookingId(bookingId, "CANCELLED", setError);
};

// import axios from "axios";

// // The Gateway URL
// const BASE_URL = "http://localhost:9090";

// // Helper to get the Auth Token from Local Storage
// const getAuthHeaders = () => {
//     const token = localStorage.getItem("token");
//     return {
//         headers: {
//             "Authorization": `Bearer ${token}`,
//             "Content-Type": "application/json"
//         }
//     };
// };

// // ==========================================
// //              BOOKING API CALLS
// // ==========================================

// /**
//  * Create a New Booking
//  * Endpoint: POST /api/v1/bookings
//  */
// export const createBooking = async (bookingDetails) => {
//     try {
//         const response = await axios.post(
//             `${BASE_URL}/api/v1/bookings`, 
//             bookingDetails,
//             getAuthHeaders()
//         );
//         return response.data;
//     } catch (error) {
//         console.error("API Error in createBooking:", error);
//         throw error;
//     }
// };

// /**
//  * Get All Bookings for a Specific Buyer
//  * Renamed to 'getBookingDetailsByBuyerId' to fix the import error in MyBookingsPage.jsx
//  * Endpoint: GET /api/v1/bookings/buyer/{buyerId}
//  */
// export const getBookingDetailsByBuyerId = async (buyerId) => {
//     try {
//         const response = await axios.get(
//             `${BASE_URL}/api/v1/bookings/buyer/${buyerId}`,
//             getAuthHeaders()
//         );
//         return response.data;
//     } catch (error) {
//         console.error("API Error in getBookingDetailsByBuyerId:", error);
//         throw error;
//     }
// };

// /**
//  * Get Booking Details by ID
//  * Endpoint: GET /api/v1/bookings/{bookingId}
//  */
// export const getBookingById = async (bookingId) => {
//     try {
//         const response = await axios.get(
//             `${BASE_URL}/api/v1/bookings/${bookingId}`,
//             getAuthHeaders()
//         );
//         return response.data;
//     } catch (error) {
//         console.error("API Error in getBookingById:", error);
//         throw error;
//     }
// };