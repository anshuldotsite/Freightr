package org.example.freightr.scenes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class CustomLabel extends Label {
    public CustomLabel(String text) {
        super(text);
        this.setStyle("-fx-font-size: 12px; -fx-text-fill: #333333;");
        this.setPadding(new Insets(3, 0, 3, 0));
    }
}