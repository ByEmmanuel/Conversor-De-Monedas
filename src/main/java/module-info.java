module conversor.conversordemonedas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires java.sql;

    opens conversor.conversordemonedas to javafx.fxml;

    exports conversor.conversordemonedas;


}