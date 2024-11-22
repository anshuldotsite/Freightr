package org.example.freightr.TableCreation;


import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.CustomerDoa;
import org.example.freightr.TableCreation.ObjectClasses.Customer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

public class CustomerTableCreation implements CustomerDoa {

    private static CustomerTableCreation instance;


    private final Database db = Database.getInstance();

    private CustomerTableCreation() {
    }

    public static synchronized CustomerTableCreation getInstance() {
        if (instance == null) {
            instance = new CustomerTableCreation();
        }
        return instance;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        String query = "SELECT * FROM " + TABLE_CUSTOMER;
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Statement getCustomers = db.getConnection().createStatement();
            ResultSet resultSet = getCustomers.executeQuery(query);

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt(CUSTOMER_COLUMN_ID),
                        resultSet.getInt(CUSTOMER_COLUMN_COMPANY_ID),
                        resultSet.getString(CUSTOMER_COLUMN_FIRST_NAME),
                        resultSet.getString(CUSTOMER_COLUMN_LAST_NAME),
                        resultSet.getString(CUSTOMER_COLUMN_CONTACT_NUMBER),
                        resultSet.getString(CUSTOMER_COLUMN_EMAIL),
                        resultSet.getString(CUSTOMER_COLUMN_ADDRESS),
                        resultSet.getString(CUSTOMER_COLUMN_ZIPCODE),
                        resultSet.getString(CUSTOMER_COLUMN_CITY),
                        resultSet.getString(CUSTOMER_COLUMN_PROVINCE),
                        resultSet.getString(CUSTOMER_COLUMN_COUNTRY),
                        resultSet.getString(CUSTOMER_COLUMN_TYPE)

                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        String query = "SELECT * FROM " + TABLE_CUSTOMER +
                " WHERE " + CUSTOMER_COLUMN_ID + " = "+id;
        try {
            PreparedStatement getCustomer = db.getConnection().prepareStatement(query);

            ResultSet data = getCustomer.executeQuery();

            if (data.next()) {
                return new Customer(
                        data.getInt(CUSTOMER_COLUMN_ID),
                        data.getInt(CUSTOMER_COLUMN_COMPANY_ID),
                        data.getString(CUSTOMER_COLUMN_FIRST_NAME),
                        data.getString(CUSTOMER_COLUMN_LAST_NAME),
                        data.getString(CUSTOMER_COLUMN_CONTACT_NUMBER),
                        data.getString(CUSTOMER_COLUMN_EMAIL),
                        data.getString(CUSTOMER_COLUMN_ADDRESS),
                        data.getString(CUSTOMER_COLUMN_ZIPCODE),
                        data.getString(CUSTOMER_COLUMN_CITY),
                        data.getString(CUSTOMER_COLUMN_PROVINCE),
                        data.getString(CUSTOMER_COLUMN_COUNTRY),
                        data.getString(CUSTOMER_COLUMN_TYPE)

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer deleteCustomer(int customerID) {
        String query = "DELETE FROM " + TABLE_CUSTOMER +
                " WHERE " + CUSTOMER_COLUMN_ID + " = "+customerID;
        Customer deletedCustomer = getCustomer(customerID);

        if (deletedCustomer == null) {
            return null;
        }
        try {
            PreparedStatement deleteStatement = db.getConnection().prepareStatement(query);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                return deletedCustomer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String query = "UPDATE " + TABLE_CUSTOMER +
                " SET " +
                CUSTOMER_COLUMN_COMPANY_ID + " = " + customer.getCompanyId() + ", " +
                CUSTOMER_COLUMN_FIRST_NAME + " = '" + customer.getFirstName() + "', " +
                CUSTOMER_COLUMN_LAST_NAME + " = '" + customer.getLastName() + "', " +
                CUSTOMER_COLUMN_CONTACT_NUMBER + " = '" + customer.getContactNumber() + "', " +
                CUSTOMER_COLUMN_EMAIL + " = '" + customer.getEmail() + "', " +
                CUSTOMER_COLUMN_ADDRESS + " = '" + customer.getAddress() + "', " +
                CUSTOMER_COLUMN_ZIPCODE + " = '" + customer.getZipcode() + "', " +
                CUSTOMER_COLUMN_CITY + " = '" + customer.getCity() + "', " +
                CUSTOMER_COLUMN_PROVINCE + " = '" + customer.getProvince() + "', " +
                CUSTOMER_COLUMN_COUNTRY + " = '" + customer.getCountry() + "', " +
                CUSTOMER_COLUMN_TYPE + " = '" + customer.getCustomerType() + "' " +
                "WHERE " + CUSTOMER_COLUMN_ID + " = " + customer.getCustomerId();
        try {
            PreparedStatement updateStatement = db.getConnection().prepareStatement(query);
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                return getCustomer(customer.getCustomerId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




