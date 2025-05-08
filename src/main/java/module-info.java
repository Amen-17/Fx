module com.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.desktop;
    requires annotations;

    opens app to javafx.fxml;
    exports app to javafx.graphics;
    exports app.datos to javafx.graphics;
    opens app.datos to javafx.fxml;
    exports app.enemigo to javafx.graphics;
    opens app.enemigo to javafx.fxml;
    exports app.paneles to javafx.graphics;
    opens app.paneles to javafx.fxml;
    exports app.personaje to javafx.graphics;
    opens app.personaje to javafx.fxml;
}