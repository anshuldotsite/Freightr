package org.example.freightr.Scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;

/**
 * @author Kautuk Prasad
 * @description This is a scene to display all customer details in a table.
 */
public class DisplayCustomerScene {
    private static DisplayCustomerScene instance;
    private static TableView tableView;
    public static Scene createDisplayCustomer(Stage stage){
        CustomerTableCreation customer = CustomerTableCreation.getInstance();

        // HBox for heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Customers");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // A table view object
        tableView = new TableView();

        // Column for First Name
        TableColumn<Customer, String> column1= new TableColumn<>("First Name");
        column1.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getFirstName()));

        // Column for Last Name
        TableColumn<Customer, String> column2= new TableColumn<>("Last Name");
        column2.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLastName()));

        // Column for Company
        TableColumn<Customer, String> column3= new TableColumn<>("Company");
        column3.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCompanyName()));

        // Column for Contact Number
        TableColumn<Customer, String> column4= new TableColumn<>("Contact No");
        column4.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getContactNumber()));

        // Column for Email
        TableColumn<Customer, String> column5= new TableColumn<>("Email");
        column5.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getEmail()));

        // Column for Street Address
        TableColumn<Customer, String> column6= new TableColumn<>("Address");
        column6.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAddress()));

        // Column for City
        TableColumn<Customer, String> column7= new TableColumn<>("City");
        column7.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));

        // Column for Province
        TableColumn<Customer, String> column8= new TableColumn<>("Province");
        column8.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProvince()));

        // Column for Country
        TableColumn<Customer, String> column9= new TableColumn<>("Country");
        column9.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCountry()));

        // Column for Customer Type
        TableColumn<Customer, String> column10= new TableColumn<>("Customer Type");
        column10.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCustomerType()));

        tableView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10);
        tableView.getItems().addAll(customer.getPrettyData());

        // NavigationVBox for navigation scenes
        NavigationVBox navigationVbox = new NavigationVBox(stage);

        // Buttons for add and delete customer
        Button addButton = new Button("Add Customer");
        addButton.setStyle("-fx-text-fill: red");
        Button updateButton = new Button("Update Customer");

        // VBox for buttons
        VBox buttonVBox = new VBox();

        // HBox for layout
        HBox buttonBox = new HBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");

        // Adding all elements to the vbox
        buttonBox.getChildren().addAll(addButton,updateButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonVBox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        buttonVBox.setAlignment(Pos.CENTER);

        updateButton.setDisable(true);

       // Enable/Disable button that checks based on the selection in table view
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue!=null){
                    updateButton.setDisable(false);
                }else {
                    updateButton.setDisable(true);
                }
            }
        });

        // This event handler handles the update button which updates the customer and navigates to a new scene
        updateButton.setOnAction(event -> {
            Customer selectedCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();
            Scene updateCustomer = UpdateCustomer.createUpdateCustomer(stage,selectedCustomer);
            stage.setScene(updateCustomer);
        });

        // This event handler handles the add button which add a new customer and navigates to a new scene
        addButton.setOnAction(event -> {
            Scene addCustomerScene = AddCustomerScene.createAddCustomer(stage);
            stage.setScene(addCustomerScene);
        });

        // BorderPane for layout
        BorderPane root = new BorderPane();

        root.setLeft(navigationVbox);
        root.setCenter(tableView);
        root.setTop(headingBox);
        root.setBottom(buttonVBox);

        return new Scene(root, 900, 640);
    }

    /**
     * @author Kautuk Prasad
     * @description This method refreshes the table to display all customers.
     */
    public void refreshTable(){
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(customerTableCreation.getAllCustomers());
    }

    // Singleton instance
    public static DisplayCustomerScene getInstance(){
        if(instance == null){
            instance = new DisplayCustomerScene();
        }
        return instance;
    }
}
