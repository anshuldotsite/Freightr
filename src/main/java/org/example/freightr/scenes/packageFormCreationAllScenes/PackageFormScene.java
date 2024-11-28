package org.example.freightr.scenes.packageFormCreationAllScenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.PackageTableCred;
import org.example.freightr.scenes.AddCustomerScene;
import org.example.freightr.scenes.CustomLabel;
import org.example.freightr.scenes.CustomTextField;
import org.example.freightr.scenes.NavigationVBox;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/***
 * @author kohinoor jeet singh
 * @ description it was form to add package detaisl but since anshul made a new one so we are not uisng it
 */


public class PackageFormScene {
    private static double totalPrice;

    private static Package packageDetails;

    public static Scene CreatePackageFormScene(Stage stage) {

// making form using custom label and textfiled
        CustomLabel descriptionLabel = new CustomLabel("Parcel Description:");
        CustomTextField descriptionField = new CustomTextField();

        CustomLabel heightLabel = new CustomLabel("Height (cm)");
        CustomTextField heightField = new CustomTextField();

        CustomLabel widthLabel = new CustomLabel("Width (cm)");
        CustomTextField widthField = new CustomTextField();

        CustomLabel lengthLabel = new CustomLabel("Length (cm)");
        CustomTextField lengthField = new CustomTextField();

        CustomLabel weightLabel = new CustomLabel("Weight (kg)");
        CustomTextField weightField = new CustomTextField();

        CustomLabel chargesLabel = new CustomLabel("Total Charges:");
        CustomLabel totalCharges = new CustomLabel("");

        Button calculateChargesBtn = new Button("Calculate Charges");

        CustomLabel charge = new CustomLabel("");

        Button goToCustomerBtn = new Button("add customer");

        // made grid pane to accomodate all the components
        GridPane grid = new GridPane();
        // giving grid pane styles
        grid.setPadding(new Insets(15));
        grid.setVgap(13);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        VBox vb = new VBox();

      grid.add(descriptionLabel, 0, 0);
        grid.add(descriptionField, 1, 0);

        grid.add(heightLabel, 0, 1);
        grid.add(heightField, 1, 1);
        grid.add(widthLabel, 2, 1);
        grid.add(widthField, 3, 1);
        grid.add(lengthLabel, 0, 3);
        grid.add(lengthField, 1, 3);
        grid.add(weightLabel, 2, 3);
        grid.add(weightField, 3,3);

        grid.add(calculateChargesBtn, 0, 4);
        grid.add(chargesLabel, 1, 4);
        grid.add(totalCharges, 2, 4);
        grid.add(charge,3,4);




        NavigationVBox navigationVbox = new NavigationVBox(stage);




        VBox vbox = new VBox();
        vbox.getChildren().addAll(grid,goToCustomerBtn);
        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vbox);




        calculateChargesBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double weight = Double.parseDouble(weightField.getText());
            int heightSurcharge=0;
            int lengthSurcharge=0;
            int widthSurcharge=0;

            int WeightPrice =0;

             if(height>100){
                heightSurcharge=15;
            }
            if(width>100){
                widthSurcharge=15;
            }
            if(length>100){
                lengthSurcharge=15;
            }
            if(15<weight&&weight<25){
                WeightPrice=20;
            }
            else if(weight>25&&weight<50){
                WeightPrice=17;
            }
            else if (weight>50){
                WeightPrice=13;
            }else {
                WeightPrice=30;
            }
           totalPrice =heightSurcharge + widthSurcharge + lengthSurcharge + (WeightPrice * weight);
            charge.setText(String.valueOf(totalPrice));
        });


        goToCustomerBtn.setOnAction(e -> {
            if (descriptionField.getText().isEmpty() || heightField.getText().isEmpty() ||
                    widthField.getText().isEmpty() || lengthField.getText().isEmpty() ||
                    weightField.getText().isEmpty()) {
                charge.setText("Please fill in all fields.");
            } else {  // Create package and pass to next scene
                packageDetails = new Package(0, descriptionField.getText(),
                        new Date(), Double.parseDouble(weightField.getText()),
                        Double.parseDouble(heightField.getText()),
                        Double.parseDouble(lengthField.getText()),
                        Double.parseDouble(widthField.getText()), totalPrice);
                stage.setScene(SenderSelectionScene.CreateSenderSelectionScene(stage, packageDetails));
            }
        });

        return new Scene(root, 900, 640);


    }


}