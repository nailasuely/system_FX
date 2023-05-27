package com.example.sistema_gerenciamentofx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private TextField cpf;

    @FXML
    private TextField fullname1;

    @FXML
    private Button loginButton1;

    @FXML
    private Button loginButton11;

    @FXML
    private TextField telephone;

    @FXML
    void login(ActionEvent event) throws Exception {
        String addressText = address.getText();
        String cpfText = cpf.getText();
        String fullnameText = fullname1.getText();
        String telephoneText = telephone.getText();

        if (addressText.isEmpty() || cpfText.isEmpty() || fullnameText.isEmpty() || telephoneText.isEmpty()) {
            System.out.println("Erro, algum campo não foi digitado.");
        } else {
            int telephoneNumber = Integer.parseInt(telephoneText);
            Tecnico tecnico = new Tecnico(fullnameText, addressText,
                    cpfText,  Integer.parseInt(telephoneText));
            DAO.getTecnicoDAO().create(tecnico);
            System.out.println("Prontinho.");
            System.out.println(DAO.getTecnicoDAO().getList());
            // Fechar a janela atual
            Stage stage = (Stage) address.getScene().getWindow();
            stage.close();

            // Carregar a tela de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/login-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        }
    }

    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'register-view.fxml'.";
        assert cpf != null : "fx:id=\"cpf\" was not injected: check your FXML file 'register-view.fxml'.";
        assert fullname1 != null : "fx:id=\"fullname1\" was not injected: check your FXML file 'register-view.fxml'.";
        assert loginButton1 != null : "fx:id=\"loginButton1\" was not injected: check your FXML file 'register-view.fxml'.";
        assert loginButton11 != null : "fx:id=\"loginButton11\" was not injected: check your FXML file 'register-view.fxml'.";
        assert telephone != null : "fx:id=\"telephone\" was not injected: check your FXML file 'register-view.fxml'.";

    }

}
