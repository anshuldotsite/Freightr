package org.example.freightr.scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.Database;
import org.example.freightr.TableCreation.EmployeeLoginTable;
import org.example.freightr.scenes.packageFormCreationAllScenes.AddPackageScene;
import org.example.freightr.scenes.packageFormCreationAllScenes.PackageFormScene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Sign In scene
 * @author Kautuk Prasad
 */
public class LoginPageScene {
    private static String userName;
    private static String employeeName;
    private static LoginPageScene instance;
    public static Scene createLoginPage(Stage stage) {

        VBox vBox = new VBox();

        String companyName = Database.getInstance().getCompanyName();

        CustomLabel welcomeLabel = new CustomLabel("Welcome, Freightr." + companyName);

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
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("EmployeeSignIn.txt"));
                    bw.write(usernameInput.getText());
                    bw.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Scene packageForm = AddPackageScene.createAddPackage(stage);
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

    /**
     * @author Kautuk Prasad
     * @description This is a method to get the employee name by using the username stored in the file.
     * @return Signed In Employee's Name
     */
    public String getEmployeeName(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("employeeSignIn.txt"));
            userName= br.readLine();
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            employeeName=employeeLoginTable.getEmployeeName(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return employeeName;
    }

    public static LoginPageScene getInstance(){
        if (instance==null){
            instance=new LoginPageScene();
        }
        return instance;
    }
}