package org.example.freightr;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ParcelFormScene  {




    public static Scene CreateParcelFormScene(Stage stage) {

        NavigationVbox navigationVbox=new NavigationVbox(stage);

        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);


return new Scene(root,900,640);


    }



}