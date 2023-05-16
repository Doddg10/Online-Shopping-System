import React from "react";
import axios from "axios";
import { useHistory, useLocation } from "react-router-dom";

export const ProcessOrder = () => {
  const location = useLocation();
  const orderId = new URLSearchParams(location.search).get("orderId");
  console.log("orderId", orderId);

  const companyName = sessionStorage.getItem("companyName"); // Get the company name from session storage, or provide a default value
  console.log("companyName", companyName);



  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/shippingCompany/${companyName}/processOrder/${orderId}`
      );
      console.log(response);
      history.push("/shippingcompany/ordersInregion");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Process Order</h2>
      <form className="logout-form" onSubmit={handleSubmit}>
      <button type="submit">Process</button>
      </form>
    </div>
  );
};
