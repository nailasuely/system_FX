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

    private NewOrderController newOrderController;

    public void setNewOrderController(NewOrderController newOrderController) {
        this.newOrderController = newOrderController;
    }

    @FXML
    void getClient(ActionEvent event) {
        NewOrderController.setCpfClient(cpfClient.getText());
        newOrderController.setCpfSelected(cpfClient.getText());

    }

    public void setClientInfos(String nameClient, String cpfClient ){
        this.nameClient.setText(nameClient);
        this.cpfClient.setText(cpfClient);

    }
}
