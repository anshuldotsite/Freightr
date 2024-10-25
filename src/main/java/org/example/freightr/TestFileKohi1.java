package org.example.freightr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFileKohi1 extends Application {
    @Override
    public void start(Stage stage){
        Scene scene = PackageFormScene.CreatePackageFormScene(stage);
        stage.setTitle("Create an account");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}