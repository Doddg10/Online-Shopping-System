import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { AdminLogin } from "./Admin/AdminLogin";
import { AdminRegister } from "./Admin/AdminRegister";
import { AdminDashboard } from "./Admin/AdminDashboard";
import { CreateSellingCompany } from "./Admin/CreateSellingCompany";
import { CreateShippingCompany } from "./Admin/CreateShippingCompany";
import { ListSellingCompanies } from "./Admin/ListSellingCompanies";
import { ListShippingCompanies } from "./Admin/ListShippingCompanies";
import { ListCustomers } from "./Admin/ListCustomers";


import { SellingCompanyDashboard } from "./SellingCompany/SellingCompanyDashboard";
import { SellingCompanyLogin } from "./SellingCompany/SellingCompanyLogin";
import { SellingCompanyRegister } from "./SellingCompany/SellingCompanyRegister";
import { SellingCompanyLogout } from "./SellingCompany/SellingCompanyLogout";
import { SellingCompanyAddProduct } from "./SellingCompany/SellingCompanyAddProduct";
import { ViewCurrentProduct } from "./SellingCompany/ViewCurrentProduct";
import { ViewSoldProduct } from "./SellingCompany/ViewSoldProduct";


import { ShippingCompanyDashboard } from "./ShippingCompany/ShippingCompanyDashboard";
import { ShippingCompanyLogin } from "./ShippingCompany/ShippingCompanyLogin";
import { ShippingCompanyLogout } from "./ShippingCompany/ShippingCompanyLogout";
import { ViewOrdersInRegion } from "./ShippingCompany/ViewOrdersInRegion";
import { ProcessOrder } from "./ShippingCompany/ProcessOrder";


import { CustomerDashboard } from "./Customer/CustomerDashboard";
import { CustomerLogin } from "./Customer/CustomerLogin";
import { CustomerLogout } from "./Customer/CustomerLogout";
import { CustomerRegister } from "./Customer/CustomerRegister";
import { ViewCurrentOrders } from "./Customer/ViewCurrentOrders";
import { ViewPastOrders } from "./Customer/ViewPastOrders";
import { ViewNotifications } from "./Customer/ViewNotifications";
import { ViewCart } from "./Customer/ViewCart";
import { MakeOrder} from "./Customer/MakeOrder";
import { AddProductToCart} from "./Customer/AddProductToCart";
import { CustomerPurchase} from "./Customer/CustomerPurchase";



import "./App.css";

function Home() {
  return (
    <div className="auth-form-container">
      <h2>Welcome to the Home page!</h2>
      <Link to="/admin/login">Admin
      </Link>
      <Link to="/customer/login">Customer
      </Link>
      <Link to="/sellingcompany/login">Selling Company
      </Link>
      <Link to="/shippingcompany/login">Shipping Company
       
      </Link>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/admin/register" component={AdminRegister} />
        <Route path="/admin/dashboard" component={AdminDashboard} />
        <Route path="/admin/createsellingcompany"component={CreateSellingCompany}/>
        <Route path="/admin/ListSellingCompanies"component={ListSellingCompanies}/>
        <Route path="/admin/login" component={AdminLogin} />
        <Route path="/admin/CreateShippingCompany"component={CreateShippingCompany}/>
        <Route path="/admin/ListShippingCompanies"component={ListShippingCompanies} />
        <Route path="/admin/ListCustomers" component={ListCustomers}/>


        <Route path="/sellingcompany/dashboard" component={SellingCompanyDashboard} />
        <Route path="/sellingcompany/login" component={SellingCompanyLogin} />
        <Route path="/sellingcompany/logout" component={SellingCompanyLogout} />
        <Route path="/sellingcompany/Register" component={SellingCompanyRegister} />
        <Route path="/sellingCompany/addproduct" component={SellingCompanyAddProduct} />
        <Route path="/sellingcompany/currentproducts" component={ViewCurrentProduct} />
        <Route path="/sellingcompany/soldproducts" component={ViewSoldProduct} />


        <Route path="/shippingcompany/dashboard" component={ShippingCompanyDashboard} />
        <Route path="/shippingcompany/login" component={ShippingCompanyLogin} />
        <Route path="/shippingcompany/logout" component={ShippingCompanyLogout} />
        <Route path="/shippingcompany/ordersInregion" component={ViewOrdersInRegion} />
        <Route path="/shippingcompany/process" component={ProcessOrder} />


        <Route path="/customer/dashboard" component={CustomerDashboard} />
        <Route path="/customer/login" component={CustomerLogin} />
        <Route path="/customer/logout" component={CustomerLogout} />
        <Route path="/cutomer/Register" component={CustomerRegister} />
        <Route path="/customer/currentorders" component={ViewCurrentOrders} />
        <Route path="/customer/pastorders" component={ViewPastOrders} />
        <Route path="/customer/notifications" component={ViewNotifications} />
        <Route path="/customer/cart" component={ViewCart} />
        <Route path="/customer/makeorder" component={MakeOrder} />
        <Route path="/customer/addproductTocart" component={AddProductToCart} />
        <Route path="/customer/purchase" component={CustomerPurchase} />
        
      </Switch>
    </Router>
  );
}

export default App;
