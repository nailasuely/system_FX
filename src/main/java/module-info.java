module com.example.sistema_gerenciamentofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistema_gerenciamentofx to javafx.fxml;
    exports com.example.sistema_gerenciamentofx;
    exports com.example.sistema_gerenciamentofx.model;
    exports com.example.sistema_gerenciamentofx.dao.cliente;
    exports com.example.sistema_gerenciamentofx.dao.estoque;
    exports com.example.sistema_gerenciamentofx.dao.ordemServico;
    exports com.example.sistema_gerenciamentofx.dao.tecnico;

    //opens com.uefs.gestaoacademica.dao.aluno;
    //opens com.uefs.gestaoacademica.dao.inscricao;
    opens com.example.sistema_gerenciamentofx.model;


}