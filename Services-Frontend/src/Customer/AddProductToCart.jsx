import React from "react";
import axios from "axios";
import { useHistory, useLocation } from "react-router-dom";


export const AddProductToCart = () => {
  const location = useLocation();
  const productId = new URLSearchParams(location.search).get("productId");
  console.log("productId",productId);

  const customerId = sessionStorage.getItem("customerId") ; // Get the company name from session storage, or provide a default value
  console.log("customerId", customerId);


  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/order/${customerId}/addProduct/${productId}`
      );
      console.log(response);
      history.push("/customer/makeorder"); // Redirect to makeorder on successful 
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Process Order</h2>
      <form className="logout-form" onSubmit={handleSubmit}>
        <button type="submit">Add</button>
      </form>
    </div>
  );
};
