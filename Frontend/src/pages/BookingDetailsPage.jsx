/**
 * BookingDetailsPage displays the details of a single booking
 * using the bookingId retrieved from the URL parameters.
 */

// import React, { useEffect, useState } from 'react';
// import { useParams, useNavigate } from "react-router-dom";
// import { getBookingDetailsById } from '../services/bookingService';
// import Container from "../components/Container";
// import Column from "../components/Column";
// import Row from "../components/Row";
// import SizedBox from "../components/SizedBox";
// import CustomButton from "../components/CustomButton";
// import Colors from '../utils/Colors';

// const BookingDetailsPage = () => {

//     // Get the bookingId from the URL (e.g., /booking/123)
//     const { bookingId } = useParams();
    
//     const navigate = useNavigate();

//     // State to hold booking details
//     const [booking, setBooking] = useState(null);

//     // State to handle loading and error states
//     const [loading, setLoading] = useState(true);

//     const [error, setError] = useState(null);

//     /**
//    * useEffect fetches booking details when the component mounts
//    * or when the bookingId changes.
//    */
//     useEffect(()=> {
//         const fetchBooking = async () => {
//             try {

//                 // Fetch booking details from backend
//                 const response = await getBookingDetailsById(bookingId);
//                 setBooking(response);       
//             } catch (error) {
//                 setError("Failed to Fetch Booking Details!!!!!!!!");        
//             } finally{
//                 setLoading(false);      
//             }
//         };

//         // Fetch only if bookingId is available
//         if(bookingId){
//             fetchBooking();
//         }
//         else{
//             setError("Booking ID is Missing");
//             setLoading(false);
//         }
//     }, [bookingId]);

//     const getStatusColor = (status) => {
//         if (status === "CONFIRMED" || status === "APPROVED") return "green";
//         if (status === "CANCELLED") return "red";
//         return "orange";
//     };

//     return (
//         <>
//             <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
//                 <Column align="center" >
//                     <h2 style = {{color: Colors.textOrange}}>Booking Details</h2>
//                     <SizedBox height={16} />
//                     {/* Loading State */}
//                     {loading && <p>Loading...</p>}
//                     {/* Error State */}
//                     {error && <p style={{ color: "red" }}>{error}</p>}
//                     {/* Booking Details */}
//                     {booking && (
//                         <Column align="flex-start" style={{
//                             border: '1px solid #ccc',
//                             padding: '1rem',
//                             borderRadius: '8px',
//                             backgroundColor: '#f4f4f4',
//                             width: "100%"
//                         }}>
//                             <Row>
//                                 <strong style = {{color: Colors.textOrange}}>Booking ID:</strong>&nbsp;{booking.bookingId}
//                             </Row>
//                             <Row>
//                                 <strong style = {{color: Colors.textOrange}}>Buyer ID:</strong>&nbsp;{booking.buyerId}
//                             </Row>
//                             <Row>
//                                 <strong style = {{color: Colors.textOrange}}>Property ID:</strong>&nbsp;{booking.propertyId}
//                             </Row>
//                             <Row>
//                                 <strong style = {{color: Colors.textOrange}}>Booking Date:</strong>&nbsp;{booking.bookingDate}
//                             </Row>
//                             <Row>
//                                 <strong style = {{color: Colors.textOrange}}>Booking Status:</strong>&nbsp;
//                                 <span style={{ color: getStatusColor(booking.status), fontWeight: 600 }}>
//                                     {booking.status}
//                                 </span>
//                             </Row>
//                         </Column>
//                     )}
//                     <SizedBox height={24} />
//                     {/* Back Button */}
//                     <CustomButton
//                         variant="outlined"
//                         color="secondary"
//                         onPress={() => navigate(-1)}
//                         style={{ width: "100%" }}
//                     >
//                         Back
//                     </CustomButton>
//                 </Column>
//             </Container>
//         </>
//     );
// };

// export default BookingDetailsPage;

// import React, { useEffect, useState } from 'react';
// import { useParams, useNavigate } from "react-router-dom";
// import { getBookingDetailsById } from '../services/bookingService';
// import Container from "../components/Container";
// import Column from "../components/Column";
// import Row from "../components/Row";
// import SizedBox from "../components/SizedBox";
// import CustomButton from "../components/CustomButton";
// import Colors from '../utils/Colors';
// import { jsPDF } from "jspdf"; // ✅ Added for PDF
// import "jspdf-autotable"; // ✅ Added for formatting

// const BookingDetailsPage = () => {
//     const { bookingId } = useParams();
//     const navigate = useNavigate();

//     const [booking, setBooking] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);

//     useEffect(() => {
//         const fetchBooking = async () => {
//             try {
//                 const response = await getBookingDetailsById(bookingId);
//                 setBooking(response);
//             } catch (error) {
//                 setError("Failed to Fetch Booking Details!");
//             } finally {
//                 setLoading(false);
//             }
//         };

//         if (bookingId) fetchBooking();
//         else {
//             setError("Booking ID is Missing");
//             setLoading(false);
//         }
//     }, [bookingId]);

//     // ✅ PDF Download Logic
//     const downloadInvoice = () => {
//         const doc = new jsPDF();
//         doc.setFillColor(230, 126, 34); 
//         doc.rect(0, 0, 210, 40, 'F');
//         doc.setTextColor(255, 255, 255);
//         doc.setFontSize(22);
//         doc.text("STAY-EN-CASA INVOICE", 20, 25);

//         doc.setTextColor(0, 0, 0);
//         doc.setFontSize(12);
//         doc.text(`Booking ID: ${booking.bookingId}`, 20, 55);
//         doc.text(`Payment ID: ${booking.paymentId || 'N/A'}`, 20, 65);
//         doc.text(`Date: ${booking.bookingDate}`, 140, 55);

//         doc.autoTable({
//             startY: 80,
//             head: [['Property ID', 'Buyer ID', 'Amount Paid', 'Status']],
//             body: [[
//                 booking.propertyId, 
//                 booking.buyerId, 
//                 `INR ${booking.amountPaid || 'Confirmed'}`, 
//                 booking.status
//             ]],
//             headStyles: { fillColor: [230, 126, 34] }
//         });

//         doc.save(`Invoice_${booking.bookingId.substring(0, 5)}.pdf`);
//     };

//     const getStatusColor = (status) => {
//         if (status === "CONFIRMED" || status === "APPROVED") return "green";
//         if (status === "CANCELLED") return "red";
//         return "orange";
//     };

//     return (
//         <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
//             <Column align="center">
//                 <h2 style={{ color: Colors.textOrange }}>Booking Details</h2>
//                 <SizedBox height={16} />
//                 {loading && <p>Loading...</p>}
//                 {error && <p style={{ color: "red" }}>{error}</p>}
                
//                 {booking && (
//                     <Column align="flex-start" style={{
//                         border: '1px solid #ccc',
//                         padding: '1.5rem',
//                         borderRadius: '8px',
//                         backgroundColor: '#f4f4f4',
//                         width: "100%"
//                     }}>
//                         <Row><strong style={{ color: Colors.textOrange }}>Booking ID:</strong>&nbsp;{booking.bookingId}</Row>
//                         <Row><strong style={{ color: Colors.textOrange }}>Buyer ID:</strong>&nbsp;{booking.buyerId}</Row>
//                         <Row><strong style={{ color: Colors.textOrange }}>Property ID:</strong>&nbsp;{booking.propertyId}</Row>
//                         <Row><strong style={{ color: Colors.textOrange }}>Booking Date:</strong>&nbsp;{booking.bookingDate}</Row>
                        
//                         {/* ✅ Added Payment ID to the UI */}
//                         <Row>
//                             <strong style={{ color: Colors.textOrange }}>Payment Ref:</strong>&nbsp;{booking.paymentId || "N/A"}
//                         </Row>

//                         <Row>
//                             <strong style={{ color: Colors.textOrange }}>Booking Status:</strong>&nbsp;
//                             <span style={{ color: getStatusColor(booking.status), fontWeight: 600 }}>
//                                 {booking.status}
//                             </span>
//                         </Row>

//                         <SizedBox height={20} />
                        
//                         {/* ✅ NEW DOWNLOAD BUTTON */}
//                         <CustomButton
//                             style={{ width: "100%", backgroundColor: Colors.textOrange }}
//                             onPress={downloadInvoice}
//                         >
//                             Download Invoice (PDF)
//                         </CustomButton>
//                     </Column>
//                 )}
                
//                 <SizedBox height={16} />
                
//                 <CustomButton
//                     variant="outlined"
//                     onPress={() => navigate(-1)}
//                     style={{ width: "100%" }}
//                 >
//                     Back
//                 </CustomButton>
//             </Column>
//         </Container>
//     );
// };

// export default BookingDetailsPage;
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import { getBookingDetailsById } from '../services/bookingService';
import { getPropertyDetailsById } from '../services/propertyService'; // Ensure this exists
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import CustomButton from "../components/CustomButton";
import Colors from '../utils/Colors';
import { jsPDF } from "jspdf";
import "jspdf-autotable";

const BookingDetailsPage = () => {
    const { bookingId } = useParams();
    const navigate = useNavigate();

    const [booking, setBooking] = useState(null);
    const [property, setProperty] = useState(null); // To store property name & amenities
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchAllDetails = async () => {
            try {
                // 1. Fetch Booking
                const bData = await getBookingDetailsById(bookingId);
                setBooking(bData);

                // 2. Fetch Property using the ID from the booking
                if (bData && bData.propertyId) {
                    const pData = await getPropertyDetailsById(bData.propertyId);
                    setProperty(pData);
                }
            } catch (error) {
                setError("Failed to Fetch Details!");
            } finally {
                setLoading(false);
            }
        };

        if (bookingId) fetchAllDetails();
    }, [bookingId]);

    const downloadInvoice = () => {
        const doc = new jsPDF();
        const primary = [230, 126, 34]; // Colors.textOrange in RGB

        // Header Section
        doc.setFillColor(...primary);
        doc.rect(0, 0, 210, 45, 'F');
        doc.setTextColor(255, 255, 255);
        doc.setFontSize(24);
        doc.text("STAY-EN-CASA", 20, 25);
        doc.setFontSize(10);
        doc.text("Booking Confirmation & Invoice", 20, 35);

        // Bill Info
        doc.setTextColor(0, 0, 0);
        doc.setFontSize(12);
        doc.setFont("helvetica", "bold");
        doc.text("Invoice To:", 20, 55);
        doc.setFont("helvetica", "normal");
        doc.text(`Buyer ID: ${booking.buyerId}`, 20, 62);
        doc.text(`Booking ID: ${booking.bookingId}`, 20, 69);
        doc.text(`Payment Ref: ${booking.paymentId || 'N/A'}`, 20, 76);

        // Property Table
        doc.autoTable({
            startY: 85,
            head: [['Property Name', 'Dates', 'Amount Paid']],
            body: [[
                property?.propertyName || booking.propertyId,
                `${booking.bookingDate}`, // You can add Check-in/Out if stored in DB
                `INR ${booking.amountPaid || property?.price}`
            ]],
            headStyles: { fillColor: primary }
        });

        // Amenities Section
        if (property?.amenities) {
            const finalY = doc.lastAutoTable.finalY + 15;
            doc.setFont("helvetica", "bold");
            doc.text("Included Amenities:", 20, finalY);
            doc.setFont("helvetica", "normal");
            doc.setFontSize(10);
            doc.text(property.amenities.join(", "), 20, finalY + 8);
        }

        doc.save(`Invoice_${booking.bookingId.substring(0, 5)}.pdf`);
    };

    if (loading) return <Container><p>Loading...</p></Container>;

    return (
        <Container style={{ maxWidth: 800, margin: "60px auto", padding: 24 }}>
            <Column align="center">
                <h2 style={{ color: Colors.textOrange }}>Your Reservation</h2>
                <SizedBox height={16} />
                
                {booking && property && (
                    <Column align="flex-start" style={{ border: '1px solid #ccc', padding: '1.5rem', borderRadius: '8px', backgroundColor: '#f4f4f4', width: "100%" }}>
                        <h3 style={{ margin: 0 }}>{property.propertyName}</h3>
                        <p style={{ color: '#666' }}>{property.location.address}</p>
                        <hr style={{ width: "100%", margin: "15px 0" }} />
                        
                        <Row><strong style={{ color: Colors.textOrange }}>Booking ID:</strong>&nbsp;{booking.bookingId}</Row>
                        <Row><strong style={{ color: Colors.textOrange }}>Booking Date:</strong>&nbsp;{booking.bookingDate}</Row>
                        <Row><strong style={{ color: Colors.textOrange }}>Status:</strong>&nbsp;<span style={{ color: "green", fontWeight: "bold" }}>{booking.status}</span></Row>
                        
                        <SizedBox height={10} />
                        <p><strong>Amenities:</strong> {property.amenities?.join(", ")}</p>
                        
                        <SizedBox height={20} />
                        <CustomButton style={{ width: "100%", backgroundColor: Colors.textOrange }} onPress={downloadInvoice}>
                            Download PDF Invoice
                        </CustomButton>
                    </Column>
                )}
                
                <SizedBox height={16} />
                <CustomButton variant="outlined" onPress={() => navigate(-1)} style={{ width: "100%" }}>Back</CustomButton>
            </Column>
        </Container>
    );
};

export default BookingDetailsPage;