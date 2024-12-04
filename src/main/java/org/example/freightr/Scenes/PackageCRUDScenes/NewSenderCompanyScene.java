package org.example.freightr.Scenes.PackageCRUDScenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Scenes.*;
import org.example.freightr.TableCreation.CompanyTableCreation;
import org.example.freightr.TableCreation.ObjectClasses.Company;
import org.example.freightr.TableCreation.ObjectClasses.Package;

/**
 * @author Kautuk Prasad
 * @description This is a customized company addition scene for new sender.
 */
public class NewSenderCompanyScene {
    public static Scene createCompanyScene(Stage stage, Package packageData){
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

        // Result Label
        CustomLabel resultLabel = new CustomLabel("");

        // An add button to add the company to the database
        CustomButton addBtn = new CustomButton("Add");

        /**
         * This event handler has conditions to check if any of the input fields are empty or not, if not a company will be added to the database
         */
        addBtn.setOnAction(event -> {
            if (companyNameTF.getText().equals("")||contactTF.getText().equals("")||emailTF.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setStyle("-fx-text-fill: red;");
                resultLabel.setText("Please fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }else {
                vBox.getChildren().remove(resultLabel);
                CompanyTableCreation company =  CompanyTableCreation.getInstance();
                Company newCompany = new Company(companyNameTF.getText(),Integer.parseInt(contactTF.getText()), emailTF.getText());
                company.addCompany(newCompany);
                resultLabel.setText("Company Added");
                vBox.getChildren().add(resultLabel);
            }
        });

        //Back button
        CustomButton backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            Scene senderScene = NewSenderScene.createNewSender(stage,packageData);
            stage.setScene(senderScene);
        });


        //Hbox for buttons
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addBtn,backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        // Border Pane for layout
        BorderPane root = new BorderPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Adding all elements to the vbox
        vBox.getChildren().addAll(headingBox,gridPane,buttonBox);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        NavigationVBox navigationVBox = new NavigationVBox(stage);

        root.setCenter(vBox);
        root.setLeft(navigationVBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900,640);
    }
}
