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
        employeeLabel.setStyle("-fx-text-fill: #F8EDE3;-fx-font-size: 22px; -fx-padding: 10px;");

        // Button for create new package
        CustomNavButton Button1 = new CustomNavButton("Create a new package");
        // Button for track package
        CustomNavButton button2 = new CustomNavButton("Track your package by ID");
        // Button for navigation to all customers
        CustomNavButton button3 = new CustomNavButton("Your customers");
        // Button for navigation to all packages
        CustomNavButton button4 = new CustomNavButton("Your packages");
        // Button for navigation to package tracking
        CustomNavButton button6 = new CustomNavButton("Track your packages");
        // Button to see package status
        CustomNavButton button7 = new CustomNavButton("Current package status");
        // Button to see the packages by location
        CustomNavButton button8 = new CustomNavButton("Current package location");
        // Button to see the number of customers per city
        CustomNavButton button9 = new CustomNavButton("View customers by city");
        // Button to log out from the application
        CustomNavButton button5 = new CustomNavButton("Logout");

        // Event handler for all buttons to switch between scenes
        Button1.setOnAction(e -> stage.setScene(AddPackageScene.createAddPackage(stage)));
        button2.setOnAction(e -> stage.setScene(TrackPackageScene.createTrackPackageScene(stage)));
        button3.setOnAction(e -> stage.setScene(DisplayCustomerScene.createDisplayCustomer(stage)));
        button4.setOnAction(e -> stage.setScene(DisplayPackageScene.createDisplayPackage(stage)));
        button5.setOnAction(e -> stage.setScene(LoginPageScene.createLoginPage(stage)));
        button6.setOnAction(e -> stage.setScene(AllPackageTrackingScene.createAllPackageTrackingScene(stage)));
        button7.setOnAction(e -> stage.setScene(StatisticsScene.createStatisticScene(stage)));
        button8.setOnAction(e -> stage.setScene(LocationBarChartStats.createBarChartScene(stage)));
        button9.setOnAction(e -> stage.setScene(CustomerCityBarChart.createBarChartScene(stage)));

        // Layout and styling
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        this.setStyle("-fx-padding: 20px;-fx-background-color: #C5705D;");

        // Add all buttons to the vbox

        this.getChildren().addAll(employeeLabel,Button1, button2, button3, button4, button6,button7,button8,button9,button5);

    }
}