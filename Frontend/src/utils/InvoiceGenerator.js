import { jsPDF } from "jspdf";
import "jspdf-autotable";

export const generateInvoice = (booking, property, user) => {
    const doc = new jsPDF();
    const primaryColor = [44, 62, 80]; 

    // Header Design
    doc.setFillColor(...primaryColor);
    doc.rect(0, 0, 210, 40, 'F');
    doc.setTextColor(255, 255, 255);
    doc.setFontSize(22);
    doc.text("STAY-EN-CASA", 20, 25);

    // Invoice Info
    doc.setTextColor(0, 0, 0);
    doc.setFontSize(12);
    doc.text(`Invoice to: ${user.email}`, 20, 60);
    doc.text(`Booking ID: ${booking.bookingId}`, 130, 60);

    // Table
    doc.autoTable({
        startY: 75,
        head: [['Property', 'Location', 'Price']],
        body: [
            [property.propertyName, property.location.address, `INR ${property.price}`]
        ],
        headStyles: { fillColor: primaryColor }
    });

    doc.save(`Invoice_${booking.bookingId.substring(0, 5)}.pdf`);
};