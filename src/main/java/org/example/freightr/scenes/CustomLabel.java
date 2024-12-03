package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class CustomLabel extends Label {
    public CustomLabel(String text) {
        super(text);
        this.setStyle("-fx-text-fill: #3498db;");
    }
}