package org.example.freightr.scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.TableCreation.EmployeeLoginTable;

/**
 * Sign In scene
 * @author Kautuk Prasad
 */
public class LoginPageScene {
    public static Scene createLoginPage(Stage stage) {

        VBox vBox = new VBox();

        CustomLabel welcomeLabel = new CustomLabel("Welcome, Freightr");

        CustomLabel signInHeading = new CustomLabel("Employee Sign In");

        //Hbox for username
        HBox userBox = new HBox();
        CustomLabel userLabel = new CustomLabel("User Name");
        CustomTextField usernameInput = new CustomTextField();
        userLabel.setMinWidth(75);
        usernameInput.setMinWidth(100);
        userBox.getChildren().addAll(userLabel, usernameInput);
        userBox.setAlignment(Pos.CENTER);
        userBox.setSpacing(10);

        //Hbox for password
        HBox passwordBox = new HBox();
        CustomLabel passwordLabel = new CustomLabel("Password");
        CustomTextField passwordInput = new CustomTextField();
        passwordLabel.setMinWidth(75);
        passwordInput.setMinWidth(100);
        passwordBox.getChildren().addAll(passwordLabel, passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(10);

        //signIN button
        Button signInB = new Button("Sign In");
        CustomLabel resultLabel = new CustomLabel("");

        signInB.setOnAction(event -> {
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            boolean signIn= employeeLoginTable.signIn(usernameInput.getText(), passwordInput.getText());
            if (signIn==true){
                Scene packageForm = PackageFormScene.CreatePackageFormScene(stage);
                stage.setScene(packageForm);
            }else{
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Wrong User Name or Password");
                vBox.getChildren().add(resultLabel);
            }
        });

        //hbox for forgot password and create account
        HBox buttonBox = new HBox();

        //forgot password
        Button forgotPasswordB = new Button("Forgot Password?");
        forgotPasswordB.setOnAction(event -> {
            Scene forgotPassScene = ForgotPasswordScene.createForgotPasswordScene(stage);
            stage.setScene(forgotPassScene);
        });

        //new user button
        Button createAccountB = new Button("Create Account");

        createAccountB.setOnAction(event -> {
            Scene accountCreationScene = AccountCreation.AccountCreationScene(stage);
            stage.setScene(accountCreationScene);
        });

        buttonBox.getChildren().addAll(forgotPasswordB, createAccountB);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(5);


        vBox.getChildren().addAll(welcomeLabel,signInHeading, userBox, passwordBox, signInB, buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);

        return new Scene(root, 900, 640);
    }
}