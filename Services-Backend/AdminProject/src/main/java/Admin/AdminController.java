package Admin;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/admin")

public class AdminController {
    @EJB
    AdminBean adminBean;

    @PUT
    @Path("/createSellingCompany")
    public String createSellingCompany(String requestBody) throws Exception {

        return adminBean.createSellingCompany(requestBody);
    }

    @POST
    @Path("/createShippingCompany")
    public String createShippingCompany(String requestBody) throws Exception {

       return adminBean.createShippingCompany(requestBody);
    }
    @GET
    @Path("/getAllSellingCompaniesWithoutAccount")
    public String getAllSellingCompaniesWithoutAccount() throws Exception {

      return adminBean.getAllSellingCompaniesWithoutAccount();
    }

    @GET
    @Path("/getAllSellingCompanies")
    public String getAllSellingCompanies() throws Exception {

        return adminBean.getAllSellingCompanies();
    }
    @GET
    @Path("/getAllShippingCompanies")
    public String getAllShippingCompanies() throws Exception {

        return adminBean.getAllShippingCompanies();
    }
    @GET
    @Path("/getAllCustomers")
    public String getAllCustomers() throws Exception {

       return  adminBean.getAllCustomers();
    }

    @POST
    @Path("/register")
    public String registerAdmin(Admin admin) {
      return adminBean.registerAdmin(admin);
    }

    @POST
    @Path("/login")
    public String loginAdmin(Admin admin) {
      return adminBean.loginAdmin(admin);
    }
    @GET
    @Path("/getAll")
    public List<Admin> getAllAdmins() {
      return adminBean.getAllAdmins();
    }
}

