/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Customer extends BaseModel {

    @SerializedName(value = "first_name")
    private String firstName;
    @SerializedName(value = "last_name")
    private String lastName;
    @SerializedName(value = "email")
    private String email;
    @SerializedName(value = "phone")
    private String phone;
    @SerializedName(value = "last_order_id")
    private long lastOrderId = -1;
    @SerializedName(value = "store_credit")
    private Float totalSpent;
    @SerializedName(value = "orders_count")
    private int totalOrders = 0;
    @SerializedName(value = "default_address")
    private Address defaultAddress;
    @SerializedName(value = "date_created")
    private Date registeredDate;

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getLastOrderId() {
        return lastOrderId;
    }

    public void setLastOrderId(long lastOrderId) {
        this.lastOrderId = lastOrderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Float getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Float totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Override
    public String toString() {
        return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", lastOrderId=" + lastOrderId + ", totalSpent=" + totalSpent + ", totalOrders=" + totalOrders + ", registeredDate=" + registeredDate + '}';
    }
}
