package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertMessageController {

    @FXML
    private Text alert;

    public void setAlert(String warning) {
        this.alert.setText(warning);
    }

    //gracas ao GETCLASS nao pode ser um metodo static
    public void showAlertMensage(String mensage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/alert-mensage-view.fxml"));
        Parent root = loader.load();
        AlertMessageController alertMessageController = loader.getController();
        alertMessageController.setAlert(mensage);
        Stage alertStage = new Stage();
        Scene scene = new Scene(root);
        alertStage.setResizable(false);
        alertStage.setScene(scene);
        alertStage.show();
    }


}
