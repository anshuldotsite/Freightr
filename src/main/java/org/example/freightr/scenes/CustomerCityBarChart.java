package org.example.freightr.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.NewClassesForStatisticsCharts.CustomerTable;
import org.example.freightr.TableCreation.ObjectClasses.CustomerCountByCity;

import java.util.ArrayList;

public class CustomerCityBarChart {

    private static BarChart<String, Number> barChart;
    private static CustomerCityBarChart instance;

    public static Scene createBarChartScene(Stage stage) {
        // Define axes
        CategoryAxis cityAxis = new CategoryAxis();
        NumberAxis countAxis = new NumberAxis();

        cityAxis.setLabel("City");
        countAxis.setLabel("Customer Count");

        // Create bar chart
        barChart = new BarChart<>(cityAxis, countAxis);
        barChart.setTitle("Customer Count by City");

        // Refresh button
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(event -> {
            CustomerCityBarChart.getInstance().generateChart();
        });

        // Generate initial chart
        CustomerCityBarChart.getInstance().generateChart();

        // Layout setup
        VBox buttonBox = new VBox();
        buttonBox.getChildren().addAll(refreshBtn);
        buttonBox.setAlignment(Pos.CENTER);

        NavigationVBox navigationVBox = new NavigationVBox(stage);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVBox);
        root.setCenter(barChart);
        root.setBottom(buttonBox);

        return new Scene(root, 900, 640);
    }

    public void generateChart() {
        CustomerTable customerTable = CustomerTable.getInstance();
        ArrayList<CustomerCountByCity> customerData = customerTable.getCustomerCountByCity();
        ObservableList<BarChart.Series<String, Number>> chartData = FXCollections.observableArrayList();

        BarChart.Series<String, Number> series = new BarChart.Series<>();
        series.setName("Customer Count");

        for (CustomerCountByCity data : customerData) {
            series.getData().add(new BarChart.Data<>(data.getCity(), data.getCustomerCount()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    public static CustomerCityBarChart getInstance() {
        if (instance == null) {
            instance = new CustomerCityBarChart();
        }
        return instance;
    }
}
