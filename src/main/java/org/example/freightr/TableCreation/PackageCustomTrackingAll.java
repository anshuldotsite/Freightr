package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;
import org.example.freightr.TableCreation.ObjectClasses.PackageTrackAllDOA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

public class PackageCustomTrackingAll implements PackageTrackAllDOA {
    private final Database db = Database.getInstance();
    private static PackageCustomTrackingAll instance;

    @Override
    public ArrayList<PackageCustomTracking> getAllPackageTrackingWithStatus(int statusId) {
        String query = "SELECT pt." + TRACKING_COLUMN_ID + ", " +
                "pt." + TRACKING_COLUMN_PACKAGE_ID + ", " +
                "pt." + TRACKING_COLUMN_LOCATION + ", " +
                "pt." + TRACKING_COLUMN_STATUS + ", " +
                "p." + PACKAGE_COLUMN_DESCRIPTION + ", " +
                "p." + PACKAGE_COLUMN_SENT_DATE +
                " FROM " + TABLE_PACKAGE_TRACKING + " pt " +
                "JOIN " + TABLE_PACKAGE + " p ON pt." + TRACKING_COLUMN_PACKAGE_ID + " = p." + PACKAGE_COLUMN_ID + " " +
                "WHERE pt." + TRACKING_COLUMN_STATUS + " = " + statusId;


        ArrayList<PackageCustomTracking> packageTracks = new ArrayList<>();
        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                PackageCustomTracking packageTracking = new PackageCustomTracking(
                        resultSet.getInt(TRACKING_COLUMN_PACKAGE_ID),
                        resultSet.getString(PACKAGE_COLUMN_DESCRIPTION),
                        resultSet.getDate(PACKAGE_COLUMN_SENT_DATE),
                        resultSet.getInt(TRACKING_COLUMN_ID),
                        resultSet.getString(TRACKING_COLUMN_LOCATION),
                        resultSet.getInt(TRACKING_COLUMN_STATUS)

                );
                packageTracks.add(packageTracking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageTracks;
    }

    /**
     * @author Kautuk Prasad
     * @description Takes in the package object and updates it in the table
     * @param packageCustomTracking
     */
    @Override
    public void updatePackage(PackageCustomTracking packageCustomTracking) {
        String query = "UPDATE " + TABLE_PACKAGE_TRACKING + " SET " +
                TRACKING_COLUMN_PACKAGE_ID + " = " + packageCustomTracking.getPackageId() +", " +
                TRACKING_COLUMN_LOCATION + " = '" + packageCustomTracking.getLocation()+ "', " +
                TRACKING_COLUMN_STATUS + " = "+ packageCustomTracking.getStatusId() +
                " WHERE " + TRACKING_COLUMN_ID + " = "+ packageCustomTracking.getTrackingId();

        try {
            Statement updateItem = db.getConnection().createStatement();
            System.out.println("Record Updated");
            updateItem.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @description  This is a method to get the packages count.
     */
    @Override
    public int getPackageCount(int packageTrack) {
        int count = -1;
        try {
            PreparedStatement getCount = db.getConnection()
                    .prepareStatement("SELECT * FROM " + TABLE_PACKAGE_TRACKING + " WHERE "
                                    + TRACKING_COLUMN_STATUS + " = '" + packageTrack + "'", ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static PackageCustomTrackingAll getInstance(){
        if(instance == null){
            instance = new PackageCustomTrackingAll();
        }
        return instance;
    }
}
