package org.example.freightr.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.PackageTableCred;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.scenes.packageFormCreationAllScenes.AddPackageScene;
import org.example.freightr.scenes.packageFormCreationAllScenes.ShowSenderReciverDetailsScene;

import java.text.SimpleDateFormat;

public class DisplayPackageScene {

    private static DisplayPackageScene instance;
    private static TableView tableView;

    public static Scene createDisplayPackage(Stage stage) {
        PackageTableCred packageTable = PackageTableCred.getInstance();

        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Packages");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        tableView = new TableView();

        // Column for Package ID
        TableColumn<Package, String> column = new TableColumn<>("Package ID");
        column.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getPackageId())));

        // Column for Description
        TableColumn<Package, String> column1 = new TableColumn<>("Description");
        column.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPackageDescription()));

        // Column for Sent Date
        TableColumn<Package, String> column2 = new TableColumn<>("Sent Date");
        column2.setCellValueFactory(e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new SimpleStringProperty(dateFormat.format(e.getValue().getSentDate()));
        });

        // Column for Weight
        TableColumn<Package, String> column3 = new TableColumn<>("Weight (kg)");
        column3.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f", e.getValue().getWeight())));

        // Column for Dimensions
        TableColumn<Package, String> column4 = new TableColumn<>("Dimensions (H x W x L)");
        column4.setCellValueFactory(e -> new SimpleStringProperty(String.format("%.2f x %.2f x %.2f",
                e.getValue().getHeight(),
                e.getValue().getBreadth(),
                e.getValue().getLength())));

        // Column for Price
        TableColumn<Package, String> column5 = new TableColumn<>("Price");
        column5.setCellValueFactory(e -> new SimpleStringProperty(String.format("$%.2f", e.getValue().getPrice())));

        tableView.getColumns().addAll(column, column1, column2, column3, column4, column5);
        tableView.getItems().addAll(packageTable.getAllPackages());

        NavigationVBox navigationVbox = new NavigationVBox(stage);

        Button deleteButton = new Button("Delete Package");
        deleteButton.setDisable(true);
        deleteButton.setOnAction(event -> {
            Package deletePackage = (Package) tableView.getSelectionModel().getSelectedItem();
            packageTable.deletePackage(deletePackage.getPackageId());
            DisplayPackageScene displayPackageScene = DisplayPackageScene.getInstance();
            displayPackageScene.refreshTable();
        });


        Button viewDetailsButton = new Button("View Details");
        viewDetailsButton.setDisable(true);
        viewDetailsButton.setOnAction(event -> {
            Package selectedPackage = (Package) tableView.getSelectionModel().getSelectedItem();
            if (selectedPackage != null) {
                int packageId = selectedPackage.getPackageId();
                Scene detailsScene = ShowSenderReciverDetailsScene.CreateSenderReciverDetailsPage(packageId, stage);
                stage.setScene(detailsScene);
            }
        });
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    deleteButton.setDisable(false);
                    viewDetailsButton.setDisable(false);
                } else {
                    deleteButton.setDisable(true);

                }
            }
        });



        HBox hBox = new HBox();
        hBox.getChildren().addAll(deleteButton,viewDetailsButton);



        BorderPane root = new BorderPane();
        root.setTop(headingBox);
        root.setLeft(navigationVbox);
        root.setCenter(tableView);
        root.setBottom(hBox);

        return new Scene(root, 900, 640);
    }

    /**
     * @description This method refreshes the table to display all packages
     */
    public void refreshTable() {
        PackageTableCred packageTableCred = PackageTableCred.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(packageTableCred.getAllPackages());
    }

    /**
     * @return Single instance of DisplayPackageScene
     */
    public static DisplayPackageScene getInstance() {
        if (instance == null) {
            instance = new DisplayPackageScene();
        }
        return instance;
    }
}

