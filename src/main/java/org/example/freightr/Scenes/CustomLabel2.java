package org.example.freightr.Scenes;

import javafx.scene.control.Label;

public class CustomLabel2 extends Label {
    public CustomLabel2(String text) {
        super(text);
        this.setStyle("-fx-font-family:'Roboto'; -fx-font-size: 18px; -fx-text-fill: #C5705D;");
    }
}