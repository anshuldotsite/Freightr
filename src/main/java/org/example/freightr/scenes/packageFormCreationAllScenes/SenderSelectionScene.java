package org.example.freightr.scenes.packageFormCreationAllScenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.scenes.AddCustomerScene;
import org.example.freightr.scenes.NavigationVBox;

public class SenderSelectionScene {
    private static SenderSelectionScene instance;
    private static TableView tableView;

    private static Package packageDetails;

    public static Scene CreateSenderSelectionScene(Stage stage, Package packageData) {

        packageDetails = packageData;

        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();

         tableView = new TableView<>();

        TableColumn<Customer, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName()));

        TableColumn<Customer, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));

        TableColumn<Customer, String> column3 = new TableColumn<>("Contact No");
        column3.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getContactNumber()));

        TableColumn<Customer, String> column4 = new TableColumn<>("Address");
        column4.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getAddress()));

        tableView.getColumns().addAll(column1, column2, column3, column4);

        ObservableList<Customer> customerList = FXCollections.observableArrayList(customerTableCreation.getAllCustomers());

        tableView.setItems(customerList);

        Button selectCustomerBtn = new Button("Select Customer");
        selectCustomerBtn.setOnAction(e -> {

            Customer selectedCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();


            if (selectedCustomer != null) {

                System.out.println("Selected Customer: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());


                stage.setScene(  RecieverSelectionScene.createReceiverTableScene(stage, packageDetails, selectedCustomer));
            } else {

                System.out.println("No customer selected! Please select a customer.");
            }
        });


        Button SenderButton = new Button("Create a new sender");
        SenderButton.setOnAction(e->{
            Stage newStage = new Stage();
            Scene addCustomerScene = AddCustomerScene.createAddCustomer(newStage);
            newStage.setScene(addCustomerScene);
            newStage.show();
        });
        NavigationVBox navigationVbox = new NavigationVBox(stage);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView, selectCustomerBtn,SenderButton);
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setLeft(navigationVbox);

        return new Scene(root, 900, 640);
    }
    public void refreshTable(){
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(customerTableCreation.getAllCustomers());
    }

    public static SenderSelectionScene getInstance(){
        if(instance == null){
            instance = new SenderSelectionScene();
        }
        return instance;
    }
}
