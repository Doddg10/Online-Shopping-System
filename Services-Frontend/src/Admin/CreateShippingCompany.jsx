import React, { useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

export const CreateShippingCompany = () => {
  const [shippingCompanyName, setshippingCompanyName] = useState("");
  const [region, setRegion] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { shippingCompanyName, region };
    try {
      const response = await axios.post(
        "http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/createShippingCompany",
        data,
        { headers: { "Content-Type": "application/json" } }
      );
      console.log(response.data);
      history.push("/admin/dashboard"); // redirect to /admin/dashboard
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Create Shipping Company</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="shippingCompanyName">Company Name:</label>
        <input
          type="text"
          id="shippingCompanyName"
          name="shippingCompanyName"
          value={shippingCompanyName}
          onChange={(e) => setshippingCompanyName(e.target.value)}
        />
        <label htmlFor="region">region:</label>
        <input
          type="text"
          id="region"
          name="region"
          value={region}
          onChange={(e) => setRegion(e.target.value)}
        />
        <button type="submit">Create Company</button>
      </form>
    </div>
  );
};
