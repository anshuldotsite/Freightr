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

        //Hbox for heading
        CustomLabel heading = new CustomLabel("Add new Company");
        HBox headingBox = new HBox();
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);


        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

        //company name
        CustomLabel companyNameLabel = new CustomLabel("Enter Company Name");
        CustomTextField companyNameTF = new CustomTextField();
        gridPane.add(companyNameLabel, 0, 0);
        gridPane.add(companyNameTF, 1, 0);

        //contact number
        CustomLabel contactLabel = new CustomLabel("Enter Contact Number");
        CustomTextField contactTF = new CustomTextField();
        gridPane.add(contactLabel, 0, 1);
        gridPane.add(contactTF, 1, 1);

        //email
        CustomLabel emailLabel = new CustomLabel("Email");
        CustomTextField emailTF = new CustomTextField();
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailTF, 1, 2);

        CustomLabel resultLabel = new CustomLabel("");

        //add button
        Button addBtn = new Button("Add");

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


        BorderPane root = new BorderPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(headingBox,gridPane,addBtn);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        NavigationVBox navigationVBox = new NavigationVBox(stage);

        root.setCenter(vBox);
        root.setLeft(navigationVBox);

        return new Scene(root, 900,640);
    }

}
