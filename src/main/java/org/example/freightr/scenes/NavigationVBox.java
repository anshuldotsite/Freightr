package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;
import org.example.freightr.scenes.packageFormCreationAllScenes.AddPackageScene;
import org.example.freightr.scenes.packageFormCreationAllScenes.PackageFormScene;

public class NavigationVBox extends VBox {

    private Stage stage;

    public NavigationVBox(Stage stage) {
        this.stage = stage;

        Button Button1 = new Button("Create New Package");
        Button button2 = new Button("Track Package");
        Button button3 = new Button("All Customers");
        Button button4 = new Button("All Packages");
        Button button6 = new Button("All Packages Tracking");
        Button button7 = new Button("Statistics");
        Button button8 = new Button("barchart location");
        Button button9 = new Button("City Wise Customer");
        Button button5 = new Button("Logout");


        Button1.setOnAction(e -> stage.setScene(AddPackageScene.createAddPackage(stage)));
        button2.setOnAction(e -> stage.setScene(TrackPackageScene.createTrackPackageScene(stage)));
        button3.setOnAction(e -> stage.setScene(DisplayCustomerScene.createDisplayCustomer(stage)));
        button4.setOnAction(e -> stage.setScene(DisplayPackageScene.createDisplayPackage(stage)));
        button5.setOnAction(e -> stage.setScene(LoginPageScene.createLoginPage(stage)));
        button6.setOnAction(e -> stage.setScene(AllPackageTrackingScene.createAllPackageTrackingScene(stage)));
        button7.setOnAction(e -> stage.setScene(StatisticsScene.createStatisticScene(stage)));
        button8.setOnAction(e -> stage.setScene(LocationBarChartStats.createBarChartScene(stage)));
        button9.setOnAction(e -> stage.setScene(CustomerCityBarChart.createBarChartScene(stage)));
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: lightgray;");

        this.getChildren().addAll(Button1, button2, button3, button4, button6,button7,button8,button9,button5);
    }
}