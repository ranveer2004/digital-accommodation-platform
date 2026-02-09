// {/* <Route path="/" element={<Home />} /> */}
// <Route path="/login" element={<Login />} />
// <Route path="/signup" element={<Signup />} />
// {/* <Route path="/account" element={<Account />} /> */}

// {/* <Route path="/" element={<BookingPage />} /> */}
// <Route path="/booking-page" element={<BookingPage />} />
// <Route path="/my-bookings" element={<MyBookingsPage />} />
// <Route path="/booking/:bookingId" element={<BookingDetailsPage />} />
// <Route path='/update-booking/:bookingId' element={<UpdateBookingStatus />} />

const AppRoutes = {
    "/": "/",
    home: "/home",
    dashboard: "/dashboard",
    login: "/login",
    signup: "/signup",
    forgotPassword: "/forgot-password",
    changePassword: "/change-password",

    //
    account: "/account",
    editProfile: "/edit-profile",
    
    //
    bookingPage: "/booking-page/:propertyId",
    myBookings: "/my-bookings/:propertyId",
    bookingById: "/booking/:bookingId",
    updateBookingById: "/update-booking/:bookingId",

    //
    showAllProperties: "/properties",
    addProperty: "/add-property",
    showPropertyById_param: (propertyId) => propertyId ? `/properties/${propertyId}` : "/properties/:propertyId",
    myProperties: "/my-properties",
    editProperty: "/edit-property/:propertyId",
    
};

export default AppRoutes;