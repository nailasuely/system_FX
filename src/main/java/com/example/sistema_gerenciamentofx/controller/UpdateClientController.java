package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateClientController {

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

    @FXML
    void login(ActionEvent event) {

    }

}
