package org.example.freightr.scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.ObjectClasses.Company;
import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;
import org.example.freightr.TableCreation.PackageTrackingTable;
import org.example.freightr.TableCreation.StatusTable;

import java.util.ArrayList;

/**
 * TrackPackage Scene
 * @author Kautuk Prasad
 */
public class TrackPackageScene {

    public static Scene createTrackPackageScene(Stage stage) {

        PackageTrackingTable packageTrackingTable = PackageTrackingTable.getInstance();
        StatusTable statusTable = StatusTable.getInstance();

        VBox vBox = new VBox();

        NavigationVBox navigationVbox = new NavigationVBox(stage);
        CustomLabel pageHeading = new CustomLabel("Track Your Package");
        HBox inputBox = new HBox();
        CustomLabel trackLabel = new CustomLabel("Please enter the Package ID");
        CustomTextField input = new CustomTextField();
        Button trackButton = new Button("Track Package");

        trackButton.setOnAction(event -> {
            PackageTracking trackedPackage = new PackageTracking();
            trackedPackage = packageTrackingTable.getPackageTracking(Integer.parseInt(input.getText()));

            GridPane gridPane = new GridPane();
            CustomLabel trackIDLabel = new CustomLabel("Tracking ID");
            CustomLabel trackIDData = new CustomLabel(String.valueOf(trackedPackage.getStatusId()));
            gridPane.add(trackIDLabel,0,0);
            gridPane.add(trackIDData,1,0);


            CustomLabel locationLabel = new CustomLabel("Location");
            CustomLabel locationData = new CustomLabel(trackedPackage.getLocation());
            gridPane.add(locationLabel,0,1);
            gridPane.add(locationData,1,1);


            ArrayList<StatusPOJO> status = new ArrayList<>();
            status=StatusTable.getInstance().getAllStatus();

            CustomLabel statusLabel = new CustomLabel("Status");
            CustomLabel statusData = new CustomLabel(String.valueOf(find(status,trackedPackage.getTrackingId())));
            gridPane.add(statusLabel,0,2);
            gridPane.add(statusData,1,2);


            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER);
            vBox.getChildren().add(gridPane);
        });

        inputBox.getChildren().addAll(trackLabel, input, trackButton);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(pageHeading, inputBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vBox);

        return new Scene(root, 900, 640);
    }

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