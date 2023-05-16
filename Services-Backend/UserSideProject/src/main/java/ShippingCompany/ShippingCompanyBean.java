package ShippingCompany;
import Order.Orderr;

import SellingCompany.Sellingcompany;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.jms.*;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;
import java.lang.IllegalStateException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Stateful
@SessionScoped
public class ShippingCompanyBean  implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();
    @Resource(mappedName = "java:/jms/queue/ProcessingQueue")
    private Queue queue;
    public String createAccount(Shippingcompany sC) {
        String name = sC.getShippingCompanyName();
        Shippingcompany existingCompany = entityManager.find(Shippingcompany.class, name);
        if(existingCompany != null){
            return "The shipping company with name " + name + " already exists!\n";
        }
        String password = generatePassword();
        sC.setPassword(password);
        entityManager.getTransaction().begin();
        entityManager.persist(sC);
        try {
            entityManager.getTransaction().commit();
        } catch (RollbackException | SecurityException |
                 IllegalStateException e) {
            e.printStackTrace();
        }
        return "The shipping company " + sC.getShippingCompanyName() + " has been registered successfully!\n";
//                + "The password for the account is " + sC.getPassword() + ".";
    }
    public static String generatePassword() {
        String lowerChar = "abcdefghijklmnopqrstuvwxyz";
        String upperChar = lowerChar.toUpperCase();
        String nums = "0123456789";
        String passBase= lowerChar + upperChar + nums;
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(6); //length=6
        for (int i = 0; i < 6; i++) {
            int randomCharIndex = random.nextInt(passBase.length());
            char randomChar = passBase.charAt(randomCharIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
    public String loginShippingCompany(Shippingcompany sC, @jakarta.ws.rs.core.Context HttpServletRequest request) {
        Shippingcompany DBsC = entityManager.createQuery("SELECT sc FROM Shippingcompany sc WHERE sc.shippingCompanyName = :shippingCompanyName", Shippingcompany.class)
                .setParameter("shippingCompanyName", sC.getShippingCompanyName())
                .getSingleResult();
        if (DBsC.getPassword().equals(sC.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("companyName", DBsC.getShippingCompanyName());
            return "Welcome back, " + DBsC.getShippingCompanyName() + "!";
        } else {
            throw new NotAuthorizedException("Invalid credentials");
        }
    }
    public String logoutShippingCompany(@jakarta.ws.rs.core.Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "You have been logged out";
    }
    public List<Shippingcompany> getAllShippingCompanies() {
        return entityManager.createQuery("SELECT sc FROM Shippingcompany sc", Shippingcompany.class).getResultList();
    }


    public List<Orderr> getAllOrders_region( @PathParam("companyName") String companyName) {
        Shippingcompany shippingcompany = entityManager.find(Shippingcompany.class, companyName);
        List<String> regions = Arrays.stream(shippingcompany.getRegion().split(",")).collect(Collectors.toList());
        List<Orderr> OrdersWithinCompanyRegion = new ArrayList<>();

        for (String region : regions) {
            try {
                List<Orderr> matchingOrders = entityManager.createQuery("SELECT o FROM Orderr o WHERE o.address LIKE :region AND o.proccessed=0 AND o.cart=0", Orderr.class).setParameter("region", "%" + region + "%").getResultList();
                OrdersWithinCompanyRegion.addAll(matchingOrders);
            } catch (NoResultException e) {
                // do nothing if there is no product with the given ID and company name
            }
        }

        return OrdersWithinCompanyRegion;
    }

    @Transactional
    public String shipOrder(  @PathParam("companyName") String companyName, int orderId) {
        Orderr order = entityManager.find(Orderr.class, orderId);
        Shippingcompany shippingCompany = entityManager.createQuery("SELECT s FROM Shippingcompany s WHERE s.shippingCompanyName = :companyName", Shippingcompany.class)
                .setParameter("companyName", companyName)
                .getSingleResult();
        order.setShippingCompanyId(shippingCompany.getShippingCompanyName());
        order.setProccessed(1);
        entityManager.persist(order);
        int customerID = order.getCustomerId();
        sendNotification(customerID + ",Your shipping request for order with id: " +orderId+" is processed ");
        return "Order Processed successfully";
    }
    public void sendNotification(String request) {
        try {
            Context context = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("java:/ConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(this.queue);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(request);
            producer.send(message);
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


