package org.example.freightr.TableCreation.NewClassesForStatisticsCharts;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.CustomerCountByCity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerTable {
    private static CustomerTable instance;
    private Database db = Database.getInstance();

    public static CustomerTable getInstance() {
        if (instance == null) {
            instance = new CustomerTable();
        }
        return instance;
    }

    public ArrayList<CustomerCountByCity> getCustomerCountByCity() {
        ArrayList<CustomerCountByCity> customerCounts = new ArrayList<>();
        String query = "SELECT city, COUNT(*) as customer_count FROM customer GROUP BY city";

        try (Statement getLocations = db.getConnection().createStatement();
             ResultSet data = getLocations.executeQuery(query)) {

            while (data.next()) {
                String city = data.getString("city");
                int count = data.getInt("customer_count");
                customerCounts.add(new CustomerCountByCity(city, count));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return customerCounts;
    }
}
