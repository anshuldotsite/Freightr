package org.example.freightr.TableCreation;


import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.CustomerDoa;
import org.example.freightr.TableCreation.ObjectClasses.Customer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * @description  This class creates the customer table
 */

public class CustomerTableCreation implements CustomerDoa {

    private static CustomerTableCreation instance;


    private final Database db = Database.getInstance();

    private CustomerTableCreation() {
    }

    public static CustomerTableCreation getInstance() {
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
        Customer customer = new Customer();
        try {
            PreparedStatement getCustomer = db.getConnection().prepareStatement(query);

            ResultSet data = getCustomer.executeQuery();
            if (data.next()) {
                customer = new Customer(
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
        return customer;
    }

    @Override
    public void deleteCustomer(int customerID) {
        String query = "DELETE FROM " + TABLE_CUSTOMER +
                " WHERE " + CUSTOMER_COLUMN_ID + " = "+customerID;

        try {
            db.getConnection().createStatement().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
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
            updateStatement.executeUpdate();
            System.out.println("Customer Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @param customer to add in the table them executes a query to add that in table
     * @description This method creates a customer record without company id.
     */
    @Override
    public void addCustomerWithoutCompany(Customer customer){
        String query = "INSERT INTO " + TABLE_CUSTOMER + " (" +
                CUSTOMER_COLUMN_FIRST_NAME + ", " +
                CUSTOMER_COLUMN_LAST_NAME + ", " +
                CUSTOMER_COLUMN_CONTACT_NUMBER + ", " +
                CUSTOMER_COLUMN_EMAIL + ", " +
                CUSTOMER_COLUMN_ADDRESS + ", " +
                CUSTOMER_COLUMN_ZIPCODE + ", " +
                CUSTOMER_COLUMN_CITY + ", " +
                CUSTOMER_COLUMN_PROVINCE + ", " +
                CUSTOMER_COLUMN_COUNTRY + ", " +
                CUSTOMER_COLUMN_TYPE + ") VALUES (" +
                "'" + customer.getFirstName() + "', " +
                "'" + customer.getLastName() + "', " +
                "'" + customer.getContactNumber() + "', " +
                "'" + customer.getEmail() + "', " +
                "'" + customer.getAddress() + "', " +
                "'" + customer.getZipcode() + "', " +
                "'" + customer.getCity() + "', " +
                "'" + customer.getProvince() + "', " +
                "'" + customer.getCountry() + "', " +
                "'" + customer.getCustomerType() + "'" +
                ");";

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Added Customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Kautuk Prasad
     * @description This method creates a customer record with company id.
     */
    @Override
    public void addCustomerWithCompany(Customer customer) {
        String query = "INSERT INTO " + TABLE_CUSTOMER + " (" +
                CUSTOMER_COLUMN_COMPANY_ID + ", " +
                CUSTOMER_COLUMN_FIRST_NAME + ", " +
                CUSTOMER_COLUMN_LAST_NAME + ", " +
                CUSTOMER_COLUMN_CONTACT_NUMBER + ", " +
                CUSTOMER_COLUMN_EMAIL + ", " +
                CUSTOMER_COLUMN_ADDRESS + ", " +
                CUSTOMER_COLUMN_ZIPCODE + ", " +
                CUSTOMER_COLUMN_CITY + ", " +
                CUSTOMER_COLUMN_PROVINCE + ", " +
                CUSTOMER_COLUMN_COUNTRY + ", " +
                CUSTOMER_COLUMN_TYPE + ") VALUES (" +
                "'" + customer.getCompanyId() + "', " +
                "'" + customer.getFirstName() + "', " +
                "'" + customer.getLastName() + "', " +
                "'" + customer.getContactNumber() + "', " +
                "'" + customer.getEmail() + "', " +
                "'" + customer.getAddress() + "', " +
                "'" + customer.getZipcode() + "', " +
                "'" + customer.getCity() + "', " +
                "'" + customer.getProvince() + "', " +
                "'" + customer.getCountry() + "', " +
                "'" + customer.getCustomerType() + "'" +
                ");";

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Added Customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Combined Customer Data from different tables in the form of array list.
     * @author Kautuk Prasad
     * @description This is a method to retrieve customer data from different tables and combining to show them.
     */
    @Override
    public ArrayList<Customer> getPrettyData() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String query = "SELECT customer.customer_id, customer.company_id, company_details.company_name AS company_name, " +
                " customer.first_name, customer.last_name, customer.contact_number, customer.email, customer.address," +
                " customer.zipcode, customer.city, " +
                " customer.province, customer.country, customer.customer_type" +
                " from customer " +
                "LEFT OUTER JOIN company_details on customer.company_id = company_details.company_id " +
                "ORDER BY customer.customer_id ASC";
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);
            while(data.next()) {
                customers.add(new Customer(
                        data.getInt(CUSTOMER_COLUMN_ID),
                        data.getInt(CUSTOMER_COLUMN_COMPANY_ID),
                        data.getString("company_name"),
                        data.getString(CUSTOMER_COLUMN_FIRST_NAME),
                        data.getString(CUSTOMER_COLUMN_LAST_NAME),
                        data.getString(CUSTOMER_COLUMN_CONTACT_NUMBER),
                        data.getString(CUSTOMER_COLUMN_EMAIL),
                        data.getString(CUSTOMER_COLUMN_ADDRESS),
                        data.getString(CUSTOMER_COLUMN_ZIPCODE),
                        data.getString(CUSTOMER_COLUMN_CITY),
                        data.getString(CUSTOMER_COLUMN_PROVINCE),
                        data.getString(CUSTOMER_COLUMN_COUNTRY),
                        data.getString(CUSTOMER_COLUMN_TYPE)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return customers;
    }
}




