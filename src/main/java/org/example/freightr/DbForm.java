package org.example.freightr;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Scenes.CustomButton;
import org.example.freightr.Scenes.CustomLabel;
import org.example.freightr.Scenes.CustomTextField;
import org.example.freightr.Scenes.LoginPageScene;

import java.io.*;

/**
 * @author Kautuk Prasad
 * @description This is a database configuration form which is used to connect to the database for the application.
 */
public class DbForm {
    public static Scene CreateDBFormScene(Stage stage) {
        //page heading
        CustomLabel pageHeading = new CustomLabel("Database Configuration Form");
        pageHeading.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();

        //username
        CustomLabel userLabel = new CustomLabel("DB User Name");
        CustomTextField usernameInput = new CustomTextField();
        gridPane.add(userLabel, 0, 0);
        gridPane.add(usernameInput, 1, 0);

        //db name
        CustomLabel dbName = new CustomLabel("DB Name");
        CustomTextField dbNameInput = new CustomTextField();
        gridPane.add(dbName, 0, 1);
        gridPane.add(dbNameInput, 1, 1);

        //host
        CustomLabel hostLabel = new CustomLabel("Host");
        CustomTextField hostInput = new CustomTextField();
        gridPane.add(hostLabel, 0, 2);
        gridPane.add(hostInput, 1, 2);

        //password
        CustomLabel passwordLabel = new CustomLabel("DB Password");
        PasswordField passwordInput = new PasswordField();
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordInput, 1, 3);
        passwordInput.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000;  -fx-font-size: 14px;");

        //company name
        CustomLabel companyNameLabel = new CustomLabel("Company Name");
        CustomTextField companyNameInput = new CustomTextField();
        gridPane.add(companyNameLabel, 0, 4);
        gridPane.add(companyNameInput, 1, 4);

        //company key
        CustomLabel companyKeyLabel = new CustomLabel("Company Key");
        PasswordField companyKeyInput = new PasswordField();
        gridPane.add(companyKeyLabel, 0, 5);
        gridPane.add(companyKeyInput, 1, 5);
        companyKeyInput.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000;  -fx-font-size: 14px;");

        //test connection button
        CustomButton testButton = new CustomButton("Test Connection");
        testButton.setMinWidth(35);

        //next page button
        CustomButton nextButton = new CustomButton("Next");
        nextButton.setMinWidth(35);
        nextButton.setDisable(true);

        //button box
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(testButton, nextButton);
        buttonBox.setSpacing(5);
        buttonBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //Vbox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(pageHeading, gridPane, buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        CustomLabel resultLabel = new CustomLabel("");


        //writing to the file through the form and establishing connection
        testButton.setOnAction(actionEvent -> {
            if (usernameInput.getText().equals("") || dbNameInput.getText().equals("")
                    || hostInput.getText().equals("") || passwordInput.getText().equals("") ||
                    companyNameInput.getText().equals("") || companyKeyInput.getText().equals("")) {
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Please fill out all the fields before proceeding.");
                resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                resultLabel.setAlignment(Pos.BASELINE_CENTER);
                vBox.getChildren().add(resultLabel);
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));

                    String empty = " ";
                    String userIn = usernameInput.getText() + empty;
                    String dbNameIn = dbNameInput.getText() + empty;
                    String hostIN = hostInput.getText() + empty;
                    String passwordIn = passwordInput.getText() + empty;
                    String companyNameIn = companyNameInput.getText() + empty;
                    String companyKeyIn = companyKeyInput.getText() + empty;

                    writer.write(userIn);
                    writer.write(dbNameIn);
                    writer.write(hostIN);
                    writer.write(passwordIn);
                    writer.write(companyNameIn);
                    writer.write(companyKeyIn);
                    writer.flush();

                    Database db = Database.getInstance();
                    if (db.getStatus() == true) {
                        //setting text field to uneditable as so user cant edit textfield values after connection is successful
                        usernameInput.setEditable(false);
                        dbNameInput.setEditable(false);
                        hostInput.setEditable(false);
                        passwordInput.setEditable(false);
                        companyNameInput.setEditable(false);
                        companyKeyInput.setEditable(false);
                        vBox.getChildren().remove(resultLabel);
                        resultLabel.setText("Connection Successful");
                        resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: green");
                        resultLabel.setAlignment(Pos.BASELINE_CENTER);
                        vBox.getChildren().add(resultLabel);
                        nextButton.setDisable(false);
                    } else {
                        vBox.getChildren().remove(resultLabel);
                        resultLabel.setText("Connection Failed");
                        resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                        resultLabel.setAlignment(Pos.BASELINE_CENTER);
                        vBox.getChildren().add(resultLabel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //changing the scene if the connection is successful
        nextButton.setOnAction(actionEvent -> {
            Scene loginPage = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPage);
        });

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
}