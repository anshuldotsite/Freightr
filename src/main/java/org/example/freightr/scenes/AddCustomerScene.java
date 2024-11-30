package org.example.freightr.scenes;

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
import org.example.freightr.scenes.packageFormCreationAllScenes.SenderSelectionScene;

/**
 * @author Kautuk Prasad
 * @description This is form to add new customers.
 */
public class AddCustomerScene {
    public static Scene createAddCustomer(Stage stage){

        NavigationVBox navigationVBox = new NavigationVBox(stage);
        CompanyTableCreation company = CompanyTableCreation.getInstance();

        //Hbox for heading
        CustomLabel heading = new CustomLabel("Add New Customer");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);


        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        //combobox for company
        CustomLabel companyLabel = new CustomLabel("Company");
        ComboBox<Company> companyComboBox = new ComboBox<>();
        companyComboBox.setItems(FXCollections.observableArrayList(company.getAllCompanies()));
        companyComboBox.getSelectionModel().select(null);
        companyComboBox.setPlaceholder(companyLabel);
        gridPane.add(companyLabel,0,0);
        gridPane.add(companyComboBox,1,0);


        //first name
        CustomLabel firstNameLabel = new CustomLabel("First Name");
        CustomTextField firstNameTF = new CustomTextField();
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameTF, 1, 1);

        //last name
        CustomLabel lastNameLabel = new CustomLabel("Last Name");
        CustomTextField lastNameTF = new CustomTextField();
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameTF, 1, 2);

        //contact name
        CustomLabel contactLabel = new CustomLabel("Contact Number");
        CustomTextField contactTF = new CustomTextField();
        gridPane.add(contactLabel, 0, 3);
        gridPane.add(contactTF, 1, 3);

        //email
        CustomLabel emailLabel = new CustomLabel("Email");
        CustomTextField emailTF = new CustomTextField();
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailTF, 1, 4);

        //address
        CustomLabel addressLabel = new CustomLabel("Address");
        CustomTextField addressTF = new CustomTextField();
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressTF, 1, 5);

        //zip code
        CustomLabel zipLabel = new CustomLabel("Zip Code");
        CustomTextField zipTF = new CustomTextField();
        gridPane.add(zipLabel, 0, 6);
        gridPane.add(zipTF, 1, 6);

        //city
        CustomLabel cityLabel = new CustomLabel("City");
        CustomTextField cityTF = new CustomTextField();
        gridPane.add(cityLabel, 0, 7);
        gridPane.add(cityTF, 1, 7);

        //province
        CustomLabel provinceLabel = new CustomLabel("Province");
        CustomTextField provinceTF = new CustomTextField();
        gridPane.add(provinceLabel, 0, 8);
        gridPane.add(provinceTF, 1, 8);

        //country
        CustomLabel countryLabel = new CustomLabel("Country");
        CustomTextField countryTF = new CustomTextField();
        gridPane.add(countryLabel, 0, 9);
        gridPane.add(countryTF, 1, 9);

        //customer type
        CustomLabel typeLabel = new CustomLabel("Customer Type");
        CustomTextField typeTF = new CustomTextField();
        gridPane.add(typeLabel, 0, 10);
        gridPane.add(typeTF, 1, 10);

        Button addButton = new Button("Add Customer");
        addButton.setAlignment(Pos.CENTER);

        CustomLabel resultLabel= new CustomLabel("");
        addButton.setOnAction(event -> {
            if (firstNameTF.getText().equals("")||lastNameTF.getText().equals("")||contactTF.getText().equals("")||emailTF.getText().equals("")||
                    addressTF.getText().equals("")||zipTF.getText().equals("")||cityTF.getText().equals("") ||provinceTF.getText().equals("") ||
                    contactTF.getText().equals("")||typeTF.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }else {
                if (companyComboBox.getSelectionModel().getSelectedItem()==null){
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer newCustomer = new Customer(firstNameTF.getText(), lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(), zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), typeTF.getText());
                    customerTableCreation.addCustomerWithoutCompany(newCustomer);
                    resultLabel.setText("Customer Added");
                    vBox.getChildren().add(resultLabel);

                    // adding condition to refresh the table when adding customer in package
                    SenderSelectionScene senderSelectionScene=SenderSelectionScene.getInstance();
                    senderSelectionScene.refreshTable();
                }else {
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer newCustomer = new Customer(companyComboBox.getSelectionModel().getSelectedItem().getCompanyId(),firstNameTF.getText(), lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(), zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), typeTF.getText());
                    customerTableCreation.addCustomerWithCompany(newCustomer);
                    resultLabel.setText("Customer Added and associated with the company");
                    vBox.getChildren().add(resultLabel);
                }

            }
        });

        Button addCompanyBtn = new Button("Add Company");
        addCompanyBtn.setOnAction(event -> {
            Stage companyStage = new Stage();
            Scene companyFormScene = AddCompanyScene.createAddCompanyScene(companyStage);
            companyStage.setScene(companyFormScene);
            companyStage.show();
        });
        addCompanyBtn.setAlignment(Pos.CENTER);




        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(headingBox,gridPane,addButton,addCompanyBtn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);

        return new Scene(root, 900,640);
    }

}