package org.example.freightr.Scenes;

import javafx.scene.control.Button;

public class CustomNavButton extends Button {

    public CustomNavButton(String text) {
        super(text);
        this.setStyle(
                "-fx-background-color: #F8EDE3; " +
                        "-fx-text-fill: #C5705D; " +
                        "-fx-padding: 5px 7.5px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-border-color: #000; " +
                        "-fx-border-width: 0px; " +
                        "-fx-border-radius: 1.5px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;"
        );


        this.setOnMouseEntered(e -> this.setStyle(
                "-fx-background-color: #FAF3E3; " +
                        "-fx-text-fill: #C5705D; " +
                        "-fx-padding: 5px 7.5px; " +
                        "-fx-font-size: 16px; " +
                        "-fx-border-color: #000; " +
                        "-fx-border-width: 0px; " +
                        "-fx-border-radius: 1.5px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;"
        ));



        this.setOnMouseExited(e -> this.setStyle(
                "-fx-background-color: #F8EDE3; " +
                        "-fx-text-fill: #C5705D; " +
                        "-fx-padding: 5px 7.5px; " +
                        "-fx-font-size: 15px; " +
                        "-fx-border-color: #000; " +
                        "-fx-border-width: 0px; " +
                        "-fx-border-radius: 1.5px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;"
        ));
    }
}