package org.example.freightr.TableCreation;

import java.util.ArrayList;

public interface CustomerDoa {
    public ArrayList<Customer> getAllCustomers();

    public Customer getCustomer(int CustomerID);

    public Customer deleteCustomer(int CustomerID);
}
