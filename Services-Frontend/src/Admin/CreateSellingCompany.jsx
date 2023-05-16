import React, { useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

export const CreateSellingCompany = () => {
  const [sellingCompanyName, setsellingCompanyName] = useState("");
  const history = useHistory();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(sellingCompanyName);

    axios
      .put("http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/createSellingCompany", { sellingCompanyName })
      .then((response) => {
        console.log(response.data);
        history.push("/admin/dashboard"); // Redirect to /admin/dashboard on successful response
      })
      .catch((error) => {
        console.log(error);
        // add logic to handle error response from server
      });
  };

  return (
    <div className="auth-form-container">
      <h2>Create Selling Company</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="sellingCompanyName">Company Name:</label>
        <input
          type="text"
          id="sellingCompanyName"
          name="sellingCompanyName"
          value={sellingCompanyName}
          onChange={(e) => setsellingCompanyName(e.target.value)}
        />
        <button type="submit">Create Company</button>
      </form>
    </div>
  );
};
