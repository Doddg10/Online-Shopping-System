import React, { useState, useEffect } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
export const MakeOrder = () => {
    const [products, setProducts] = useState([]);
  


  useEffect(() => {
    const fetchData = async () => {
      try {
  
        const response = await axios.get(`http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer/getAllProducts`);

      
        setProducts(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);
  const history = useHistory();

  const handleAdd = (id) => {
    console.log("productId", id);
   
    
    history.push({
      pathname: "/customer/addproductTocart",
      search: `productId=${id}`,
    });
  };

 return (
    <div className="auth-form-container">
      <h2>All Products</h2>
      <table className="company-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Add to Cart</th>
           
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.productName}</td>
              <td>{product.price}</td>
              <td>{product.quantity}</td>
             
              <td>
                <button onClick={() => handleAdd(product.id)}>Add</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/customer/dashboard">
          <button>go to dashboard</button>
        </Link>
                   
    </div>
   
  );
}