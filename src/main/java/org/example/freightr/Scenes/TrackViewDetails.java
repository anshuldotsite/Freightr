package org.example.freightr.Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerPackageReceiverCRUD;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;

public class TrackViewDetails {
    public static Scene createTrackView(int packageID ,Stage stage){
        BorderPane root = new BorderPane();
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        root.setLeft(navigationVBox);

        // Fetching sender and receiver details
        CustomerPackageReceiverCRUD customerPackageReceiverTableCred = CustomerPackageReceiverCRUD.getInstance();
        int senderId = customerPackageReceiverTableCred.getCustomerIdByPackageId(packageID);
        int receiverId = customerPackageReceiverTableCred.getReciverIdByPackageId(packageID);

        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        Customer sender = customerTableCreation.getCustomer(senderId);
        Customer receiver = customerTableCreation.getCustomer(receiverId);

        //Vbox
        VBox vBox = new VBox();


        // Creating a styled GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(15);
        gridPane.setHgap(30);
        gridPane.setAlignment(Pos.CENTER);

        CustomLabel headerSender = new CustomLabel("Sender Details");
        headerSender.setFont(new Font("Arial", 18));
        gridPane.add(headerSender, 0, 0);
        GridPane.setColumnSpan(headerSender, 2);

        gridPane.add(new CustomLabel("Name: "), 0, 1);
        gridPane.add(new CustomLabel2(sender.getFirstName() + " " + sender.getLastName()), 1, 1);

        gridPane.add(new CustomLabel("Contact: "), 0, 2);
        gridPane.add(new CustomLabel2(sender.getContactNumber()), 1, 2);
        gridPane.add(new CustomLabel("Email: "), 0, 3);
        gridPane.add(new CustomLabel2(sender.getEmail()), 1, 3);

        gridPane.add(new CustomLabel("Address: "), 0, 4);
        gridPane.add(new CustomLabel2(sender.getAddress()), 1, 4);

        gridPane.add(new CustomLabel("Type: "), 0, 5);
        gridPane.add(new CustomLabel2(sender.getCustomerType()), 1, 5);


        CustomLabel headerReceiver = new CustomLabel("Receiver Details");
        headerReceiver.setFont(new Font("Arial", 18));
        gridPane.add(headerReceiver, 2, 0);
        GridPane.setColumnSpan(headerReceiver, 2);
        gridPane.add(new CustomLabel("Name: "), 2, 1);
        gridPane.add(new CustomLabel2(receiver.getFirstName() + " " + receiver.getLastName()), 3, 1);

        gridPane.add(new CustomLabel("Contact: "), 2, 2);
        gridPane.add(new CustomLabel2(receiver.getContactNumber()), 3, 2);
        gridPane.add(new CustomLabel("Email: "), 2, 3);
        gridPane.add(new CustomLabel2(receiver.getEmail()), 3, 3);
        gridPane.add(new CustomLabel("Address: "), 2, 4);
        gridPane.add(new CustomLabel2(receiver.getAddress()), 3, 4);


        gridPane.add(new CustomLabel("Type: "), 2, 5);
        gridPane.add(new CustomLabel2(receiver.getCustomerType()), 3, 5);

        //back button
        CustomButton backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            Scene trackAll = AllPackageTrackingScene.createAllPackageTrackingScene(stage);
            stage.setScene(trackAll);
        });

        vBox.getChildren().addAll(gridPane,backButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);


        // Adding the GridPane to the BorderPane
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return  new Scene(root, 900, 640);

    }
}
