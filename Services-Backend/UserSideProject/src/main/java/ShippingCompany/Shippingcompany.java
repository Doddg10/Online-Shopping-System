package ShippingCompany;

import jakarta.persistence.*;

@Entity
@Table(name = "shippingcompany")
public class Shippingcompany {
    @Id
    private String shippingCompanyName;
    private String password;
    private String region;

    public Shippingcompany(String shippingCompanyName, String password, String region) {
        this.shippingCompanyName = shippingCompanyName;
        this.password = password;
        this.region = region;
    }

    public Shippingcompany() {

    }

    public String getShippingCompanyName() {
        return shippingCompanyName;
    }

    public void setShippingCompanyName(String name) {
        this.shippingCompanyName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}
