package org.example.freightr.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Database;
import org.example.freightr.TableCreation.EmployeeLoginTable;

/**
 * @description A forgot password scene for users to reset their passwords
 * @author Kautuk Prasad
 */
public class ForgotPasswordScene {
    public static Scene createForgotPasswordScene(Stage stage){
        // Initialise database singleton
        Database db = Database.getInstance();

        // Vbox for layout
        VBox vBox = new VBox();

        // Forgot password for label
        Label headingForgot = new Label("Forgot password? No Problem!");
        headingForgot.setAlignment(Pos.CENTER);

        // HBox for username
        HBox userNameBox = new HBox();

        // Username Label
        CustomLabel userNameLabel = new CustomLabel("Enter your username");
        CustomTextField userNameInput = new CustomTextField();
        userNameLabel.setMinWidth(75);
        userNameInput.setMinWidth(100);
        userNameBox.getChildren().addAll(userNameLabel, userNameInput);
        userNameBox.setAlignment(Pos.CENTER);
        userNameBox.setSpacing(10);

        // HBox for new password
        HBox passwordBox = new HBox();

        // Password Label
        CustomLabel passwordLabel = new CustomLabel("Enter new password");
        CustomTextField passwordInput = new CustomTextField();
        passwordLabel.setMinWidth(75);
        passwordInput.setMinWidth(100);
        passwordBox.getChildren().addAll(passwordLabel, passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(10);

        // Hbox for confirm password
        HBox confirmPasswordBox = new HBox();

        // Confirm Password Label
        CustomLabel confirmPassLabel = new CustomLabel("Confirm Password");
        CustomTextField confirmPasswordIn = new CustomTextField();
        confirmPassLabel.setMinWidth(75);
        confirmPasswordIn.setMinWidth(100);
        confirmPasswordBox.getChildren().addAll(confirmPassLabel, confirmPasswordIn);
        confirmPasswordBox.setAlignment(Pos.CENTER);
        confirmPasswordBox.setSpacing(10);

        // HBox for company key
        HBox companyKeyBox = new HBox();

        // Label for company key
        CustomLabel companyKeyLabel = new CustomLabel("Enter Company Key");
        CustomTextField companyKeyIn = new CustomTextField();
        companyKeyLabel.setMinWidth(75);
        companyKeyIn.setMinWidth(100);
        companyKeyBox.getChildren().addAll(companyKeyLabel, companyKeyIn);
        companyKeyBox.setAlignment(Pos.CENTER);
        companyKeyBox.setSpacing(10);

        // Update password button
        Button updatePassB = new Button("Update Password");
        /**
         * This event handler checks for conditions to update the user's password with errors if they passwords not match and you enter the wrong company key
         */
        updatePassB.setOnAction(event -> {
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            if (companyKeyIn.getText().equals(db.getCompanyKey())){
                if (passwordInput.getText().equals(confirmPasswordIn.getText())){
                    employeeLoginTable.updatePassword(userNameInput.getText(),passwordInput.getText());
                    CustomLabel updatedPassLabel = new CustomLabel("Password Updated");
                    updatedPassLabel.setAlignment(Pos.CENTER);
                    vBox.getChildren().add(updatedPassLabel);
                }else {
                    CustomLabel unidenticalPass = new CustomLabel("Password and Confirm password fields did not match.");
                    unidenticalPass.setAlignment(Pos.CENTER);
                    vBox.getChildren().add(unidenticalPass);
                }
            }else {
                CustomLabel wrongCompanyKey = new CustomLabel("Wrong Company Key");
                wrongCompanyKey.setAlignment(Pos.CENTER);
                vBox.getChildren().add(wrongCompanyKey);
            }
        });

        // Button to sign in
        Button signInB = new Button("Sign In");
        signInB.setOnAction(event -> {
            Scene loginPageScene = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPageScene);
        });

        // HBox for buttons
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(updatePassB,signInB);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(5);

        // Adding all elements to the vbox
        vBox.getChildren().addAll(headingForgot, userNameBox, passwordBox, confirmPasswordBox, companyKeyBox,buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        // BorderPane for root
        BorderPane root = new BorderPane();
        root.setCenter(vBox);

        return new Scene(root, 900, 640);
    }
}
