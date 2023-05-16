import React from "react";
import { Link } from "react-router-dom";


export const SellingCompanyDashboard = () => {
  return (
   

    <div className="auth-form-container">
  <h2>Welcome to the Selling Company Dashboard</h2>
  <div className="button-container">
        <Link to="/sellingcompany/currentproducts">
          <button>View current products</button>
        </Link>
        <Link to="/sellingcompany/soldproducts">
          <button>View sold products</button>
        </Link>
        <Link to="/sellingcompany/addproduct">
          <button>Add product</button>
        </Link>
        <Link to="/sellingcompany/logout">
          <button>Logout</button>
        </Link>
        
      </div>
    </div>
  );
};