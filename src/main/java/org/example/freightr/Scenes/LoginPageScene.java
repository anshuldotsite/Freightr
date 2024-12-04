package org.example.freightr.Scenes;


import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.freightr.Database;
import org.example.freightr.TableCreation.EmployeeLoginTable;
import org.example.freightr.Scenes.PackageCRUDScenes.AddPackageScene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A sign in scene for the user to sign in
 * @author Kautuk Prasad
 */
public class LoginPageScene {
    // Variables for storing user and employee names
    private static String userName;
    private static String employeeName;

    // Singleton instance
    private static LoginPageScene instance;

    public static Scene createLoginPage(Stage stage) {
        // VBox for layout
        VBox vBox = new VBox();
        vBox.setStyle("-fx-spacing: 10px;");
        vBox.setAlignment(Pos.CENTER);

        // Retrieving company name
        String companyName = Database.getInstance().getCompanyName();

        // Welcome label
        CustomLabel welcomeLabel = new CustomLabel(companyName + ".Freightr");

        // Image
        ImageView imageView = new ImageView(new Image("logo.png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        // Fade in Transition
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), imageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Fade out Transition
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), imageView);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Play animation
        fadeIn.play();

        // Sign in label
        CustomLabel signInHeading = new CustomLabel("Employee Sign In");

        // HBox for username
        HBox userBox = new HBox();
        CustomLabel userLabel = new CustomLabel("Username");
        userLabel.setMinWidth(100);
        CustomTextField usernameInput = new CustomTextField();
        usernameInput.setPrefWidth(200);
        userBox.getChildren().addAll(userLabel, usernameInput);
        userBox.setAlignment(Pos.CENTER);
        userBox.setSpacing(12);

        // HBox for password
        HBox passwordBox = new HBox();
        CustomLabel passwordLabel = new CustomLabel("Password");
        passwordLabel.setMinWidth(100);
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPrefWidth(200);
        passwordBox.getChildren().addAll(passwordLabel, passwordInput);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(12);
        passwordInput.setStyle("-fx-background-color: #D0B8A8; -fx-text-fill: #000; -fx-font-size: 14px;");

        // Sign-in button
        CustomButton signInB = new CustomButton("Sign In");

        // Result Label
        CustomLabel resultLabel = new CustomLabel("");
        resultLabel.setStyle("-fx-text-fill: red;");

        // This event handler creates a file which stores the details, and if any errors gives an error
        signInB.setOnAction(event -> {
            if (usernameInput.getText().equals("")||passwordInput.getText().equals("")){
                vBox.getChildren().remove(resultLabel);
                resultLabel.setText("Please fill out all the fields");
                vBox.getChildren().add(resultLabel);
            }else {
                EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
                boolean signIn = employeeLoginTable.signIn(usernameInput.getText(), passwordInput.getText());
                if (signIn == true) {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("EmployeeSignIn.txt"));
                        bw.write(usernameInput.getText());
                        bw.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Scene packageForm = AddPackageScene.createAddPackage(stage);
                    stage.setScene(packageForm);
                } else {
                    vBox.getChildren().remove(resultLabel);
                    resultLabel.setText("Wrong User Name or Password");
                    vBox.getChildren().add(resultLabel);
                }
            }
        });

        // HBox for forgot password and create account
        HBox buttonBox = new HBox();

        // Button for forgot password
        CustomButton forgotPasswordB = new CustomButton("Forgot Password?");
        forgotPasswordB.setOnAction(event -> {
            fadeOut.play();
            fadeOut.setOnFinished(e ->{
                Scene forgotPassScene = ForgotPasswordScene.createForgotPasswordScene(stage);
                stage.setScene(forgotPassScene);
            });
        });


        // Button for creating a new user account
        CustomButton createAccountB = new CustomButton("Create Account");
             createAccountB.setOnAction(event -> {
                 fadeOut.play();
                 fadeOut.setOnFinished(e ->{
                     Scene accountCreationScene = AccountCreation.AccountCreationScene(stage);
                     stage.setScene(accountCreationScene);
                 });
        });

        buttonBox.getChildren().addAll(signInB, createAccountB);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        //2nd button box for guest user and sign in
        HBox buttonBoxTwo = new HBox();

        //button for customer tracking
        CustomButton customerBtn = new CustomButton("Already a customer?");
        customerBtn.setOnAction(event -> {
            fadeOut.play();
            fadeOut.setOnFinished(e ->{
                Scene trackForCustomer = TrackForCustomersScene.createTrackForCustomer(stage);
                stage.setScene(trackForCustomer);
            });
        });

        buttonBoxTwo.getChildren().addAll(forgotPasswordB,customerBtn);
        buttonBoxTwo.setAlignment(Pos.CENTER);
        buttonBoxTwo.setSpacing(10);

        // HBox for credits button
        HBox buttonBoxThree = new HBox();

        // Button for Credits
        CustomButton creditsBtn = new CustomButton("Project Credits");
        creditsBtn.setOnAction(event -> {
            fadeOut.play();
            fadeOut.setOnFinished(e ->{
                Scene projectCredits = ProjectCredits.ProjectCreditsScene(stage);
                stage.setScene(projectCredits);
            });
        });

        buttonBoxThree.getChildren().add(creditsBtn);
        buttonBoxThree.setAlignment(Pos.CENTER);
        buttonBoxThree.setSpacing(10);

        // Adding all elements to vbox
        vBox.getChildren().addAll(imageView, welcomeLabel, signInHeading, userBox, passwordBox, buttonBox, buttonBoxTwo, buttonBoxThree);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        // Border Pane for layout
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(root, 900, 640);
    }

    /**
     * @return Signed In Employee's Name
     * @author Kautuk Prasad
     * @description This is a method to get the employee name by using the username stored in the file.
     */
    public String getEmployeeName() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("employeeSignIn.txt"));
            userName = br.readLine();
            EmployeeLoginTable employeeLoginTable = EmployeeLoginTable.getInstance();
            employeeName = employeeLoginTable.getEmployeeName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeName;
    }

    // Singleton instance
    public static LoginPageScene getInstance() {
        if (instance == null) {
            instance = new LoginPageScene();
        }
        return instance;
    }
}