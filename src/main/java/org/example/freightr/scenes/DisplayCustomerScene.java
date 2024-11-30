package org.example.freightr.scenes;

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
import org.example.freightr.TableCreation.ObjectClasses.DisplayCustomerPOJO;
import org.example.freightr.TableCreation.ObjectClasses.StatusPOJO;

import java.util.ArrayList;

/**
 * @author Kautuk Prasad
 * @description This is a scene to display all customer details in a table.
 */
public class DisplayCustomerScene {
    private static DisplayCustomerScene instance;
    private static TableView tableView;
    public static Scene createDisplayCustomer(Stage stage){

        CustomerTableCreation customer = CustomerTableCreation.getInstance();
        //heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Customers");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        tableView = new TableView();

        //column1
        TableColumn<Customer, String> column1= new TableColumn<>("First Name");
        column1.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getFirstName()));

        //column2
        TableColumn<Customer, String> column2= new TableColumn<>("Last Name");
        column2.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLastName()));

        //column3
        TableColumn<Customer, String> column3= new TableColumn<>("Company");
        column3.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCompanyName()));

        //column4
        TableColumn<Customer, String> column4= new TableColumn<>("Contact No");
        column4.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getContactNumber()));

        //column5
        TableColumn<Customer, String> column5= new TableColumn<>("Email");
        column5.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getEmail()));

        //column6
        TableColumn<Customer, String> column6= new TableColumn<>("Address");
        column6.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAddress()));

        //column7
        TableColumn<Customer, String> column7= new TableColumn<>("City");
        column7.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));

        //column8
        TableColumn<Customer, String> column8= new TableColumn<>("Province");
        column8.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProvince()));

        //column9
        TableColumn<Customer, String> column9= new TableColumn<>("Country");
        column9.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCountry()));

        //column10
        TableColumn<Customer, String> column10= new TableColumn<>("Customer Type");
        column10.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCustomerType()));

        tableView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10);
        tableView.getItems().addAll(customer.getPrettyData());

        NavigationVBox navigationVbox = new NavigationVBox(stage);
        Button addButton = new Button("Add Customer");
        Button updateButton = new Button("Update Customer");

        VBox buttonVBox = new VBox();

        HBox buttonBox = new HBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");

        buttonBox.getChildren().addAll(addButton,updateButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonVBox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        buttonVBox.setAlignment(Pos.CENTER);

        updateButton.setDisable(true);


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

        updateButton.setOnAction(event -> {
            Customer selectedCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();
            Scene updateCustomer = UpdateCustomer.createUpdateCustomer(stage,selectedCustomer);
            stage.setScene(updateCustomer);
        });


        addButton.setOnAction(event -> {
            Scene addCustomerScene = AddCustomerScene.createAddCustomer(stage);
            stage.setScene(addCustomerScene);
        });

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

    public static DisplayCustomerScene getInstance(){
        if(instance == null){
            instance = new DisplayCustomerScene();
        }
        return instance;
    }
}
