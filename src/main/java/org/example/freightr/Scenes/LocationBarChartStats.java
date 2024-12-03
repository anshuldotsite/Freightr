package org.example.freightr.Scenes;

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
import org.example.freightr.TableCreation.LocationTable;
import org.example.freightr.TableCreation.ObjectClasses.LocationForStats;

import java.util.ArrayList;

/**
 * @description This class generates and displays a bar chart and refreshes the chart date dynamically
 */
public class LocationBarChartStats {

    // Bar chart for displaying packages by location
    private static BarChart<String, Number> barChart;
    // Singleton instance of the class
    private static LocationBarChartStats instance;

    public static Scene createBarChartScene(Stage stage) {
        //Axis for locations and package counts
        CategoryAxis LocationAxis = new CategoryAxis();
        NumberAxis NumberAxis = new NumberAxis();

        // Labels for axis
        LocationAxis.setLabel("Location");
        NumberAxis.setLabel("Package Count");

        // Initialising bar chart with axes
        barChart = new BarChart<>(LocationAxis, NumberAxis);
        barChart.setTitle("Packages by Location");

        // Generate chart data
        LocationBarChartStats.getInstance().generateChart();

        // VBox for layout
        VBox buttonBox = new VBox();
        buttonBox.getChildren().addAll();
        buttonBox.setAlignment(Pos.CENTER);

        // NavigationVBox for navigation scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setLeft(navigationVBox);
        root.setCenter(barChart);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    // This method generates and updates the bar chart with data from the database
    public void generateChart() {
        LocationTable locationTable = LocationTable.getInstance();
        ArrayList<LocationForStats> locations = locationTable.getPackagesAtLocations();
        ObservableList<BarChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        BarChart.Series<String, Number> series = new BarChart.Series<>();
        series.setName("Package Count");
        for (LocationForStats location : locations) {
            int count = location.getPackageCount();
            if (count > 0) {
                series.getData().add(new BarChart.Data<>(location.getLocation(), count));
            }
        }
        barChart.getData().clear();
        barChart.getData().add(series);
    }

    // Singleton instance
    public static LocationBarChartStats getInstance() {
        if (instance == null) {
            instance = new LocationBarChartStats();
        }
        return instance;
    }
}
