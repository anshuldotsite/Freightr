package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.freightr.Scenes.CustomLabel;
import org.example.freightr.Scenes.CustomLabel2;
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

    public static Scene CreateSenderReceiverDetailsPage(int PackageId, Stage stage) {
        BorderPane bp = new BorderPane();
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        bp.setLeft(navigationVBox);

        // Fetching sender and receiver details
        CustomerPackageReceiverCRUD customerPackageReceiverTableCred = CustomerPackageReceiverCRUD.getInstance();
        int senderId = customerPackageReceiverTableCred.getCustomerIdByPackageId(PackageId);
        int receiverId = customerPackageReceiverTableCred.getReciverIdByPackageId(PackageId);

        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        Customer sender = customerTableCreation.getCustomer(senderId);
        Customer receiver = customerTableCreation.getCustomer(receiverId);

        CompanyTableCreation companyTableCreation = CompanyTableCreation.getInstance();
        Company senderCompany = sender.getCompanyId() != 0 ? companyTableCreation.getCompany(sender.getCompanyId()) : null;
        Company receiverCompany = receiver.getCompanyId() != 0 ? companyTableCreation.getCompany(receiver.getCompanyId()) : null;

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
        if (senderCompany != null) {
            gridPane.add(new CustomLabel("Company: "), 0, 5);
            gridPane.add(new CustomLabel2(senderCompany.getCompanyName()), 1, 5);
        }

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

        if (receiverCompany != null) {
            gridPane.add(new CustomLabel("Company: "), 2, 5);
            gridPane.add(new Label(receiverCompany.getCompanyName()), 3, 5);
        }

        // Adding the GridPane to the BorderPane
        bp.setCenter(gridPane);
        bp.setStyle("-fx-background-color: #F8EDE3");

        return  new Scene(bp, 900, 640);
    }
}
