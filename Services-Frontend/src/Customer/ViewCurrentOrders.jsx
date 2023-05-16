import React, { useState, useEffect } from "react";
import axios from "axios";

export const ViewCurrentOrders = () => {
    const [orders, setOrders] = useState([]);
  const customerId = sessionStorage.getItem("customerId") ; // Get the company name from session storage, or provide a default value

  console.log("customerId", customerId);
  useEffect(() => {
    const fetchData = async () => {
      try {
  
        const response = await axios.get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/${customerId}/getCurrentOrders`);

      
        setOrders(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

 return (
    <div className="auth-form-container">
    <h2>Your current orders</h2>
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
        {orders.map((order) => (
          <tr key={order.id}>
            <td>{order.id}</td>
            <td>{order.productsIDs}</td>
            <td>{order.sellingCompanyIDs}</td>
            <td>{order.address}</td>
            <td>{order.totalPrice}</td>
            
          </tr>
        ))}
      </tbody>
    </table>
  </div>
  );
}