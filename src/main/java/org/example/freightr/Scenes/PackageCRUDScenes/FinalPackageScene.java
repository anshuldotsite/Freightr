package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerPackageReceiverCRUD;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;
import org.example.freightr.TableCreation.ObjectClasses.Package;

import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;
import org.example.freightr.TableCreation.PackageTableCRUD;
import org.example.freightr.TableCreation.PackageTrackingTable;
import org.example.freightr.Scenes.CustomLabel;
import org.example.freightr.Scenes.NavigationVBox;

public class FinalPackageScene {
    // Member Variables
    private static int packageGeneratedkey;
    private static int senderSelectedkey;
    private static int reciverSelectedkey;

    /**
     * This scene displays the package details,sender and receiver information
     * @param stage
     * @param packageDetails
     * @param sender
     * @param receiver
     * @return confirmation scene
     */

    public static Scene createConfirmationScene(Stage stage, Package packageDetails, Customer sender, Customer receiver) {

        // VBox for layout
        VBox mainBox = new VBox();

        // Label for package details
        CustomLabel heading = new CustomLabel("Package Details");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // Use the custom label to display package details, sender, and receiver info

        // Grid Pane for layout
        GridPane headingPane = new GridPane();

        // Label for package description
        CustomLabel packageLabel = new CustomLabel("Package Description: ");
        CustomLabel packageDescription = new CustomLabel(packageDetails.getPackageDescription());
        headingPane.add(packageLabel,0,0);
        headingPane.add(packageDescription,1,0);

        // GridPane for sender's info
        GridPane customerPane = new GridPane();

        // Label for sender's info
        CustomLabel senderHeading = new CustomLabel("Sender Info");
        HBox senderBox = new HBox();
        senderBox.getChildren().add(senderHeading);
        senderBox.setAlignment(Pos.CENTER);



        // Label for sender's details
        CustomLabel senderLabel = new CustomLabel("Sender Name: ");
        CustomLabel senderName = new CustomLabel(sender.getFirstName()+" "+sender.getLastName());
        customerPane.add(senderLabel,0,0);
        customerPane.add(senderName,1,0);

        // Label for sender's address
        CustomLabel senderAddressLabel = new CustomLabel("Sender Address: " );
        CustomLabel senderAddress = new CustomLabel(sender.getAddress()+" "+sender.getCity()+
                " "+sender.getProvince()+" "+ sender.getCountry());
        customerPane.add(senderAddressLabel,0,1);
        customerPane.add(senderAddress,1,1);


        // Label for sender's email
        CustomLabel senderEmailLabel = new CustomLabel("Sender Email: ");
        CustomLabel senderEmail = new CustomLabel(sender.getEmail());
        customerPane.add(senderEmailLabel,0,2);
        customerPane.add(senderEmail,1,2);

        // Label for sender's contact
        CustomLabel senderContactLabel = new CustomLabel("Sender Contact: ");
        CustomLabel senderContact = new CustomLabel(sender.getContactNumber());
        customerPane.add(senderContactLabel,0,3);
        customerPane.add(senderContact,1,3);

        // Grid Pane for receiver's info
        GridPane receiverPane = new GridPane();

        // Label for receiver's info
        CustomLabel receiverHeading = new CustomLabel("Receiver Info");
        HBox receiverBox = new HBox();
        receiverBox.getChildren().add(receiverHeading);
        receiverBox.setAlignment(Pos.CENTER);


        // Label for receiver name
        CustomLabel receiverLabel = new CustomLabel("Receiver Name: ");
        CustomLabel receiverName = new CustomLabel(receiver.getFirstName()+" "+receiver.getLastName());
        receiverPane.add(receiverLabel,0,0);
        receiverPane.add(receiverName,1,0);


        // Label for receiver's address
        CustomLabel receiverAddressLabel = new CustomLabel("Receiver Address: " );
        CustomLabel receiverAddress = new CustomLabel(receiver.getAddress()+" "+receiver.getCity()+
                " "+receiver.getProvince()+" "+ receiver.getCountry());
        receiverPane.add(receiverAddressLabel,0,1);
        receiverPane.add(receiverAddress,1,1);


        // Label for receiver's email
        CustomLabel receiverEmailLabel = new CustomLabel("Receiver Email: ");
        CustomLabel receiverEmail = new CustomLabel(receiver.getEmail());
        receiverPane.add(receiverEmailLabel,0,2);
        receiverPane.add(receiverEmail,1,2);


        // Label for receiver's contact
        CustomLabel receiverContactLabel = new CustomLabel("Receiver Contact: ");
        CustomLabel receiverContact = new CustomLabel(receiver.getContactNumber());
        receiverPane.add(receiverContactLabel,0,3);
        receiverPane.add(receiverContact,1,3);

        // Label for warehouse location
        CustomLabel warehouseLocation = new CustomLabel("Package at Warehouse In: ");
        TextField location = new TextField();
        location.setText(sender.getCity());

        // HBox for buttons
        HBox buttonBox = new HBox();

        // Button to add the data into the database
        Button addDataButton = new Button("Create Package");

        buttonBox.getChildren().addAll(warehouseLocation,addDataButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // Label for errors
        CustomLabel resultLabel = new CustomLabel("");

        /**
         * This buttons adds a package to the database, creates a customer-package connection, adds the tracking info and displays it
         */
        addDataButton.setOnAction(e -> {
            mainBox.getChildren().remove(resultLabel);
            PackageTableCRUD packageTableCRUD = PackageTableCRUD.getInstance();
            packageGeneratedkey = packageTableCRUD.addPackage(packageDetails);
            System.out.println(packageGeneratedkey);
            senderSelectedkey = sender.getCustomerId();
            reciverSelectedkey = receiver.getCustomerId();

            CustomerPackageReceiver NewPackageConnection = new CustomerPackageReceiver(senderSelectedkey, packageGeneratedkey, reciverSelectedkey);
            CustomerPackageReceiverCRUD.getInstance().addCustomerPackageReceiver(NewPackageConnection);

PackageTracking packageTracking = new PackageTracking(0,packageGeneratedkey,location.getText(),1);
            PackageTrackingTable.getInstance().addPackageTracking(packageTracking);
            resultLabel.setText("Package Created Successfully");
            mainBox.getChildren().add(resultLabel);

        });

        // NavigationVBox for navigation to different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);

        customerPane.setAlignment(Pos.CENTER);
        receiverPane.setAlignment(Pos.CENTER);
        receiverBox.setAlignment(Pos.CENTER);

        // Adding all elements to the VBox
        mainBox.getChildren().addAll(headingBox,senderBox,customerPane,receiverBox,receiverPane,buttonBox);
        mainBox.setSpacing(20);
        mainBox.setAlignment(Pos.CENTER);

        // Border Pane for layout
        BorderPane bp = new BorderPane();
        bp.setCenter(mainBox);
        bp.setLeft(navigationVBox);

        return new Scene(bp, 900, 640);
    }
}