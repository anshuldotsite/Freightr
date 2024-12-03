package org.example.freightr.TableCreation;

import org.example.freightr.Database;

import org.example.freightr.TableCreation.DOA.PackageDoa;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * This class has CRUD operations for package table
 */
public class PackageTableCRUD implements PackageDoa {
    private static PackageTableCRUD instance;
    private PackageTableCRUD(){
        db= Database.getInstance();
    }
    Database db = Database.getInstance();
    ArrayList<Package> Package;
    /**
     * @description It gets all the package data from the table and stores it in an ArrayList.
     * @return All package data from the database.
     * @author kohinoor jeet isngh
     */
    @Override
    public ArrayList<Package> getAllPackages() {
        String query = "SELECT * FROM " + TABLE_PACKAGE ;
        ArrayList<Package> packages = new ArrayList<>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int packageId = resultSet.getInt(PACKAGE_COLUMN_ID);
                String packageDescription = resultSet.getString(PACKAGE_COLUMN_DESCRIPTION);
                Date sentDate = resultSet.getDate(PACKAGE_COLUMN_SENT_DATE);
                double weight = resultSet.getDouble(PACKAGE_COLUMN_WEIGHT);
                double height = resultSet.getDouble(PACKAGE_COLUMN_HEIGHT);
                double length = resultSet.getDouble(PACKAGE_COLUMN_LENGTH);
                double breadth = resultSet.getDouble(PACKAGE_COLUMN_BREADTH);
                double price = resultSet.getDouble(PACKAGE_COLUMN_PRICE);
                packages.add(new Package(packageId, packageDescription, sentDate, weight, height, length, breadth, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    /**
     * @description It gets a single package from the database
     * @param packageId The ID of the package to be retrieved.
     * @return A Package object if found, or null if not found.
     * @author kohinoor jeet singh
     */
    @Override
    public Package getPackage(int packageId) {
        String query = "SELECT * FROM " + TABLE_PACKAGE + " WHERE " + PACKAGE_COLUMN_ID + " = " + packageId;
        Package Package = null;

            try{
                Statement statement = db.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String packageDescription = resultSet.getString(PACKAGE_COLUMN_DESCRIPTION);
                    java.sql.Date sentDateSql = resultSet.getDate(PACKAGE_COLUMN_SENT_DATE);
                    Date sentDate = sentDateSql != null ? new Date(sentDateSql.getTime()) : null;
                    double weight = resultSet.getDouble(PACKAGE_COLUMN_WEIGHT);
                    double height = resultSet.getDouble(PACKAGE_COLUMN_HEIGHT);
                    double length = resultSet.getDouble(PACKAGE_COLUMN_LENGTH);
                    double breadth = resultSet.getDouble(PACKAGE_COLUMN_BREADTH);
                    double price = resultSet.getDouble(PACKAGE_COLUMN_PRICE);

                    Package = new Package(packageId, packageDescription, sentDate, weight, height, length, breadth, price);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Package;
        }


    @Override
    public void deletePackage(int packageId) {
        String query = "DELETE FROM " + TABLE_PACKAGE + " WHERE "+ PACKAGE_COLUMN_ID + " = " + packageId;
        Package deletedPackage = null;
        // First retrieve the package details before deletion
        deletedPackage = getPackage(packageId);

        if (deletedPackage != null) {
            // Perform the delete operation
            try {
                CustomerPackageReceiverCRUD customerPackageReceiverCRUD = CustomerPackageReceiverCRUD.getInstance();
                customerPackageReceiverCRUD.deleteCustomerPackageReceiver(packageId);

                PackageTrackingTable packageTrackingTable = PackageTrackingTable.getInstance();
                packageTrackingTable.deletePackageTrack(packageId);

                PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
                preparedStatement.execute();
                System.out.println("Package deleted: " + packageId);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int addPackage(Package Package) {
        String query = "INSERT INTO " + TABLE_PACKAGE + " (" +
                PACKAGE_COLUMN_DESCRIPTION + ", " +
                PACKAGE_COLUMN_SENT_DATE + ", " +
                PACKAGE_COLUMN_WEIGHT + ", " +
                PACKAGE_COLUMN_HEIGHT + ", " +
                PACKAGE_COLUMN_LENGTH + ", " +
                PACKAGE_COLUMN_BREADTH + ", " +
                PACKAGE_COLUMN_PRICE + ") VALUES ('" +
                Package.getPackageDescription() + "', '" +
                new java.sql.Date(Package.getSentDate().getTime()) + "', " +
                Package.getWeight() + ", " +
                Package.getHeight() + ", " +
                Package.getLength() + ", " +
                Package.getBreadth() + ", " +
                Package.getPrice() + ")";

        int generatedId = 0;
        try {
            Statement statement = db.getConnection().createStatement();

            // Execute the query
            int affectedRows = statement.executeUpdate(query);

            if (affectedRows > 0) {
                // Retrieve the generated keys
                String getInsertedKey = "SELECT LAST_INSERT_ID();";
                try{
                    Statement retrieveStatement = db.getConnection().createStatement();
                    retrieveStatement.executeQuery(getInsertedKey);
                    ResultSet data = retrieveStatement.getResultSet();
                    if (data.next()){
                        generatedId=data.getInt(1);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            System.out.println("Package Added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    /**
     * @author Kautuk Prasad
     * @return Single Table Instance
     */
    public static PackageTableCRUD getInstance(){
        if(instance == null){
            instance = new PackageTableCRUD();
        }
        return instance;
    }


}
