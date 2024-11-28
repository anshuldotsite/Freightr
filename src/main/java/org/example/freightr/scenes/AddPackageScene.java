package org.example.freightr.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.PackageTableCred;
import org.example.freightr.TableCreation.ObjectClasses.Package;

import java.util.Date;

public class AddPackageScene {
    public static Scene createAddPackage(Stage stage) {

        NavigationVBox navigationVBox = new NavigationVBox(stage);
        PackageTableCred packageTableCred = PackageTableCred.getInstance();

        // Heading
        CustomLabel heading = new CustomLabel("Add New Package");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        // Description
        CustomLabel descriptionLabel = new CustomLabel("Package Description");
        CustomTextField descriptionTF = new CustomTextField();
        gridPane.add(descriptionLabel, 0, 0);
        gridPane.add(descriptionTF, 1, 0);

        // Height
        CustomLabel heightLabel = new CustomLabel("Height (cm)");
        CustomTextField heightTF = new CustomTextField();
        gridPane.add(heightLabel, 0, 1);
        gridPane.add(heightTF, 1, 1);

        // Width
        CustomLabel widthLabel = new CustomLabel("Width (cm)");
        CustomTextField widthTF = new CustomTextField();
        gridPane.add(widthLabel, 0, 2);
        gridPane.add(widthTF, 1, 2);

        // Length
        CustomLabel lengthLabel = new CustomLabel("Length (cm)");
        CustomTextField lengthTF = new CustomTextField();
        gridPane.add(lengthLabel, 0, 3);
        gridPane.add(lengthTF, 1, 3);

        // Weight
        CustomLabel weightLabel = new CustomLabel("Weight (kg)");
        CustomTextField weightTF = new CustomTextField();
        gridPane.add(weightLabel, 0, 4);
        gridPane.add(weightTF, 1, 4);

        // Price Calculation Button
        Button calculatePriceBtn = new Button("Calculate Price");
        CustomLabel priceLabel = new CustomLabel("Price: ");
        gridPane.add(calculatePriceBtn, 0, 5);
        gridPane.add(priceLabel, 1, 5);

        // Add Package Button
        Button addPackageBtn = new Button("Add Package");
        CustomLabel resultLabel = new CustomLabel("");

        addPackageBtn.setOnAction(event -> {
            if (descriptionTF.getText().isEmpty() ||
                    heightTF.getText().isEmpty() ||
                    widthTF.getText().isEmpty() ||
                    lengthTF.getText().isEmpty() ||
                    weightTF.getText().isEmpty()) {

                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Fill out all the fields");
                vBox.getChildren().add(resultLabel);
            } else {
                try {
                    double height = Double.parseDouble(heightTF.getText());
                    double width = Double.parseDouble(widthTF.getText());
                    double length = Double.parseDouble(lengthTF.getText());
                    double weight = Double.parseDouble(weightTF.getText());

                    // Calculate the price based on the dimensions and weight
                    double totalPrice = calculatePrice(height, width, length, weight);

                    // Display the calculated price
                    priceLabel.setText(String.format("Price: $%.2f", totalPrice));

                    // Create a new Package object
                    Date currentDate = new java.util.Date();
                    Package newPackage = new Package(0, descriptionTF.getText(), currentDate,
                            weight, height, length, width, totalPrice);

                    packageTableCred.addPackage(newPackage);

                    vBox.getChildren().remove(resultLabel);
                    resultLabel.setText("Package Added Successfully");
                    vBox.getChildren().add(resultLabel);

                    // Clear the fields after addition
                    descriptionTF.clear();
                    heightTF.clear();
                    widthTF.clear();
                    lengthTF.clear();
                    weightTF.clear();
                    priceLabel.setText("Price: ");

                } catch (NumberFormatException ex) {
                    vBox.getChildren().remove(resultLabel);
                    resultLabel.setText("Invalid input. Please enter numeric values.");
                    vBox.getChildren().add(resultLabel);
                }
            }
        });

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(headingBox, gridPane, calculatePriceBtn, addPackageBtn, resultLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);

        return new Scene(root, 900, 640);
    }

    // Pricing Logic
    private static double calculatePrice(double height, double width, double length, double weight) {
        // If the dimension is greater than 100 cm,a surcharge of $15 will be added
        int dimensionSurcharge = 0;
        if (height > 100) dimensionSurcharge += 15;  // $15 for height > 100 cm
        if (width > 100) dimensionSurcharge += 15;   // $15 for width > 100 cm
        if (length > 100) dimensionSurcharge += 15;  // $15 for length > 100 cm

        // Price is based on the weight of the package
        int weightPrice = 0;
        if (weight > 50) {
            weightPrice = 13;  // Price per kg for weight > 50 kg
        } else if (weight > 25) {
            weightPrice = 17;  // Price per kg for weight between 25 and 50 kg
        } else if (weight > 15) {
            weightPrice = 20;  // Price per kg for weight between 15 and 25 kg
        } else {
            weightPrice = 30;  // Price per kg for weight less than 15 kg
        }

        // Total price is the sum of the dimension surcharge and weight price * weight
        return dimensionSurcharge + (weightPrice * weight);
    }
}
