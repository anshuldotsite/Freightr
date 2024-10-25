package org.example.freightr;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PackageFormScene {

    public static Scene CreatePackageFormScene(Stage stage) {


        CustomLabel descriptionLabel = new CustomLabel(" package description");
        CustomTextField descriptionTextField = new CustomTextField();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(descriptionLabel, descriptionTextField);


        NavigationVbox navigationVbox = new NavigationVbox(stage);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vbox);


        return new Scene(root, 900, 640);


    }


}