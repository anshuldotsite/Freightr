package org.example.freightr.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;
import org.example.freightr.TableCreation.PackageCustomTrackingAll;
import org.example.freightr.TableCreation.StatusTable;

import java.util.ArrayList;

/**
 * @author Kautuk Prasad
 * @description This is the statistics scene which creates and manages stats
 * and displays them in a pie chart
 */
public class StatisticsScene {
    // Member Variables
    private static PieChart pieChart;
    private static StatisticsScene instance;
    public static Scene createStatisticScene(Stage stage){
        // Initialise pie chart
        pieChart = new PieChart();
        pieChart.setTitle("All Packages Status");
        pieChart.setLabelsVisible(false);

        // Refresh button to refresh data
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(event -> {
            StatisticsScene.getInstance().generateChart();
        });

        // Generate chart data
        StatisticsScene.getInstance().generateChart();

        // Layout for refresh button
        VBox buttonBox = new VBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");
        buttonBox.getChildren().addAll(emptyLabel,refreshBtn,emptyLabel2);
        buttonBox.setAlignment(Pos.CENTER);

        // NavigationVBox for navigation to different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setLeft(navigationVBox);
        root.setCenter(pieChart);
        root.setBottom(buttonBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    // Generates chart data for the pie chart and updates the data based on the package status
    public void generateChart(){
        PackageCustomTrackingAll packageCustomTrackingAll = PackageCustomTrackingAll.getInstance();
        StatusTable statusTable = StatusTable.getInstance();
        ArrayList<StatusPOJO> status = statusTable.getAllStatus();
        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (StatusPOJO statuses: status){
            double count = packageCustomTrackingAll.getPackageCount(statuses.getId());
            if (count>0){
                data.add(new PieChart.Data(statuses.getStatus(), count));
            }
        }
        ObservableList<PieChart.Data> chartData
                = FXCollections.observableArrayList(data);
        pieChart.setData(chartData);
    }

    // Singleton instance
    public static StatisticsScene getInstance() {
        if (instance==null){
            instance=new StatisticsScene();
        }
        return instance;
    }
}
