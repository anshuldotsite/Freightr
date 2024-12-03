package org.example.freightr.TableCreation.NewClassesForStatisticsCharts;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.CustomerCountByCity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerTable {
    private Database db = Database.getInstance();
    private static CustomerTable instance;

    public static CustomerTable getInstance() {
        if (instance == null) {
            instance = new CustomerTable();
        }
        return instance;
    }

    public ArrayList<CustomerCountByCity> getCustomerCountByCity() {
        ArrayList<CustomerCountByCity> customerCounts = new ArrayList<>();
        String query = "SELECT city, COUNT(*) as customer_count FROM customer GROUP BY city";

        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String city = resultSet.getString("city");
                int count = resultSet.getInt("customer_count");
                customerCounts.add(new CustomerCountByCity(city, count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCounts;
    }
}
