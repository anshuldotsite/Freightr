package org.example.freightr.scenes;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

import java.util.ArrayList;

/**
 * @author Kautuk Prasad
 * @description This class updates the user's existing customer detail in the database
 */
public class UpdateCustomer {
    public static Scene createUpdateCustomer(Stage stage, Customer customer){
        // Table instances
        DisplayCustomerScene displayCustomerScene = DisplayCustomerScene.getInstance();
        CompanyTableCreation company = CompanyTableCreation.getInstance();

        // VBox and HBox for layout
        VBox vBox = new VBox();
        HBox headingBox = new HBox();

        // Label for updating the customer
        CustomLabel heading = new CustomLabel("Update Customer");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // GridPane for layout
        GridPane gridPane = new GridPane();

        // CombBbox for company
        CustomLabel companyLabel = new CustomLabel("Company");
        ComboBox<Company> companyComboBox = new ComboBox<>();
        ArrayList<Company> companies = company.getAllCompanies();
        companyComboBox.setItems(FXCollections.observableArrayList(company.getAllCompanies()));
        if (customer.getCompanyName()==null){
            companyComboBox.getSelectionModel().select(null);
        }else {
            companyComboBox.getSelectionModel().select(find(companies, customer.getCompanyId()));
        }

        gridPane.add(companyLabel,0,0);
        gridPane.add(companyComboBox,1,0);


        // Label for First Name
        CustomLabel firstNameLabel = new CustomLabel("First Name");
        CustomTextField firstNameTF = new CustomTextField();
        firstNameTF.setText(customer.getFirstName());
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameTF, 1, 1);

        // Label for Last Name
        CustomLabel lastNameLabel = new CustomLabel("Last Name");
        CustomTextField lastNameTF = new CustomTextField();
        lastNameTF.setText(customer.getLastName());
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameTF, 1, 2);

        // Label for Contact Name
        CustomLabel contactLabel = new CustomLabel("Contact Number");
        CustomTextField contactTF = new CustomTextField();
        contactTF.setText(customer.getContactNumber());
        gridPane.add(contactLabel, 0, 3);
        gridPane.add(contactTF, 1, 3);

        // Label for Email
        CustomLabel emailLabel = new CustomLabel("Email");
        CustomTextField emailTF = new CustomTextField();
        emailTF.setText(customer.getEmail());
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailTF, 1, 4);

        // Label for Street Address
        CustomLabel addressLabel = new CustomLabel("Address");
        CustomTextField addressTF = new CustomTextField();
        addressTF.setText(customer.getAddress());
        gridPane.add(addressLabel, 0, 5);
        gridPane.add(addressTF, 1, 5);

        // Label for Zip Code
        CustomLabel zipLabel = new CustomLabel("Zip Code");
        CustomTextField zipTF = new CustomTextField();
        zipTF.setText(customer.getZipcode());
        gridPane.add(zipLabel, 0, 6);
        gridPane.add(zipTF, 1, 6);

        // Label for City
        CustomLabel cityLabel = new CustomLabel("City");
        CustomTextField cityTF = new CustomTextField();
        cityTF.setText(customer.getCity());
        gridPane.add(cityLabel, 0, 7);
        gridPane.add(cityTF, 1, 7);

        // Label for Province
        CustomLabel provinceLabel = new CustomLabel("Province");
        CustomTextField provinceTF = new CustomTextField();
        provinceTF.setText(customer.getProvince());
        gridPane.add(provinceLabel, 0, 8);
        gridPane.add(provinceTF, 1, 8);

        // Label for Country
        CustomLabel countryLabel = new CustomLabel("Country");
        CustomTextField countryTF = new CustomTextField();
        countryTF.setText(customer.getCountry());
        gridPane.add(countryLabel, 0, 9);
        gridPane.add(countryTF, 1, 9);

        // Label for Customer Type
        CustomLabel typeLabel = new CustomLabel("Customer Type");
        CustomTextField typeTF = new CustomTextField();
        typeTF.setText(customer.getCustomerType());
        gridPane.add(typeLabel, 0, 10);
        gridPane.add(typeTF, 1, 10);

        // Button for update
        Button updateBtn = new Button("Update Customer");
        updateBtn.setAlignment(Pos.CENTER);

        // Label for result
        CustomLabel resultLabel = new CustomLabel("");

        /**
         * This event handler checks if the input fields are empty or not, if they are a result gets displayed
         * If they're not, the customer's details get updated even if any company is selected or not
         */
        updateBtn.setOnAction(event -> {
            if (firstNameTF.getText().equals("")||lastNameTF.getText().equals("")||contactTF.getText().equals("")||emailTF.getText().equals("")||
                    addressTF.getText().equals("")||zipTF.getText().equals("")||cityTF.getText().equals("") ||provinceTF.getText().equals("") ||
                    contactTF.getText().equals("")||typeTF.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }else {
                // If the company is not selected
                if (companyComboBox.getSelectionModel().getSelectedItem()==null){
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer updatedCustomer = new Customer(firstNameTF.getText(),
                            lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(),
                            zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), typeTF.getText());
                    customerTableCreation.updateCustomer(updatedCustomer);
                    resultLabel.setText("Customer Updated");
                    vBox.getChildren().add(resultLabel);
                    DisplayCustomerScene.getInstance().refreshTable();
                }else {
                    // If a company is selected
                    vBox.getChildren().remove(resultLabel);
                    CustomerTableCreation customerTableCreation = CustomerTableCreation.getInstance();
                    Customer updatedCustomer = new Customer(companyComboBox.getSelectionModel().getSelectedItem().getCompanyId(),firstNameTF.getText(), lastNameTF.getText(), contactTF.getText(),
                            emailTF.getText(), addressTF.getText(),
                            zipTF.getText(), cityTF.getText(), provinceTF.getText(),
                            countryTF.getText(), typeTF.getText());
                    updatedCustomer.setCustomerId(customer.getCustomerId());
                    customerTableCreation.updateCustomer(updatedCustomer);
                    resultLabel.setText("Customer Updated");
                    vBox.getChildren().add(resultLabel);
                    DisplayCustomerScene.getInstance().refreshTable();
                }
            }
        });


        // GridPane for layout
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // NavigationVBox to navigate to different scenes
        NavigationVBox navigationVBox = new NavigationVBox(stage);

        // Add all components to the vbox
        vBox.getChildren().addAll(headingBox,gridPane,updateBtn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setLeft(navigationVBox);

        return new Scene(root, 900, 640);
    }

    // Finds the index of a company by its ID
    public static int find(ArrayList<?> arrayList, int id){
        ArrayList<Company> searchList = (ArrayList<Company>) ((ArrayList<?>) arrayList);
        for(int i = 0; i < searchList.size(); i++){
            if(searchList.get(i).getCompanyId() == id){
                System.out.println(i);
                return i;
            }
        }
        return 0;
    }
}
