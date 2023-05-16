import React, { useState } from "react";
import axios from "axios";
import {  useHistory } from "react-router-dom";

export const ShippingCompanyLogin = () => {
  const [shippingCompanyName, setshippingCompanyName] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { shippingCompanyName, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/shippingCompany/login",
        data
      );
     
      console.log("companyName:",shippingCompanyName);
      sessionStorage.setItem("companyName", shippingCompanyName);
      history.push("/ShippingCompany/dashboard"); // Redirect to SellingCompanyDashboard page on successful login
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Login as Shipping Company</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="shippingCompanyName">Name</label>
        <input
          type="text"
          id="shippingCompanyName"
          name="shippingCompanyName"
          value={shippingCompanyName}
          onChange={(e) => setshippingCompanyName(e.target.value)}
          placeholder="Your Company Name"
        />
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="****"
        />
        <button type="submit">Log In</button>
        
      </form>
    </div>
  );
};
