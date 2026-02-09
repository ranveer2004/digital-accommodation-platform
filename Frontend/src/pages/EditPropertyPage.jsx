import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getMyProperties, updateProperty } from "../services/propertyService";
import CustomButton from "../components/CustomButton";
import Container from "../components/Container";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import UserContext from "../utils/UserContext";
import AppRoutes from "../utils/AppRoutes";
import logo from "../assets/stay_en_casa-nobg.png";
import Colors from "../utils/Colors";

const EditPropertyPage = () => {
  const { propertyId } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    propertyName: "",
    listingType: "",
    propertyCategory: "",
    propertyDescription: "",
    price: "",
    area: "",
    unit: "",
    bedrooms: "",
    bathrooms: "",
    floorNumber: "",
    totalFloors: "",
    furnishing: "",
    amenities: [],
    location: {
      address: "",
      locality: "",
      city: "",
      state: "",
      country: "",
      pincode: "",
      latitude: "",
      longitude: "",
    },
    isAvailable: true,
    images: [],
  });

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchProperty = async () => {
      try {
        // const ownerId = UserContext.getLoggedInUser()?.id;
        // if (!ownerId) {
        //   alert("User not logged in.");
        //   navigate(AppRoutes.login);
        //   return;
        // }

        const data = await getMyProperties(ownerId);
        const property = data.find((p) => p.propertyId === propertyId);

        if (!property) {
          alert("Property not found or you don't own this property.");
          navigate(AppRoutes.myProperties);
        } else {
          setForm(property);
        }
      } catch (err) {
        console.error(err);
        alert("Failed to fetch property details.");
        navigate(AppRoutes.myProperties);
      }
    };
    fetchProperty();
  }, [propertyId, navigate]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLocationChange = (e) => {
    setForm({
      ...form,
      location: { ...form.location, [e.target.name]: e.target.value },
    });
  };

  const handleAmenitiesChange = (e) => {
    setForm({ ...form, amenities: e.target.value.split(",").map((a) => a.trim()) });
  };

  const handleImagesChange = (e) => {
    setForm({ ...form, images: e.target.value.split(",").map((url) => url.trim()) });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await updateProperty(propertyId, form);
      alert("Property updated successfully!");
      navigate(AppRoutes.myProperties);
    } catch (err) {
      console.error(err);
      alert("Failed to update property.");
    } finally {
      setLoading(false);
    }
  };

  const inputStyle = {
    padding: "12px 16px",
    border: "2px solid #e1e5e9",
    borderRadius: "8px",
    fontSize: "14px",
    outline: "none",
    transition: "border-color 0.2s ease",
    fontFamily: "inherit",
    flex: 1,
    minWidth: 200,
    boxSizing: "border-box",
  };

  const focusStyle = {
    borderColor: "rgb(226,117,45)",
    boxShadow: "0 0 0 3px rgba(0, 123, 255, 0.1)"
  };

  const fieldsetStyle = {
    border: "2px solid rgb(226,117,45)",
    padding: 24,
    borderRadius: 16,
    marginBottom: 24,
  };

  const legendStyle = {
    fontSize: "16px",
    fontWeight: "600",
    color: "#f9f9f9ff",
    padding: "0 12px",
    backgroundColor: "rgb(226,117,45)",
    borderRadius: "6px",
  };

  return (
    <Container maxWidth={1100}>
      <h2 style={{
            display: "flex",
            alignItems: "center",
            gap: "20px",
            justifyContent: "center",
            color: Colors.textOrange,
            }}>
            <img src={logo} alt="Logo" style={{ height: "60px", width: "auto" }} />
            <span>Edit Property</span>
            <img src={logo} alt="Logo" style={{ height: "60px", width: "auto" }} />
      </h2>

      <form onSubmit={handleSubmit} style={{ width: "100%" }}>
        {/* Property Details */}
        <fieldset style={fieldsetStyle}>
          <legend style={legendStyle}>Property Details</legend>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <input type="text" name="propertyName" placeholder="Property Name" value={form.propertyName || ""} onChange={handleChange} required style={inputStyle}/>
            <select name="listingType" value={form.listingType || ""} onChange={handleChange} required style={inputStyle}>
              <option value="">Select Listing Type</option>
              <option value="RENT">Rent</option>
              <option value="SALE">Sale</option>
            </select>
            <select name="propertyCategory" value={form.propertyCategory || ""} onChange={handleChange} required style={inputStyle}>
              <option value="">Select Category</option>
              <option value="FLAT">Flat</option>
              <option value="VILLA">Villa</option>
              <option value="PLOT">Plot</option>
              <option value="COMMERCIAL">Commercial</option>
            </select>
          </Row>
          <SizedBox height={16} width={100}/>
          <textarea name="propertyDescription" placeholder="Property Description" value={form.propertyDescription || ""} onChange={handleChange} required style={{ ...inputStyle, minHeight: "100px", resize: "vertical" }}/>
        </fieldset>

        {/* Pricing & Specifications */}
        <fieldset style={fieldsetStyle}>
          <legend style={legendStyle}>Pricing & Specifications</legend>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <input type="number" name="price" placeholder="Price" value={form.price || ""} onChange={handleChange} style={inputStyle} required />
            <input type="number" name="area" placeholder="Area" value={form.area || ""} onChange={handleChange} style={inputStyle} required />
            <select name="unit" value={form.unit || ""} onChange={handleChange} style={inputStyle}>
              <option value="">Select Unit</option>
              <option value="SQ_FT">Sq.Ft.</option>
              <option value="SQ_YARD">Sq.Yard</option>
              <option value="SQ_M">Sq.M.</option>
              <option value="HECTARE">Hectare</option>
              <option value="ACRE">Acre</option>
            </select>
          </Row>
          <SizedBox height={16}/>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <input type="number" name="bedrooms" placeholder="Bedrooms" value={form.bedrooms || ""} onChange={handleChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"} />
            <input type="number" name="bathrooms" placeholder="Bathrooms" value={form.bathrooms || ""} onChange={handleChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"} />
            <input type="number" name="floorNumber" placeholder="Floor Number" value={form.floorNumber || ""} onChange={handleChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"} />
            <input type="number" name="totalFloors" placeholder="Total Floors" value={form.totalFloors || ""} onChange={handleChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"} />
          </Row>
          <SizedBox height={16}/>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <select name="furnishing" value={form.furnishing || ""} onChange={handleChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"}>
              <option value="">Select Furnishing</option>
              <option value="FURNISHED">Fully Furnished</option>
              <option value="SEMI_FURNISHED">Semi Furnished</option>
              <option value="UNFURNISHED">Unfurnished</option>
            </select>
            <input type="text" placeholder="Amenities (comma separated)" value={form.amenities?.join(", ") || ""} onChange={handleAmenitiesChange} style={inputStyle} disabled={form.propertyCategory==="PLOT"} />
          </Row>
        </fieldset>

        {/* Location */}
        <fieldset style={fieldsetStyle}>
          <legend style={legendStyle}>Location</legend>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <input type="text" name="address" placeholder="Address" value={form.location?.address || ""} onChange={handleLocationChange} style={inputStyle}/>
            <input type="text" name="locality" placeholder="Locality" value={form.location?.locality || ""} onChange={handleLocationChange} style={inputStyle}/>
            <input type="text" name="city" placeholder="City" value={form.location?.city || ""} onChange={handleLocationChange} style={inputStyle} required/>
            <input type="text" name="state" placeholder="State" value={form.location?.state || ""} onChange={handleLocationChange} style={inputStyle} required/>
            <input type="text" name="country" placeholder="Country" value={form.location?.country || ""} onChange={handleLocationChange} style={inputStyle} required/>
            <input type="number" name="pincode" placeholder="Pincode" value={form.location?.pincode || ""} onChange={handleLocationChange} style={inputStyle}/>
          </Row>
          <SizedBox height={16}/>
          <Row style={{ gap: 16, flexWrap: "wrap" }}>
            <input type="number" step="any" name="latitude" placeholder="Latitude" value={form.location?.latitude || ""} onChange={handleLocationChange} style={inputStyle}/>
            <input type="number" step="any" name="longitude" placeholder="Longitude" value={form.location?.longitude || ""} onChange={handleLocationChange} style={inputStyle}/>
          </Row>
        </fieldset>

        {/* Availability */}
        <fieldset style={fieldsetStyle}>
          <legend style={legendStyle}>Availability</legend>
          <select
            name="isAvailable"
            value={form.isAvailable ? "true" : "false"}
            onChange={(e) =>
              setForm({ ...form, isAvailable: e.target.value === "true" })
            }
            style={inputStyle}
          >
            <option value="true">Available</option>
            <option value="false">Not Available</option>
          </select>
        </fieldset>

        {/* Images */}
        <fieldset style={fieldsetStyle}>
          <legend style={legendStyle}>Images</legend>
          <input
            type="text"
            placeholder="Images (comma separated URLs)"
            value={form.images?.join(", ") || ""}
            onChange={handleImagesChange}
            style={inputStyle}
          />
        </fieldset>

        <SizedBox height={20}/>
        <CustomButton
          onClick={handleSubmit}
          disabled={loading}
          title={loading ? "Saving..." : "Save Changes"}
          style={{
            width: "100%",
            backgroundColor: loading ? "#6c757d" : "#28a745",
            cursor: loading ? "not-allowed" : "pointer",
            opacity: loading ? 0.8 : 1,
          }}
        />
      </form>
    </Container>
  );
};

export default EditPropertyPage;
