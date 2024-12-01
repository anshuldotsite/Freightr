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

public class AllPackageTrackingScene {
    private static TableView tableView;

    public static Scene createAllPackageTrackingScene(Stage stage) {
        BorderPane bp = new BorderPane();

        StatusTable statusTable = StatusTable.getInstance();

        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Update Package");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);


        ComboBox<StatusPOJO> statusComboBox = new ComboBox<>();
        statusComboBox.setItems(FXCollections.observableArrayList(statusTable.getAllStatus()));
        statusComboBox.getSelectionModel().select(0);
        statusComboBox.setOnAction(event -> filterTableByStatus(statusComboBox.getValue().getStatus()));


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

        tableView.getColumns().addAll(column1, column2, column3, column4);


        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        ObservableList<PackageCustomTracking> data = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(1)
        );

        HBox buttonBox = new HBox();

        Button viewDetailsButton = new Button("View Details");
        Button updateButton = new Button("Update");
        updateButton.setDisable(true);
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");

        VBox alignBox = new VBox();

        buttonBox.getChildren().addAll(viewDetailsButton,updateButton);
        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.setSpacing(10);

        alignBox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        alignBox.setAlignment(Pos.BASELINE_CENTER);

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



        bp.setCenter(tableView);
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        navigationVBox.getChildren().add(statusComboBox);
        bp.setLeft(navigationVBox);
        bp.setBottom(alignBox);
        bp.setTop(headingBox);
        // Return Scene
        return new Scene(bp, 900, 640);
    }


    private static void filterTableByStatus(String selectedStatus) {
        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        int statusId = getStatusIdFromString(selectedStatus);

        // Fetch and set data according to selected status
        ObservableList<PackageCustomTracking> filteredData = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(statusId)
        );


        tableView.setItems(filteredData);
    }


    private static int getStatusIdFromString(String status) {
        switch (status) {
            case "In Transit": return 1;
            case "In Warehouse": return 2;
            case "Delivered": return 3;
            default: return 1;
        }
    }
}
