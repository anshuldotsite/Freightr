package org.example.freightr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.freightr.scenes.PackageFormScene;

public class kohitest extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(PackageFormScene.CreatePackageFormScene(stage));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
