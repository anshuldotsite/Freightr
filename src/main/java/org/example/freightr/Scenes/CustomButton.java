package org.example.freightr.Scenes;

import javafx.scene.control.Button;

public class CustomButton  extends Button {
    public CustomButton(String buttonText) {
        super(buttonText);
        this.setStyle("-fx-background-color: #C5705D; -fx-padding: 10px; -fx-text-fill: #F8EDE3; -fx-background-radius: 5px; -fx-cursor: hand;");
    }
}
