import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {  getMyProperties, deleteProperty, toggleAvailability } from "../services/propertyService";
import CustomButton from "../components/CustomButton";
import Container from "../components/Container";
import Row from "../components/Row";
import Column from "../components/Column";
import SizedBox from "../components/SizedBox";
import UserContext from "../utils/UserContext";
import AppRoutes from "../utils/AppRoutes";

const MyPropertiesPage = () => {
  const [properties, setProperties] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const ownerId = UserContext.getLoggedInUser().id;

  console.log("Owner ID:", ownerId);
  

  useEffect(() => {
    if (!ownerId) {
      navigate(AppRoutes.login, { state: { from: AppRoutes.myProperties } });
      return;
    }
    fetchProperties();

    console.log(data);
  }, [ownerId, navigate]);

  const fetchProperties = async () => {
    try {
      setLoading(true);
      const data = await getMyProperties(ownerId);
      
      
      setProperties(data);
    } catch (err) {
      setError("Failed to load your properties.");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (propertyId) => {
    if (!window.confirm("Are you sure you want to delete this property?")) return;
    try {
      await deleteProperty(propertyId);
      alert("Property deleted successfully!");
      setProperties(properties.filter((p) => p.propertyId !== propertyId));
    } catch {
      alert("Failed to delete property.");
    }
  };

  const handleToggleAvailability = async (propertyId) => {
    try {
      const updated = await toggleAvailability(propertyId);
      setProperties(
        properties.map((p) => (p.propertyId === propertyId ? updated : p))
      );
    } catch {
      alert("Failed to update availability.");
    }
  };

  if (loading) return <p>Loading your properties...</p>;
  if (error) return <p>{error}</p>;

  return (
    <>
      <h2>My Properties</h2>
      <SizedBox height={20} />
      {properties.length === 0 ? (
        <p>You have not added any properties yet.</p>
      ) : (
        <Column style={{ gap: 20 }}>
          {properties.map((property) => (
            <div
              key={property.propertyId}
              style={{
                border: "1px solid #ddd",
                padding: 16,
                borderRadius: 8,
                backgroundColor: "#fafafa",
              }}
            >
              <Row justify="space-between" align="center">
                <Column>
                  <h3>{property.propertyName}</h3>
                  <p>{property.propertyDescription}</p>
                  <p>
                    Status:{" "}
                    <b
                      style={{
                        color: property.isAvailable ? "green" : "red",
                      }}
                    >
                      {property.isAvailable ? "Available" : "Not Available"}
                    </b>
                  </p>
                </Column>
                <Row style={{ gap: 12 }}>
                  <CustomButton
                    onClick={() =>
                      navigate(AppRoutes.editProperty)
                    }
                  >
                    Edit
                  </CustomButton>
                  <CustomButton
                    onPress={() => handleToggleAvailability(property.propertyId)}
                    style={{ backgroundColor: "#f0ad4e" }}
                  >
                    Toggle Availability
                  </CustomButton>
                  <CustomButton
                    onPress={() => handleDelete(property.propertyId)}
                    style={{ backgroundColor: "#d9534f" }}
                  >
                    Delete
                  </CustomButton>
                </Row>
              </Row>
            </div>
          ))}
        </Column>
      )}
    </>
  );
};

export default MyPropertiesPage;
