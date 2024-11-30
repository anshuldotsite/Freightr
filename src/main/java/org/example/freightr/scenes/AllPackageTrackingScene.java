package org.example.freightr.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.PackageCustomTrackingAll;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;
import org.example.freightr.scenes.packageFormCreationAllScenes.ShowSenderReciverDetailsScene;

import java.text.SimpleDateFormat;
import java.util.Date;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class AllPackageTrackingScene {
    private static TableView tableView;

    public static Scene createAllPackageTrackingScene(Stage stage) {
        BorderPane bp = new BorderPane();


        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.setItems(FXCollections.observableArrayList("In Transit", "In Warehouse", "Delivered"));
        statusComboBox.setValue("In Transit");
        statusComboBox.setOnAction(event -> filterTableByStatus(statusComboBox.getValue()));

        VBox topLayout = new VBox(statusComboBox);
        topLayout.setAlignment(Pos.TOP_RIGHT);
        bp.setTop(topLayout);

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
        column3.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getTrackingId()));

        TableColumn<PackageCustomTracking, String> column4 = new TableColumn<>("Location");
        column4.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLocation()));

        tableView.getColumns().addAll(column1, column2, column3, column4);


        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        ObservableList<PackageCustomTracking> data = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(1)
        );

        Button viewDetailsButton = new Button("View Details");

        viewDetailsButton.setOnAction(event -> {
            PackageCustomTracking trackingData1 = (PackageCustomTracking) tableView.getSelectionModel().getSelectedItem();
            if (trackingData1 != null) {
                int packageId = trackingData1.getPackageId();
                Scene detailsScene = ShowSenderReciverDetailsScene.CreateSenderReciverDetailsPage(packageId, stage);
                stage.setScene(detailsScene);
            }
        });
        tableView.setItems(data);

        HBox hBox =new HBox();
        hBox.getChildren().addAll(viewDetailsButton);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        bp.setCenter(tableView);
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        bp.setLeft(navigationVBox);
bp.setBottom(hBox);
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
