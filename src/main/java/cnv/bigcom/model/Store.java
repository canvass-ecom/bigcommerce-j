/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Store implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("domain")
    private String domain;
    @SerializedName("phone")
    private String phone;
    @SerializedName("admin_email")
    private String adminEmail;
    @SerializedName("order_email")
    private String orderEmail;
    @SerializedName("address")
    private String address;
    private transient Address addr;

    public Address getAddress() {
        if (addr == null) {
            addr = new Address();
            if (address != null && !address.isEmpty()) {
                String[] parts = address.split("\n");
                if (parts.length > 3) {
                    addr.setCountryName(parts[3].trim());
                }
                if (parts.length > 2) {
                    String line2 = parts[2];
                    String[] line2Split = line2.split(",");
                    if (line2Split.length > 1) {
                        addr.setCity(line2Split[0].trim());
                        String state_pin = line2Split[1].trim();
                        String[] state_pin_split = state_pin.split(" ");
                        if (state_pin_split.length > 1) {
                            addr.setPincode(state_pin_split[1].trim());
                            addr.setState(state_pin_split[0].trim());
                        }
                    }
                }
                if (parts.length > 1) {
                    addr.setAddress1(parts[0].trim());
                    addr.setAddress2(parts[1].trim());
                }
            }
        }
        return addr;
    }

    public String getStoreId() {
        return id;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", domain=" + domain + ", phone=" + phone + ", adminEmail=" + adminEmail + ", orderEmail=" + orderEmail + '}';
    }
}
