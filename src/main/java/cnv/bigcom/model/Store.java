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
