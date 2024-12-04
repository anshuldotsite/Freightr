package org.example.freightr.Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class ForgotPasswordScene  {
    public static Scene createForgotPasswordScene(Stage stage) {
        // Initialise database singleton
        Database db = Database.getInstance();

        // Vbox for layout
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        // Forgot password for label
        CustomLabel headingForgot = new CustomLabel("Forgot your password? No Problem!");
        headingForgot.setAlignment(Pos.CENTER);

        // HBox for username
        HBox userNameBox = new HBox(20);

        // Username Label
        CustomLabel userNameLabel = new CustomLabel("Enter your username");
        userNameLabel.setMinWidth(150);
        CustomTextField userNameInput = new CustomTextField();
        userNameInput.setPrefWidth(300);
        userNameBox.getChildren().addAll(userNameLabel, userNameInput);
        userNameBox.setAlignment(Pos.CENTER);

        // HBox for new password
        HBox passwordBox = new HBox(20);

        // Password Label
        CustomLabel passwordLabel = new CustomLabel("Enter new password");
        userNameInput.setMinWidth(150);
        PasswordField passwordInput = new PasswordField();
        userNameInput.setPrefWidth(300);
        passwordBox.getChildren().addAll(passwordLabel, passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordInput.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");

        // Hbox for confirm password
        HBox confirmPasswordBox = new HBox(20);

        // Confirm Password Label
        CustomLabel confirmPassLabel = new CustomLabel("Confirm Password");
        confirmPassLabel.setMinWidth(150);
        PasswordField confirmPasswordIn = new PasswordField();
        confirmPasswordIn.setPrefWidth(300);
        confirmPasswordBox.getChildren().addAll(confirmPassLabel, confirmPasswordIn);
        confirmPasswordBox.setAlignment(Pos.CENTER);
        confirmPasswordIn.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");
;

        // HBox for company key
        HBox companyKeyBox = new HBox(20);

        // Label for company key
        CustomLabel companyKeyLabel = new CustomLabel("Enter Company Key");
        confirmPassLabel.setMinWidth(150);
        PasswordField companyKeyIn = new PasswordField();
        companyKeyIn.setPrefWidth(300);
        companyKeyBox.getChildren().addAll(companyKeyLabel, companyKeyIn);
        companyKeyBox.setAlignment(Pos.CENTER);
        companyKeyIn.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");

        // Update password button
        CustomButton updatePassB = new CustomButton("Update Password");

        /**
         * This event handler checks for conditions to update the user's password with errors if they passwords not match and you enter the wrong company key
         */
        updatePassB.setOnAction(event -> {
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            if (companyKeyIn.getText().equals(db.getCompanyKey())) {
                if (passwordInput.getText().equals(confirmPasswordIn.getText())) {
                    employeeLoginTable.updatePassword(userNameInput.getText(), passwordInput.getText());
                    CustomLabel updatedPassLabel = new CustomLabel("Password Updated");
                    updatedPassLabel.setStyle("-fx-text-fill: green");
                    updatedPassLabel.setAlignment(Pos.CENTER);
                    vBox.getChildren().add(updatedPassLabel);
                } else {
                    CustomLabel unidenticalPass = new CustomLabel("Password and Confirm password fields did not match.");
                    unidenticalPass.setStyle("-fx-text-fill: red");
                    unidenticalPass.setAlignment(Pos.CENTER);
                    vBox.getChildren().add(unidenticalPass);
                }
            } else {
                CustomLabel wrongCompanyKey = new CustomLabel("Wrong Company Key");
                wrongCompanyKey.setStyle("-fx-text-fill: red");
                wrongCompanyKey.setAlignment(Pos.CENTER);
                vBox.getChildren().add(wrongCompanyKey);
            }
        });

        // Button to sign in
        CustomButton signInB = new CustomButton("Sign In");
        signInB.setOnAction(event -> {
            Scene loginPageScene = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPageScene);
        });

        // HBox for buttons
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(updatePassB, signInB);
        buttonBox.setAlignment(Pos.CENTER);

        // Adding all elements to the vbox
        vBox.getChildren().addAll(headingForgot, userNameBox, passwordBox, confirmPasswordBox, companyKeyBox, buttonBox);
        vBox.setSpacing(20);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
}
