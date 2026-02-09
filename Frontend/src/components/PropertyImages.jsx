// src/components/PropertyImages.jsx
import React, { useState } from "react";
import Row from "./Row";

const PropertyImages = ({ images }) => {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageClick = (url) => setSelectedImage(url);
  const handleClose = () => setSelectedImage(null);

  return (
    <div style={{ marginBottom: "24px" }}>
      <Row style={{ flexWrap: "wrap", gap: "12px" }}>
        {images.map((url, i) => (
          <img
            key={i}
            src={url}
            alt={`property-${i}`}
            onClick={() => handleImageClick(url)}
            style={{
              width: "400px",
              height: "200px",
              objectFit: "cover",
              borderRadius: "12px",
              border: "2px solid #e1e5e9",
              boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
              cursor: "pointer",
              transition: "transform 0.2s",
            }}
            onMouseOver={(e) => (e.currentTarget.style.transform = "scale(1.05)")}
            onMouseOut={(e) => (e.currentTarget.style.transform = "scale(1)")}
          />
        ))}
      </Row>

      {selectedImage && (
        <div
          onClick={handleClose}
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0,0,0,0.8)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000,
          }}
        >
          <img
            src={selectedImage}
            alt="enlarged"
            onClick={(e) => e.stopPropagation()}
            style={{
              maxWidth: "90%",
              maxHeight: "90%",
              borderRadius: "12px",
              boxShadow: "0 4px 12px rgba(0,0,0,0.5)",
            }}
          />
        </div>
      )}
    </div>
  );
};

export default PropertyImages;
