package org.example.freightr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene dbScene = DbForm.CreateDBFormScene(stage);
        Scene loginScene = LoginPageScene.createLoginPage(stage);

        File file = new File("credentials.txt");
        if (!file.exists()){
            stage.setScene(dbScene);
        }else {
            stage.setScene(loginScene);
        }
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}