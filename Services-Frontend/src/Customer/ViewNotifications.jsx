import React, { useState, useEffect } from "react";
import axios from "axios";

export const ViewNotifications = () => {
    const [notify, setnotify] = useState([]);
  const customerId = sessionStorage.getItem("customerId") ; // Get the company name from session storage, or provide a default value

  console.log("customerId", customerId);
  useEffect(() => {
    const fetchData = async () => {
      try {
  
        const response = await axios.get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/${customerId}/getNotifications`);

      
        setnotify(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

 return (
    <div className="auth-form-container">
    <h2>Your Notifications</h2>
    <table className="company-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Message</th>
        
        </tr>
      </thead>
      <tbody>
        {notify.map((order) => (
          <tr key={order.id}>
            <td>{order.id}</td>
            <td>{order.message}</td>
           
            
          </tr>
        ))}
      </tbody>
    </table>
  </div>
  );
}