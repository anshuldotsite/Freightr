module org.example.freightr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.freightr to javafx.fxml;
    exports org.example.freightr;
    exports org.example.freightr.scenes;
    opens org.example.freightr.scenes to javafx.fxml;
}