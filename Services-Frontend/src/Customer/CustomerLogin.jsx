import React, { useState } from "react";
import axios from "axios";
import { Link, useHistory } from "react-router-dom";

export const CustomerLogin = () => {
  const [email, setemail] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { email, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/login",
        data
      );
      // Extract customerId from response
      const customerId = response.data.split(",")[1].trim(); 
       // Save customerId in session storage
      sessionStorage.setItem("customerId", customerId);
      console.log(customerId);
      console.log(response);
      history.push("/customer/dashboard"); // Redirect to customer Dashboard page on successful login
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div className="auth-form-container">
      <h2>Login as cutomer</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          type="text"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setemail(e.target.value)}
          placeholder="Your email"
        />
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="**"
        />
        <button type="submit">Log In</button>
        <p>
          Don't have an account?{" "}
          <Link to="/cutomer/Register">Register here</Link>
        </p>
      </form>
    </div>
  );
};