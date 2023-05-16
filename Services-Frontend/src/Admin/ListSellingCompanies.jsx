import React, { useState, useEffect } from "react";


export const ListSellingCompanies = () => {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/getAllSellingCompanies")
      .then((res) => res.json())
      .then((data) => setCompanies(data));
  }, []);

  return (
    <div className="auth-form-container">
      <h2>List of Selling Companies</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Products</th>
          </tr>
        </thead>
        <tbody>
          {companies.map((company) => (
            <tr key={company.sellingCompanyName}>
              <td>{company.sellingCompanyName}</td>
              <td>{company.password}</td>
              <td>{JSON.stringify(company.products)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
