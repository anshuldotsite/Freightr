package org.example.freightr;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ParcelFormScene  {




    public static Scene CreateParcelFormScene(Stage stage) {

        Label descriptionLabel = new Label("Parcel Description:");
        TextField descriptionField = new TextField();

        Label heightLabel = new Label("Height (m):");
        TextField heightField = new TextField();

        Label widthLabel = new Label("Width (m):");
        TextField widthField = new TextField();

        Label lengthLabel = new Label("Length (m):");
        TextField lengthField = new TextField();

        Label weightLabel = new Label("Weight (kg):");
        TextField weightField = new TextField();

        Label customerIdLabel = new Label("Customer ID/Name:");
        TextField customerIdField = new TextField();

        Label receiverAddressLabel = new Label("Receiver Address:");
        TextField receiverAddressField = new TextField();

        Label chargesLabel = new Label("Total Charges: ");
        Label totalCharges = new Label();

        Button calculateChargesBtn = new Button("Calculate Charges");
        Button newOrderBtn = new Button("Create New Order");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

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



        NavigationVbox navigationVbox=new NavigationVbox(stage);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(grid);


return new Scene(root,900,640);


    }



}