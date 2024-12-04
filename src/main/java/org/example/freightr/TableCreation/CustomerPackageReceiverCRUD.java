package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.CustomerPackageReceiverDOA;
import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;

import java.sql.*;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * @author kohinoor jeet singh
 * @description Implementation for CustomerPackageReceiver table operations
 */
public class CustomerPackageReceiverCRUD implements CustomerPackageReceiverDOA {
    private static CustomerPackageReceiverCRUD instance;
    private final Database db = Database.getInstance();

    private CustomerPackageReceiverCRUD() { }


    @Override
    public void addCustomerPackageReceiver(CustomerPackageReceiver customerPackageReceiver) {
        String query = "INSERT INTO " + Dbconst.TABLE_CUSTOMER_PACKAGES + " (" +
                CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + ", " +
                CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + ", " +
                CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID + ") VALUES (" +
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
                CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " = " + packageId;
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Package with ID " + packageId + " deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCustomerIdByPackageId(int packageId) {
        String query = "SELECT " + CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " FROM " + TABLE_CUSTOMER_PACKAGES +
                " WHERE " + CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " = " + packageId;
        int customerId = -1;
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                customerId = resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }
    public int getReciverIdByPackageId(int packageId) {
        String query = "SELECT " + CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID+ " FROM " + TABLE_CUSTOMER_PACKAGES +
                " WHERE " + CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " = " + packageId;
        int ReciverID = -1;
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                ReciverID = resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReciverID;
    }



    @Override
    public ArrayList<Integer> getPackageIdsByCustomerId(int customerId) {
        String query = "SELECT " + CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " FROM " + TABLE_CUSTOMER_PACKAGES +
                " WHERE " + CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " = " + customerId;
        ArrayList<Integer> packageIds = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                packageIds.add(resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageIds;
    }


    public ArrayList<Integer> getReceiverIdsByCustomerId(int customerId) {
        String query = "SELECT " + CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID + " FROM " + TABLE_CUSTOMER_PACKAGES +
                " WHERE " + CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " = " + customerId;
        ArrayList<Integer> receiverIds = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                receiverIds.add(resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiverIds;
    }

    public ArrayList<CustomerPackageReceiver> getAllCustomerPackageReceivers() {
        String query = "SELECT * FROM " + TABLE_CUSTOMER_PACKAGES;
        ArrayList<CustomerPackageReceiver> customerPackageReceivers = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int customerId = resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID);
                int packageId = resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID);
                int receiverId = resultSet.getInt(CUSTOMER_PACKAGES_COLUMN_RECEIVER_ID);

                // Assuming that CustomerPackageReceiver class only contains customerId, packageId, receiverId.
                CustomerPackageReceiver customerPackageReceiver = new CustomerPackageReceiver(customerId, packageId, receiverId);
                customerPackageReceivers.add(customerPackageReceiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerPackageReceivers;
    }


    public static CustomerPackageReceiverCRUD getInstance() {
        if (instance == null) {
            instance = new CustomerPackageReceiverCRUD();
        }
        return instance;
    }
}