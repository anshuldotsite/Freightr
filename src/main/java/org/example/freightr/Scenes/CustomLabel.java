package org.example.freightr.Scenes;

import javafx.scene.control.Label;

public class CustomLabel extends Label {
    public CustomLabel(String text) {
        super(text);
        this.setStyle("-fx-font-family:'Roboto';-fx-font-size: 22px; -fx-text-fill: #C5705D;");
    }
}