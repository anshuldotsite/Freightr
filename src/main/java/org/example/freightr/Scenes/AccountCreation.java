package org.example.freightr.Scenes;

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

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.EmployeeLogin;

import org.example.freightr.TableCreation.EmployeeLoginTable;

public class AccountCreation {
    /**
     * @description A form for employee registration with input fields
     * @param stage
     * @return
     */
    public static Scene AccountCreationScene(Stage stage) {
        // Initialise database singleton
        Database db = Database.getInstance();

        // HBox for heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Create your account");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // VBox for layout
        VBox vbox = new VBox(10);
        GridPane gridPane = new GridPane();

        // Label and text field for name
        CustomLabel name = new CustomLabel("Employee Name");
        CustomTextField nameField = new CustomTextField();
        gridPane.add(name,0,0);
        gridPane.add(nameField,1,0);

        // Label and text field for email
        CustomLabel email = new CustomLabel("Email");
        CustomTextField emailText = new CustomTextField();
        gridPane.add(email,0,1);
        gridPane.add(emailText,1,1);

        // Label and text field for employee designation
        CustomLabel designation = new CustomLabel("Designation");
        CustomTextField designationText = new CustomTextField();
        gridPane.add(designation,0,2);
        gridPane.add(designationText,1,2);

        // Label and text field for username
        CustomLabel username = new CustomLabel("Username");
        CustomTextField usernameText = new CustomTextField();
        gridPane.add(username,0,3);
        gridPane.add(usernameText,1,3);

        // Label and text field for password
        CustomLabel password = new CustomLabel("Password");
        PasswordField passwordText = new PasswordField();
        gridPane.add(password,0,4);
        gridPane.add(passwordText,1,4);
        passwordText.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000;  -fx-font-size: 14px;");

        // Label and text field for confirming password
        CustomLabel confirmPassword = new CustomLabel("Confirm Password");
        PasswordField confirmPasswordText = new PasswordField();
        gridPane.add(confirmPassword,0,5);
        gridPane.add(confirmPasswordText,1,5);
        confirmPasswordText.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000;  -fx-font-size: 14px;");

        // Label and text field for company key
        CustomLabel companyKeyLabel = new CustomLabel("Company Key");
        PasswordField companyKeyTF = new PasswordField();
        gridPane.add(companyKeyLabel,0,6);
        gridPane.add(companyKeyTF,1,6);
        companyKeyTF.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000;  -fx-font-size: 14px;");

        // Button to create account
        CustomButton createAccount = new CustomButton("Create Account");

        // CustomLabel
        CustomLabel resultLabel = new CustomLabel("");
        resultLabel.setStyle("-fx-font-family: 'Roboto'; -fx-font-weight: bold; -fx-font-size: 18px");
        resultLabel.setAlignment(Pos.CENTER);

        /**
         * This event handler validates company key, creates a new employee login object
         * and conditions to check if a username already exists or not and creates an account if username is unique
         * along with conditions to handle wrong passwords and handle wrong company key
         */
        createAccount.setOnAction(e -> {
            vbox.getChildren().remove(resultLabel);

            if (nameField.getText().equals("")||emailText.getText().equals("")||designationText.getText().equals("")||
            usernameText.getText().equals("")||passwordText.getText().equals("")||confirmPassword.getText().equals("")||
            companyKeyTF.getText().equals("")){
                resultLabel.setText("Please fill out all the fields");
                resultLabel.setStyle("-fx-text-fill: red;");
                vbox.getChildren().add(resultLabel);
            }else {
                if (companyKeyTF.getText().equals(db.getCompanyKey())){
                    if (passwordText.getText().equals(confirmPasswordText.getText())){
                        EmployeeLogin newEmployee = new EmployeeLogin(nameField.getText(),emailText.getText(),designationText.getText(),usernameText.getText(),passwordText.getText());
                        EmployeeLoginTable employeeLoginTable =EmployeeLoginTable.getInstance();
                        if (employeeLoginTable.checkUserExists(usernameText.getText()) == true){
                            resultLabel.setText("User Name already exists, try another one.");
                            resultLabel.setStyle("-fx-text-fill: red;");
                            vbox.getChildren().add(resultLabel);
                        }else {
                            employeeLoginTable.createAccount(newEmployee);
                            resultLabel.setText("Account Created");
                            resultLabel.setStyle("-fx-text-fill: green;");
                            vbox.getChildren().add(resultLabel);
                        }
                    }else {
                        resultLabel.setText("Password and Confirm Password fields did not match.");
                        resultLabel.setStyle("-fx-text-fill: red;");
                        vbox.getChildren().add(resultLabel);
                    }
                }else {
                    resultLabel.setText("Wrong Company Key");
                    resultLabel.setStyle("-fx-background-color: red;");
                    vbox.getChildren().add(resultLabel);
                }
            }
        });

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // A sign in button to sign in the application
        CustomButton signIn = new CustomButton("Sign In");
        signIn.setOnAction(event -> {
            Scene loginPageScene = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPageScene);
        });

        // HBox for layout
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(createAccount,signIn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        // BorderPane for layout
        BorderPane root = new BorderPane();

        // Adding all elements to the vbox
        vbox.getChildren().addAll(headingBox,gridPane,buttonBox);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }
}
