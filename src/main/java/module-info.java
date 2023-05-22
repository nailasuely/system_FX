module com.example.sistema_gerenciamentofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistema_gerenciamentofx to javafx.fxml;
    opens com.example.sistema_gerenciamentofx.controller to javafx.fxml;
    exports com.example.sistema_gerenciamentofx;
    exports com.example.sistema_gerenciamentofx.controller;
    exports com.example.sistema_gerenciamentofx.model;
    exports com.example.sistema_gerenciamentofx.dao.cliente;
    exports com.example.sistema_gerenciamentofx.dao.estoque;
    exports com.example.sistema_gerenciamentofx.dao.ordemServico;
    exports com.example.sistema_gerenciamentofx.dao.tecnico;

    opens com.example.sistema_gerenciamentofx.dao.cliente;
    opens com.example.sistema_gerenciamentofx.dao.estoque;
    opens com.example.sistema_gerenciamentofx.dao.ordemServico;
    opens com.example.sistema_gerenciamentofx.dao.tecnico;
    opens com.example.sistema_gerenciamentofx.model;



}