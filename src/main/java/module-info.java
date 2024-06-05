module org.example.battleshipd {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.battleshipd to javafx.fxml;
    exports org.example.battleshipd;
    exports org.example.battleshipd.view;
    opens org.example.battleshipd.controller to javafx.fxml;
}