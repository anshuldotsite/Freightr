package org.example.freightr;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class dummyScene {




    public static Scene CreatedummyScene(Stage stage) {
        NavigationVbox navigationVbox=new NavigationVbox(stage);




        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);

return new Scene(root,900,640);


    }



}