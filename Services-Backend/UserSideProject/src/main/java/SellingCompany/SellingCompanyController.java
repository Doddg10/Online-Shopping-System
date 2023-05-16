package SellingCompany;

import Product.Product;
import jakarta.ejb.EJB;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/sellingCompany")
public class SellingCompanyController {

    @EJB
    SellingCompanyBean sellingCompanyBean;

    @POST
    @Path("/registerCompanyName")
    public String registerCompanyName(Sellingcompany sC)
    {
        return sellingCompanyBean.registerCompanyName(sC);
    }

    @PUT
    @Path("/createAccount")
    public String createAccount(Sellingcompany sC)
    {
        return sellingCompanyBean.createAccount(sC);
    }

    @POST
    @Path("/login")
    public String loginSellingCompany(Sellingcompany sC, @Context HttpServletRequest request)
    {
     return sellingCompanyBean.loginSellingCompany(sC,request);
    }
    @POST
    @Path("/logout")
    public String logoutSellingCompany(@Context HttpServletRequest request)
    {
        return sellingCompanyBean.logoutSellingCompany(request);
    }
    @GET
    @Path("/getAll")
    public List<Sellingcompany> getAllSellingCompanies() {
       return sellingCompanyBean.getAllSellingCompanies();
    }

    @GET
    @Path("/getAllWithoutAccount")
    public List<Sellingcompany> getAllSellingCompaniesWithoutAccount() {
        return sellingCompanyBean.getAllSellingCompaniesWithoutAccount();
    }

    @PUT
    @Path("/addProduct/{companyName}")
    public String addProduct(Product product , @PathParam("companyName") String companyName) {
        System.out.println("companyName: " + companyName); // print the companyName
        return sellingCompanyBean.addProduct(product,companyName);
    }

//    @PUT
//    @Path("/addProduct/{productId}")
//    public String addProductByID(@PathParam("productId") int productId, @Context HttpServletRequest request) {
//        return sellingCompanyBean.addProductByID(productId,request);
//    }

    @GET
    @Path("/{companyName}/products")
    public List<Product> getProducts( @PathParam("companyName") String companyName) {
        return sellingCompanyBean.getProducts(companyName);
    }


    @GET
    @Path("/{companyName}/orders")
    public JsonObject getOrdersByCompany(@PathParam("companyName") String companyName) {
       return sellingCompanyBean.getOrdersByCompany(companyName);
    }
}
