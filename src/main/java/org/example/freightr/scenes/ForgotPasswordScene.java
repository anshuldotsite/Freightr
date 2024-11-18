package org.example.freightr.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Forgot Password Scene
 * @author Kautuk Prasad
 */
public class ForgotPasswordScene {
    public static Scene createForgotPasswordScene(Stage stage){

        VBox vBox = new VBox();

        Label headingForgot = new Label("Forgot password? No Problem!");
        headingForgot.setAlignment(Pos.CENTER);

        //hbox for username
        HBox userNameBox = new HBox();

        CustomLabel userNameLabel = new CustomLabel("Enter your username");
        CustomTextField userNameInput = new CustomTextField();
        userNameLabel.setMinWidth(75);
        userNameInput.setMinWidth(100);
        userNameBox.getChildren().addAll(userNameLabel, userNameInput);
        userNameBox.setAlignment(Pos.CENTER);
        userNameBox.setSpacing(10);

        //hbox for new password
        HBox passwordBox = new HBox();

        CustomLabel passwordLabel = new CustomLabel("Enter new password");
        CustomTextField passwordInput = new CustomTextField();
        passwordLabel.setMinWidth(75);
        passwordInput.setMinWidth(100);
        passwordBox.getChildren().addAll(passwordLabel, passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(10);

        //hbox for confirm password
        HBox confirmPasswordBox = new HBox();

        CustomLabel confirmPassLabel = new CustomLabel("Confirm Password");
        CustomTextField confirmPasswordIn = new CustomTextField();
        confirmPassLabel.setMinWidth(75);
        confirmPasswordIn.setMinWidth(100);
        confirmPasswordBox.getChildren().addAll(confirmPassLabel, confirmPasswordIn);
        confirmPasswordBox.setAlignment(Pos.CENTER);
        confirmPasswordBox.setSpacing(10);

        //hbox for company key
        HBox companyKeyBox = new HBox();

        CustomLabel companyKeyLabel = new CustomLabel("Enter Company Key");
        CustomTextField companyKeyIn = new CustomTextField();
        companyKeyLabel.setMinWidth(75);
        companyKeyIn.setMinWidth(100);
        companyKeyBox.getChildren().addAll(companyKeyLabel, companyKeyIn);
        companyKeyBox.setAlignment(Pos.CENTER);
        companyKeyBox.setSpacing(10);

        //update password button
        Button updatePassB = new Button("Update Password");


        vBox.getChildren().addAll(headingForgot, userNameBox, passwordBox, confirmPasswordBox, companyKeyBox,updatePassB);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);




        BorderPane root = new BorderPane();
        root.setCenter(vBox);

        return new Scene(root, 900, 640);
    }
}
