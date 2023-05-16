import React, { useState, useEffect } from "react";

export const ListCustomers = () => {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/AdminProject-1.0-SNAPSHOT/api/admin/getAllCustomers")
      .then((res) => res.json())
      .then((data) => setCustomers(data));
  }, []);

  return (
    <div className="auth-form-container">
      <h2>List of Customers</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((customer) => (
            <tr key={customer.name}>
              <td>{customer.id}</td>
              <td>{customer.name}</td>
              <td>{customer.email}</td>
              <td>{customer.password}</td>
              <td>{customer.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
