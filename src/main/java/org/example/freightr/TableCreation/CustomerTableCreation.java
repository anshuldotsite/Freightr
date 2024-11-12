package org.example.freightr.TableCreation;


import org.example.freightr.Database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Tableconst.*;

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
                " WHERE " + CUSTOMER_COLUMN_ID + " = ?";
        try {
            PreparedStatement getCustomer = db.getConnection().prepareStatement(query);
            getCustomer.setInt(1, id);
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
    public Customer deleteCustomer(int CustomerID) {
        return null;
    }
}
