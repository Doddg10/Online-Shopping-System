package Order;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.jboss.weld.context.ejb.Ejb;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/order")
public class OrderController {
    @EJB
    private OrderBean orderBean;

    @GET
    @Path("/getAll")
    public List<Orderr> getAllOrders()
    {
        return orderBean.getAllOrders();
    }


    @Path("/{customerId}/addProduct/{productId}")
    @PUT
    public String addProductToCart(@PathParam("productId") int productId,@PathParam("customerId") int customerId)
    {
       return  orderBean.addProductToCart(productId,customerId);
    }


    @Path("/{customerId}/purchase")
    @PUT
    public String purchaseCart(@PathParam("customerId") int customerId)
    {
       return orderBean.purchaseCart(customerId);

    }

    @Path("/{customerId}/viewCart")
    @GET
    public Orderr viewCart(@PathParam("customerId") int customerId)
    {
        return orderBean.viewCart(customerId);

    }
}
