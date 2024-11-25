package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.PackageTableCred;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;


public class PackageFormScene {
    private static double totalPrice;

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


        calculateChargesBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double weight = Double.parseDouble(weightField.getText());
            int heightSurcharge=0;
            int lengthSurcharge=0;
            int widthSurcharge=0;

            int WeightPrice =0;

             if(height>100){
                heightSurcharge=15;
            }
            if(width>100){
                widthSurcharge=15;
            }
            if(length>100){
                lengthSurcharge=15;
            }
            if(15<weight&&weight<25){
                WeightPrice=20;
            }
            else if(weight>25&&weight<50){
                WeightPrice=17;
            }
            else if (weight>50){
                WeightPrice=13;
            }else {
                WeightPrice=30;
            }
           totalPrice =heightSurcharge + widthSurcharge + lengthSurcharge + (WeightPrice * weight);

        });

        newOrderBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double weight = Double.parseDouble(weightField.getText());

            String description = descriptionField.getText();

           Date date = new java.sql.Date(System.currentTimeMillis());
            int id = 0;
            Package newPackage = new Package(id, description, date, weight, height, length, width, totalPrice);


            PackageTableCred packageTableCred = PackageTableCred.getInstance();
            packageTableCred.addPackage(newPackage);
        });



        return new Scene(root, 900, 640);


    }


}