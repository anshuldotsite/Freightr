package org.example.freightr.TableCreation.NewClassesForStatisticsCharts;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.StatusDOA;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * @author Kautuk Prasad
 * @description This is a class to perform CRUD all on the status table.
 */
public class StatusTable implements StatusDOA {

    private ArrayList<StatusPOJO> allStatus;
    Database db = Database.getInstance();
    private static StatusTable instance;

    @Override
    public ArrayList<StatusPOJO> getAllStatus() {
        String query = "SELECT * FROM " + TABLE_STATUS;
        allStatus = new ArrayList<StatusPOJO>();
        try {
            Statement getLocations = db.getConnection().createStatement();
            ResultSet data = getLocations.executeQuery(query);
            while(data.next()) {
                allStatus.add(
                        new StatusPOJO(
                                data.getInt(STATUS_COLUMN_ID),
                                data.getString(STATUS_COLUMN_NAME)
                        ));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return allStatus;
    }

    public static StatusTable getInstance(){
        if(instance == null){
            instance = new StatusTable();
        }
        return instance;
    }
}
