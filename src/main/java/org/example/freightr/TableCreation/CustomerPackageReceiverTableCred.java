package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.CustomerPackageRecieverDOA;
import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author kohinoor jeet singh
 * @description Implementation for CustomerPackageReceiver table operations
 */
public class CustomerPackageReceiverTableCred implements CustomerPackageRecieverDOA {
    private static CustomerPackageReceiverTableCred instance;
    private final Database db = Database.getInstance();

    private CustomerPackageReceiverTableCred() { }


    @Override
    public void addCustomerPackageReceiver(CustomerPackageReceiver customerPackageReceiver) {
        String query = "INSERT INTO " + Dbconst.TABLE_CUSTOMER_PACKAGES + " (" +
                Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + ", " +
                Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + ", " +
                Dbconst.CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID + ") VALUES (" +
                customerPackageReceiver.getCustomerId() + ", " +
                customerPackageReceiver.getPackageId() + ", " +
                customerPackageReceiver.getReceiverId() + ")";
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Customer Package Receiver Added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void deleteCustomerPackageReceiver(int packageId) {
        String query = "DELETE FROM " + Dbconst.TABLE_CUSTOMER_PACKAGES + " WHERE " +
                Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " = " + packageId;
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Package with ID " + packageId + " deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getCustomerIdByPackageId(int packageId) {
        String query = "SELECT " + Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " FROM " + Dbconst.TABLE_CUSTOMER_PACKAGES +
                " WHERE " + Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " = " + packageId;
        int customerId = -1;
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                customerId = resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }



    @Override
    public ArrayList<Integer> getPackageIdsByCustomerId(int customerId) {
        String query = "SELECT " + Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " FROM " + Dbconst.TABLE_CUSTOMER_PACKAGES +
                " WHERE " + Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " = " + customerId;
        ArrayList<Integer> packageIds = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                packageIds.add(resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageIds;
    }


    public ArrayList<Integer> getReceiverIdsByCustomerId(int customerId) {
        String query = "SELECT " + Dbconst.CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID + " FROM " + Dbconst.TABLE_CUSTOMER_PACKAGES +
                " WHERE " + Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " = " + customerId;
        ArrayList<Integer> receiverIds = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                receiverIds.add(resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiverIds;
    }

    public ArrayList<CustomerPackageReceiver> getAllCustomerPackageReceivers() {
        String query = "SELECT * FROM " + Dbconst.TABLE_CUSTOMER_PACKAGES;
        ArrayList<CustomerPackageReceiver> customerPackageReceivers = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int customerId = resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID);
                int packageId = resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID);
                int receiverId = resultSet.getInt(Dbconst.CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID);

                // Assuming that CustomerPackageReceiver class only contains customerId, packageId, receiverId.
                CustomerPackageReceiver customerPackageReceiver = new CustomerPackageReceiver(customerId, packageId, receiverId);
                customerPackageReceivers.add(customerPackageReceiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerPackageReceivers;
    }


    public static CustomerPackageReceiverTableCred getInstance() {
        if (instance == null) {
            instance = new CustomerPackageReceiverTableCred();
        }
        return instance;
    }
}