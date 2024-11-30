package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.Customer;

import java.util.ArrayList;

public interface CustomerDoa {
    public ArrayList<Customer> getAllCustomers();

    public Customer getCustomer(int CustomerID);

    public void deleteCustomer(int CustomerID);

    public void updateCustomer(Customer customer);
    public  void addCustomerWithoutCompany(Customer customer);
    public void addCustomerWithCompany(Customer customer);
    public ArrayList<Customer> getPrettyData();
}
