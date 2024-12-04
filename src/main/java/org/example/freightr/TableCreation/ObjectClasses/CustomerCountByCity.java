package org.example.freightr.TableCreation.ObjectClasses;



public class CustomerCountByCity {
    private String city;
    private int customerCount;

    public CustomerCountByCity(String city, int customerCount) {
        this.city = city;
        this.customerCount = customerCount;
    }

    public String getCity() {
        return city;
    }

    public int getCustomerCount() {
        return customerCount;
    }
}
