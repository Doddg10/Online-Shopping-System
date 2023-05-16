import React from "react";
import { Link } from "react-router-dom";


export const CustomerDashboard = () => {
  return (
   

    <div className="auth-form-container">
  <h2>Welcome customer Dashboard</h2>
  <div className="button-container">
        <Link to="/customer/currentorders">
          <button>View current orders</button>
        </Link>
        <Link to="/customer/pastorders">
          <button>View past orders</button>
        </Link>
        <Link to="/customer/notifications">
          <button>View notifications</button>
        </Link>
        <Link to="/customer/cart">
          <button>View cart</button>
        </Link>
        <Link to="/customer/makeorder">
          <button>Make order</button>
        </Link>
      
        <Link to="/customer/logout">
          <button>Logout</button>
        </Link>
        
      </div>
    </div>
  );
};