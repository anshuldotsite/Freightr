package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CompanyTableCreation;
import org.example.freightr.TableCreation.CustomerPackageReceiverCRUD;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Company;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.Scenes.NavigationVBox;

/**
 * This class shows the details of both the sender and the receiver of the package
 */
public class PackageDetailsScene {

    public static Scene CreateSenderReciverDetailsPage(int PackageId, Stage stage) {
        // BorderPane for layout
        BorderPane bp = new BorderPane();

        // NavigationVBox for navigating to different scens
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        bp.setLeft(navigationVBox);

        // Fetching sender and receiver details
        CustomerPackageReceiverCRUD customerPackageReceiverCRUD = CustomerPackageReceiverCRUD.getInstance();
        int senderId = customerPackageReceiverCRUD.getCustomerIdByPackageId(PackageId);
        int receiverId = customerPackageReceiverCRUD.getReciverIdByPackageId(PackageId);

        // Fetch customer details
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        Customer sender = customerTableCreation.getCustomer(senderId);
        Customer receiver = customerTableCreation.getCustomer(receiverId);

        // Fetch company details
        CompanyTableCreation companyTableCreation = CompanyTableCreation.getInstance();
        Company senderCompany = sender.getCompanyId() != 0 ? companyTableCreation.getCompany(sender.getCompanyId()) : null;
        Company receiverCompany = receiver.getCompanyId() != 0 ? companyTableCreation.getCompany(receiver.getCompanyId()) : null;

        // Creating a styled GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(15);
        gridPane.setHgap(30);
        gridPane.setAlignment(Pos.CENTER);

        // Label for sender details
        Label headerSender = new Label("Sender Details");
        headerSender.setFont(new Font("Arial", 18));
        gridPane.add(headerSender, 0, 0);

        // Add sender details to the GridPane
        gridPane.add(new Label("Name: "), 0, 1);
        gridPane.add(new Label(sender.getFirstName() + " " + sender.getLastName()), 0, 2);
        gridPane.add(new Label("Contact: "), 0, 3);
        gridPane.add(new Label(sender.getContactNumber()), 0, 4);
        gridPane.add(new Label("Email: "), 0, 5);
        gridPane.add(new Label(sender.getEmail()), 0, 6);
        gridPane.add(new Label("Address: "), 0, 7);
        gridPane.add(new Label(sender.getAddress()), 0, 8);

        // Add sender company details
        if (senderCompany != null) {
            gridPane.add(new Label("Company: "), 0, 9);
            gridPane.add(new Label(senderCompany.getCompanyName()), 0, 10);
        }

        // Label for the receiver details
        Label headerReceiver = new Label("Receiver Details");
        headerReceiver.setFont(new Font("Arial", 18));

        // Add receiver details to the GridPane
        gridPane.add(headerReceiver, 1, 0);
        gridPane.add(new Label("Name: "), 1, 1);
        gridPane.add(new Label(receiver.getFirstName() + " " + receiver.getLastName()), 1, 2);
        gridPane.add(new Label("Contact: "), 1, 3);
        gridPane.add(new Label(receiver.getContactNumber()), 1, 4);
        gridPane.add(new Label("Email: "), 1, 5);
        gridPane.add(new Label(receiver.getEmail()), 1, 6);
        gridPane.add(new Label("Address: "), 1, 7);
        gridPane.add(new Label(receiver.getAddress()), 1, 8);

        // Add receiver company details
        if (receiverCompany != null) {
            gridPane.add(new Label("Company: "), 1, 9);
            gridPane.add(new Label(receiverCompany.getCompanyName()), 1, 10);
        }

        // Adding the GridPane to the BorderPane
        bp.setCenter(gridPane);

        return  new Scene(bp, 900, 640);
    }
}
