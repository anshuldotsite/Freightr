package org.example.freightr.scenes.packageFormCreationAllScenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerPackageReceiverTableCred;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;
import org.example.freightr.TableCreation.ObjectClasses.Package;

import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;
import org.example.freightr.TableCreation.PackageTableCred;
import org.example.freightr.TableCreation.PackageTrackingTable;
import org.example.freightr.scenes.CustomLabel;
import org.example.freightr.scenes.NavigationVBox;

public class FinalPackageDetailsAndCreationScene {
    private static int packageGeneratedkey;
    private static int senderSelectedkey;
    private static int reciverSelectedkey;

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

        CustomLabel warehouseLocation = new CustomLabel("which warehouse is the package at country loaction");
        TextField location = new TextField();
        // Layout to display the information
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                packageLabel,
                senderLabel, senderAddressLabel, senderEmailLabel, senderContactLabel,
                receiverLabel, receiverAddressLabel, receiverEmailLabel, receiverContactLabel,warehouseLocation,location
        );

        // Button to add the data into the database
        Button addDataButton = new Button("Add Data to Tables");
        addDataButton.setOnAction(e -> {

            PackageTableCred packageTableCred = PackageTableCred.getInstance();
            packageGeneratedkey = packageTableCred.addPackage(packageDetails);
            System.out.println(packageGeneratedkey);
            senderSelectedkey = sender.getCustomerId();
            reciverSelectedkey = receiver.getCustomerId();

            CustomerPackageReceiver NewPackageConnection = new CustomerPackageReceiver(senderSelectedkey, packageGeneratedkey, reciverSelectedkey);
            CustomerPackageReceiverTableCred.getInstance().addCustomerPackageReceiver(NewPackageConnection);

PackageTracking packageTracking = new PackageTracking(0,packageGeneratedkey,location.getText(),1);
            PackageTrackingTable.getInstance().addPackageTracking(packageTracking);

        });
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        vbox.getChildren().add(addDataButton);
        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);
        bp.setLeft(navigationVBox);

        return new Scene(bp, 900, 640);
    }



}