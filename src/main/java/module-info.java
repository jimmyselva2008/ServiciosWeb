module com.tuempresa.serviciosweb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    exports com.tuempresa.serviciosweb.controller to javafx.fxml;
    opens com.tuempresa.serviciosweb.model to javafx.fxml, javafx.base;

    opens com.tuempresa.serviciosweb.controller to javafx.fxml;
    opens com.tuempresa.serviciosweb to javafx.fxml;
    exports com.tuempresa.serviciosweb;
}