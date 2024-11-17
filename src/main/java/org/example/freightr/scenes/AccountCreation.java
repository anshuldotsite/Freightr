package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.DOA.EmployeeLogin;
import org.example.freightr.TableCreation.EmployeeLoginTable;

import static org.example.freightr.TableCreation.Dbconst.COMPANY_KEY;

public class AccountCreation {

    public static Scene AccountCreationScene(Stage stage) {

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        // Label and text field for name
        Label name = new Label("Employee Name: ");
        TextField nameField = new TextField();
        nameField.setMinWidth(150);
        name.setMinWidth(100);
        HBox nameBox = new HBox(12, name, nameField);

        // Label and text field for email
        Label email = new Label("Email: ");
        TextField emailText = new TextField();
        emailText.setMinWidth(150);
        email.setMinWidth(100);
        HBox emailBox = new HBox(12, email, emailText);


        // Label and text field for employee designation
        Label designation = new Label("Designation: ");
        TextField designationText = new TextField();
        designationText.setMinWidth(150);
        designation.setMinWidth(100);
        HBox designationBox = new HBox(12, designation, designationText);

        // Label and text field for username
        Label username = new Label("Username: ");
        TextField usernameText = new TextField();
        usernameText.setMinWidth(150);
        username.setMinWidth(100);
        HBox usernameBox = new HBox(12, username, usernameText);

        // Label and text field for password
        Label password = new Label("Password: ");
        PasswordField passwordText = new PasswordField();
        passwordText.setMinWidth(150);
        password.setMinWidth(100);
        HBox passwordBox = new HBox(12, password, passwordText);

        // Label and text field for confirming password
        Label confirmPassword = new Label("Confirm Password: ");
        PasswordField confirmPasswordText = new PasswordField();
        confirmPasswordText.setMinWidth(150);
        confirmPassword.setMinWidth(100);
        HBox confirmPasswordBox = new HBox(7, confirmPassword, confirmPasswordText);

        // Label and text field for company key
        Label companyKeyLabel = new Label("Company Key: ");
        PasswordField companyKeyTF = new PasswordField();
        companyKeyTF.setMinWidth(150);
        companyKeyLabel.setMinWidth(100);
        HBox companyKeyBox = new HBox(7, companyKeyLabel, companyKeyTF);

        Button createAccount = new Button("Create Account");
        createAccount.setOnAction(e -> {
            if (companyKeyTF.getText().equals(COMPANY_KEY)){
                if (passwordText.getText().equals(confirmPasswordText.getText())){
                    EmployeeLogin newEmployee = new EmployeeLogin(nameField.getText(),emailText.getText(),designationText.getText(),usernameText.getText(),passwordText.getText());
                    EmployeeLoginTable employeeLoginTable =EmployeeLoginTable.getInstance();
                    employeeLoginTable.createAccount(newEmployee);
                    Label accountCreated = new Label("Account Created");
                    vbox.getChildren().add(accountCreated);
                    accountCreated.setAlignment(Pos.CENTER);
                }else {
                    Label unidenticalPass = new Label("Password and Confirm Password fields did not match.");
                    vbox.getChildren().add(unidenticalPass);
                    unidenticalPass.setAlignment(Pos.CENTER);
                }
            }else {
                Label wrongKey = new Label("Wrong Company Key");
                vbox.getChildren().add(wrongKey);
                wrongKey.setAlignment(Pos.CENTER);
            }
        });


        vbox.getChildren().addAll(nameBox, emailBox, designationBox, usernameBox, passwordBox, confirmPasswordBox, companyKeyBox,createAccount);

        return new Scene(vbox, 900, 640);
    }
}
