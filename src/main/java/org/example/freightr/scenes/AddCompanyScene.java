package org.example.freightr.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.CompanyTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Company;

/**
 * @author Kautuk Prasad
 * @description This is a form to add companies in the database.
 */
public class AddCompanyScene {
    public static Scene createAddCompanyScene(Stage stage){
        // HBox for heading
        CustomLabel heading = new CustomLabel("Add new Company");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // VBox and a GridPane for layout
        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        // Label for the company name
        CustomLabel companyNameLabel = new CustomLabel("Enter Company Name");
        CustomTextField companyNameTF = new CustomTextField();
        gridPane.add(companyNameLabel, 0, 0);
        gridPane.add(companyNameTF, 1, 0);

        // Label for the contact number
        CustomLabel contactLabel = new CustomLabel("Enter Contact Number");
        CustomTextField contactTF = new CustomTextField();
        gridPane.add(contactLabel, 0, 1);
        gridPane.add(contactTF, 1, 1);

        // Label for the email address
        CustomLabel emailLabel = new CustomLabel("Email");
        CustomTextField emailTF = new CustomTextField();
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailTF, 1, 2);

        // Label for displaying errors if any
        CustomLabel resultLabel = new CustomLabel("");

        // An add button to add the company to the database
        Button addBtn = new Button("Add");

        /**
         * This event handler has conditions to check if any of the input fields are empty or not, if not a company will be added to the database
         */
        addBtn.setOnAction(event -> {
            if (companyNameTF.getText().equals("")||contactTF.getText().equals("")||emailTF.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Please fill out all the fields");
            }else {
                vBox.getChildren().remove(resultLabel);
                CompanyTableCreation company =  CompanyTableCreation.getInstance();
                Company newCompany = new Company(companyNameTF.getText(),Integer.parseInt(contactTF.getText()), emailTF.getText());
                company.addCompany(newCompany);
                resultLabel.setText("Company Added");
                vBox.getChildren().add(resultLabel);
            }
        });

        // Border Pane for layout
        BorderPane root = new BorderPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Adding all elements to the vbox and that to the root to display
        vBox.getChildren().addAll(headingBox,gridPane,addBtn);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        NavigationVBox navigationVBox = new NavigationVBox(stage);

        root.setCenter(vBox);
        root.setLeft(navigationVBox);

        return new Scene(root, 900,640);
    }
}
