// function Home() {
//     return (
//         <>
//             <h1>Home Page (For All Visitors)</h1>
//             <h3>Public page (accessible without login)</h3>
//             <h3>Hero Section (Banner) Catchy headline like: “Find your dream home to buy or rent”</h3>
//             <h3>Search bar for location, property type, budget</h3>
//         </>
//     );
// }

// export default Home;

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllProperties } from "../services/propertyService";
import Container from "../components/Container";
import Row from "../components/Row";
import Colors from "../utils/Colors";
import AppRoutes from "../utils/AppRoutes";
import { Backdrop, CircularProgress } from "@mui/material";
import LocalStorageHelper from "../utils/LocalStorageHelper";
import PropertyCard from "../components/PropertyCard";

const HomePage = () => {
  const [properties, setProperties] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProperties = async () => {
      try {
        if (LocalStorageHelper.toFetchAllProperty()) {
          const response = await getAllProperties();
          setProperties(response);
        } else {
          setProperties(LocalStorageHelper.getAllProperty());
        }
      } catch (error) {
        console.error("Error fetching properties:", error);
      } finally {
        setIsLoading(false);
      }
    };
    fetchProperties();
  }, []);

  // Filter properties based on search
  const filteredProperties = properties.filter((p) =>
    `${p.propertyName} ${p.location?.city} ${p.location?.state} ${p.location?.locality}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  return (
    <>
      {isLoading && (
        <Backdrop open={isLoading}>
          <CircularProgress style={{ color: Colors.textOrange }} />
        </Backdrop>
      )}

      {!isLoading && (
        <div style={{ padding: "20px" }}>
          {/* Search Bar */}
          <div style={{ textAlign: "center", marginBottom: "30px" }}>
            <h2 style={{ color: Colors.textOrange, marginBottom: "20px" }}>
              Browse Properties
            </h2>
            <input
              type="text"
              placeholder="Search by Name, City, or Locality"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              style={{
                padding: "12px 18px",
                borderRadius: "8px",
                border: "2px solid #e1e5e9",
                width: "60%",
                fontSize: "16px",
                outline: "none",
              }}
            />
          </div>

          {/* Properties Grid */}
          {filteredProperties.length > 0 ? (
            <Row style={{ flexWrap: "wrap", gap: "20px", justifyContent: "center" }}>
              {filteredProperties.map((property) => (
                <PropertyCard
                  key={property.propertyId}
                  property={property}
                  onClick={() => navigate(`/properties/${property.propertyId}`)}
                />
              ))}
            </Row>
          ) : (
            <p style={{ textAlign: "center", color: "white" }}>
              No properties found.
            </p>
          )}
        </div>
      )}
    </>
  );
};
export default HomePage;
