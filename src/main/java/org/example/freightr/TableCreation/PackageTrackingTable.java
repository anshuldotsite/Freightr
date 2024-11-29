package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.PackageTrackDOA;
import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

public class PackageTrackingTable implements PackageTrackDOA {

    private static PackageTrackingTable instance;
    private final Database db;

    private PackageTrackingTable() {
        this.db = Database.getInstance();
    }
    @Override
    public  void addPackageTracking(PackageTracking packageTracking) {
        String query = "INSERT INTO " + TABLE_PACKAGE_TRACKING + " (" +
                TRACKING_COLUMN_PACKAGE_ID + ", " +
                TRACKING_COLUMN_LOCATION + ", " +
                TRACKING_COLUMN_STATUS + ") VALUES (" +
                packageTracking.getPackageId() + ", '" +
                packageTracking.getLocation() + "', " +
                packageTracking.getStatusId() + ")";
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Package Tracking added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding Package Tracking: " + e.getMessage());
        }
    }
    @Override
    public PackageTracking getPackageTracking(int trackingId) {
        String query = "SELECT * FROM " + TABLE_PACKAGE_TRACKING + " WHERE " +
                TRACKING_COLUMN_ID + " = " + trackingId;
        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new PackageTracking(
                        trackingId,
                        resultSet.getInt(TRACKING_COLUMN_PACKAGE_ID),
                        resultSet.getString(TRACKING_COLUMN_LOCATION),
                        resultSet.getInt(TRACKING_COLUMN_STATUS));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Package Tracking: " + e.getMessage());
        }
        return null;
    }
    @Override
    public ArrayList<PackageTracking> getAllPackageTracking() {
        String query = "SELECT * FROM " + TABLE_PACKAGE_TRACKING;
        ArrayList<PackageTracking> packageTracks = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                packageTracks.add(new PackageTracking(
                        resultSet.getInt(TRACKING_COLUMN_ID),
                        resultSet.getInt(TRACKING_COLUMN_PACKAGE_ID),
                        resultSet.getString(TRACKING_COLUMN_LOCATION),
                        resultSet.getInt(TRACKING_COLUMN_STATUS)));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all Package Tracking records: " + e.getMessage());
        }
        return packageTracks;
    }
    @Override
    public void updatePackageTracking(PackageTracking packageTracking) {
        String query = "UPDATE " + TABLE_PACKAGE_TRACKING + " SET " +
                TRACKING_COLUMN_LOCATION + " = '" + packageTracking.getLocation() + "', " +
                TRACKING_COLUMN_STATUS + " = " + packageTracking.getStatusId() + " WHERE " +
                TRACKING_COLUMN_ID + " = " + packageTracking.getTrackingId();
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Package Tracking updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating Package Tracking: " + e.getMessage());
        }
    }
    public static PackageTrackingTable getInstance() {
        if (instance == null) {
            instance = new PackageTrackingTable();
        }
        return instance;
    }
}
