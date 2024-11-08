package org.example.freightr.scenes;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
    public CustomTextField() {
        this.setStyle("-fx-font-size: 14px; -fx-padding: 5; -fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 3;");
        this.setWidth(100);
    }

    public String getTextValue() {
        return this.getText();
    }
}
