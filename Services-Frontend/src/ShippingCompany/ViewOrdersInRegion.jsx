import React, { useState, useEffect } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";
import { Link } from "react-router-dom";

export const ViewOrdersInRegion = () => {
  const [orders, setOrders] = useState([]);
  
  const companyName = sessionStorage.getItem("companyName");
  console.log("companyName", companyName);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/shippingCompany/${companyName}/getOrdersInRegion`
        );
        setOrders(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  const history = useHistory();

  const handleAdd = (id) => {
    console.log("orderId", id);
   
    
    history.push({
      pathname: "/shippingcompany/process",
      search: `orderId=${id}`,
    });
  };

  return (
    <div className="auth-form-container">
      <h2>List of current orders in region</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>CustomerId</th>
            <th>ProductsIDs</th>
            <th>SellingCompanyIDs</th>
            
            <th>Address</th>
            <th>TotalPrice</th>
            
            <th>Process order</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id}>
              <td>{order.id}</td>
              <td>{order.customerId}</td>
              <td>{order.productsIDs}</td>
              <td>{order.sellingCompanyIDs}</td>
          
              <td>{order.address}</td>
              <td>{order.totalPrice}</td>
             
              <td>
                <button onClick={() => handleAdd(order.id)}>Ship Order</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/shippingcompany/dashboard">
          <button>go to dashboard</button>
        </Link>
    </div>
  );
};
