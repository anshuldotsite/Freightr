package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PackageTrackingDOA {

    public PackageTrackingDOA(){
        db= Database.getInstance();
    }


     Database db = Database.getInstance();


    public void addPackageTracking(PackageTracking packageTracking) {
        String query = "INSERT INTO " + Dbconst.TABLE_PACKAGE_TRACKING + " (" +
                Dbconst.TRACKING_COLUMN_PACKAGE_ID + ", " +
                Dbconst.TRACKING_COLUMN_LOCATION + ", " +
                Dbconst.TRACKING_COLUMN_STATUS + ") VALUES (" +
                packageTracking.getPackageId() + ", '" +
                packageTracking.getLocation() + "', '" +
                packageTracking.getStatus() + "')";
        try (Statement statement = db.getConnection().createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Package Tracking Added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public PackageTracking getPackageTracking(int trackingId) {
        String query = "SELECT * FROM " + Dbconst.TABLE_PACKAGE_TRACKING + " WHERE " +
                Dbconst.TRACKING_COLUMN_ID + " = " + trackingId;
        PackageTracking packageTracking = null;
        try ( Statement statement = db.getConnection().createStatement();
              ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                int packageId = resultSet.getInt(Dbconst.TRACKING_COLUMN_PACKAGE_ID);
                String location = resultSet.getString(Dbconst.TRACKING_COLUMN_LOCATION);
                String status = resultSet.getString(Dbconst.TRACKING_COLUMN_STATUS);
                packageTracking = new PackageTracking(trackingId, packageId, location, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageTracking;
    }

    public boolean updatePackageTracking(PackageTracking packageTracking) {
        String query = "UPDATE " + Dbconst.TABLE_PACKAGE_TRACKING + " SET " +
                Dbconst.TRACKING_COLUMN_LOCATION + " = '" + packageTracking.getLocation() + "', " +
                Dbconst.TRACKING_COLUMN_STATUS + " = '" + packageTracking.getStatus() + "' WHERE " +
                Dbconst.TRACKING_COLUMN_ID + " = " + packageTracking.getTrackingId();
        try ( Statement statement = db.getConnection().createStatement()
              ) {
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
