package Customer;
import Order.Orderr;
import Product.Product;
import Notification.Notification;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Stateful
@SessionScoped
public class CustomerBean implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();

    public String registerCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        try {
            entityManager.getTransaction().commit();
        } catch (RollbackException | SecurityException |
                 IllegalStateException e) {
            e.printStackTrace();
        }
        return "You've registered successfully!\n" + "Welcome Dear.. " + customer.getName();
    }

    public String loginCustomer(Customer customer, @Context HttpServletRequest request) {
        Customer DBCustomer = entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", customer.getEmail())
                .getSingleResult();
        if (DBCustomer.getPassword().equals(customer.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("customerId", DBCustomer.getId());
            return "Welcome back, " + DBCustomer.getId();
        } else {
            throw new NotAuthorizedException("Invalid credentials");
        }
    }

    public String logoutCustomer(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "You have been logged out";
    }
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }


    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<Orderr> getAllCurrentOrders(@PathParam("customerId") int customerId) {
        return entityManager.createQuery("SELECT o FROM Orderr o WHERE o.customerId = :customerId AND o.proccessed = :processed AND o.cart=0", Orderr.class)
                .setParameter("customerId", customerId)
                .setParameter("processed", 0)
                .getResultList();
    }

    public List<Orderr> getAllPastOrders(@PathParam("customerId") int customerId) {
        return entityManager.createQuery("SELECT o FROM Orderr o WHERE o.customerId = :customerId AND o.proccessed = :processed", Orderr.class)
                .setParameter("customerId", customerId)
                .setParameter("processed", 1)
                .getResultList();
    }

    public List<Notification> getAllNotifications(@PathParam("customerId") int customerId) {
        Customer DBCustomer = entityManager.find(Customer.class, customerId);
        List<Notification> allNotifications = new ArrayList<>();
        for (int i = 0; i < DBCustomer.getNotifications().size(); i++) {
            allNotifications.add(DBCustomer.getNotifications().get(i));
        }
        return allNotifications;
    }
}
