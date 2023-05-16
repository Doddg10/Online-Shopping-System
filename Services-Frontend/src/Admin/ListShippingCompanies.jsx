import React, { useState, useEffect } from "react";
import axios from "axios";

export const ListShippingCompanies = () => {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/getAllShippingCompanies")
      .then((response) => {
        setCompanies(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="auth-form-container">
      <h2>List of Shipping Companies</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>shippingCompanyName</th>
            <th>Region</th>
            <th>Password</th>
          </tr>
        </thead>
        <tbody>
          {companies.map((company) => (
            <tr key={company.shippingCompanyName}>
              <td>{company.shippingCompanyName}</td>
              <td>{company.region}</td>
              <td>{company.password}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
