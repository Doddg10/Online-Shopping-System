import React, { useState, useEffect } from "react";
import axios from "axios";

export const ViewSoldProduct = () => {
  const [orders, setOrders] = useState([]);
  const companyName = sessionStorage.getItem("companyName") ; // Get the company name from session storage, or provide a default value

  console.log("companyName", companyName);
  useEffect(() => {
    axios
      .get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/${companyName}/orders`)
      .then((response) => {
        setOrders(response.data.orders);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="auth-form-container">
      <h2>sold products, information about the customers who bought each product </h2>
      <h2>and the shipping company.</h2>
      <div className="orders-list">
        {orders.map((order, index) => (
          <div key={order.orderDetails} className="order" style={{ borderBottom: '2px solid black', paddingTop: '2rem', paddingBottom: '2rem' }}>
          
            <h3>{order.orderDetails}</h3>
            <h3>{order.customerDetails}</h3>
            <table className="company-table">
              <thead>
                <tr>
                  <th>Product ID</th>
                  <th>Name</th>
                  <th>Price</th>
                </tr>
              </thead>
              <tbody>
                {order.products.map((product) => (
                  <tr key={product.id}>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ))}
      </div>
    </div>
  );
};
