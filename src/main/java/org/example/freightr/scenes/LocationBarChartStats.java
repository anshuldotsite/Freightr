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
import org.example.freightr.TableCreation.LocationTable;
import org.example.freightr.TableCreation.ObjectClasses.LocationForStats;

import java.util.ArrayList;

public class LocationBarChartStats {

    private static BarChart<String, Number> barChart;
    private static LocationBarChartStats instance;

    public static Scene createBarChartScene(Stage stage) {


        CategoryAxis LocationAxis = new CategoryAxis();
        NumberAxis NumberAxis = new NumberAxis();


        LocationAxis.setLabel("Location");
        NumberAxis.setLabel("Package Count");

        // Create the BarChart with the axes
        barChart = new BarChart<>(LocationAxis, NumberAxis);
        barChart.setTitle("Packages by Location");

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(event -> {
            LocationBarChartStats.getInstance().generateChart();
        });

        LocationBarChartStats.getInstance().generateChart();

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
        LocationTable locationTable = LocationTable.getInstance();

        // Fetch the locations and package count data
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

    public static LocationBarChartStats getInstance() {
        if (instance == null) {
            instance = new LocationBarChartStats();
        }
        return instance;
    }
}
