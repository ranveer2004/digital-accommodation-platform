// import React from "react";

// const PropertyCard = ({ property, onClick }) => {
//   return (
//     <div
//       onClick={onClick}
//       style={{
//         margin: "0px 10px",
//         cursor: "pointer",
//         width: "280px",
//         backgroundColor: "#1e1e2f",
//         borderRadius: "12px",
//         overflow: "hidden",
//         boxShadow: "0 4px 12px rgba(0,0,0,0.3)",
//         transition: "transform 0.2s, box-shadow 0.2s",
//       }}
//       onMouseOver={(e) => {
//         e.currentTarget.style.transform = "scale(1.1)";
//         e.currentTarget.style.boxShadow = "0 6px 16px rgba(0,0,0,0.4)";
//       }}
//       onMouseOut={(e) => {
//         e.currentTarget.style.transform = "scale(1)";
//         e.currentTarget.style.boxShadow = "0 4px 12px rgba(0,0,0,0.3)";
//       }}
//     >
//       {/* Property Image */}
//       <img
//         src={property.images?.[0] || "https://via.placeholder.com/280x180"}
//         alt={property.propertyName}
//         style={{
//           width: "100%",
//           height: "180px",
//           objectFit: "cover",
//         }}
//       />

//       {/* Property Details */}
//       <div style={{ padding: "16px", color: "white", textAlign: "left" }}>
//         <h3 style={{ margin: "0 0 8px 0" }}>{property.propertyName}</h3>
//         <p style={{ margin: "0 0 4px 0", fontSize: "14px", color: "#ccc" }}>
//           {property.propertyCategory} • {property.listingType}
//         </p>
//         <p style={{ margin: "0 0 4px 0", fontSize: "16px", fontWeight: "bold" }}>
//           ₹{property.price.toLocaleString()}
//         </p>
//         <p style={{ margin: "0", fontSize: "14px", color: "#ccc" }}>
//           {property.bedrooms} BHK • {property.bathrooms} Bath
//         </p>
//         <p style={{ margin: "4px 0 0 0", fontSize: "14px", color: "#aaa" }}>
//           {property.location?.city}, {property.location?.state}
//         </p>
//       </div>
//     </div>
//   );
// };

// export default PropertyCard;
import React from "react";

const BASE_URL = "http://localhost:9090";

const PropertyCard = ({ property, onClick }) => {
  
  const getImageUrl = (imageName) => {
    if (!imageName) return null;
    if (imageName.startsWith("http")) return imageName;
    return `${BASE_URL}/properties/images/${imageName}`; 
  };

  const displayImage = getImageUrl(property.images?.[0]);

  return (
    <div
      onClick={onClick}
      style={{
        margin: "0px 10px",
        cursor: "pointer",
        width: "280px",
        backgroundColor: "#1e1e2f",
        borderRadius: "12px",
        overflow: "hidden",
        boxShadow: "0 4px 12px rgba(0,0,0,0.3)",
        transition: "transform 0.2s, box-shadow 0.2s",
      }}
      onMouseOver={(e) => {
        e.currentTarget.style.transform = "scale(1.05)";
        e.currentTarget.style.boxShadow = "0 6px 16px rgba(0,0,0,0.4)";
      }}
      onMouseOut={(e) => {
        e.currentTarget.style.transform = "scale(1)";
        e.currentTarget.style.boxShadow = "0 4px 12px rgba(0,0,0,0.3)";
      }}
    >
      <img
        src={displayImage || "https://placehold.co/600x400?text=No+Image"}
        alt={property.propertyName}
        style={{
          width: "100%",
          height: "180px",
          objectFit: "cover",
        }}
        onError={(e) => { 
          e.target.onerror = null; 
          e.target.src = "https://placehold.co/600x400?text=Error+Loading"; 
        }}
      />

      <div style={{ padding: "16px", color: "white", textAlign: "left" }}>
        <h3 style={{ margin: "0 0 8px 0", fontSize: "18px" }}>{property.propertyName}</h3>
        <p style={{ margin: "0 0 4px 0", fontSize: "14px", color: "#ccc" }}>
          {property.propertyCategory} • {property.listingType}
        </p>
        <p style={{ margin: "0 0 4px 0", fontSize: "16px", fontWeight: "bold", color: "#4caf50" }}>
          ₹{property.price?.toLocaleString()}
        </p>
        <p style={{ margin: "0", fontSize: "14px", color: "#ccc" }}>
          {property.bedrooms} BHK • {property.bathrooms} Bath
        </p>
        <p style={{ margin: "4px 0 0 0", fontSize: "14px", color: "#aaa" }}>
          {property.location?.city}, {property.location?.state}
        </p>
      </div>
    </div>
  );
};

export default PropertyCard;