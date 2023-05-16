import React, { useState } from 'react';
import axios from 'axios';

export const CustomerRegister = (props) => {
  const [name, setname] = useState('');
  const [email, setemail] = useState('');
  const [password, setpassword] = useState('');
  const [address, setaddress] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { name,email,password,address };
    try {
      const response = await axios.post(
        'http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/register',
        data
      );
      console.log(response);
      setResponse(response.data); // Set response state
      if (props.onFormSwitch) {
        props.onFormSwitch('CustomerLogin');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="name">Name</label>
        <input
          type="name"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setname(e.target.value)}
          placeholder="name"
        />
        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setemail(e.target.value)}
          placeholder="youremail@gmail.com"
        />
         <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setpassword(e.target.value)}
          placeholder="****"
        />
         <label htmlFor="address">address</label>
        <input
          type="address"
          id="address"
          name="address"
          value={address}
          onChange={(e) => setaddress(e.target.value)}
          placeholder="address"
        />
        <button type="submit">Register</button>
      </form>
      {response && <p> {response}</p>} {/* Render response if it exists */}
    </div>
  );
};
