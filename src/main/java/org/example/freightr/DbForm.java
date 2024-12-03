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
import org.example.freightr.Scenes.LoginPageScene;

import java.io.*;

/**
 * @author Kautuk Prasad
 * @description This is a database configuration form which is used to connect to the database for the application.
 */
public class DbForm {
    public static Scene CreateDBFormScene (Stage stage){

        //page heading
        Label pageHeading = new Label("Database Configuration Form");
        pageHeading.setAlignment(Pos.CENTER);
        pageHeading.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");

        GridPane gridPane = new GridPane();

        //username
        Label userLabel = new Label("DB User Name");
        TextField usernameInput = new TextField();
        gridPane.add(userLabel,0,0);
        gridPane.add(usernameInput,1,0);
        userLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");
        usernameInput.setStyle("-fx-text-fill: #3498db;");

        //db name
        Label dbName = new Label("DB  Name");
        TextField dbNameInput = new TextField();
        gridPane.add(dbName,0,1);
        gridPane.add(dbNameInput,1,1);
        dbName.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");
        dbNameInput.setStyle("-fx-text-fill: #3498db;");

        //host
        Label hostLabel = new Label("Host");
        TextField hostInput = new TextField();
        gridPane.add(hostLabel,0,2);
        gridPane.add(hostInput,1,2);
        hostLabel.setStyle("-fx-font-size: 18px;-fx-font-weight: bold; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");
        hostInput.setStyle("-fx-text-fill: #3498db;");


        //password
        Label passwordLabel = new Label("DB Password");
        PasswordField passwordInput = new PasswordField();
        gridPane.add(passwordLabel,0,3);
        gridPane.add(passwordInput,1,3);
        passwordLabel.setStyle("-fx-font-size: 18px;-fx-font-weight: bold; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");


        //company name
        Label companyNameLabel = new Label("Company Name");
        TextField companyNameInput = new TextField();
        gridPane.add(companyNameLabel,0,4);
        gridPane.add(companyNameInput,1,4);
        companyNameLabel.setStyle("-fx-font-size: 18px;-fx-font-weight: bold; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");
        companyNameInput.setStyle("-fx-text-fill: #3498db;");

        //company key
        Label companyKeyLabel = new Label("Company Key");
        TextField companyKeyInput = new TextField();
        gridPane.add(companyKeyLabel,0,5);
        gridPane.add(companyKeyInput,1,5);
        companyKeyLabel.setStyle("-fx-font-size: 18px;-fx-font-weight: bold; -fx-text-fill: #7f8c8d; -fx-min-width: 75px;");
        companyKeyInput.setStyle("-fx-text-fill: #3498db;");

        //test connection button
        Button testButton = new Button("Test Connection");
        testButton.setMinWidth(35);
        testButton.setStyle("  -fx-background-color: #3498db; -fx-text-fill: white;  -fx-padding: 10px 20px;       -fx-font-size: 16px; -fx-background-radius: 5px;  -fx-cursor: hand;");

        //next page button
        Button nextButton = new Button("Next");
        nextButton.setMinWidth(35);
        nextButton.setDisable(true);
        nextButton.setStyle("  -fx-background-color: #3498db; -fx-text-fill: white;  -fx-padding: 10px 20px;       -fx-font-size: 16px; -fx-background-radius: 5px;  -fx-cursor: hand;");

        //button box
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(testButton,nextButton);
        buttonBox.setSpacing(5);
        buttonBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //Vbox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(pageHeading,gridPane,buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        Label resultLabel = new Label("Please fill out all the fields before proceeding");
        resultLabel.setStyle("-fx-text-fill: red");

        //writing to the file through the form and establishing connection
        testButton.setOnAction(actionEvent -> {
            if (usernameInput.getText().equals("")||dbNameInput.getText().equals("")
                    ||hostInput.getText().equals("")|| passwordInput.getText().equals("")||
                    companyNameInput.getText().equals("") ||companyKeyInput.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                vBox.getChildren().add(resultLabel);

            }else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));

                    String empty = " ";
                    String userIn = usernameInput.getText()+empty;
                    String dbNameIn = dbNameInput.getText()+empty;
                    String hostIN = hostInput.getText()+empty;
                    String passwordIn = passwordInput.getText()+empty;
                    String companyNameIn = companyNameInput.getText()+empty;
                    String companyKeyIn = companyKeyInput.getText()+empty;

                    writer.write(userIn);
                    writer.write(dbNameIn);
                    writer.write(hostIN);
                    writer.write(passwordIn);
                    writer.write(companyNameIn);
                    writer.write(companyKeyIn);
                    writer.flush();

                    Database db = Database.getInstance();
                    if (db.getStatus()==true){
                        Label successLabel = new Label("Connection Successful");
                        successLabel.setStyle("-fx-text-fill: green");
                        vBox.getChildren().add(successLabel);
                        successLabel.setAlignment(Pos.BASELINE_CENTER);
                        nextButton.setDisable(false);
                    }else {
                        Label failLabel = new Label("Connection Failed");
                        failLabel.setStyle("-fx-text-fill: red");
                        vBox.getChildren().add(failLabel);
                        failLabel.setAlignment(Pos.BASELINE_CENTER);
                    }
                }catch (Exception e){
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
        return new Scene(root,900,640);
    }
}