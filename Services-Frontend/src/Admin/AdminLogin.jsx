import React, { useState } from "react";
import axios from "axios";
import { Link, useHistory } from "react-router-dom";


export const AdminLogin = () => {
  const [adminEmail, setadminEmail] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { adminEmail, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/login",
        data
      );
      console.log(response);
      history.push("/admin/dashboard"); // Redirect to AdminDashboard page on successful login
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Login</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          type="adminEmail"
          id="adminEmail"
          name="adminEmail"
          value={adminEmail}
          onChange={(e) => setadminEmail(e.target.value)}
          placeholder="youremail@gmail.com"
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
        <p>Don't have an account? <Link to="/admin/register">Register here</Link></p>
      </form>
      
    </div>
  );
};
