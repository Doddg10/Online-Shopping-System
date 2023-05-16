package Product;

import SellingCompany.Sellingcompany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private int price;
    private int quantity;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_name")
    private Sellingcompany sellingCompany;

    public Product(String productName, int price, int quantity, Sellingcompany sellingCompany) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.sellingCompany = sellingCompany;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Sellingcompany getSellingCompany() {
        return sellingCompany;
    }

    public void setSellingCompany(Sellingcompany sellingCompany) {
        this.sellingCompany = sellingCompany;
    }
}