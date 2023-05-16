import React, { useState } from "react";
import axios from "axios";
import { Link, useHistory } from "react-router-dom";

export const SellingCompanyLogin = () => {
  const [sellingCompanyName, setsellingCompanyName] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { sellingCompanyName, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/login",
        data
      );
      console.log("companyName:", sellingCompanyName);
      sessionStorage.setItem("companyName", sellingCompanyName);
      history.push("/sellingCompany/dashboard");
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div className="auth-form-container">
      <h2>Login as Selling Company</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="sellingCompanyName">Name</label>
        <input
          type="text"
          id="sellingCompanyName"
          name="sellingCompanyName"
          value={sellingCompanyName}
          onChange={(e) => setsellingCompanyName(e.target.value)}
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
        <p>
          Don't have an account?{" "}
          <Link to="/sellingcompany/Register">Register here</Link>
        </p>
      </form>
    </div>
  );
};
