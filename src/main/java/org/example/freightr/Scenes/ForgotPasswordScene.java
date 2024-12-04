package org.example.freightr.Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

        //Grid pane for form
        GridPane gridPane = new GridPane();

        // Forgot password for label
        CustomLabel headingForgot = new CustomLabel("Forgot your password? No Problem!");
        headingForgot.setAlignment(Pos.CENTER);


        // Username Label
        CustomLabel userNameLabel = new CustomLabel("Enter your username");
        CustomTextField userNameInput = new CustomTextField();
        gridPane.add(userNameLabel,0,0);
        gridPane.add(userNameInput,1,0);


        // Password Label
        CustomLabel passwordLabel = new CustomLabel("Enter new password");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordInput,1,1);


        // Confirm Password Label
        CustomLabel confirmPassLabel = new CustomLabel("Confirm Password");
        PasswordField confirmPasswordIn = new PasswordField();
        gridPane.add(confirmPassLabel,0,2);
        gridPane.add(confirmPasswordIn,1,2);
        confirmPasswordIn.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");
;

        // Label for company key
        CustomLabel companyKeyLabel = new CustomLabel("Enter Company Key");
        PasswordField companyKeyIn = new PasswordField();
        gridPane.add(companyKeyLabel,0,3);
        gridPane.add(companyKeyIn,1,3);
        companyKeyIn.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");

        // Update password button
        CustomButton updatePassB = new CustomButton("Update Password");

        // Result Label
        CustomLabel resultLabel = new CustomLabel("");

        /**
         * This event handler checks for conditions to update the user's password with errors if they passwords not match and you enter the wrong company key
         */
        updatePassB.setOnAction(event -> {
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            if (userNameInput.getText().equals("")||passwordInput.getText().equals("")||
                    confirmPasswordIn.getText().equals("")||companyKeyIn.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Please fill out all the fields");
                resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                vBox.getChildren().add(resultLabel);
            }
            else{
                if (companyKeyIn.getText().equals(db.getCompanyKey())) {
                    if (passwordInput.getText().equals(confirmPasswordIn.getText())) {
                        if (employeeLoginTable.checkUserExists(userNameInput.getText())==true){
                            vBox.getChildren().remove(resultLabel);
                            resultLabel.setText("Password Updated!");
                            employeeLoginTable.updatePassword(userNameInput.getText(), passwordInput.getText());
                            resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: green");
                            resultLabel.setAlignment(Pos.CENTER);
                            vBox.getChildren().add(resultLabel);
                        }else {
                            vBox.getChildren().remove(resultLabel);
                            resultLabel.setText("User Name does not exist");
                            resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                            vBox.getChildren().add(resultLabel);
                        }
                    } else {
                        vBox.getChildren().remove(resultLabel);
                        resultLabel.setText("Password and Confirm password fields did not match.");
                        resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                        resultLabel.setAlignment(Pos.CENTER);
                        vBox.getChildren().add(resultLabel);
                    }
                } else {
                    vBox.getChildren().remove(resultLabel);
                    resultLabel.setText("Wrong Company Key");
                    resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: red");
                    resultLabel.setAlignment(Pos.CENTER);
                    vBox.getChildren().add(resultLabel);
                }
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

        //Setting grid pane
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Adding all elements to the vbox
        vBox.getChildren().addAll(headingForgot, gridPane, buttonBox);
        vBox.setSpacing(20);

        // BorderPane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
}
