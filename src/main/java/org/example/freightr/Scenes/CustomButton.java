package org.example.freightr.Scenes;

import javafx.scene.control.Button;

public class CustomButton  extends Button {
    public CustomButton(String buttonText) {
        super(buttonText);
        this.setStyle("-fx-background-color: #C5705D; -fx-padding: 8px 15px; -fx-text-fill: #F8EDE3; -fx-background-radius: 16px; -fx-cursor: hand; -fx-text-alignment: center; -fx-font-size: 16px;");
    }
}