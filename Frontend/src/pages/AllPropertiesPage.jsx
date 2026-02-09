import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllProperties } from '../services/propertyService';
import Container from "../components/Container";
import Column from "../components/Column";
import Row from "../components/Row";
import SizedBox from "../components/SizedBox";
import PropertyCard from '../components/PropertyCard';
import Colors from '../utils/Colors';
import { Backdrop, Button, CircularProgress } from '@mui/material';
import LocalStorageHelper from '../utils/LocalStorageHelper';
import Navigate from '../services/NavigationService';
import AppRoutes from '../utils/AppRoutes';

/**
 * AllPropertiesPage component
 * 
 * - Fetches and displays all available properties.
 * - Allows users to click on a row to view detailed information 
 *   about a specific property.
 * - Uses React Router for navigation.
 */

const AllPropertiesPage = () => {

  // State to store list of properties
  const [properties, setProperties] = useState([]);

  // State to handle loading UI
  const [isLoading, setIsLoading] = useState(true);

  const [error, setError] = useState(null);

  // React Router's navigation hook
  const navigate = useNavigate();

  /**
   * useEffect hook to fetch all properties from backend
   * when the component is mounted.
   */
  
  useEffect(() => {
    const fetchProperties = async () => {
      try {
        if(LocalStorageHelper.toFetchAllProperty()) {
          const response = await getAllProperties();
  
          console.log("All Prop Response : ", response);
          
          setProperties(response);    // Save response to state
        } else {
          console.log("Prop fetched locally....");
          setProperties(LocalStorageHelper.getAllProperty());
        }
      } 
      catch (error) {
        console.error("Error fetching properties:", error);
      } 
      finally {
        setIsLoading(false);
      }
    };

    fetchProperties();
  }, []);

  return (
    <>
      {/* <Button
        onClick={() => {
          LocalStorageHelper.getPropertyById("prop-7b7ed51e47404b2597d41d1d16c0f249");
        }}
      >
        Get Property by ID
      </Button> */}

      { isLoading && (
        <Backdrop open={isLoading} >
          <CircularProgress />
        </Backdrop>
      )}
      
      {!isLoading && properties.length > 0 && (
          
          <Row style={{ flexWrap: "wrap", gap: "20px", justifyContent: "center" }}>
          
            {properties.map((property) => (
              
              <PropertyCard
                key={property.propertyId}
                property={property}
                onClick={ () => Navigate.to({ path: AppRoutes.showPropertyById_param(property.propertyId) }) }
              />

            ))}
          
          </Row>
        )}
    </>

    // <Container maxWidth={"auto"}>
    //   <Column align="center">

    //     {/* <h2 style={{ color: "white", marginBottom: "20px" }}>All Properties</h2> */}
      
    //     {/* <SizedBox height={16} /> */}
      
    //     {/* {isLoading && <p style={{ color: Colors.textOrange }}>Loading properties...</p>} */}
      
    //     {/* {error && <p style={{ color: "red" }}>{error}</p>} */}
      
    //     {!isLoading && properties.length === 0 && (
    //       <p style={{ color: "white" }}>No properties found.</p>
    //     )}.

    //     { isLoading && (
    //       <Backdrop>
    //         <CircularProgress />
    //       </Backdrop>
    //     )}
      
        
    //   </Column>
    // </Container>
  );
};

export default AllPropertiesPage;