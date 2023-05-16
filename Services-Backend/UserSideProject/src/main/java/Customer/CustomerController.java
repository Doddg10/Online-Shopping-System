package Customer;
import Notification.Notification;
import Order.Orderr;
import Product.Product;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/customer")

public class CustomerController  {

    @EJB
    private CustomerBean customerBean;

    @POST
    @Path("/register")
    public String registerCustomer(Customer customer)
    {
        return customerBean.registerCustomer(customer);
    }

    @POST
    @Path("/login")
    public String loginCustomer(Customer customer, @Context HttpServletRequest request)
    {

       return this.customerBean.loginCustomer(customer, request);
    }
    @POST
    @Path("/logout")
    public String logoutCustomer(@Context HttpServletRequest request)
    {
      return customerBean.logoutCustomer(request);
    }

    @GET
    @Path("/getAll")
    public List<Customer> getAllCustomers()
    {
        return customerBean.getAllCustomers();
    }

    @GET
    @Path("/getAllProducts")
    public List<Product> getAllProducts()
    {
        return customerBean.getAllProducts();
    }

    @GET
    @Path("/{customerId}/getCurrentOrders")
    public List<Orderr> getAllCurrentOrders(@PathParam("customerId") int customerId)
    {
      return customerBean.getAllCurrentOrders(customerId);
    }

    @GET
    @Path("/{customerId}/getPastOrders")
    public List<Orderr> getAllPastOrders(@PathParam("customerId") int customerId)
    {
      return customerBean.getAllPastOrders(customerId);
    }
    @GET
    @Path("/{customerId}/getNotifications")
    public List<Notification> getAllNotifications(@PathParam("customerId") int customerId)
    {
        return customerBean.getAllNotifications(customerId);
    }

}
