package SellingCompany;
import Customer.Customer;
import Order.Orderr;
import Product.Product;
import ShippingCompany.Shippingcompany;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.json.*;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateful
@SessionScoped
public class SellingCompanyBean implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();

    public String registerCompanyName(Sellingcompany sC) {
        Sellingcompany existingCompany = entityManager.find(Sellingcompany.class, sC.getSellingCompanyName());
        if (existingCompany != null) {
            return "The selling company name already exists!";
        }
        entityManager.getTransaction().begin();
        entityManager.persist(sC);
        try {
            entityManager.getTransaction().commit();
        } catch (RollbackException | SecurityException |
                 IllegalStateException e) {
            e.printStackTrace();
        }
        return "Your selling company's name " + sC.getSellingCompanyName() + " has been successfully added!\n" +
                "Wait for one of our admins to make a representative account for you!";
    }

    public String createAccount(Sellingcompany sC) {
        entityManager.getTransaction().begin();
        Sellingcompany company = entityManager.createQuery(
                        "SELECT s FROM Sellingcompany s WHERE s.sellingCompanyName = :name", Sellingcompany.class)
                .setParameter("name", sC.getSellingCompanyName())
                .getSingleResult();
        company.setPassword(generatePassword()); // Assuming you have a generatePassword() function
        entityManager.merge(company);
        try {
            entityManager.getTransaction().commit();
        } catch (RollbackException | SecurityException |
                 IllegalStateException e) {
            e.printStackTrace();
        }
        return "The selling company's representative account has been created!\n";
    }

    public static String generatePassword() {
        String lowerChar = "abcdefghijklmnopqrstuvwxyz";
        String upperChar = lowerChar.toUpperCase();
        String nums = "0123456789";
        String passBase = lowerChar + upperChar + nums;
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(6); //length=6
        for (int i = 0; i < 6; i++) {
            int randomCharIndex = random.nextInt(passBase.length());
            char randomChar = passBase.charAt(randomCharIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public String loginSellingCompany(Sellingcompany sC, @Context HttpServletRequest request) {
        Sellingcompany DBsC = entityManager.createQuery("SELECT sc FROM Sellingcompany sc WHERE sc.sellingCompanyName = :sellingCompanyName", Sellingcompany.class)
                .setParameter("sellingCompanyName", sC.getSellingCompanyName())
                .getSingleResult();
        if (DBsC.getPassword().equals(sC.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("companyName", DBsC.getSellingCompanyName());
            return "Welcome back, " + DBsC.getSellingCompanyName() + "!";
        } else {
            throw new NotAuthorizedException("Invalid credentials");
        }
    }

    public String logoutSellingCompany(@jakarta.ws.rs.core.Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "You have been logged out";
    }

    public List<Sellingcompany> getAllSellingCompanies() {
        return entityManager.createQuery("SELECT sc FROM Sellingcompany sc", Sellingcompany.class).getResultList();
    }

    public List<Sellingcompany> getAllSellingCompaniesWithoutAccount() {
        return entityManager.createQuery("SELECT sc FROM Sellingcompany sc WHERE sc.password IS NULL", Sellingcompany.class).getResultList();
    }

    public String addProduct(Product product, @PathParam("companyName") String companyName) {
        try {
            entityManager.getTransaction().begin();
            Sellingcompany company = entityManager.createQuery(
                            "SELECT sC FROM Sellingcompany sC WHERE sC.sellingCompanyName = :name", Sellingcompany.class)
                    .setParameter("name", companyName)
                    .getSingleResult();
            product.setSellingCompany(company);
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return "Product added successfully!";
        } catch (Exception e) {
            return "There was an error adding the product.";
        }
    }


    public String addProductByID(int productId, @Context HttpServletRequest request) {
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, productId);
            HttpSession session = request.getSession();
            String companyName = (String) session.getAttribute("companyName");
            Sellingcompany company = entityManager.createQuery("SELECT sC FROM Sellingcompany sC WHERE sC.sellingCompanyName = :name", Sellingcompany.class)
                    .setParameter("name", companyName)
                    .getSingleResult();
            product.setSellingCompany(company);
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return "Product added successfully!";
        } catch (Exception e) {
            return "There was an error adding the product.";
        }
    }

    public List<Product> getProducts(@PathParam("companyName") String companyName) {
        Sellingcompany company = entityManager.createQuery(
                        "SELECT s FROM Sellingcompany s WHERE s.sellingCompanyName = :name", Sellingcompany.class)
                .setParameter("name", companyName)
                .getSingleResult();
        entityManager.refresh(company);
        return company.getProducts();
    }

    @Transactional
    public JsonObject getOrdersByCompany(@PathParam("companyName") String companyName) {
        List<Orderr> orders = entityManager.createQuery("SELECT o FROM Orderr o WHERE o.sellingCompanyIDs LIKE :companyName AND o.proccessed=1", Orderr.class)
                .setParameter("companyName", "%" + companyName + "%")
                .getResultList();

        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
        JsonArrayBuilder ordersArrayBuilder = Json.createArrayBuilder();

        for (Orderr order : orders) {
            List<Integer> productIds = Arrays.stream(order.getProductsIDs().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            JsonArrayBuilder productsWithSameCompanyArrayBuilder = Json.createArrayBuilder();
            for (int productId : productIds) {
                try {
                    Product product = entityManager.createQuery("SELECT p FROM Product p WHERE p.id = :productId AND p.sellingCompany.sellingCompanyName = :companyName", Product.class)
                            .setParameter("productId", productId)
                            .setParameter("companyName", companyName)
                            .getSingleResult();

                    JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                    productBuilder.add("id", product.getId());
                    productBuilder.add("name", product.getProductName());
                    productBuilder.add("price", product.getPrice());

                    productsWithSameCompanyArrayBuilder.add(productBuilder);
                } catch (NoResultException e) {
                    // do nothing if there isn't a product with the given ID and company name
                }
            }
            JsonArray productsWithSameCompanyArray = productsWithSameCompanyArrayBuilder.build();
            if (!productsWithSameCompanyArray.isEmpty()) {
                int customerId = order.getCustomerId();
                Customer customer = entityManager.find(Customer.class, customerId);
                String customerDetails = "Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Email: " + customer.getEmail() + ", Address: " + customer.getAddress();
                String shippingCompanyId = order.getShippingCompanyId();
                Shippingcompany shippingCompany = entityManager.find(Shippingcompany.class, shippingCompanyId);
                String orderDetails = "Order ID: " + order.getId() + ", Total Price: " + order.getTotalPrice() + ", Shipping Company: " + shippingCompany.getShippingCompanyName();

                JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
                orderBuilder.add("customerDetails", customerDetails);
                orderBuilder.add("orderDetails", orderDetails);
                orderBuilder.add("products", productsWithSameCompanyArray);

                ordersArrayBuilder.add(orderBuilder);
            }
        }

        responseBuilder.add("orders", ordersArrayBuilder);

        return responseBuilder.build();
    }
}
