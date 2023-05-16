package SellingCompany;

import Product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sellingcompany")
public class Sellingcompany {
    @Id
    private String sellingCompanyName;
    private String password;

    @OneToMany(mappedBy = "sellingCompany", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Sellingcompany(String sellingCompanyName, String password) {
        this.sellingCompanyName = sellingCompanyName;
        this.password = password;
    }

    public Sellingcompany(String sellingCompanyName) {
        this.sellingCompanyName = sellingCompanyName;
    }

    public Sellingcompany() {
    }

    public String getSellingCompanyName() {
        return sellingCompanyName;
    }

    public void setSellingCompanyName(String name) {
        this.sellingCompanyName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}