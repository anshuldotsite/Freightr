package org.example.freightr.TableCreation.ObjectClasses;

/**
 * @description This class has constructors for displaying customer objects, along with getters and setters
 */
public class DisplayCustomerPOJO {
    private int customerId;
    private String company;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String country;
    private String customerType;

    public DisplayCustomerPOJO(int customerId, String company, String firstName, String lastName, String contactNumber, String email, String address, String zipcode,
                               String city, String province, String country, String customerType) {
        this.customerId = customerId;
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.country = country;
        this.customerType = customerType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
