package org.example.freightr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFileKautuk1 extends Application {
    @Override
    public void start(Stage stage){
        Scene scene = trackPackageScene.createTrackPackageScene(stage);
        stage.setTitle("Track Package");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
