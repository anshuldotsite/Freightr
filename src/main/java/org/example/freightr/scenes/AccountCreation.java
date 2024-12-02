package org.example.freightr.scenes;

import javafx.geometry.Insets;
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
     * A form for employee registration with input fields
     * @param stage
     * @return
     */
    public static Scene AccountCreationScene(Stage stage) {
        // Initialise database singleton
        Database db = Database.getInstance();

        // HBox for heading
        HBox headingBox = new HBox();
        CustomLabel heading = new CustomLabel("Create An Account");
        headingBox.getChildren().add(heading);
        headingBox.setAlignment(Pos.CENTER);

        // VBox for layout
        VBox vbox = new VBox(10);
        GridPane gridPane = new GridPane();

        // Label and text field for name
        Label name = new Label("Employee Name: ");
        TextField nameField = new TextField();
        nameField.setMinWidth(150);
        name.setMinWidth(100);
        gridPane.add(name,0,0);
        gridPane.add(nameField,1,0);

        // Label and text field for email
        Label email = new Label("Email: ");
        TextField emailText = new TextField();
        emailText.setMinWidth(150);
        email.setMinWidth(100);
        gridPane.add(email,0,1);
        gridPane.add(emailText,1,1);

        // Label and text field for employee designation
        Label designation = new Label("Designation: ");
        TextField designationText = new TextField();
        designationText.setMinWidth(150);
        designation.setMinWidth(100);
        gridPane.add(designation,0,2);
        gridPane.add(designationText,1,2);

        // Label and text field for username
        Label username = new Label("Username: ");
        TextField usernameText = new TextField();
        usernameText.setMinWidth(150);
        username.setMinWidth(100);
        gridPane.add(username,0,3);
        gridPane.add(usernameText,1,3);

        // Label and text field for password
        Label password = new Label("Password: ");
        PasswordField passwordText = new PasswordField();
        passwordText.setMinWidth(150);
        password.setMinWidth(100);
        gridPane.add(password,0,4);
        gridPane.add(passwordText,1,4);

        // Label and text field for confirming password
        Label confirmPassword = new Label("Confirm Password: ");
        PasswordField confirmPasswordText = new PasswordField();
        confirmPasswordText.setMinWidth(150);
        confirmPassword.setMinWidth(100);
        gridPane.add(confirmPassword,0,5);
        gridPane.add(confirmPasswordText,1,5);

        // Label and text field for company key
        Label companyKeyLabel = new Label("Company Key: ");
        PasswordField companyKeyTF = new PasswordField();
        companyKeyTF.setMinWidth(150);
        companyKeyLabel.setMinWidth(100);
        gridPane.add(companyKeyLabel,0,6);
        gridPane.add(companyKeyTF,1,6);

        // Button to create account
        Button createAccount = new Button("Create Account");
        CustomLabel resultLabel = new CustomLabel("");
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
                vbox.getChildren().add(resultLabel);
            }else {
                if (companyKeyTF.getText().equals(db.getCompanyKey())){
                    if (passwordText.getText().equals(confirmPasswordText.getText())){
                        EmployeeLogin newEmployee = new EmployeeLogin(nameField.getText(),emailText.getText(),designationText.getText(),usernameText.getText(),passwordText.getText());
                        EmployeeLoginTable employeeLoginTable =EmployeeLoginTable.getInstance();
                        if (employeeLoginTable.checkUserExists(usernameText.getText()) == true){
                            resultLabel.setText("User Name already exists, try another one.");
                            vbox.getChildren().add(resultLabel);
                        }else {
                            employeeLoginTable.createAccount(newEmployee);
                            resultLabel.setText("Account Created");
                            vbox.getChildren().add(resultLabel);
                        }

                    }else {
                        resultLabel.setText("Password and Confirm Password fields did not match.");
                        vbox.getChildren().add(resultLabel);
                    }
                }else {
                    resultLabel.setText("Wrong Company Key");
                    vbox.getChildren().add(resultLabel);
                }
            }


        });

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // A sign in button to sign in the application
        Button signIn = new Button("Sign In");
        signIn.setOnAction(event -> {
            Scene loginPageScene = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPageScene);
        });

        // HBox for layout
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(createAccount,signIn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(5);

        // BorderPane for layout
        BorderPane root = new BorderPane();

        // Adding all elements to the vbox and that to the root to display
        vbox.getChildren().addAll(headingBox,gridPane,buttonBox);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);

        return new Scene(root, 900, 640);
    }
}
