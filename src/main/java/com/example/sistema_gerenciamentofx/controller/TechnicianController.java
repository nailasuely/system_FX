package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TechnicianController {

    @FXML
    private Label OrdersWaiting;

    @FXML
    private Label ordersConcluded;

    @FXML
    private Label ordersPeding;

    @FXML
    private Label ordersTotal;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField search_order;

}
