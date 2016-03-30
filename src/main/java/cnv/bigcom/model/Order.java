/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Order extends BaseModel {

    @SerializedName("currency")
    private String currency;
    @SerializedName("customer_id")
    private long customerId;
    @SerializedName("total_inc_tax")
    private float totalPrice;
    @SerializedName("products")
    private List<Item> products;
    @SerializedName("date_created")
    private Date orderDate;

    public String getCurrency() {
        return currency;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Inner class that represents an Item in an Order
     */
    public static class Item extends BaseModel {

        @SerializedName("name")
        private String name;
        @SerializedName("product_id")
        private long productId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getProductId() {
            return productId;
        }

        public void setProductId(long productId) {
            this.productId = productId;
        }
    }
}
