import React from "react";
import { Link } from "react-router-dom";


export const ShippingCompanyDashboard = () => {
  return (
   

    <div className="auth-form-container">
  <h2>Welcome to the Shipping Company Dashboard</h2>
  <div className="button-container">
        <Link to="/shippingcompany/ordersInregion">
          <button>View orders in region</button>
        </Link>
       
        <Link to="/shippingcompany/logout">
          <button>Logout</button>
        </Link>
        
      </div>
    </div>
  );
};