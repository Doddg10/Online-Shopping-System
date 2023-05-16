import React, { useState } from 'react';
import axios from 'axios';

export const SellingCompanyRegister = (props) => {
  const [sellingCompanyName, setsellingCompanyName] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { sellingCompanyName };
    try {
      const response = await axios.post(
        'http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/registerCompanyName',
        data
      );
      console.log(response);
      setResponse(response.data); // Set response state
      if (props.onFormSwitch) {
        props.onFormSwitch('SellingCompanyLogin');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="sellingCompanyName">Selling Company Name</label>
        <input
          type="sellingCompanyName"
          id="sellingCompanyName"
          name="sellingCompanyName"
          value={sellingCompanyName}
          onChange={(e) => setsellingCompanyName(e.target.value)}
          placeholder="Selling Company Name"
        />
       
        <button type="submit">Register</button>
      </form>
      {response && <p> {response}</p>} {/* Render response if it exists */}
    </div>
  );
};
