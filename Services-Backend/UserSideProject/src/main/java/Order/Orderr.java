package Order;

import jakarta.persistence.*;

@Entity
public class Orderr {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private Integer customerId;
    private String productsIDs;
    private String sellingCompanyIDs;
    private String shippingCompanyId;
    private String address;
    private Integer totalPrice;
    private Integer proccessed;
    private Integer cart;

    public Orderr(Integer customerId, String productsIDs, String sellingCompanyIDs, String shippingCompanyId, String address, Integer totalPrice, Integer proccessed, Integer cart) {
        this.customerId = customerId;
        this.productsIDs = productsIDs;
        this.sellingCompanyIDs = sellingCompanyIDs;
        this.shippingCompanyId = shippingCompanyId;
        this.address = address;
        this.totalPrice = totalPrice;
        this.proccessed = proccessed;
        this.cart = cart;
    }

    public Orderr() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getProductsIDs() {
        return productsIDs;
    }

    public void setProductsIDs(String productsIDs) {
        this.productsIDs = productsIDs;
    }

    public String getSellingCompanyIDs() {
        return sellingCompanyIDs;
    }

    public void setSellingCompanyIDs(String sellingCompanyIDs) {
        this.sellingCompanyIDs = sellingCompanyIDs;
    }

    public String getShippingCompanyId() {
        return shippingCompanyId;
    }

    public void setShippingCompanyId(String shippingCompanyId) {
        this.shippingCompanyId = shippingCompanyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getProccessed() {
        return proccessed;
    }

    public void setProccessed(Integer proccessed) {
        this.proccessed = proccessed;
    }

    public Integer getCart() {
        return cart;
    }

    public void setCart(Integer cart) {
        this.cart = cart;
    }


}
