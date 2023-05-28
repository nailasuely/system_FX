package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class ElementController {

    @FXML
    private Label clientName;

    @FXML
    private Label dateOrder;

    @FXML
    private Label idOrder;

    @FXML
    private HBox itemC;

    @FXML
    private Button statusOrder;

    @FXML
    private Label valueOrder;

    public void setClientName(String name) {
        this.clientName.setText(name);
    }

    public void setDateOrder(LocalDate date) {
        this.dateOrder.setText(date.toString());
    }

    public void setIdOrder(String id) {
        this.idOrder.setText(id);
    }

    public void setStatusOrder(String status) {
        this.statusOrder.setText(status);
    }

    public void setValueOrder(String value) {
        this.valueOrder.setText(value);
    }


}
