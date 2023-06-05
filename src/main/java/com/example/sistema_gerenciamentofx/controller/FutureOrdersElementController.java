package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FutureOrdersElementController {

    @FXML
    private Label cpfClient;

    @FXML
    private Label nameClient;

    @FXML
    private Label startDate;

    @FXML
    private Label typeService;

    public void setInfos(String name, String cpf, String date, String type) {
        this.cpfClient.setText(cpf);
        this.nameClient.setText(name);
        this.startDate.setText(date);
        this.typeService.setText(type);
    }
}
