package ShippingCompany;

import Order.Orderr;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.weld.context.ejb.Ejb;

import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/shippingCompany")

public class ShippingCompanyController {

  @EJB
  ShippingCompanyBean shippingCompanyBean;

    @POST
    @Path("/createAccount")
    public String createAccount(Shippingcompany sC) {
     return shippingCompanyBean.createAccount(sC);
        }
  @POST
  @Path("/login")
  public String loginShippingCompany(Shippingcompany sC, @jakarta.ws.rs.core.Context HttpServletRequest request) {
   return shippingCompanyBean.loginShippingCompany(sC,request);
  }

  @POST
  @Path("/logout")
  public String logoutShippingCompany(@jakarta.ws.rs.core.Context HttpServletRequest request) {
   return shippingCompanyBean.logoutShippingCompany(request);
  }
    @GET
    @Path("/getAll")
    public List<Shippingcompany> getAllShippingCompanies() {
        return shippingCompanyBean.getAllShippingCompanies();
    }

    @GET
    @Path("/{companyName}/getOrdersInRegion")
    public List<Orderr> getAllOrders_region(  @PathParam("companyName") String companyName) {
        return shippingCompanyBean.getAllOrders_region(companyName);
    }

    @PUT
    @Path("/{companyName}/processOrder/{orderId}")
    public String shipOrder(   @PathParam("companyName") String companyName,@PathParam("orderId") int orderId) {
      return shippingCompanyBean.shipOrder(companyName,orderId);
    }

}


