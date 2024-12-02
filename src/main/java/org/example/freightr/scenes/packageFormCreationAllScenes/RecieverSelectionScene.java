package org.example.freightr.scenes.packageFormCreationAllScenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.scenes.AddCustomerScene;
import org.example.freightr.scenes.CustomLabel;
import org.example.freightr.scenes.NavigationVBox;

public class RecieverSelectionScene {
    private static Package packageDetails;
    private static RecieverSelectionScene instance;
    private static TableView<Customer> tableView;
    public static Scene createReceiverTableScene(Stage stage, Package packageData, Customer selectedCustomer) {

        packageDetails = packageData;

        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Select receiver");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);


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


        tableView.getColumns().addAll(column1, column2, column3,column4);

        ObservableList<Customer> customerList = FXCollections.observableArrayList(customerTableCreation.getAllCustomers());
        tableView.setItems(customerList);

        Button selectReceiverBtn = new Button("Select Receiver");
        selectReceiverBtn.setDisable(true);

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


        selectReceiverBtn.setOnAction(e -> {
            Customer selectedReceiver = tableView.getSelectionModel().getSelectedItem();
            if (selectedReceiver != null) {
                stage.setScene(FinalPackageDetailsAndCreationScene.createConfirmationScene(stage,packageDetails,selectedCustomer,selectedReceiver));
                System.out.println("Package Details: " + packageDetails);
                System.out.println("Customer Details: " + selectedCustomer);
                System.out.println("Receiver Details: " + selectedReceiver);
            }
        });
        Button createReceiver = new Button("Create a new receiver");
        createReceiver.setOnAction(e->{
            Stage newStage = new Stage();
            Scene addCustomerScene = AddCustomerScene.createAddCustomer(newStage);
            newStage.setScene(addCustomerScene);
            newStage.show();
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(selectReceiverBtn,createReceiver);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        VBox buttonVbox = new VBox();
        Label emptyLabel = new Label("");
        Label emptyLabel2 = new Label("");

        buttonVbox.getChildren().addAll(emptyLabel,buttonBox,emptyLabel2);
        buttonVbox.setAlignment(Pos.CENTER);

        NavigationVBox navigationVbox = new NavigationVBox(stage);

        BorderPane root = new BorderPane();
        root.setCenter(tableView);
        root.setLeft(navigationVbox);
        root.setTop(headingBox);
        root.setBottom(buttonVbox);
        return new Scene(root, 900, 640);
    }

    public void refreshTable(){
        CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(customerTableCreation.getAllCustomers());
    }

    public static RecieverSelectionScene getInstance(){
        if (instance==null){
            instance=new RecieverSelectionScene();
        }
        return instance;
    }
}

