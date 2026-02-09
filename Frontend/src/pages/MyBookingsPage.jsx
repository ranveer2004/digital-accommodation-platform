
// // Page to show bookings done by the logged-in user (buyer)

// import React, { useEffect, useState } from "react";
// import { getBookingDetailsByBuyerId } from "../services/bookingService";
// import { useNavigate } from "react-router-dom";
// import Container from "../components/Container";
// import Column from "../components/Column";
// import Row from "../components/Row";
// import CustomButton from "../components/CustomButton";
// import SizedBox from "../components/SizedBox";

// const MyBookingsPage = () => {

//   // State to store all bookings made by the current buyer
//   const [bookings, setBookings] = useState([]);

//   // State to handle loading and error states
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);

//   // Hook to navigate to other routes programmatically
//   const navigate = useNavigate();

//   /**
//    * useEffect hook to fetch bookings for the logged-in buyer on component mount.
//    */
//   useEffect(() => {
//     const fetchBookings = async () => {
//       try {
//         const buyerId = localStorage.getItem('userId');

//         const response = await getBookingDetailsByBuyerId(buyerId);

//         // Save the bookings to local state
//         setBookings(response);
//       } catch (error) {
        
//         console.error('Failed to fetch bookings:', error);
//       }
//     };

//     // Call booking fetch
//     fetchBookings();
//   }, []);

//   /**
//    * Navigate to booking details page using booking ID
//    */
//   const handleBookingClick = (bookingId) => {
//     navigate(`/booking/${bookingId}`);
//   };

//   const getStatusColor = (status) => {
//     if (status === "CONFIRMED" || status === "APPROVED") return "green";
//     if (status === "CANCELLED") return "red";
//     return "orange";
//   };

//   return (
//     <>
//       <Container style={{ maxWidth: 600, margin: "60px auto", padding: 24 }}>
//         <Column align="center">
//           <h2>My Bookings</h2>
//           <SizedBox height={16} />
//           {loading && <p>Loading...</p>}
//           {error && <p style={{ color: "red" }}>{error}</p>}
//           {!loading && bookings.length === 0 && (
//             <p style={{ color: "red" }}>No Bookings Found!</p>
//           )}
//           {!loading && bookings.length > 0 && (
//             <Column align="stretch" style={{ width: "100%" }}>
//               {bookings.map((booking) => (
//                 <Row
//                   key={booking.bookingId}
//                   style={{
//                     border: "1px solid #ccc",
//                     padding: "1rem",
//                     borderRadius: "8px",
//                     marginBottom: "1rem",
//                     backgroundColor: "#f7f7f7",
//                     cursor: "pointer",
//                     alignItems: "center",
//                   }}
//                   onClick={() => handleBookingClick(booking.bookingId)}
//                 >
//                   <Column align="flex-start" style={{ flex: 1 }}>
//                     <div><strong>Property ID:</strong> {booking.propertyId}</div>
//                     <div><strong>Date:</strong> {booking.bookingDate}</div>
//                     <div>
//                       <strong>Status:</strong>{" "}
//                       <span style={{ color: getStatusColor(booking.status), fontWeight: 600 }}>
//                         {booking.status}
//                       </span>
//                     </div>
//                   </Column>
//                   <CustomButton
//                     variant="outlined"
//                     color="secondary"
//                     onClick={e => {
//                       e.stopPropagation();
//                       navigate(`/update-booking/${booking.bookingId}`);
//                     }}
//                   >
//                     Update
//                   </CustomButton>
//                 </Row>
//               ))}
//             </Column>
//           )}
//         </Column>
//       </Container>
//     </>
//   );
// };

// export default MyBookingsPage;
// import React, { useEffect, useState } from "react";
// import { getBookingDetailsByBuyerId } from "../services/bookingService";
// import { getPropertyDetailsById } from "../services/propertyService";
// import { generateInvoice } from "../utils/InvoiceGenerator";
// import LocalStorageHelper from "../utils/LocalStorageHelper";
// import Container from "../components/Container";
// import Column from "../components/Column";
// import Row from "../components/Row";
// import CustomButton from "../components/CustomButton";

// const MyBookingsPage = () => {
//   const [bookings, setBookings] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);

//   useEffect(() => {
//     const fetchBookings = async () => {
//       try {
//         const user = LocalStorageHelper.getUser();
//         const response = await getBookingDetailsByBuyerId(user.uid, setError);
//         setBookings(Array.isArray(response) ? response : []);
//       } catch (err) {
//         setError("Failed to load bookings.");
//       } finally {
//         setLoading(false);
//       }
//     };
//     fetchBookings();
//   }, []);

//   const handleDownloadInvoice = async (booking) => {
//     try {
//       const property = await getPropertyDetailsById(booking.propertyId, setError);
//       const user = LocalStorageHelper.getUser();
//       generateInvoice(booking, property, user);
//     } catch (err) {
//       alert("Error generating invoice. Property data could not be retrieved.");
//     }
//   };

//   return (
//     <Container style={{ maxWidth: 800, margin: "40px auto", padding: "20px" }}>
//       <h2 style={{ textAlign: 'center' }}>My Booking History</h2>
//       {loading && <p>Loading...</p>}
//       {bookings.map((booking) => (
//         <Row key={booking.bookingId} style={{ border: "1px solid #ddd", padding: "20px", marginBottom: "15px", borderRadius: "10px", backgroundColor: '#fff' }}>
//           <Column align="flex-start" style={{ flex: 1 }}>
//             <strong>Property: {booking.propertyId}</strong>
//             <div style={{ fontSize: '0.9rem', color: '#666' }}>Date: {new Date(booking.bookingDate).toLocaleDateString()}</div>
//             <div style={{ color: 'green', fontWeight: 'bold' }}>Status: {booking.status}</div>
//           </Column>
//           <CustomButton variant="contained" color="primary" onClick={() => handleDownloadInvoice(booking)}>
//             Download Invoice
//           </CustomButton>
//         </Row>
//       ))}
//     </Container>
//   );
// };

// export default MyBookingsPage;
// import React, { useEffect, useState } from "react";
// import { getBookingDetailsByBuyerId, cancelBooking } from "../services/bookingService";
// import LocalStorageHelper from "../utils/LocalStorageHelper";
// import Container from "../components/Container";
// import CustomButton from "../components/CustomButton";
// import { jsPDF } from "jspdf";

// const MyBookingsPage = () => {
//   const [bookings, setBookings] = useState([]);

//   // 1. Load bookings on page open
//   useEffect(() => {
//     const load = async () => {
//       const user = LocalStorageHelper.getUser();
//       const data = await getBookingDetailsByBuyerId(user.uid);
//       setBookings(data);
//     };
//     load();
//   }, []);

//   // 2. The simple PDF Download logic
//   const downloadPDF = (b) => {
//     const doc = new jsPDF();
//     doc.setFontSize(20);
//     doc.text("STAY-EN-CASA INVOICE", 20, 20);
//     doc.setFontSize(12);
//     doc.text(`Booking ID: ${b.bookingId}`, 20, 40);
//     doc.text(`Date: ${b.bookingDate}`, 20, 50);
//     doc.text(`Payment ID: ${b.paymentId || "N/A"}`, 20, 60);
//     doc.text(`Amount Paid: INR ${b.amountPaid || "0"}`, 20, 70);
//     doc.text(`Status: ${b.status}`, 20, 80);
//     doc.save(`Invoice_${b.bookingId.substring(0, 5)}.pdf`);
//   };

//   // 3. Using your existing cancelBooking function
//   const handleCancel = async (id) => {
//     if (window.confirm("Are you sure you want to cancel?")) {
//       await cancelBooking(id);
//       window.location.reload(); // Quickest way to refresh the list
//     }
//   };

//   return (
//     <Container style={{ marginTop: "50px" }}>
//       <h2>My Bookings</h2>
//       {bookings.map((b) => (
//         <div key={b.bookingId} style={{ border: "1px solid #ddd", padding: "15px", margin: "10px 0", borderRadius: "8px" }}>
//           <h4>Booking #{b.bookingId.substring(0, 8)}</h4>
//           <p>Status: {b.status} | Date: {b.bookingDate}</p>
          
//           <CustomButton onClick={() => downloadPDF(b)} style={{ marginRight: "10px" }}>
//             Download Invoice
//           </CustomButton>

//           {b.status !== "CANCELLED" && (
//             <button onClick={() => handleCancel(b.bookingId)} style={{ color: "red", border: "none", background: "none", cursor: "pointer" }}>
//               Cancel Booking
//             </button>
//           )}
//         </div>
//       ))}
//     </Container>
//   );
// };

// export default MyBookingsPage;
import React, { useEffect, useState } from "react";
import { getBookingDetailsByBuyerId, cancelBooking } from "../services/bookingService";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import Container from "../components/Container";
import CustomButton from "../components/CustomButton";
import { jsPDF } from "jspdf";

const MyBookingsPage = () => {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    const fetchMyData = async () => {
      const user = LocalStorageHelper.getUser();
      // Uses your getBookingDetailsByBuyerId service
      const data = await getBookingDetailsByBuyerId(user.uid);
      setBookings(data);
    };
    fetchMyData();
  }, []);

  const downloadPDF = (b) => {
    const doc = new jsPDF();
    doc.text("STAY-EN-CASA INVOICE", 20, 20);
    doc.text(`Booking ID: ${b.bookingId}`, 20, 40);
    doc.text(`Payment Ref: ${b.paymentId || "N/A"}`, 20, 50);
    doc.text(`Total Paid: INR ${b.amountPaid || "0"}`, 20, 60);
    doc.save("Invoice.pdf");
  };

  const handleCancel = async (id) => {
    if (window.confirm("Cancel this booking?")) {
      await cancelBooking(id); // Uses your cancelBooking service
      window.location.reload(); 
    }
  };

  return (
    <Container style={{ marginTop: "50px" }}>
      <h2>My Bookings</h2>
      {bookings.length === 0 ? <p>No bookings yet.</p> : 
        bookings.map((b) => (
          <div key={b.bookingId} style={{ border: "1px solid #ddd", padding: "15px", margin: "10px 0" }}>
            <p><strong>Booking ID:</strong> {b.bookingId}</p>
            <p><strong>Status:</strong> {b.status}</p>
            
            {/* ✅ DOWNLOAD BUTTON */}
            <CustomButton onClick={() => downloadPDF(b)} style={{ marginRight: "10px" }}>
              Download Invoice
            </CustomButton>

            {/* ✅ CANCEL BUTTON */}
            {b.status !== "CANCELLED" && (
              <button onClick={() => handleCancel(b.bookingId)} style={{ color: "red", cursor: "pointer" }}>
                Cancel Booking
              </button>
            )}
          </div>
        ))
      }
    </Container>
  );
};

export default MyBookingsPage;