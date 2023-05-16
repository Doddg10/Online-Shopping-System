import React from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

export const SellingCompanyLogout = () => {
  const history = useHistory();

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    const response = await axios.post(
      "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/logout"
    );
    console.log(response);
    sessionStorage.removeItem("companyName"); // Remove the companyName from sessionStorage
    history.push("/"); // Redirect to home page on successful logout
  } catch (error) {
    console.error(error);
  }
};

  return (
    <div className="auth-form-container">
      <h2>Logout Selling Company</h2>
      <form className="logout-form" onSubmit={handleSubmit}>
        <button type="submit">Log Out</button>
      </form>
    </div>
  );
};
