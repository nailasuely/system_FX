module com.example.sistema_gerenciamentofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistema_gerenciamentofx to javafx.fxml;
    exports com.example.sistema_gerenciamentofx;
}