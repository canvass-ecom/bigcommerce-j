/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Address extends BaseModel {

    public static enum AddressType {

        residential,
        commercial;
    }
    @SerializedName("customer_id")
    private long custId;
    @SerializedName("street_1")
    private String address1;
    @SerializedName("street_2")
    private String address2;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String countryName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("zip")
    private String pincode;
    @SerializedName("state")
    private String state;
    @SerializedName("address_type")
    private AddressType addressType;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static void main(String[] args) {
        System.out.println(AddressType.valueOf("commercial"));
    }
}
