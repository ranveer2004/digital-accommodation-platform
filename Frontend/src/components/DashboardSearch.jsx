import React, { useState } from "react";
import Row from "../components/Row";
import CustomButton from "../components/CustomButton";
import Colors from "../utils/Colors";
import { TextField } from "@mui/material";

const DashboardSearch = ({ onSearch }) => {
  const [query, setQuery] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (query.trim()) onSearch(query);
  };

  return (
    <form onSubmit={handleSubmit} style={{
      display: "flex",
      gap: "10px",
      margin: "20px auto",
      maxWidth: "600px",
    }}>
      <TextField 
        type="text"
        placeholder="Search by property name, city or locality..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
      />
      {/* <input
        type="text"
        placeholder="Search by property name, city or locality..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        style={{
          flex: 1,
          padding: "12px 16px",
          border: "2px solid #e1e5e9",
          borderRadius: "8px",
          fontSize: "14px",
          backgroundColor: Colors.background
        }}
      /> */}
      <CustomButton 
        title="Search" 
        type="submit" 
        width={150}
        fullWidth={false}
      />
    </form>
  );
};

export default DashboardSearch;