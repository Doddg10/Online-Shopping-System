package Notification;

import Customer.Customer;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@MessageDriven(
        activationConfig = {
                @jakarta.ejb.ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @jakarta.ejb.ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ProcessingQueue")
        },
        mappedName = "java:/jms/queue/ProcessingQueue", name = "NotificationController")
public class NotificationBean implements MessageListener {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();
    @Override
    public void onMessage(Message message) {
        try {
            String[] orderIsProcessedMessage = message.getBody(String.class).split(",");
            int customerID = Integer.parseInt(orderIsProcessedMessage[0]);
            String processedMssg=orderIsProcessedMessage[1];
            System.out.println("Received message: " + processedMssg);
            // adding notification to customer's notifications
            Customer customer=entityManager.find(Customer.class,customerID);
            Notification notification=new Notification();
            notification.setCustomer(customer);
            notification.setMessage(processedMssg);
            customer.getNotifications().add(notification);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
