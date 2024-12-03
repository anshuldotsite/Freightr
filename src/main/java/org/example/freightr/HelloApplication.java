package org.example.freightr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.freightr.Scenes.DisplayPackageScene;
import org.example.freightr.Scenes.LoginPageScene;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene dbScene = DbForm.CreateDBFormScene(stage);

        // CSS Stylesheets
        String stylesheet = getClass().getResource("styles.css").toExternalForm();
//        dbScene.getStylesheets().add(stylesheet);

        File file = new File("credentials.txt");
        if (!file.exists()){
            stage.setScene(dbScene);
        }else {
            Scene loginScene = LoginPageScene.createLoginPage(stage);
            loginScene.getStylesheets().add(stylesheet);
            stage.setScene(loginScene);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}