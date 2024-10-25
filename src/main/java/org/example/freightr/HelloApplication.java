package org.example.freightr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Hello!");
        stage.setScene(ParcelFormScene.CreateParcelFormScene(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}