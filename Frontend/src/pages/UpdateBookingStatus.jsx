import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getBookingDetailsById, updateBookingStatusByBookingId } from "../services/bookingService";
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import SizedBox from "../components/SizedBox";
import Navbar from "../components/Navbar";

/**
 * UpdateBookingStatus component allows the admin to update the status of a booking.
 * It fetches the booking details using the bookingId from the URL,
 * allows the admin to select a new status from a dropdown,
 * and submits the updated status back to the backend.
*/
const UpdateBookingStatus = () => {

    const { bookingId } = useParams();

    const navigate = useNavigate();

    const [booking, setBooking] = useState(null);

    const [status, setStatus] = useState('');

    const [loading, setLoading] = useState(true);
    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState(null);

    // Fetch booking details by ID when component mounts
    useEffect(
        () => {
            const fetchBooking = async () => {
                try {
                    const response = await getBookingDetailsById(bookingId);
                    setBooking(response);
                    setStatus(response.status);     // Set current status in dropdown
                }catch(error) {
                    console.error('Failed to Fetch Booking: ', error);
                }
            };

            fetchBooking();
        }, [bookingId]
    );

    // Handle status change from dropdown
    const handleStatusChange = (e) => {
        setStatus(e.target.value);
    };

    // Handle form submission to update booking status
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await updateBookingStatusByBookingId(bookingId, status);
            alert('Booking Status Updated Successfully!!!');
            navigate('/my-bookings');       // Redirect to My Bookings page after update
        } catch (error) {
            console.error('Failed to Update Booking Status: ', error);
            alert('Something went wrong...');
            
        }
    };

    if (loading) return <p>Loading Booking Details...</p>;
    if (error) return <p style={{ color: "red" }}>{error}</p>;


    return (
        <>
            <Container style={{ maxWidth: 500, margin: "60px auto", padding: 24 }}>
                <Column align="center">
                    <h2>Update Booking Status</h2>
                    <SizedBox height={16} />
                    <Row><strong>Booking ID:</strong>&nbsp;{booking.bookingId}</Row>
                    <Row><strong>Property ID:</strong>&nbsp;{booking.propertyId}</Row>
                    <Row><strong>Buyer ID:</strong>&nbsp;{booking.buyerId}</Row>
                    <Row><strong>Booking Date:</strong>&nbsp;{booking.bookingDate}</Row>
                    <Row><strong>Current Status:</strong>&nbsp;{booking.status}</Row>
                    <SizedBox height={16} />
                    {/* Form to update booking status */}
                    <form onSubmit={handleSubmit} style={{ width: "100%" }}>
                        <label htmlFor="status"><strong>Select Status:</strong> </label>
                        <select
                            id="status"
                            value={status}
                            onChange={handleStatusChange}
                            required
                            style={{ marginLeft: 8, padding: 4 }}
                        >
                            <option value="PENDING">Pending</option>
                            <option value="CONFIRMED">Confirmed</option>
                            <option value="CANCELLED">Cancelled</option>
                        </select>
                        <SizedBox height={16} />
                        <CustomButton
                            type="submit"
                            disabled={submitting}
                            style={{ width: "100%", marginTop: 12 }}
                        >
                            {submitting ? "Updating..." : "Update Status"}
                        </CustomButton>
                    </form>
                    {error && (
                        <Row justify="center">
                            <span style={{ color: "red" }}>{error}</span>
                        </Row>
                    )}
                </Column>
            </Container>
        </>
    )
}

export default UpdateBookingStatus;