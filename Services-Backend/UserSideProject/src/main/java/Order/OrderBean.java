package Order;
import Customer.Customer;
import Order.Orderr;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import java.util.List;

@Stateless
public class OrderBean {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();

    public List<Orderr> getAllOrders()
    {
        return entityManager.createQuery("SELECT o FROM Orderr o", Orderr.class).getResultList();
    }

    @Transactional
    public String addProductToCart(@PathParam("productId") int productId,@PathParam("customerId") int customerId)
    {
        Customer customer = entityManager.find(Customer.class, customerId);

        Orderr orderr;
        try {
            orderr = entityManager.createQuery("SELECT o FROM Orderr o WHERE o.customerId = :customerId AND o.cart = 1", Orderr.class)
                    .setParameter("customerId", customer.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            // Create a new Orderr object here in case there was no result returned from the query
            orderr = new Orderr();
            orderr.setCustomerId(customerId);
            orderr.setAddress(customer.getAddress());
            orderr.setProccessed(0);
            orderr.setCart(1);
            entityManager.persist(orderr);
            entityManager.flush();
        }

        String productIds = orderr.getProductsIDs();
        if (productIds == null) {
            productIds = String.valueOf(productId);
        } else {
            if (!productIds.contains(String.valueOf(productId))) {
                productIds += "," + productId;
            }
        }
        // Retrieve the name of the company associated with the product ID
        String companyName = entityManager
                .createQuery("SELECT p.sellingCompany.sellingCompanyName FROM Product p WHERE p.id = :productId", String.class)
                .setParameter("productId", productId)
                .getSingleResult();

        // Add the company name to the sellingCompany_IDs field
        String sellingCompanyIds = orderr.getSellingCompanyIDs();
        if (sellingCompanyIds == null) {
            sellingCompanyIds = companyName;
        } else {
            if (!sellingCompanyIds.contains(companyName)) {
                sellingCompanyIds += "," + companyName;
            }
        }
        // Retrieve the price of the product associated with the product ID
        int productPrice = entityManager
                .createQuery("SELECT p.price FROM Product p WHERE p.id = :productId", Integer.class)
                .setParameter("productId", productId)
                .getSingleResult();

        // Add the price of the product to the total price of the order
        Integer totalPrice = orderr.getTotalPrice();
        if (totalPrice == null) {
            totalPrice = productPrice;
        } else {
            totalPrice += productPrice;
        }
        orderr.setProductsIDs(productIds);
        orderr.setSellingCompanyIDs(sellingCompanyIds);
        orderr.setTotalPrice(totalPrice);
        entityManager.merge(orderr);
        entityManager.flush();

        return "Product added to cart";
    }

    @Transactional
    public String purchaseCart(@PathParam("customerId") int customerId)
    {
        Orderr orderr;
        try {
            orderr = entityManager.createQuery("SELECT o FROM Orderr o WHERE o.customerId = :customerId AND o.cart = 1", Orderr.class)
                    .setParameter("customerId", customerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalStateException("Cart not found");
        }
        orderr.setCart(0);
        entityManager.merge(orderr);
        entityManager.flush();
        return "Items in your cart purchased!\n Your order will be processed soon.";
    }

    @Transactional
    public Orderr viewCart(@PathParam("customerId") int customerId) {
        try {
            return entityManager.createQuery("SELECT o FROM Orderr o WHERE o.customerId = :customerId AND o.cart = 1", Orderr.class)
                    .setParameter("customerId", customerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            // return null if no result is found
            return null;
        }
    }

}
