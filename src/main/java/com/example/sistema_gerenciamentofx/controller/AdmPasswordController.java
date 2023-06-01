package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdmPasswordController {

    @FXML
    private Button acessBtn;

    @FXML
    private PasswordField admPassword;

    @FXML
    private Text alert;

    private boolean access;

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public void getPasswordAdm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/password-adm-view.fxml"));
        Parent root = loader.load();
        Stage alertStage = new Stage();
        Scene scene = new Scene(root);
        alertStage.setResizable(false);
        alertStage.setScene(scene);
        alertStage.show();
    }

    @FXML
    public void liberateAcess(ActionEvent event){

        String passwordText = admPassword.getText();
        String passwordCorrect = "adm1234";
        AlertMessageController alertMessageController = new AlertMessageController();
        try {
            if(passwordText.isEmpty()){
                alertMessageController.showAlertMensage("Campo vazio");
                setAccess(false);
            } else if (passwordText.equals(passwordCorrect)) {
                setAccess(true);

            } else if (!passwordText.equals(passwordCorrect)) {
                alertMessageController.showAlertMensage("Senha incorreta");
                setAccess(false);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void checkPassword(){
        String passwordText = admPassword.getText();
        String passwordCorrect = "adm1234";
        AlertMessageController alertMessageController = new AlertMessageController();
        try {
            if(passwordText.isEmpty()){
                alertMessageController.showAlertMensage("Campo vazio");
                setAccess(false);
            } else if (passwordText.equals(passwordCorrect)) {
                setAccess(true);

            } else if (!passwordText.equals(passwordCorrect)) {
                alertMessageController.showAlertMensage("Senha incorreta");
                setAccess(false);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
