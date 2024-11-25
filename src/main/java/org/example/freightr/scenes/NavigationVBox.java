package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.freightr.scenes.PackageFormScene;
import org.example.freightr.scenes.TrackPackageScene;

public class NavigationVBox extends VBox {

    private Stage stage;

    public NavigationVBox(Stage stage) {
        this.stage = stage;

        Button Button1 = new Button("Package Scene Button");
        Button button2 = new Button("Track Package");
        Button button3 = new Button("All Customers");
        Button1.setOnAction(e -> stage.setScene(PackageFormScene.CreatePackageFormScene(stage)));
        button2.setOnAction(e -> stage.setScene(TrackPackageScene.createTrackPackageScene(stage)));
        button3.setOnAction(e -> stage.setScene(DisplayCustomerScene.createDisplayCustomer(stage)));

        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: lightgray;");

        this.getChildren().addAll(Button1, button2, button3);
    }
}