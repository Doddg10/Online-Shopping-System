import React from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

export const CustomerLogout = () => {
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/logout"
      );
      console.log(response);
      sessionStorage.removeItem("customerId"); // Remove the customerId from sessionStorage
    history.push("/"); // Redirect to home page on successful logout
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Logout customer</h2>
      <form className="logout-form" onSubmit={handleSubmit}>
        <button type="submit">Log Out</button>
      </form>
    </div>
  );
};