module org.example.battleshipd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.battleshipd to javafx.fxml;
    exports org.example.battleshipd;
}