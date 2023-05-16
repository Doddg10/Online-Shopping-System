package Admin;


import jakarta.ejb.Singleton;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



@Singleton

public class AdminBean {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = emf.createEntityManager();

    public String createSellingCompany(String requestBody) throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/createAccount"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String createShippingCompany(String requestBody) throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/shippingCompany";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/createAccount"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAllSellingCompaniesWithoutAccount() throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/getAllWithoutAccount"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


    public String getAllSellingCompanies() throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/sellingCompany";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/getAll"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAllShippingCompanies() throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/shippingCompany";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/getAll"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getAllCustomers() throws Exception {

        String BASE_URL = "http://localhost:8080/UserSideProject-1.0-SNAPSHOT/api/customer";
        HttpResponse<String> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/getAll"))
                .header("Content-Type", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String registerAdmin(Admin admin) {
        try {
            entityManager.createQuery("SELECT a FROM Admin a WHERE a.adminEmail = :email", Admin.class)
                    .setParameter("email", admin.getAdminEmail())
                    .getSingleResult();
            throw new NotAcceptableException("This email is already registered");
        } catch (NoResultException e) {
            // Email is unique, continue with registration
            entityManager.getTransaction().begin();
            entityManager.persist(admin);
            try {
                entityManager.getTransaction().commit();
            } catch (RollbackException | SecurityException | IllegalStateException ex) {
                ex.printStackTrace();
            }
            return "You've registered successfully!\n";
        }
    }

    public String loginAdmin(Admin admin) {
        try {
            Admin DBAdmin = entityManager.createQuery("SELECT a FROM Admin a WHERE a.adminEmail = :email", Admin.class)
                    .setParameter("email", admin.getAdminEmail())
                    .getSingleResult();
            if (DBAdmin.getPassword().equals(admin.getPassword())) {
                return "Logged in Successfully!nWelcome back!";
            } else {
                throw new NotAuthorizedException("Invalid credentials");
            }
        } catch (NoResultException e) {
            throw new NotAuthorizedException(e);
        } catch (NonUniqueResultException e) {
            throw new NotAuthorizedException(e);
        }
    }

    public List<Admin> getAllAdmins() {
        return entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
    }
}

