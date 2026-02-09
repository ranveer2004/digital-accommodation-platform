// import React, { useEffect, useState } from "react";
// import { useParams, useNavigate } from "react-router-dom";
// import { getPropertyDetailsById } from "../services/propertyService"; 
// import Container from "../components/Container";
// import Column from "../components/Column";
// import Row from "../components/Row";
// import CustomButton from "../components/CustomButton";
// import AppRoutes from "../utils/AppRoutes";
// import PropertyImages from "../components/PropertyImages";
// import Colors from "../utils/Colors";
// import UserContext from "../utils/UserContext";
// import RedirectionHelper from "../services/RedirectionHelper";
// import LocalStorageHelper from "../utils/LocalStorageHelper";
// import ImageCarousel from "../components/ImageCarousel";

// import {
//   Box,
//   Tabs,
//   Tab,
//   Typography,
//   Grid,
//   Paper,
//   Stack,
//   SvgIcon,
//   Card,
//   CardContent,
//   Divider,
//   TextField,
// } from "@mui/material";

// import {
//   Bed as BedIcon,
//   Bathtub as BathIcon,
//   SquareFoot as AreaIcon,
//   Home as HomeIcon,
//   Wifi as WifiIcon,
//   LocalParking as ParkingIcon,
//   Elevator as ElevatorIcon,
//   Yard as GardenIcon,
//   FitnessCenter as GymIcon,
//   Spa as SpaIcon,
//   Pool as PoolIcon,
//   OutdoorGrill as BBQIcon,
//   SportsBasketball as PlaygroundIcon,
//   LocalBar as BarIcon,
//   Weekend as LoungeIcon,
// } from "@mui/icons-material";

// // Icon mapper for known amenities and fields
// const iconMap = {
//   Bedrooms: <BedIcon />,
//   Bathrooms: <BathIcon />,
//   Area: <AreaIcon />,
//   Category: <HomeIcon />,
//   "Free Wifi": <WifiIcon />,
//   Parking: <ParkingIcon />,
//   Lift: <ElevatorIcon />,
//   Elevator: <ElevatorIcon />,
//   Garden: <GardenIcon />,
//   Gym: <GymIcon />,
//   Spa: <SpaIcon />,
//   Pool: <PoolIcon />,
//   "BBQ Area": <BBQIcon />,
//   Playground: <PlaygroundIcon />,
//   Bar: <BarIcon />,
//   Lounge: <LoungeIcon />,
// };


// const PropertyDetailsPage = () => {
//   const { propertyId } = useParams(); // propertyId from route

//   RedirectionHelper.fromSpecificPropertyPage(propertyId);

//   /** @type { [ Property, Function ] } */
//   const [ property, setProperty ] = useState(null);
//   const [ error, setError ] = useState(null);
//   const navigate = useNavigate();

//   useEffect(() => {
//     const fetchProperty = async () => {
//       try {
//         let propertyById = LocalStorageHelper.getPropertyById(propertyId);

//         if(!propertyById) {
//           propertyById = await getPropertyDetailsById(propertyId);
//         }

//         setProperty(propertyById);
//       } 
//       catch (err) {
//         setError(err.message || "Failed to load property details.");
//       }
//     };

//     fetchProperty();
//   }, [propertyId]);

//   const handleBooking = () => {
//     const user = UserContext.getLoggedInUser();
//     if (user && user.uid) {
//       // Redirect to BookingPage with propertyId
//       // navigate(AppRoutes.bookingPage, { state: { propertyId } });
//       navigate(`/booking-page/${property.propertyId}`);
//     } else {
//       // Redirect to LoginPage
//       navigate(AppRoutes.login, { state: { from: AppRoutes.showPropertyById_param.replace(":propertyId", propertyId) } });
//     }
//   };

//   if (error) {
//     return (
//       <Container>
//         <Column align="center">
//           <h2 style={{ color: "red" }}>{error}</h2>
//         </Column>
//       </Container>
//     );
//   }

//   if (!property) {
//     return (
//       <Container>
//         <Column align="center">
//         <h2 style={{color: Colors.textOrange }}>Loading property details...</h2>
//         </Column>
//       </Container>
//     );
//   }

//   const legendStyle = {
//     fontSize: "16px",
//     fontWeight: "600",
//     color: "#f9f9f9ff",
//     padding: "0 12px",
//     backgroundColor: "rgb(226,117,45)",
//     borderRadius: "6px",
//   };

//   const fieldsetStyle = {
//     border: "1px solid rgb(226,117,45)",
//     borderRadius: "8px",
//     padding: "20px",
//     color: "white",
//     textAlign: "left",
//     marginBottom: "24px",
//   }

// //   console.log("Property Details: ", property);
//   /** @type {import("@mui/material").SxProps<Theme>} */
//   const typoStyle = {
//     width: "180px",
//     textAlign: "start",
//     fontSize: "18px",
//   }

//   return (
//     <>
//         <ImageCarousel images={ property.images } />

//         <Container>
//           <Row width="100%" justifyContent="space-evenly" >
            
//             <fieldset style={ fieldsetStyle }>
//               <legend style={ legendStyle }>Details</legend>
//                   <p><b>Description: </b> {property.propertyDescription}</p>
//                   <p><b>Type: </b> {property.listingType
//                   ? property.listingType.charAt(0).toUpperCase() + property.listingType.slice(1).toLowerCase() : "N/A"}
//                   </p>
//                   <p><b>Category: </b>
//                   {property.propertyCategory 
//                   ? property.propertyCategory.charAt(0).toUpperCase() + property.propertyCategory.slice(1).toLowerCase() : "N/A"}
//                   </p>
//                   <p><b>Price: </b> ₹{property.price.toLocaleString()}</p>
//                   <p><b>Area: </b> {property.area} {property.unit}</p>
//                   <p><b>Bedrooms: </b> {property.bedrooms}</p>
//                   <p><b>Bathrooms: </b> {property.bathrooms}</p>
//                   <p><b>Furnishing: </b> {property.furnishing}</p>
//               </fieldset>

//               <fieldset style={ fieldsetStyle }>
//                 <legend style={ legendStyle }>Location</legend>
//                 <p><b>Address: </b>{property.location?.address}, {property.location?.locality}</p>
//                 <p>{property.location?.city}, {property.location?.state}, {property.location?.country}</p>
//                 <p><b>Pincode:</b> {property.location?.pincode}</p>
//                 </fieldset>

//           </Row>
//         </Container>
//   </>
//   );
// };



// export default PropertyDetailsPage;

import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getPropertyDetailsById } from "../services/propertyService"; 
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton"; // ✅ Importing Button
import AppRoutes from "../utils/AppRoutes";
import Colors from "../utils/Colors";
import UserContext from "../utils/UserContext";
import RedirectionHelper from "../services/RedirectionHelper";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import ImageCarousel from "../components/ImageCarousel";

// (Keep your Icon imports if you want to use them later, though they aren't used in the return below)
import {
  Bed as BedIcon,
  Bathtub as BathIcon,
  SquareFoot as AreaIcon,
  Home as HomeIcon,
} from "@mui/icons-material";

const PropertyDetailsPage = () => {
  const { propertyId } = useParams(); 
  const navigate = useNavigate();

  // Helper to save current location if user needs to login
  RedirectionHelper.fromSpecificPropertyPage(propertyId);

  const [property, setProperty] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProperty = async () => {
      try {
        // Try local storage first to save API calls
        let propertyById = LocalStorageHelper.getPropertyById(propertyId);

        if(!propertyById) {
          propertyById = await getPropertyDetailsById(propertyId);
        }

        setProperty(propertyById);
      } 
      catch (err) {
        setError(err.message || "Failed to load property details.");
      }
    };

    fetchProperty();
  }, [propertyId]);

  // ✅ 1. The Logic to Navigate to Booking
  const handleBooking = () => {
    const user = UserContext.getLoggedInUser();
    
    if (user && user.uid) {
      // User is logged in -> Go to Booking Page
      navigate(`/booking-page/${property.propertyId}`);
    } else {
      // User not logged in -> Go to Login
      alert("Please login to book a property.");
      navigate(AppRoutes.login, { 
        state: { from: `/booking-page/${property.propertyId}` } 
      });
    }
  };

  if (error) {
    return (
      <Container>
        <Column align="center">
          <h2 style={{ color: "red" }}>{error}</h2>
        </Column>
      </Container>
    );
  }

  if (!property) {
    return (
      <Container>
        <Column align="center">
          <h2 style={{color: Colors.textOrange }}>Loading property details...</h2>
        </Column>
      </Container>
    );
  }

  // Styles
  const legendStyle = {
    fontSize: "16px",
    fontWeight: "600",
    color: "#f9f9f9ff",
    padding: "0 12px",
    backgroundColor: "rgb(226,117,45)",
    borderRadius: "6px",
  };

  const fieldsetStyle = {
    border: "1px solid rgb(226,117,45)",
    borderRadius: "8px",
    padding: "20px",
    color: "white",
    textAlign: "left",
    marginBottom: "24px",
    flex: 1,       // Allow flex growing
    margin: "10px" // Spacing between boxes
  };

  return (
    <>
        {/* Top Image Carousel */}
        <ImageCarousel images={ property.images } />

        <Container>
          <h1 style={{ color: Colors.textOrange, textAlign: "center", marginBottom: "20px" }}>
            {property.propertyName}
          </h1>

          <Row width="100%" justifyContent="space-evenly" style={{flexWrap: "wrap"}}>
            
            {/* --- LEFT COLUMN: DETAILS --- */}
            <fieldset style={ fieldsetStyle }>
              <legend style={ legendStyle }>Details</legend>
                  <p><b>Description: </b> {property.propertyDescription}</p>
                  <p><b>Type: </b> {property.listingType}</p>
                  <p><b>Category: </b> {property.propertyCategory}</p>
                  <p style={{fontSize: "1.2rem", color: Colors.textOrange}}>
                    <b>Price: </b> ₹{property.price?.toLocaleString()}
                  </p>
                  <p><b>Area: </b> {property.area} {property.unit}</p>
                  <p><b>Bedrooms: </b> {property.bedrooms}</p>
                  <p><b>Bathrooms: </b> {property.bathrooms}</p>
                  <p><b>Furnishing: </b> {property.furnishing}</p>

                  {/* ✅ 2. THE BOOK NOW BUTTON */}
                  <div style={{ marginTop: "20px" }}>
                    <CustomButton 
                      title="Book Now" 
                      onPress={handleBooking}
                      style={{ width: "100%" }}
                    />
                  </div>
            </fieldset>

            {/* --- RIGHT COLUMN: LOCATION --- */}
            <fieldset style={ fieldsetStyle }>
                <legend style={ legendStyle }>Location</legend>
                <p><b>Address: </b>{property.location?.address}, {property.location?.locality}</p>
                <p>{property.location?.city}, {property.location?.state}, {property.location?.country}</p>
                <p><b>Pincode:</b> {property.location?.pincode}</p>
            </fieldset>

          </Row>
        </Container>
  </>
  );
};

export default PropertyDetailsPage;