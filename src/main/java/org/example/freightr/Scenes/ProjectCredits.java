package org.example.freightr.Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectCredits {
    public static Scene ProjectCreditsScene(Stage stage) {
        // VBox for layout
        VBox vBox = new VBox();

        // Project Created By
        CustomLabel label = new CustomLabel("Project Credits:");
        CustomLabel dev1 = new CustomLabel("Anshul Karande");
        CustomLabel dev2 = new CustomLabel("Kautuk Prasad");
        CustomLabel dev3 = new CustomLabel("Kohinoor Jeet Singh");

        // Button to sign in
        CustomButton signInB = new CustomButton("Sign In");
        signInB.setOnAction(event -> {
            Scene loginPageScene = LoginPageScene.createLoginPage(stage);
            stage.setScene(loginPageScene);
        });

        // Adding elements to the vbox
        vBox.getChildren().addAll(label, dev1, dev2, dev3, signInB);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        // BorderPane for layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setStyle("-fx-background-color: #F8EDE3");

        return new Scene(borderPane, 900, 640);
    }
}
