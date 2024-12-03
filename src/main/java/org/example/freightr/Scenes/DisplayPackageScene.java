package org.example.freightr.Scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.PackageTableCRUD;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.Scenes.PackageCRUDScenes.PackageDetailsScene;

import java.text.SimpleDateFormat;

/**
 * @description  This scene displays all package details
 */
public class DisplayPackageScene {
    private static DisplayPackageScene instance;
    private static TableView tableView;

    public static Scene createDisplayPackage(Stage stage) {
        PackageTableCRUD packageTable = PackageTableCRUD.getInstance();

        // HBox for heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Packages");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // A tableview object
        tableView = new TableView();

        // Column for Package ID
        TableColumn<Package, String> column = new TableColumn<>("Package ID");
        column.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getPackageId())));

        // Column for Description
        TableColumn<Package, String> column1 = new TableColumn<>("Description");
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPackageDescription()));

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

        // Navigation VBox for navigation to different scenes
        NavigationVBox navigationVbox = new NavigationVBox(stage);

        // Button to delete package and event handler to delete packages
        CustomButton deleteButton = new CustomButton("Delete Package");
        deleteButton.setDisable(true);
        deleteButton.setOnAction(event -> {
            Package deletePackage = (Package) tableView.getSelectionModel().getSelectedItem();
            packageTable.deletePackage(deletePackage.getPackageId());
            DisplayPackageScene displayPackageScene = DisplayPackageScene.getInstance();
            displayPackageScene.refreshTable();
        });

        // Button to view details of the package
        CustomButton viewDetailsButton = new CustomButton("View Details");
        viewDetailsButton.setDisable(true);
        viewDetailsButton.setOnAction(event -> {
            Package selectedPackage = (Package) tableView.getSelectionModel().getSelectedItem();
            if (selectedPackage != null) {
                int packageId = selectedPackage.getPackageId();
                Scene detailsScene = PackageDetailsScene.CreateSenderReciverDetailsPage(packageId, stage);
                stage.setScene(detailsScene);
            }
        });

        // Enable/Disable button that checks based on the selection in table view
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

        // HBox for layout
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(deleteButton, viewDetailsButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // VBox for layout
        VBox vBox = new VBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");
        vBox.getChildren().addAll(emptyLabel, buttonBox, emptyLabel2);

        vBox.setAlignment(Pos.BASELINE_CENTER);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(tableView);
        root.setTop(headingBox);
        root.setBottom(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
    /**
     * @description This method refreshes the table to display all packages
     */
    public void refreshTable() {
        PackageTableCRUD packageTableCRUD = PackageTableCRUD.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(packageTableCRUD.getAllPackages());
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

