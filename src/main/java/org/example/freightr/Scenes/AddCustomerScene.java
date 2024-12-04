package org.example.freightr.Scenes;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CompanyTableCreation;
import org.example.freightr.TableCreation.CustomerTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Company;
import org.example.freightr.TableCreation.ObjectClasses.Customer;
import org.example.freightr.TableCreation.ObjectClasses.Package;
import org.example.freightr.Scenes.PackageCRUDScenes.SenderSelectionScene;

/**
 * @author Kautuk Prasad
 * @description This is form to add new customers.
 */
public class AddCustomerScene {
    public static Scene createAddCustomer(Stage stage){
        // A navigation VBox to navigate different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);
        // A singleton instance for the database
        CompanyTableCreation company = CompanyTableCreation.getInstance();

        // HBox for heading
        CustomLabel heading = new CustomLabel("Add New Customer");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // VBox and GridPane for layout
        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        // Combobox for company
        CustomLabel companyLabel = new CustomLabel("Company");
        ComboBox<Company> companyComboBox = new ComboBox<>();
        companyComboBox.setItems(FXCollections.observableArrayList(company.getAllCompanies()));
        companyComboBox.getSelectionModel().select(null);
        gridPane.add(companyLabel,0,0);
        gridPane.add(companyComboBox,1,0);

        // Label for first name
        CustomLabel firstNameLabel = new CustomLabel("First Name");
        CustomTextField firstNameTF = new CustomTextField();
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameTF, 1, 1);

        // Label for last name
        CustomLabel lastNameLabel = new CustomLabel("Last Name");
        CustomTextField lastNameTF = new CustomTextField();
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameTF, 1, 2);

        // Label for the contact number
        CustomLabel contactLabel = new CustomLabel("Contact Number");
        CustomTextField contactTF = new CustomTextField();
        gridPane.add(contactLabel, 0, 3);
        gridPane.add(contactTF, 1, 3);

        // Label for the email address
        CustomLabel emailLabel = new CustomLabel("Email");
        CustomTextField emailTF = new CustomTextField();
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailTF, 1, 4);

        // Label for the street address
        CustomLabel addressLabel = new CustomLabel("Address");
        CustomTextField addressTF = new CustomTextField();
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressTF, 1, 5);

        // Label for the zip code
        CustomLabel zipLabel = new CustomLabel("Zip Code");
        CustomTextField zipTF = new CustomTextField();
        gridPane.add(zipLabel, 0, 6);
        gridPane.add(zipTF, 1, 6);

        // Label for city
        CustomLabel cityLabel = new CustomLabel("City");
        CustomTextField cityTF = new CustomTextField();
        gridPane.add(cityLabel, 0, 7);
        gridPane.add(cityTF, 1, 7);

        // Label for province
        CustomLabel provinceLabel = new CustomLabel("Province");
        CustomTextField provinceTF = new CustomTextField();
        gridPane.add(provinceLabel, 0, 8);
        gridPane.add(provinceTF, 1, 8);

        // Label for country
        CustomLabel countryLabel = new CustomLabel("Country");
        CustomTextField countryTF = new CustomTextField();
        gridPane.add(countryLabel, 0, 9);
        gridPane.add(countryTF, 1, 9);


        // A button to the add customer to the database
        CustomButton addButton = new CustomButton("Add Customer");
        addButton.setAlignment(Pos.CENTER);

        // Label for displaying errors if any
        CustomLabel resultLabel= new CustomLabel("");

        /**
         * This event handler checks if any of the labels are empty, displays an error message if they do
         * and if they're not, customer gets added to the database
         */
        addButton.setOnAction(event -> {
            if (firstNameTF.getText().equals("")||lastNameTF.getText().equals("")||contactTF.getText().equals("")||emailTF.getText().equals("")||
                    addressTF.getText().equals("")||zipTF.getText().equals("")||cityTF.getText().equals("") ||provinceTF.getText().equals("") ||
                    contactTF.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }else {
                if (companyComboBox.getSelectionModel().getSelectedItem()==null){
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer newCustomer = new Customer(firstNameTF.getText(), lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(), zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), "Self");
                    customerTableCreation.addCustomerWithoutCompany(newCustomer);
                    resultLabel.setText("Customer Added");
                    vBox.getChildren().add(resultLabel);

                    // adding condition to refresh the table when adding customer in package
                    Package packages = new Package();
                    Scene senderScene = SenderSelectionScene.CreateSenderSelectionScene(stage,packages);
                    Scene displayCustomerScene = DisplayCustomerScene.createDisplayCustomer(stage);
                    DisplayCustomerScene.getInstance().refreshTable();
                    SenderSelectionScene.getInstance().refreshTable();
                }else {
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer newCustomer = new Customer(companyComboBox.getSelectionModel().getSelectedItem().getCompanyId(),firstNameTF.getText(), lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(), zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), "Corporate");
                    customerTableCreation.addCustomerWithCompany(newCustomer);
                    resultLabel.setText("Customer Added and associated with the company");
                    vBox.getChildren().add(resultLabel);
                    Package packages = new Package();
                    Scene senderScene = SenderSelectionScene.CreateSenderSelectionScene(stage,packages);
                    Scene displayCustomerScene = DisplayCustomerScene.createDisplayCustomer(stage);
                    DisplayCustomerScene.getInstance().refreshTable();
                    SenderSelectionScene.getInstance().refreshTable();
                }
            }
        });

        HBox buttonBox = new HBox();

        // A button to add company to the database
        CustomButton addCompanyBtn = new CustomButton("Add Company");
        addCompanyBtn.setOnAction(event -> {
            Scene companyFormScene = AddCompanyScene.createAddCompanyScene(stage);
            stage.setScene(companyFormScene);
        });
        addCompanyBtn.setAlignment(Pos.CENTER);

        buttonBox.getChildren().addAll(addButton,addCompanyBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Adding all elements to the vbox
        vBox.getChildren().addAll(headingBox,gridPane,buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900,640);
    }
}