package org.example.freightr.scenes.packageFormCreationAllScenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;

import org.example.freightr.TableCreation.PackageTableCred;
import org.example.freightr.scenes.CustomLabel;

public class FinalPackageDetailsAndCreationScene {
    private static int packageGeneratedkey;
    private int senderSelectedkey;
    private int reciverSelectedkey;

    public static Scene createConfirmationScene(Stage stage, Package packageDetails, Customer sender, Customer receiver) {

        // Use the custom label to display package details, sender, and receiver info
        CustomLabel packageLabel = new CustomLabel("Package Description: " + packageDetails.getPackageDescription());
        CustomLabel senderLabel = new CustomLabel("Sender Name: " + sender.getFirstName() + " " + sender.getLastName());
        CustomLabel senderAddressLabel = new CustomLabel("Sender Address: " + sender.getAddress());
        CustomLabel senderEmailLabel = new CustomLabel("Sender Email: " + sender.getEmail());
        CustomLabel senderContactLabel = new CustomLabel("Sender Contact: " + sender.getContactNumber());

        CustomLabel receiverLabel = new CustomLabel("Receiver Name: " + receiver.getFirstName() + " " + receiver.getLastName());
        CustomLabel receiverAddressLabel = new CustomLabel("Receiver Address: " + receiver.getAddress());
        CustomLabel receiverEmailLabel = new CustomLabel("Receiver Email: " + receiver.getEmail());
        CustomLabel receiverContactLabel = new CustomLabel("Receiver Contact: " + receiver.getContactNumber());

        // Layout to display the information
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                packageLabel,
                senderLabel, senderAddressLabel, senderEmailLabel, senderContactLabel,
                receiverLabel, receiverAddressLabel, receiverEmailLabel, receiverContactLabel
        );

        // Button to add the data into the database
        Button addDataButton = new Button("Add Data to Tables");
        addDataButton.setOnAction(e -> {
            PackageTableCred packageTableCred = PackageTableCred.getInstance();
            packageGeneratedkey = packageTableCred.addPackage(packageDetails);
            System.out.println(packageGeneratedkey);


        });

        vbox.getChildren().add(addDataButton);

        return new Scene(vbox, 900, 640);
    }



}