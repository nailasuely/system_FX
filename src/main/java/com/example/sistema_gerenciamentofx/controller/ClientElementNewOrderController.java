package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientElementNewOrderController {


    @FXML
    private Label cpfClient;

    @FXML
    private Label nameClient;

    @FXML
    private Button selectClient;

    @FXML
    void getClient(ActionEvent event) {
        NewOrderController.setCpfClient(cpfClient.getText());
        NewOrderController newOrderController = new NewOrderController();
        newOrderController.setCpfSelected(cpfClient.getText());

    }

    public void setClientInfos(String nameClient, String cpfClient ){
        this.nameClient.setText(nameClient);
        this.cpfClient.setText(cpfClient);

    }
}
