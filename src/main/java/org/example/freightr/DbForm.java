package org.example.freightr;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.scenes.LoginPageScene;

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

        //Hbox for username
        HBox userBox = new HBox();
        Label userLabel = new Label("DB User Name");
        TextField usernameInput = new TextField();
        userLabel.setMinWidth(75);
        usernameInput.setMinWidth(100);
        userBox.getChildren().addAll(userLabel,usernameInput);
        userBox.setAlignment(Pos.CENTER);
        userBox.setSpacing(10);

        //Hbox for db name
        HBox dbNameBox = new HBox();
        Label dbName = new Label("DB  Name");
        TextField dbNameInput = new TextField();
        dbName.setMinWidth(75);
        dbNameInput.setMinWidth(100);
        dbNameBox.getChildren().addAll(dbName,dbNameInput);
        dbNameBox.setAlignment(Pos.CENTER);
        dbNameBox.setSpacing(10);

        //Hbox for host
        HBox hostBox = new HBox();
        Label hostLabel = new Label("Host");
        TextField hostInput = new TextField();
        hostLabel.setMinWidth(75);
        hostInput.setMinWidth(100);
        hostBox.getChildren().addAll(hostLabel,hostInput);
        hostBox.setAlignment(Pos.CENTER);
        hostBox.setSpacing(10);


        //Hbox for password
        HBox passwordBox = new HBox();
        Label passwordLabel = new Label("DB Password");
        TextField passwordInput = new TextField();
        passwordLabel.setMinWidth(75);
        passwordInput.setMinWidth(100);
        passwordBox.getChildren().addAll(passwordLabel,passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(10);


        //test connection button
        Button testButton = new Button("Test Connection");
        testButton.setMinWidth(35);

        //next page button
        Button nextButton = new Button("Next");
        nextButton.setMinWidth(35);
        nextButton.setDisable(true);

        //button box
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(testButton,nextButton);
        buttonBox.setSpacing(5);
        buttonBox.setAlignment(Pos.CENTER);

        //Vbox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(pageHeading,userBox,dbNameBox,hostBox,passwordBox,buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        //writing to the file through the form and establishing connection
        testButton.setOnAction(actionEvent -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"));

                String empty = " ";
                String userIn = usernameInput.getText()+empty;
                String dbNameIn = dbNameInput.getText()+empty;
                String hostIN = hostInput.getText()+empty;
                String passwordIn = passwordInput.getText()+empty;

                writer.write(userIn);
                writer.write(dbNameIn);
                writer.write(hostIN);
                writer.write(passwordIn);
                writer.flush();

                Database db = Database.getInstance();
                if (db.getStatus()==true){
                    Label successLabel = new Label("Connection Successful");
                    vBox.getChildren().add(successLabel);
                    successLabel.setAlignment(Pos.BASELINE_CENTER);
                    nextButton.setDisable(false);
                }else {
                    Label failLabel = new Label("Connection Failed");
                    vBox.getChildren().add(failLabel);
                    failLabel.setAlignment(Pos.BASELINE_CENTER);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        //changing the scene if the connection is successful
        nextButton.setOnAction(actionEvent -> {
            Scene r = LoginPageScene.createLoginPage(stage);
            stage.setScene(r);
        });

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        return new Scene(root,900,640);
    }
}