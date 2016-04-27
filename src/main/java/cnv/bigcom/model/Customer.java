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
    private List<Address> _addresses;
    @SerializedName(value = "date_created")
    private Date registeredDate;
    private transient Address defaultAddress;

    public void setAddresses(List<Address> addresses) {
        this._addresses = addresses;
    }

    /**
     * This method will gives the default address <p>Note: gives high priority
     * to "residential" then "commercial"</p>
     */
    public Address getDefaultAddress() {
        if (defaultAddress == null) {
            if (_addresses != null && !_addresses.isEmpty()) {
                for (Address address : _addresses) {
                    defaultAddress = address;
                    if (address.getAddressType() == Address.AddressType.residential) {
                        // if the address is a resedential then stopping the process
                        // as we are giving the high proiority to residential
                        break;
                    }
                }
            }
        }
        return defaultAddress;
    }

    public String getFullName() {
        return (firstName != null ? firstName : "") + (lastName != null ? " " + lastName : "");
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
