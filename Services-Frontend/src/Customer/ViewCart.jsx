import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export const ViewCart = () => {
  const [orderr, setOrderr] = useState({});
  const customerId = sessionStorage.getItem("customerId") || 0; // Get the customer ID from session storage, or provide a default value

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/order/${customerId}/viewCart`);
        setOrderr(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, [customerId]);

  return (
    <div className="auth-form-container">
      <h2>Your cart</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Products IDs</th>
            <th>Selling Companies</th>
            <th>Address</th>
            <th>Total Price</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{orderr.id}</td>
            <td>{orderr.productsIDs}</td>
            <td>{orderr.sellingCompanyIDs}</td>
            <td>{orderr.address}</td>
            <td>{orderr.totalPrice}</td>
          </tr>
        </tbody>
      </table>
      <Link to="/customer/purchase">
          <button>Purchase</button>
        </Link>
    </div>
  );
};