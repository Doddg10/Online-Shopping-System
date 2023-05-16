import React, { useState } from 'react';
import axios from 'axios';

export const AdminRegister = (props) => {
  const [adminEmail, setadminEmail] = useState('');
  const [password, setPassword] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { adminEmail, password };
    try {
      const response = await axios.post(
        'http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/register',
        data
      );
      console.log(response);
      setResponse(response.data); // Set response state
      if (props.onFormSwitch) {
        props.onFormSwitch('AdminLogin');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleSubmit}>
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
        <button type="submit">Register</button>
      </form>
      {response && <p> {response}</p>} {/* Render response if it exists */}
    </div>
  );
};
