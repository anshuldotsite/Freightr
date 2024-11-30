package org.example.freightr.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.PackageCustomTrackingAll;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AllPackageTrackingScene {

    public static Scene createAllPackageTrackingScene(Stage stage) {
        BorderPane bp = new BorderPane();


        TableView<PackageCustomTracking> tableView = new TableView<>();

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

        TableColumn<PackageCustomTracking, String> column5 = new TableColumn<>("Status ID");
        column5.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getStatusId())));


        tableView.getColumns().addAll(column1, column2, column3, column4, column5);


        PackageCustomTrackingAll trackingData = new PackageCustomTrackingAll();
        ObservableList<PackageCustomTracking> data = FXCollections.observableArrayList(
                trackingData.getAllPackageTrackingWithStatus(1)
        );
        tableView.setItems(data);

        // Add TableView to Layout
        bp.setCenter(tableView);

        // Return Scene
        return new Scene(bp, 900, 640);
    }
}
