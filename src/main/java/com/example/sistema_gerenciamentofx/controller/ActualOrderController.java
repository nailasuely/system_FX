package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

public class ActualOrderController {

    @FXML
    private Label adressClient;

    @FXML
    private Button cancelOrder;

    @FXML
    private Label cpfClient;

    @FXML
    private Button deleteClient1;

    @FXML
    private Button finalizeOrder;

    @FXML
    private RadioButton fiveStar;

    @FXML
    private RadioButton fourStar;

    @FXML
    private ImageView imgClient;

    @FXML
    private ImageView imgStartDate;

    @FXML
    private ImageView imgTypeService;

    @FXML
    private Label nameClient;

    @FXML
    private RadioButton oneStar;

    @FXML
    private ChoiceBox<?> setPaymentMethod;

    @FXML
    private Label startDate;

    @FXML
    private Label telephoneClient;

    @FXML
    private RadioButton threeStar;

    @FXML
    private RadioButton twoStar;

    @FXML
    private Label typeService;

    @FXML
    private Button updateOrder;

    @FXML
    void handleClicks(ActionEvent event) {

    }

}
