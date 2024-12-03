package org.example.freightr.Scenes;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;
import org.example.freightr.TableCreation.PackageCustomTrackingAll;
import org.example.freightr.TableCreation.StatusTable;

import java.util.ArrayList;

/**
 * @author Kautuk Prasad
 * @description This scene is an update scene for package status.
 */
public class UpdatePackageStatus {
    public static Scene createUpdateStatus(Stage stage, PackageCustomTracking packageCustomTracking){
        // Instance of the table
        StatusTable statusTable = StatusTable.getInstance();
        PackageCustomTrackingAll packageCustomTrackingAll = PackageCustomTrackingAll.getInstance();

        // Retrieve all statues
        ArrayList<StatusPOJO> statusPOJOS = new ArrayList<>();
        statusPOJOS = statusTable.getAllStatus();

        // GridPane for layout
        GridPane gridPane = new GridPane();

        // VBox for layout
        VBox vBox = new VBox();

        // HBox for heading
        HBox headingBox = new HBox();

        // Label for updating the package
        CustomLabel heading = new CustomLabel("Update Package");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // Label for tracking ID
        CustomLabel trackingIDLabel = new CustomLabel("Tracking ID");
        CustomTextField trackingTF = new CustomTextField();
        trackingTF.setText(String.valueOf(packageCustomTracking.getTrackingId()));
        trackingTF.setEditable(false);
        gridPane.add(trackingIDLabel,0,0);
        gridPane.add(trackingTF,1,0);

        // Label for seeing the package status
        CustomLabel packageStatus = new CustomLabel("Package Status");
        ComboBox<StatusPOJO>packageStatusComboBox = new ComboBox<>();
        packageStatusComboBox.setItems(FXCollections.observableArrayList(statusTable.getAllStatus()));
        packageStatusComboBox.getSelectionModel().select(find(statusPOJOS,packageCustomTracking.getStatusId()));
        gridPane.add(packageStatus,0,1);
        gridPane.add(packageStatusComboBox,1,1);

        // Label for description
        CustomLabel descriptionLabel = new CustomLabel("Description");
        CustomTextField descriptionTF = new CustomTextField();
        descriptionTF.setText(packageCustomTracking.getDescription());
        descriptionTF.setEditable(false);
        gridPane.add(descriptionLabel,0,2);
        gridPane.add(descriptionTF,1,2);

        // Label for location
        CustomLabel locationLabel = new CustomLabel("Location");
        CustomTextField locationTF = new CustomTextField();
        locationTF.setText(packageCustomTracking.getLocation());
        gridPane.add(locationLabel,0,3);
        gridPane.add(locationTF,1,3);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Navigation VBox for navigating to different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);

        gridPane.setAlignment(Pos.CENTER);

        // Button to update the status
        CustomButton updateBtn = new CustomButton("Update");
        updateBtn.setAlignment(Pos.CENTER);

        // Label for result
        CustomLabel resultLabel = new CustomLabel("");

        /**
         * This event handler creates a package objected with updated status and it updates the packages in the database and displays the result
         */
        updateBtn.setOnAction(event -> {
            vBox.getChildren().remove(resultLabel);
            PackageCustomTracking packageCustomTracked = new PackageCustomTracking(packageCustomTracking.getPackageId(),descriptionTF.getText(),packageCustomTracking.getSentDate(),
                    packageCustomTracking.getTrackingId(),locationTF.getText(),packageStatusComboBox.getSelectionModel().getSelectedItem().getId());
            packageCustomTrackingAll.updatePackage(packageCustomTracked);
            resultLabel.setText("Updated Package");
            vBox.getChildren().add(resultLabel);
        });

        // Add all elements to the vbox
        vBox.getChildren().addAll(headingBox,gridPane,updateBtn);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    // Finds the index of a company by its ID
    public static int find(ArrayList<?> arrayList, int id){
        ArrayList<StatusPOJO> searchList = (ArrayList<StatusPOJO>) ((ArrayList<?>) arrayList);
        for(int i = 0; i < searchList.size(); i++){
            if(searchList.get(i).getId() == id){
                System.out.println(i);
                return i;
            }
        }
        return 0;
    }
}
