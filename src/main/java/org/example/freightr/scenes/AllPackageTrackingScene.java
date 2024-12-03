package org.example.freightr.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;
import org.example.freightr.TableCreation.PackageCustomTrackingAll;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;
import org.example.freightr.TableCreation.StatusTable;
import org.example.freightr.scenes.packageFormCreationAllScenes.ShowSenderReciverDetailsScene;

import java.text.SimpleDateFormat;
import java.util.Date;

import static javafx.geometry.Pos.BOTTOM_CENTER;

/**
 * @description A scene to track all packages
 */
public class AllPackageTrackingScene {
    // A tableview variable
    private static TableView tableView;
    public static Scene createAllPackageTrackingScene(Stage stage) {
        // A BorderPane for layout
        BorderPane bp = new BorderPane();
        // A singleton instance for the database
        StatusTable statusTable = StatusTable.getInstance();

        // HBox for updating package
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Update Package");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // Combobox for tracking status
        ComboBox<StatusPOJO> statusComboBox = new ComboBox<>();
        statusComboBox.setItems(FXCollections.observableArrayList(statusTable.getAllStatus()));
        statusComboBox.getSelectionModel().select(0);
        statusComboBox.setOnAction(event -> filterTableByStatus(statusComboBox.getValue().getStatus()));

        // A tableview object
        tableView = new TableView<>();

        // Define Columns
        TableColumn<PackageCustomTracking, String> column1 = new TableColumn<>("Description");
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDescription()));

        TableColumn<PackageCustomTracking, String> column2 = new TableColumn<>("Sent Date");
        column2.setCellValueFactory(e -> {
            Date sentDate = e.getValue().getSentDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new SimpleStringProperty(dateFormat.format(sentDate));
        });

        TableColumn<PackageCustomTracking, String> column3 = new TableColumn<>("Tracking ID");
        column3.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getTrackingId())));

        TableColumn<PackageCustomTracking, String> column4 = new TableColumn<>("Location");
        column4.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLocation()));

        // Add columns to tableview
        tableView.getColumns().addAll(column1, column2, column3, column4);

        // Load data and set it to the TableView
        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        ObservableList<PackageCustomTracking> data = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(1)
        );

        // HBox for layout
        HBox buttonBox = new HBox();

        // Buttons for updating and viewing
        Button viewDetailsButton = new Button("View Details");
        Button updateButton = new Button("Update");
        updateButton.setDisable(true);
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");

        VBox alignBox = new VBox();

        // Adding all elements to the hbox
        buttonBox.getChildren().addAll(viewDetailsButton,updateButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        alignBox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        alignBox.setAlignment(Pos.CENTER);

        // Enable/Disable button that checks based on the selection in table view
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue!=null){
                    updateButton.setDisable(false);
                }else {
                    updateButton.setDisable(true);
                }
            }
        });

        /**
         * This event handler handles the view button which retrieves the selected package and navigates to a new scene
         * and update button retrieves the package and updates it and navigates the statys
         */
        viewDetailsButton.setOnAction(event -> {
            PackageCustomTracking trackingData1 = (PackageCustomTracking) tableView.getSelectionModel().getSelectedItem();
            if (trackingData1 != null) {
                int packageId = trackingData1.getPackageId();
                Scene detailsScene = ShowSenderReciverDetailsScene.CreateSenderReciverDetailsPage(packageId, stage);
                stage.setScene(detailsScene);
            }
        });

        updateButton.setOnAction(event -> {
            PackageCustomTracking selectedPackage = (PackageCustomTracking) tableView.getSelectionModel().getSelectedItem();
            Scene updatePackageStatus = UpdatePackageStatus.createUpdateStatus(stage,selectedPackage);
            stage.setScene(updatePackageStatus);
        });

        tableView.setItems(data);

        // Set up layout structure
        bp.setCenter(tableView);
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        navigationVBox.getChildren().add(statusComboBox);
        bp.setLeft(navigationVBox);
        bp.setBottom(alignBox);
        bp.setTop(headingBox);

        return new Scene(bp, 900, 640);
    }

    // Filters the table view data based on the selected status and updates the table view.
    private static void filterTableByStatus(String selectedStatus) {
        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        int statusId = getStatusIdFromString(selectedStatus);

        // Fetch and set data according to selected status
        ObservableList<PackageCustomTracking> filteredData = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(statusId)
        );

        tableView.setItems(filteredData);
    }

    // A status string for its ID
    private static int getStatusIdFromString(String status) {
        switch (status) {
            case "In Warehouse": return 1;
            case "In Transit": return 2;
            case "Delivered": return 3;
            default: return 1;
        }
    }
}
