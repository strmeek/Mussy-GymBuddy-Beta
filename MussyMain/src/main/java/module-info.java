module com.example.mussymain {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.example.mussymain to javafx.fxml;
    exports com.example.mussymain;
}