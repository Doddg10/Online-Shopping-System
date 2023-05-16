import React, { useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

export const SellingCompanyAddProduct = (props) => {
  const [productName, setProductName] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState("");
  const companyName = sessionStorage.getItem("companyName") ; // Get the company name from session storage, or provide a default value

  console.log("companyName", companyName);

  const history = useHistory(); // Create a history object

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { 
      productName, 
      price, 
      quantity
    };
  
    try {
      const response = await axios.put(
        `http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany/addProduct/${companyName}`,
        data
      );
      console.log(response.data);
      history.push("/sellingcompany/dashboard"); // Redirect to "/sellingcompany/dashboard"
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="auth-form-container">
      <h2>New Product</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="productName">Enter Product Name</label>
        <input
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
          type="text"
          placeholder="Product Name"
          id="productName"
          name="productName"
        />

        <label htmlFor="price">Enter Product Price</label>
        <input
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          type="text"
          placeholder="Price"
          id="price"
          name="price"
        />

        <label htmlFor="quantity">Enter Product Quantity</label>
        <input
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          type="text"
          placeholder="Quantity"
          id="quantity"
          name="quantity"
        />

        <button>Add</button>
      </form>
    </div>
  );
};
