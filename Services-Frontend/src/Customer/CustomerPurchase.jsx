import React from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";


export const CustomerPurchase = () => {
  
 

  const customerId = sessionStorage.getItem("customerId") ; // Get the company name from session storage, or provide a default value
  console.log("customerId", customerId);


  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/order/${customerId}/purchase`
      );
      console.log(response);
      history.push("/customer/dashboard"); // Redirect to dashboard on successful 
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>If you finshed your order press on purchase </h2>
      <form className="logout-form" onSubmit={handleSubmit}>
        <button type="submit">Purchase</button>
      </form>
    </div>
  );
};
