import { getLocationData } from "../types/LocationType";
import { createLocationCoords, sendResponseError } from "../types/ResponseType";
import AxiosHelper from "./AxiosHelper";

/**
 * 
 * @param {number} latitude 
 * @param {number} longitude 
*/
// * @returns {import("../types/LocationType").LocationData}
async function getLocationDetail(latitude, longitude) {
    const url = `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`;

    try {
        const response = await AxiosHelper.GET({ url, isAuthHeader: false });
        const location = response.data;
        
        console.log("from location service 1 : ", location.address);
        return getLocationData(location.address);
    } catch(error) {
        console.log(error);
    }
}

/**
 * 
 * @param {function} setIsLoading 
 * @param {function} setForm 
 * @param {function} setError 
 */
function getLocation(setIsLoading, setForm, setError) {
    if (!navigator.geolocation) {
        return sendResponseError(404, "Location service is not supported by your browser.");
    }

    setIsLoading(true);

    navigator
        .geolocation
        .getCurrentPosition(
            async (geoPosition) => {
                /**
                 * @type {import("../types/LocationType").LocationData}
                 */
                const locationData = await getLocationDetail(geoPosition.coords.latitude, geoPosition.coords.longitude);

                setForm((prev) => {
                    return {
                        ...prev,
                        latitude: geoPosition.coords.latitude,
                        longitude: geoPosition.coords.longitude,
                        ...locationData,
                    };
                });

                setIsLoading(false);
            }, 
            (geoError) => {
                setError(geoError.message);

                setIsLoading(false);
            },
            { 
                enableHighAccuracy: true, 
                timeout: 10000, 
                maximumAge: 0 
            }
        );
}

export default getLocation;