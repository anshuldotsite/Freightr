package org.example.freightr;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavigationVbox extends VBox {

    private Stage stage;

    public NavigationVbox(Stage stage) {
        this.stage = stage;

        Button Button1 = new Button("Parcel Scene Button");
        Button button2 = new Button("dummy button");
        Button button3 = new Button("dummy button");
        Button button4 = new Button("dummy button");
        Button1.setOnAction(e-> stage.setScene(ParcelFormScene.CreateParcelFormScene(stage)));

        button2.setOnAction(e-> stage.setScene(dummyScene.CreatedummyScene(stage)));

        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: lightgray;");


        this.getChildren().addAll(Button1,button2,button3,button4);
    }



}