package pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyPojo {
    @SerializedName("PharmacyRecordID")
    private String pharmacyRecordID;
    @SerializedName("PharmacyID")
    private String pharmacyID;
    @SerializedName("PharmacyNPI")
    private String pharmacyNPI;
    @SerializedName("PharmacyNABP")
    private String pharmacyNABP;
    @SerializedName("IsMailOrder")
    private String isMailOrder;
    @SerializedName("Name")
    private String name;
    @SerializedName("Address1")
    private String address1;
    @SerializedName("Address2")
    private String address2;
    @SerializedName("City")
    private String city;
    @SerializedName("Zip")
    private String zip;
    @SerializedName("State")
    private String state;
    @SerializedName("PharmacyPhone")
    private String pharmacyPhone;

    public String getPharmacyRecordID() {
        return pharmacyRecordID;
    }

    public void setPharmacyRecordID(String pharmacyRecordID) {
        this.pharmacyRecordID = pharmacyRecordID;
    }

    public String getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(String pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getPharmacyNPI() {
        return pharmacyNPI;
    }

    public void setPharmacyNPI(String pharmacyNPI) {
        this.pharmacyNPI = pharmacyNPI;
    }

    public String getPharmacyNABP() {
        return pharmacyNABP;
    }

    public void setPharmacyNABP(String pharmacyNABP) {
        this.pharmacyNABP = pharmacyNABP;
    }

    public String getIsMailOrder() {
        return isMailOrder;
    }

    public void setIsMailOrder(String isMailOrder) {
        this.isMailOrder = isMailOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }
}
