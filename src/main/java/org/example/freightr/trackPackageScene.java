package org.example.freightr;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class trackPackageScene {




    public static Scene createTrackPackageScene(Stage stage) {
        NavigationVbox navigationVbox=new NavigationVbox(stage);
        CustomLabel pageHeading = new CustomLabel("Track Your Package");
        HBox inputBox = new HBox();
        CustomLabel trackLabel = new CustomLabel("Please enter the Package ID");
        CustomTextField input = new CustomTextField();
        Button trackButton = new Button("Track Package");
        inputBox.getChildren().addAll(trackLabel,input,trackButton);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(pageHeading,inputBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);


        




        BorderPane root = new BorderPane();
        root.setLeft(navigationVbox);
        root.setCenter(vBox);


        return new Scene(root,900,640);


    }



}