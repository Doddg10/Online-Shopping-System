import React, { useState, useEffect } from "react";
import axios from "axios";

export const ViewCurrentProduct = () => {
  const [products, setProducts] = useState([]);
  const companyName = sessionStorage.getItem("companyName") ; // Get the company name from session storage, or provide a default value

  console.log("companyName", companyName);
  useEffect(() => {
    const fetchData = async () => {
      try {
  
        const response = await axios.get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/${companyName}/products`);

      
        setProducts(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

 return (
    <div className="auth-form-container">
    <h2>Products currently offered for sale</h2>
    <table className="company-table">
      <thead>
        <tr>
          <th>Name</th>
          <th>Price</th>
          <th>Quantity</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => (
          <tr key={product.id}>
            <td>{product.productName}</td>
            <td>{product.price}</td>
            <td>{product.quantity}</td>
            
          </tr>
        ))}
      </tbody>
    </table>
  </div>
  );
}