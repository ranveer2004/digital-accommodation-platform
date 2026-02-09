// // //Booking Page
// // // Page to create a new Booking using form

// // import React, { useEffect, useState } from "react";
// // import { useParams, useNavigate, useLocation } from "react-router-dom";
// // import { createBooking } from "../services/bookingService";
// // import { getPropertyDetailsById } from "../services/propertyService"; 
// // import Container from "../components/Container";
// // import CustomButton from "../components/CustomButton";
// // import Column from "../components/Column";
// // import SizedBox from "../components/SizedBox";
// // import Row from "../components/Row";
// // import Colors from "../utils/Colors";
// // import UserContext from "../utils/UserContext";
// // import AppRoutes from "../utils/AppRoutes";

// // /**
// //  * BookingPage component is responsible for handling the creation of a new booking.
// //  * It accepts buyerId and propertyId (typically passed from context or props)
// //  * and sends a booking request to the backend using the createBooking API.
// //  */

// // const BookingPage = () => {
// //   const { propertyId } = useParams();
// //   const [buyerId, setBuyerId] = useState(null);
// //   const [bookingStatus, setBookingStatus] = useState(null);
// //   const [loadingStatus, setLoadingStatus] = useState(false);
// //   const [bookingId, setBookingId] = useState(null);
// //   const [property, setProperty] = useState(null); 

// //   const navigate = useNavigate();
// //   const location = useLocation();

// //   useEffect(() => {
// //     const user = UserContext.getLoggedInUser();
// //     if (user && user.uid) {
// //       setBuyerId(user.uid);
// //     } else {
// //         // If not logged in, send to login with redirect back to this booking page
// //       navigate(AppRoutes.login,{ state: { from: location.pathname } });
// //     }

// //     const fetchProperty = async () => {
// //       try {
// //         const propertyData = await getPropertyDetailsById(propertyId);
// //         setProperty(propertyData);
// //       } catch (error) {
// //         console.error("Failed to fetch property details:", error);
// //       }
// //     };
// //     fetchProperty();
// //   }, [navigate, location, propertyId]);

// //   const handleBooking = async () => {
// //     if (!buyerId || !propertyId) {
// //       setBookingStatus("Missing booking details.");
// //       return;
// //     }

// //     const bookingDetails = {
// //       buyerId,
// //       propertyId,
// //       bookingDate: new Date().toISOString().split("T")[0],
// //       status: "PENDING",
// //     };

// //     try {
// //       setLoadingStatus(true);
// //       const response = await createBooking(bookingDetails);
// //       setBookingStatus("Booking Successful!");
// //       setBookingId(response?.bookingId);
// //     } catch (error) {
// //       setBookingStatus("Booking failed. Please try again!");
// //     } finally {
// //       setLoadingStatus(false);
// //     }
// //   };

// //   return (
// //     <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
// //       <Column align="center">
// //         <h2 style={{ color: Colors.textOrange }}>Book This Property</h2>
// //         <SizedBox height={20} />

// //         {/* Show property details if available */}
// //         {property ? (
// //           <div
// //             style={{
// //               border: `2px solid `,
// //               borderRadius: "12px",
// //               padding: "16px",
// //               width: "100%",
// //               marginBottom: "20px",
// //               backgroundColor: "#fff8f0",
// //             }}
// //           >
// //             <h3 style={{ margin: "0 0 12px 0", color: Colors.textOrange }}>
// //               {property.propertyName}
// //             </h3>
// //             <p style={{ margin: "4px 0" }}>
// //               <b>Category:</b> {property.propertyCategory}
// //             </p>
// //             <p style={{ margin: "4px 0" }}>
// //               <b>Price:</b> ₹{property.price}
// //             </p>
// //             <p style={{ margin: "4px 0" }}>
// //               <b>Location:</b> {property.location?.city}, {property.location?.state}
// //             </p>
// //           </div>
// //         ) : (
// //           <p style={{color: Colors.textOrange}}>Loading property details...</p>
// //         )}

// //         <CustomButton
// //           title="Book Now"
// //           onPress={handleBooking}
// //           disabled={loadingStatus}
// //           style={{ width: "100%" }}
// //         >
// //           {loadingStatus ? "Booking in Progress..." : "Confirm Booking"}
// //         </CustomButton>
// //         <SizedBox height={16} />

// //         {bookingStatus && (
// //           <Row justify="center">
// //             <span
// //               style={{
// //                 color: bookingStatus.includes("Successful") ? "green" : "red",
// //                 fontWeight: 500,
// //               }}
// //             >
// //               {bookingStatus}
// //               {bookingId && bookingStatus.includes("Successful") && (
// //                 <span style={{ display: "block", marginTop: 8 }}>
// //                   Booking ID: <b>{bookingId}</b>
// //                 </span>
// //               )}
// //             </span>
// //           </Row>
// //         )}

// //         {bookingId && (
// //           <Row justify="center">
// //             <CustomButton
// //               title="View Booking Details"
// //               variant="outlined"
// //               color="secondary"
// //               onPress={() => navigate(AppRoutes.bookingById)}
// //               style={{ width: "100%", marginTop: 12 }}
// //             >
// //               View Booking Details
// //             </CustomButton>
// //           </Row>
// //         )}
// //       </Column>
// //     </Container>
// //   );
// // };

// // // export default BookingPage;
// // import React from "react";
// // import { useParams, useNavigate } from "react-router-dom";
// // import Colors from "../utils/Colors";

// // const BookingPage = () => {
// //   const { propertyId } = useParams();
// //   const navigate = useNavigate();

// //   const handleBooking = () => {
// //     alert(`Booking successful for property: ${propertyId}`);
// //     navigate("/my-properties"); // Redirect after booking
// //   };

// //   return (
// //     <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "90vh" }}>
// //       <div style={{
// //         backgroundColor: "#111827",
// //         padding: "30px",
// //         borderRadius: "20px",
// //         color: "white",
// //         boxShadow: "0px 0px 15px rgba(0,0,0,0.3)",
// //         minWidth: "350px",
// //         textAlign: "center",
// //       }}>
// //         <h2 style={{ color: Colors.textOrange }}>Book This Property</h2>
// //         <p>Property ID: {propertyId}</p>
// //         <button
// //           onClick={handleBooking}
// //           style={{
// //             backgroundColor: Colors.textOrange,
// //             border: "none",
// //             color: "white",
// //             padding: "10px 20px",
// //             borderRadius: "8px",
// //             cursor: "pointer",
// //             marginTop: "20px"
// //           }}
// //         >
// //           Confirm Booking
// //         </button>
// //       </div>
// //     </div>
// //   );
// // };

// // export default BookingPage;

// import React, { useEffect, useState } from "react";
// import { useParams, useNavigate, useLocation } from "react-router-dom";
// import { createBooking } from "../services/bookingService";
// import { getPropertyDetailsById } from "../services/propertyService"; 
// import Container from "../components/Container";
// import CustomButton from "../components/CustomButton";
// import Column from "../components/Column";
// import SizedBox from "../components/SizedBox";
// import Row from "../components/Row";
// import Colors from "../utils/Colors";
// import UserContext from "../utils/UserContext";
// import AppRoutes from "../utils/AppRoutes";

// const BookingPage = () => {
//   const { propertyId } = useParams();
//   const navigate = useNavigate();
//   const location = useLocation();

//   // State
//   const [buyerId, setBuyerId] = useState(null);
//   const [property, setProperty] = useState(null);
//   const [checkIn, setCheckIn] = useState("");
//   const [checkOut, setCheckOut] = useState("");
//   const [loading, setLoading] = useState(false);
//   const [totalPrice, setTotalPrice] = useState(0);

//   // 1. Fetch User & Property on Mount
//   useEffect(() => {
//     // Check Login
//     const user = UserContext.getLoggedInUser();
//     if (user && user.uid) {
//       setBuyerId(user.uid);
//     } else {
//       alert("You must be logged in to book.");
//       navigate(AppRoutes.login, { state: { from: location.pathname } });
//       return;
//     }

//     // Fetch Property Details
//     const fetchProperty = async () => {
//       try {
//         const data = await getPropertyDetailsById(propertyId);
//         setProperty(data);
//       } catch (error) {
//         console.error("Failed to fetch property:", error);
//         alert("Could not load property details.");
//       }
//     };
//     fetchProperty();
//   }, [propertyId, navigate, location]);

//   // 2. Auto-Calculate Price
//   useEffect(() => {
//     if (checkIn && checkOut && property?.price) {
//         const start = new Date(checkIn);
//         const end = new Date(checkOut);
        
//         // Calculate difference in days
//         const diffTime = end - start;
//         const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

//         if (diffDays > 0) {
//             setTotalPrice(diffDays * property.price);
//         } else {
//             setTotalPrice(0);
//         }
//     }
//   }, [checkIn, checkOut, property]);

//   // 3. Handle Booking Submission
//   const handleBooking = async () => {
//     if (!checkIn || !checkOut) {
//       alert("Please select Check-In and Check-Out dates.");
//       return;
//     }
    
//     if (new Date(checkIn) >= new Date(checkOut)) {
//         alert("Check-Out date must be after Check-In date.");
//         return;
//     }

//     setLoading(true);

//     // ✅ FIX: Payload matches Backend BookingRequest.java EXACTLY
//     const bookingDetails = {
//       propertyId: propertyId,
//       tenantId: buyerId,        // Backend expects 'tenantId', NOT 'buyerId'
//       checkInDate: checkIn,     // Backend expects 'checkInDate' (YYYY-MM-DD)
//       checkOutDate: checkOut,   // Backend expects 'checkOutDate'
//       pricePerNight: property?.price || 0
//     };

//     try {
//       console.log("Sending Payload:", bookingDetails);
//       await createBooking(bookingDetails);
//       alert("Booking Successful! 🎉");
//       navigate(AppRoutes.myBookings); // Redirect to My Bookings
//     } catch (error) {
//       console.error("Booking Error:", error);
//       alert("Booking Failed. Check console for details.");
//     } finally {
//       setLoading(false);
//     }
//   };

//   if (!property) return <div style={{textAlign:"center", marginTop: 50}}>Loading Property Details...</div>;

//   return (
//     <Container style={{ maxWidth: 600, margin: "40px auto", padding: 24, border: "1px solid #eee", borderRadius: 12, boxShadow: "0 4px 12px rgba(0,0,0,0.1)" }}>
//       <Column align="center">
//         <h2 style={{ color: Colors.textOrange }}>Confirm Booking</h2>
//         <SizedBox height={20} />

//         {/* Property Summary */}
//         <div style={{ background: "#f9f9f9", padding: 20, borderRadius: 8, width: "100%", textAlign: "left" }}>
//           <h3 style={{ margin: "0 0 10px 0", color: "#333" }}>{property.propertyName}</h3>
//           <p style={{ color: "#666", margin: "5px 0" }}>{property.location?.city}, {property.location?.state}</p>
//           <p style={{ fontWeight: "bold", color: Colors.primary }}>Rate: ₹{property.price?.toLocaleString()} / night</p>
//         </div>

//         <SizedBox height={20} />

//         {/* Date Inputs */}
//         <Row style={{ width: "100%", justifyContent: "space-between", gap: 10 }}>
//             <div style={{ flex: 1 }}>
//                 <label style={{display:"block", marginBottom: 5, fontWeight:"bold"}}>Check-In</label>
//                 <input 
//                     type="date" 
//                     value={checkIn}
//                     onChange={(e) => setCheckIn(e.target.value)}
//                     style={inputStyle}
//                 />
//             </div>
//             <div style={{ flex: 1 }}>
//                 <label style={{display:"block", marginBottom: 5, fontWeight:"bold"}}>Check-Out</label>
//                 <input 
//                     type="date" 
//                     value={checkOut}
//                     onChange={(e) => setCheckOut(e.target.value)}
//                     style={inputStyle}
//                 />
//             </div>
//         </Row>

//         <SizedBox height={20} />

//         {/* Total Price Display */}
//         {totalPrice > 0 && (
//             <div style={{ fontSize: "1.2rem", color: "green", fontWeight: "bold" }}>
//                 Total Price: ₹{totalPrice.toLocaleString()}
//             </div>
//         )}

//         <SizedBox height={20} />

//         <CustomButton
//           title={loading ? "Processing..." : "Confirm & Pay"}
//           onPress={handleBooking}
//           disabled={loading}
//           style={{ width: "100%", backgroundColor: Colors.primary }}
//         />
        
//         <SizedBox height={10} />
//         <button 
//             onClick={() => navigate(-1)} 
//             style={{ background: "none", border: "none", cursor: "pointer", color: "gray", textDecoration: "underline" }}
//         >
//             Cancel
//         </button>

//       </Column>
//     </Container>
//   );
// };

// const inputStyle = {
//     width: "100%",
//     padding: "10px",
//     borderRadius: "8px",
//     border: "1px solid #ccc",
//     fontSize: "1rem"
// };

// export default BookingPage;


import React, { useEffect, useState } from "react";
import { useParams, useNavigate, useLocation } from "react-router-dom";
import { createBooking } from "../services/bookingService";
import { getPropertyDetailsById } from "../services/propertyService"; 
import Container from "../components/Container";
import CustomButton from "../components/CustomButton";
import Column from "../components/Column";
import SizedBox from "../components/SizedBox";
import Colors from "../utils/Colors";
import UserContext from "../utils/UserContext";
import AppRoutes from "../utils/AppRoutes";

const BookingPage = () => {
  const { propertyId } = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  // State
  const [buyerId, setBuyerId] = useState(null);
  const [property, setProperty] = useState(null);
  const [bookingDate, setBookingDate] = useState(""); // ✅ Single Date
  const [loading, setLoading] = useState(false);

  // 1. Fetch User & Property on Mount
  useEffect(() => {
    // Check Login
    const user = UserContext.getLoggedInUser();
    if (user && user.uid) {
      setBuyerId(user.uid);
    } else {
      alert("You must be logged in to book.");
      navigate(AppRoutes.login, { state: { from: location.pathname } });
      return;
    }

    // Fetch Property Details
    const fetchProperty = async () => {
      try {
        const data = await getPropertyDetailsById(propertyId);
        setProperty(data);
      } catch (error) {
        console.error("Failed to fetch property:", error);
        alert("Could not load property details.");
      }
    };
    fetchProperty();
  }, [propertyId, navigate, location]);

  // 2. Handle Booking Submission
  const handleBooking = async () => {
    // 👇 DEBUG LOGS: Check your Console (F12) to see these!
    console.log("--------------------------------------");
    console.log("🟢 'Confirm Booking' Button Clicked!");
    console.log("🟢 Current User ID (Buyer):", buyerId);
    console.log("🟢 Selected Date Value:", bookingDate);

    // Validation
    if (!bookingDate) {
      console.error("🔴 Error: Booking Date is empty!");
      alert("Please select a Booking Date.");
      return;
    }

    // Optional: Prevent past dates
    const today = new Date().toISOString().split("T")[0];
    if (bookingDate < today) {
        console.error("🔴 Error: Date is in the past!");
        alert("You cannot book a date in the past.");
        return;
    }

    setLoading(true);

    // ✅ PAYLOAD MATCHING YOUR BACKEND
    const bookingDetails = {
      propertyId: propertyId,
      buyerId: buyerId,
      bookingDate: bookingDate 
    };

    try {
      console.log("🟡 Sending Payload to Backend:", bookingDetails);
      
      const response = await createBooking(bookingDetails);
      
      console.log("✅ Backend Response:", response);
      alert("Booking Successful! 🎉");
      navigate("/my-bookings"); 
    } catch (error) {
      console.error("🔴 Booking Failed. Error Details:", error);
      alert("Booking Failed. Check console for details.");
    } finally {
      setLoading(false);
    }
  };

  if (!property) return <div style={{textAlign:"center", marginTop: 50}}>Loading Property Details...</div>;

  return (
    <Container style={{ maxWidth: 600, margin: "40px auto", padding: 24, border: "1px solid #eee", borderRadius: 12, boxShadow: "0 4px 12px rgba(0,0,0,0.1)" }}>
      <Column align="center">
        <h2 style={{ color: Colors.textOrange }}>Book {property.propertyName}</h2>
        <SizedBox height={10} />

        {/* Property Summary */}
        <div style={{ background: "#f9f9f9", padding: 20, borderRadius: 8, width: "100%", textAlign: "left" }}>
          <p style={{ color: "#666", margin: "5px 0" }}>{property.location?.city}, {property.location?.state}</p>
          <p style={{ fontWeight: "bold", color: Colors.primary }}>Price: ₹{property.price?.toLocaleString()}</p>
        </div>

        <SizedBox height={20} />

        {/* Single Date Input */}
        <div style={{ width: "100%" }}>
            <label style={{display:"block", marginBottom: 5, fontWeight:"bold"}}>Select Date</label>
            <input 
                type="date" 
                value={bookingDate}
                onChange={(e) => {
                    console.log("📅 Date Changed to:", e.target.value); // Log date changes
                    setBookingDate(e.target.value);
                }}
                style={inputStyle}
            />
        </div>

        <SizedBox height={30} />

        <CustomButton
          title={loading ? "Processing..." : "Confirm Booking"}
          onPress={handleBooking}
          disabled={loading}
          style={{ width: "100%", backgroundColor: Colors.primary }}
        />
        
        <SizedBox height={10} />
        <button 
            onClick={() => navigate(-1)} 
            style={{ background: "none", border: "none", cursor: "pointer", color: "gray", textDecoration: "underline" }}
        >
            Cancel
        </button>

      </Column>
    </Container>
  );
};

const inputStyle = {
    width: "100%",
    padding: "10px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    fontSize: "1rem"
};

export default BookingPage;