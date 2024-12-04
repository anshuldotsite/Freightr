package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Scenes.CustomButton;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.Scenes.AddCustomerScene;
import org.example.freightr.Scenes.CustomLabel;
import org.example.freightr.Scenes.NavigationVBox;

/**
 * @description This class allows the user to select the sender of the package and displays the table of customers and allows the user to choose as the sender
 */
public class SenderSelectionScene {
    // Singleton instance and member variables
    private static SenderSelectionScene instance;
    private static TableView tableView;

    private static Package packageDetails;

    public static Scene CreateSenderSelectionScene(Stage stage, Package packageData) {

        // Set package details for the scene
        packageDetails = packageData;

        // Instance
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();

        tableView = new TableView<>();

        // Heading Label and HBox
        CustomLabel heading = new CustomLabel("Select Sender");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // Define columns
        TableColumn<Customer, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName()));

        TableColumn<Customer, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));

        TableColumn<Customer, String> column3 = new TableColumn<>("Contact No");
        column3.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getContactNumber()));

        TableColumn<Customer, String> column4 = new TableColumn<>("Address");
        column4.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getAddress()));

        // Add columns to the table view
        tableView.getColumns().addAll(column1, column2, column3, column4);

        // Populate table with a list of customers
        ObservableList<Customer> customerList = FXCollections.observableArrayList(customerTableCreation.getAllCustomers());

        tableView.setItems(customerList);

        // Button to select the customers
        CustomButton selectCustomerBtn = new CustomButton("Select Customer");
        selectCustomerBtn.setDisable(true);

        // This event handler enables the select button when a customer is selected
        selectCustomerBtn.setOnAction(e -> {
            Customer selectedCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                System.out.println("Selected Customer: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
                stage.setScene(  ReceiverSelectionScene.createReceiverTableScene(stage, packageDetails, selectedCustomer));
            } else {
                System.out.println("No customer selected! Please select a customer.");
            }
        });

        // A listener to enable/disable the select button when any row is selected by the user
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    selectCustomerBtn.setDisable(false);

                } else {
                    selectCustomerBtn.setDisable(true);

                }
            }
        });


        // Button to create a new sender
        CustomButton SenderButton = new CustomButton("Create a new sender");
        SenderButton.setOnAction(e->{
            Scene newSenderScene = NewSenderScene.createNewSender(stage,packageData);
            stage.setScene(newSenderScene);
        });

        // NavigationVBox for navigation to different scenes
        NavigationVBox navigationVbox = new NavigationVBox(stage);


        // HBox for layout
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(selectCustomerBtn,SenderButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // Styling
        VBox buttonVBox = new VBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");
        buttonVBox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        buttonVBox.setAlignment(Pos.CENTER);

        // Border Pane for layout
        BorderPane root = new BorderPane();
        root.setTop(headingBox);
        root.setCenter(tableView);
        root.setLeft(navigationVbox);
        root.setBottom(buttonVBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    // Refreshes table with new data
    public void refreshTable(){
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(customerTableCreation.getAllCustomers());
    }

    // Singleton instance
    public static SenderSelectionScene getInstance(){
        if(instance == null){
            instance = new SenderSelectionScene();
        }
        return instance;
    }
}
