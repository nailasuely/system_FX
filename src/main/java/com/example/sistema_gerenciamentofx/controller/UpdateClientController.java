package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateClientController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField cpf;

    @FXML
    private TextField fullname1;

    @FXML
    private Button loginButton1;

    @FXML
    private TextField telephone;
    private ClientsController clientsController;

    public void setClientsController(ClientsController clientsController) {
        this.clientsController = clientsController;
    }

    public void setAddress(String currentAddress) {
        this.address.setPromptText(currentAddress);
    }

    public void setCpf(String currentCpf) {
        this.cpf.setPromptText(currentCpf);
    }

    public void setFullname1(String currentFullName) {
        this.fullname1.setPromptText(currentFullName);
    }

    public void setTelephone(String CurrentTelephone) {
        this.telephone.setPromptText(CurrentTelephone);
    }

    @FXML
    void login(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setFullname1("fala vida");
    }
}
