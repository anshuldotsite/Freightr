module org.example.freightr {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.freightr to javafx.fxml;
    exports org.example.freightr;


}