package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Scenes.*;
import org.example.freightr.TableCreation.PackageTableCRUD;
import org.example.freightr.TableCreation.ObjectClasses.Package;

import java.util.Date;

/***
 * @author anshul/kohinoor
 * @description This scene add a package
 */
public class AddPackageScene {
    // Member Variables
    private static double totalPrice;
    private static Package packageDetails;

    public static Scene createAddPackage(Stage stage) {

        // NavigationVBox for navigating to different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        PackageTableCRUD packageTableCRUD = PackageTableCRUD.getInstance();
        LoginPageScene loginPageScene = LoginPageScene.getInstance();

        // Heading Label
        CustomLabel heading = new CustomLabel("Add New Package");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // VBox and GridPane for layout
        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        // Description Label
        CustomLabel descriptionLabel = new CustomLabel("Package Description");
        CustomTextField descriptionTF = new CustomTextField();
        gridPane.add(descriptionLabel, 0, 0);
        gridPane.add(descriptionTF, 1, 0);

        // Height Label
        CustomLabel heightLabel = new CustomLabel("Height (cm)");
        CustomTextField heightTF = new CustomTextField();
        gridPane.add(heightLabel, 0, 1);
        gridPane.add(heightTF, 1, 1);

        // Width Label
        CustomLabel widthLabel = new CustomLabel("Width (cm)");
        CustomTextField widthTF = new CustomTextField();
        gridPane.add(widthLabel, 0, 2);
        gridPane.add(widthTF, 1, 2);

        // Length Label
        CustomLabel lengthLabel = new CustomLabel("Length (cm)");
        CustomTextField lengthTF = new CustomTextField();
        gridPane.add(lengthLabel, 0, 3);
        gridPane.add(lengthTF, 1, 3);

        // Weight Label
        CustomLabel weightLabel = new CustomLabel("Weight (kg)");
        CustomTextField weightTF = new CustomTextField();
        gridPane.add(weightLabel, 0, 4);
        gridPane.add(weightTF, 1, 4);

        // Price Calculation Button
        CustomButton calculateChargesBtn = new CustomButton("Calculate Charges");
        CustomLabel priceLabel = new CustomLabel("Price: ");
        CustomLabel charge = new CustomLabel("");
        gridPane.add(calculateChargesBtn, 0, 5);
        gridPane.add(priceLabel, 1, 5);
        gridPane.add(charge,2,5);


        // Add Package Button
        CustomButton addPackageBtn = new CustomButton("Add sender details");
        Label resultLabel = new Label("");

        // Pricing Logic
        calculateChargesBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightTF.getText());
            double width = Double.parseDouble(widthTF.getText());
            double length = Double.parseDouble(lengthTF.getText());
            double weight = Double.parseDouble(weightTF.getText());

            // Surcharge Variables
            int heightSurcharge=0;
            int lengthSurcharge=0;
            int widthSurcharge=0;

            // Price per kg based on weight
            int WeightPrice =0;

            // Determine weight-based pricing
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

        /**
         * This event handler adds a package ang navigates to the scene, and displays error if any of the field are empty
         */
        addPackageBtn.setOnAction(event -> {
            if (descriptionTF.getText().isEmpty() ||
                    heightTF.getText().isEmpty() ||
                    widthTF.getText().isEmpty() ||
                    lengthTF.getText().isEmpty() ||
                    weightTF.getText().isEmpty()) {

                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }
                else {  // Create package and pass to next scene
                    packageDetails = new Package(0, descriptionTF.getText(),
                            new Date(), Double.parseDouble(weightTF.getText()),
                            Double.parseDouble(heightTF.getText()),
                            Double.parseDouble(lengthTF.getText()),
                            Double.parseDouble(widthTF.getText()), totalPrice);
                    stage.setScene(SenderSelectionScene.CreateSenderSelectionScene(stage, packageDetails));
                }
                    // Clear the fields after addition
                    descriptionTF.clear();
                    heightTF.clear();
                    widthTF.clear();
                    lengthTF.clear();
                    weightTF.clear();
                    priceLabel.setText("Price: ");
        });

        // Grid Pane for layout
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Adding all elements to the VBox
        vBox.getChildren().addAll(headingBox, gridPane, addPackageBtn, resultLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
}
