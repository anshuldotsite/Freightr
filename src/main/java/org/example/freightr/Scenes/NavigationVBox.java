package org.example.freightr.Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Scenes.PackageCRUDScenes.AddPackageScene;

/**
 * @description This class has a navigation vbox has a vbox for navigation between
 * different scenes
 */
public class NavigationVBox extends VBox {
    private Stage stage;
    public NavigationVBox(Stage stage) {
        this.stage = stage;

        //Getting employee's name
        LoginPageScene loginPageScene = LoginPageScene.getInstance();
        String employeeName = loginPageScene.getEmployeeName();

        //Signed in Employee's Name
        CustomLabel employeeLabel = new CustomLabel(employeeName);

        // Button for create new package
        Button Button1 = new Button("Create New Package");
        // Button for track package
        Button button2 = new Button("Track Package");
        // Button for navigation to all customers
        Button button3 = new Button("All Customers");
        // Button for navigation to all packages
        Button button4 = new Button("All Packages");
        // Button for navigation to package tracking
        Button button6 = new Button("All Packages Tracking");
        // Button for seeing stats
        Button button7 = new Button("Statistics");
        // Button to see the bar chart
        Button button8 = new Button("barchart location");
        // Button to log out from the application
        Button button5 = new Button("Logout");

        // Event handler for all buttons to switch between scenes
        Button1.setOnAction(e -> stage.setScene(AddPackageScene.createAddPackage(stage)));
        button2.setOnAction(e -> stage.setScene(TrackPackageScene.createTrackPackageScene(stage)));
        button3.setOnAction(e -> stage.setScene(DisplayCustomerScene.createDisplayCustomer(stage)));
        button4.setOnAction(e -> stage.setScene(DisplayPackageScene.createDisplayPackage(stage)));
        button5.setOnAction(e -> stage.setScene(LoginPageScene.createLoginPage(stage)));
        button6.setOnAction(e -> stage.setScene(AllPackageTrackingScene.createAllPackageTrackingScene(stage)));
        button7.setOnAction(e -> stage.setScene(StatisticsScene.createStatisticScene(stage)));
        button8.setOnAction(e -> stage.setScene(LocationBarChartStats.createBarChartScene(stage)));

        // Layout and styling
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: lightgray;");

        // Add all buttons to the vbox
        this.getChildren().addAll(employeeLabel,Button1, button2, button3, button4, button6,button7,button8,button5);
    }
}