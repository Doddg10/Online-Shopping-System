import React from "react";
import { Link } from "react-router-dom";


export const AdminDashboard = () => {
  return (
    <div className="auth-form-container">
  <h2>Welcome to the Admin Dashboard</h2>
  <div className="button-container">
        <Link to="/admin/createsellingcompany">
          <button>create selling company</button>
        </Link>
        <Link to="/admin/createshippingcompany">
          <button>create shipping company</button>
        </Link>
        <Link to="/admin/Listcustomers">
          <button>List customer accounts</button>
        </Link>
        <Link to="/admin/Listsellingcompanies">
          <button>List selling companies</button>
        </Link>
        <Link to="/admin/Listshippingcompanies">
          <button>List shipping companies</button>
        </Link>
        <Link to="/">
          <button>Logout</button>
        </Link>
      </div>
    </div>
  );
};