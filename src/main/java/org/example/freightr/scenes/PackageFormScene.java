package org.example.freightr.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.TableCreation.PackageTableCred;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/***
 * @author kohinoor jeet singh
 */
public class PackageFormScene {
    private static double totalPrice;
    private static double packageGeneratedkey;
    private static double CustomerSelectedKey;

    public static Scene CreatePackageFormScene(Stage stage) {

// making form using custom label and textfiled
        CustomLabel descriptionLabel = new CustomLabel("Parcel Description:");
        CustomTextField descriptionField = new CustomTextField();

        CustomLabel heightLabel = new CustomLabel("Height (cm)");
        CustomTextField heightField = new CustomTextField();

        CustomLabel widthLabel = new CustomLabel("Width (cm)");
        CustomTextField widthField = new CustomTextField();

        CustomLabel lengthLabel = new CustomLabel("Length (cm)");
        CustomTextField lengthField = new CustomTextField();

        CustomLabel weightLabel = new CustomLabel("Weight (kg)");
        CustomTextField weightField = new CustomTextField();

        CustomLabel chargesLabel = new CustomLabel("Total Charges:");
        CustomLabel totalCharges = new CustomLabel("");

        Button calculateChargesBtn = new Button("Calculate Charges");

        CustomLabel charge = new CustomLabel("");

        Button newOrderBtn = new Button("Create New Order");

        // made grid pane to accomodate all the components
        GridPane grid = new GridPane();
        // giving grid pane styles
        grid.setPadding(new Insets(15));
        grid.setVgap(13);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        VBox vb = new VBox();

      grid.add(descriptionLabel, 0, 0);
        grid.add(descriptionField, 1, 0);

        grid.add(heightLabel, 0, 1);
        grid.add(heightField, 1, 1);
        grid.add(widthLabel, 2, 1);
        grid.add(widthField, 3, 1);
        grid.add(lengthLabel, 0, 3);
        grid.add(lengthField, 1, 3);
        grid.add(weightLabel, 2, 3);
        grid.add(weightField, 3,3);

        grid.add(calculateChargesBtn, 0, 4);
        grid.add(chargesLabel, 1, 4);
        grid.add(totalCharges, 2, 4);
        grid.add(charge,3,4);



        CustomerTableCreation customer = CustomerTableCreation.getInstance();
        TableView tableView = new TableView();


        TableColumn<Customer, String> column1= new TableColumn<>("First Name");
        column1.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getFirstName()));


        TableColumn<Customer, String> column2= new TableColumn<>("Last Name");
        column2.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLastName()));


        TableColumn<Customer, String> column3= new TableColumn<>("Contact No");
        column3.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getContactNumber()));


        TableColumn<Customer, String> column5= new TableColumn<>("Address");
        column5.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAddress()));


        TableColumn<Customer, String> column6= new TableColumn<>("City");
        column6.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));



        TableColumn<Customer, String> column8= new TableColumn<>("Country");
        column8.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCountry()));


        tableView.getColumns().addAll(column1,column2,column3,column5,column6,column8);
        tableView.getItems().addAll(customer.getAllCustomers());


        int visibleRowCount = 5;
        tableView.setFixedCellSize(25);
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(visibleRowCount + 1.01));
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());
        tableView.setPrefWidth(300);

        VBox tableContainer = new VBox(tableView);
        tableContainer.setPadding(new Insets(15, 15, 15, 15));
        tableContainer.setAlignment(Pos.CENTER);

        NavigationVBox navigationVbox = new NavigationVBox(stage);

        CustomLabel details = new CustomLabel("select a customer from the table below or add a new customer and then select it ");


        Button customerButton = new Button("Create a new Customer");
        customerButton.setOnAction(e->{
                Stage newStage = new Stage();
                Scene addCustomerScene = AddCustomerScene.createAddCustomer(newStage);
                newStage.setScene(addCustomerScene);
                newStage.show();
        });



        VBox vbox = new VBox();
        vbox.getChildren().addAll(grid,details,tableContainer,customerButton,newOrderBtn);
        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vbox);




        calculateChargesBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double weight = Double.parseDouble(weightField.getText());
            int heightSurcharge=0;
            int lengthSurcharge=0;
            int widthSurcharge=0;

            int WeightPrice =0;

             if(height>100){
                heightSurcharge=15;
            }
            if(width>100){
                widthSurcharge=15;
            }
            if(length>100){
                lengthSurcharge=15;
            }
            if(15<weight&&weight<25){
                WeightPrice=20;
            }
            else if(weight>25&&weight<50){
                WeightPrice=17;
            }
            else if (weight>50){
                WeightPrice=13;
            }else {
                WeightPrice=30;
            }
           totalPrice =heightSurcharge + widthSurcharge + lengthSurcharge + (WeightPrice * weight);
            charge.setText(String.valueOf(totalPrice));
        });

        Customer selectedCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();



        newOrderBtn.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double weight = Double.parseDouble(weightField.getText());

            String description = descriptionField.getText();

           Date date = new java.sql.Date(System.currentTimeMillis());
            int id = 0;
            Package newPackage = new Package(id, description, date, weight, height, length, width, totalPrice);


            PackageTableCred packageTableCred = PackageTableCred.getInstance();
         packageGeneratedkey = packageTableCred.addPackage(newPackage);


        });



        return new Scene(root, 900, 640);


    }


}