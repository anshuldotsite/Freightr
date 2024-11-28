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

    private PackageTrackingTable(){
        db= Database.getInstance();
    }
    private static PackageTrackingTable instance;
    ArrayList<PackageTracking> packageTracks;
     Database db = Database.getInstance();

    @Override
    public void addPackageTracking(PackageTracking packageTracking) {
        String query = "INSERT INTO " + Dbconst.TABLE_PACKAGE_TRACKING + " (" +
                TRACKING_COLUMN_PACKAGE_ID + ", " +
                TRACKING_COLUMN_LOCATION + ", " +
                TRACKING_COLUMN_STATUS + ") VALUES (" +
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



    @Override
    public PackageTracking getPackageTracking(int trackingId) {
        String query = "SELECT * FROM " + Dbconst.TABLE_PACKAGE_TRACKING + " WHERE " +
                TRACKING_COLUMN_ID + " = " + trackingId;
        PackageTracking packageTracking = new PackageTracking();
        try ( Statement statement = db.getConnection().createStatement();
              ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                packageTracking = new PackageTracking(trackingId,
                        resultSet.getInt(TRACKING_COLUMN_PACKAGE_ID),
                        resultSet.getString(TRACKING_COLUMN_LOCATION),
                        resultSet.getString(TRACKING_COLUMN_STATUS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageTracking;
    }

    /**
     * @author Kautuk Prasad
     * @description Returns all data from package table and stores in an arraylist
     * @return All data in PackageTrack Table
     */
    @Override
    public ArrayList<PackageTracking> getAllPackageTracking() {
        String query = "SELECT * FROM " + TABLE_PACKAGE_TRACKING;
        packageTracks = new ArrayList<PackageTracking>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet data = statement.executeQuery(query);
            while(data.next()) {
                packageTracks.add(new PackageTracking(data.getInt(Dbconst.TRACKING_COLUMN_ID),
                        data.getInt(TRACKING_COLUMN_PACKAGE_ID),
                        data.getString(TRACKING_COLUMN_LOCATION),
                        data.getString(TRACKING_COLUMN_STATUS)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageTracks;
    }
    @Override
    public void updatePackageTracking(PackageTracking packageTracking) {
        String query = "UPDATE " + Dbconst.TABLE_PACKAGE_TRACKING + " SET " +
                TRACKING_COLUMN_LOCATION + " = '" + packageTracking.getLocation() + "', " +
                TRACKING_COLUMN_STATUS + " = '" + packageTracking.getStatus() + "' WHERE " +
                TRACKING_COLUMN_ID + " = " + packageTracking.getTrackingId();
        try {
            Statement statement = db.getConnection().createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @return Single Table instance
     */
    public static PackageTrackingTable getInstance(){
        if(instance == null){
            instance = new PackageTrackingTable();
        }
        return instance;
    }



}
