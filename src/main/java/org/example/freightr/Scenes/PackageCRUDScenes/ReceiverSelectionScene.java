package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * This class allows the user to select a receiver for the package created
 * and displays a table of customers from the database and allows the user
 * to choose the receiver
 */
public class ReceiverSelectionScene {
    private static Package packageDetails;
    private static ReceiverSelectionScene instance;
    private static TableView<Customer> tableView;
    public static Scene createReceiverTableScene(Stage stage, Package packageData, Customer selectedCustomer) {
        // Set package details for the scene
        packageDetails = packageData;

        // HBox for the heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Select receiver");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // Table view to display customer details
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView = new TableView<>();

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
        tableView.getColumns().addAll(column1, column2, column3,column4);

        // Populate table with a list of customers
        ObservableList<Customer> customerList = FXCollections.observableArrayList(customerTableCreation.getAllCustomers());
        tableView.setItems(customerList);

        // Button to select a receiver
        CustomButton selectReceiverBtn = new CustomButton("Select Receiver");
        selectReceiverBtn.setDisable(true);

        // A listener to enable the select button when any row is selected by the user
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    selectReceiverBtn.setDisable(false);

                } else {
                    selectReceiverBtn.setDisable(true);

                }
            }
        });


        // Handles the selection of the receiver
        selectReceiverBtn.setOnAction(e -> {
            Customer selectedReceiver = tableView.getSelectionModel().getSelectedItem();
            if (selectedReceiver != null) {
                stage.setScene(FinalPackageScene.createConfirmationScene(stage,packageDetails,selectedCustomer,selectedReceiver));
                System.out.println("Package Details: " + packageDetails);
                System.out.println("Customer Details: " + selectedCustomer);
                System.out.println("Receiver Details: " + selectedReceiver);
            }
        });

        // Button to create a new receiver
        CustomButton createReceiver = new CustomButton("Create a new receiver");
        createReceiver.setOnAction(e->{
            Stage newStage = new Stage();
            Scene addCustomerScene = AddCustomerScene.createAddCustomer(newStage);
            newStage.setScene(addCustomerScene);
            newStage.show();
        });

        // HBox for the customers
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(selectReceiverBtn,createReceiver);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // Styling
        VBox buttonVbox = new VBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");
        buttonVbox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        buttonVbox.setAlignment(Pos.CENTER);

        // NavigationVBox for navigating to different scenes
        NavigationVBox navigationVbox = new NavigationVBox(stage);

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(tableView);
        root.setLeft(navigationVbox);
        root.setTop(headingBox);
        root.setBottom(buttonVbox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    public void refreshTable(){
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(customerTableCreation.getAllCustomers());
    }


    // Singleton instance
    public static ReceiverSelectionScene getInstance(){
        if (instance==null){
            instance=new ReceiverSelectionScene();
        }
        return instance;
    }
}
