package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ClientElementController {

    @FXML
    private Label adressClient;

    @FXML
    private Label cpfClient;

    @FXML
    private Button deleteClient;

    @FXML
    private HBox itemC;

    @FXML
    private Label nameClient;

    @FXML
    private Label telephoneClient;

    @FXML
    private Button updateClient;

    public void setAdressClient(String adress) {
        this.adressClient.setText(adress);
    }

    public void setCpfClient(String cpf) {
        this.cpfClient.setText(cpf);
    }


    public void setNameClient(String name) {
        this.nameClient.setText(name);
    }

    public void setTelephoneClient(String telephone) {
        this.telephoneClient.setText(telephone);
    }
}
