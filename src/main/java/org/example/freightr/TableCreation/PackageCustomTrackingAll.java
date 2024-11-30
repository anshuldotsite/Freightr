package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

public class PackageCustomTrackingAll {
    private final Database db = Database.getInstance();


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
                        resultSet.getString(TRACKING_COLUMN_ID),
                        resultSet.getString(TRACKING_COLUMN_LOCATION),
                        resultSet.getInt(TRACKING_COLUMN_STATUS)

                );
                packageTracks.add(packageTracking);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Package Tracking records with status: " + e.getMessage());
        }
        return packageTracks;
    }


}
