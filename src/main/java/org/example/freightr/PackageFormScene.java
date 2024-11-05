package org.example.freightr;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class PackageFormScene {

    public static Scene CreatePackageFormScene(Stage stage) {

// making form using custom label and textfiled
        CustomLabel descriptionLabel = new CustomLabel("Parcel Description:");
        CustomTextField descriptionField = new CustomTextField();

        CustomLabel heightLabel = new CustomLabel("Height (cm)");
        CustomTextField heightField = new CustomTextField();

        CustomLabel widthLabel = new CustomLabel("Width (cm)");
        CustomTextField widthField = new CustomTextField();

        CustomLabel lengthLabel = new CustomLabel("Length (cm)");
        CustomTextField lengthField = new CustomTextField();

        CustomLabel weightLabel = new CustomLabel("Weight (kg)");
        CustomTextField weightField = new CustomTextField();

        CustomLabel customerIdLabel = new CustomLabel("Customer ID/Name:");
        CustomTextField customerIdField = new CustomTextField();

        CustomLabel receiverAddressLabel = new CustomLabel("Receiver Address:");
        CustomTextField receiverAddressField = new CustomTextField();

        CustomLabel chargesLabel = new CustomLabel("Total Charges:");
        CustomLabel totalCharges = new CustomLabel("");

        Button calculateChargesBtn = new Button("Calculate Charges");
        Button newOrderBtn = new Button("Create New Order");

        // made grid pane to accomodate all the components
        GridPane grid = new GridPane();
        // giving grid pane styles
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
// adding all components to grid pane
        grid.add(descriptionLabel, 0, 0);
        grid.add(descriptionField, 1, 0);
        grid.add(heightLabel, 0, 1);
        grid.add(heightField, 1, 1);
        grid.add(widthLabel, 0, 2);
        grid.add(widthField, 1, 2);
        grid.add(lengthLabel, 0, 3);
        grid.add(lengthField, 1, 3);
        grid.add(weightLabel, 0, 4);
        grid.add(weightField, 1, 4);
        grid.add(customerIdLabel, 0, 5);
        grid.add(customerIdField, 1, 5);
        grid.add(receiverAddressLabel, 0, 6);
        grid.add(receiverAddressField, 1, 6);
        grid.add(calculateChargesBtn, 0, 7);
        grid.add(chargesLabel, 1, 7);
        grid.add(totalCharges, 1, 8);
        grid.add(newOrderBtn, 0, 9);


        NavigationVBox navigationVbox = new NavigationVBox(stage);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(grid);


        return new Scene(root, 900, 640);


    }


}