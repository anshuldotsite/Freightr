package org.example.freightr.Scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;
import org.example.freightr.TableCreation.PackageTrackingTable;
import org.example.freightr.TableCreation.StatusTable;

import java.util.ArrayList;

/**
 * @description This class allows users to track the statuses and location of a package using
 * a tracking ID
 * @author Kautuk Prasad
 */
public class TrackPackageScene {
    public static Scene createTrackPackageScene(Stage stage) {
        // Tables for tracking package and to see status
        PackageTrackingTable packageTrackingTable = PackageTrackingTable.getInstance();
        StatusTable statusTable = StatusTable.getInstance();

        // VBox and GridPane for layout
        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        // Navigation VBox for navigation to different scenes
        NavigationVBox navigationVbox = new NavigationVBox(stage);

        // Label for header
        CustomLabel pageHeading = new CustomLabel("Track Your Package");

        // HBox and Label for tracking
        HBox inputBox = new HBox();
        CustomLabel trackLabel = new CustomLabel("Please enter the Package ID");
        CustomTextField input = new CustomTextField();
        CustomButton trackButton = new CustomButton("Track Package");

        // Result Label
        CustomLabel resultLabel = new CustomLabel("");
        resultLabel.setStyle("-fx-text-fill: red;");

        /**
         * This event handler retrieves the package details and populates the grid with the package details,
         * also retrieves status from status ID
         */
        trackButton.setOnAction(event -> {
            //error handling for null input
            if (input.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                vBox.getChildren().remove(gridPane);
                gridPane.getChildren().clear();
                resultLabel.setText("Please fill out the field");
                vBox.getChildren().add(resultLabel);
            }else{
                vBox.getChildren().remove(resultLabel);
                vBox.getChildren().remove(gridPane);
                gridPane.getChildren().clear();
                PackageTracking trackedPackage = new PackageTracking();
                trackedPackage = packageTrackingTable.getPackageTracking(Integer.parseInt(input.getText()));

                CustomLabel trackIDLabel = new CustomLabel("Tracking ID");
                CustomLabel trackIDData = new CustomLabel(String.valueOf(trackedPackage.getTrackingId()));
                gridPane.add(trackIDLabel,0,0);
                gridPane.add(trackIDData,1,0);

                CustomLabel locationLabel = new CustomLabel("Location");
                CustomLabel locationData = new CustomLabel(trackedPackage.getLocation());
                gridPane.add(locationLabel,0,1);
                gridPane.add(locationData,1,1);

                ArrayList<StatusPOJO> status = new ArrayList<>();
                status=StatusTable.getInstance().getAllStatus();

                CustomLabel statusLabel = new CustomLabel("Status");
                CustomLabel statusData = new CustomLabel(String.valueOf(find(status,trackedPackage.getStatusId())));
                gridPane.add(statusLabel,0,2);
                gridPane.add(statusData,1,2);

                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setAlignment(Pos.CENTER);
                vBox.getChildren().add(gridPane);
            }
        });

        // Input Box for layout
        inputBox.getChildren().addAll(trackLabel, input, trackButton);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(pageHeading, inputBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    /**
     * @author Kautuk Prasad
     * @description This method matches the status id with the status name and then returns
     * the status name for display.
     * @param arrayList
     * @param id
     * @return
     */
    public static String find(ArrayList<?> arrayList, int id){
        ArrayList<StatusPOJO> searchList = (ArrayList<StatusPOJO>) ((ArrayList<?>) arrayList);
        for(int i = 0; i < searchList.size(); i++){
            if(searchList.get(i).getId() == id){
               return searchList.get(i).getStatus();
            }
        }
        return "Status Not Found";
    }
}