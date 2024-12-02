package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.LocationDOA;
import org.example.freightr.TableCreation.ObjectClasses.LocationForStats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * @description This class handles fetching the package count per location from the database.
 */
public class LocationTable implements LocationDOA {

    private ArrayList<LocationForStats> locations;
    private Database db = Database.getInstance();
    private static LocationTable instance;

    @Override
    public ArrayList<LocationForStats> getPackagesAtLocations() {
        String query = "SELECT " + TRACKING_COLUMN_LOCATION + ", COUNT(" + TRACKING_COLUMN_PACKAGE_ID + ") AS package_count " +
                "FROM " + TABLE_PACKAGE_TRACKING + " " +
                "GROUP BY " + TRACKING_COLUMN_LOCATION;
        locations = new ArrayList<>();
        try {
            Statement getLocations = db.getConnection().createStatement();
            ResultSet data = getLocations.executeQuery(query);
            while (data.next()) {
                locations.add(
                        new LocationForStats(
                                data.getString(TRACKING_COLUMN_LOCATION),
                                data.getInt("package_count")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public static LocationTable getInstance() {
        if (instance == null) {
            instance = new LocationTable();
        }
        return instance;
    }
}
