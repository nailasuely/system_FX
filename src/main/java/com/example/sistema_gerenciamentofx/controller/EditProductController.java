package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {

    @FXML
    private Button loginButton1;

    @FXML
    private TextField peca;

    @FXML
    private TextField price;

    @FXML
    private ChoiceBox<String> setTypeProduct;
    private ManagerStockController managerStockController;

    private Pane pnl;
    private String productType[] = {"RAM", "SSD/HD", "Fonte", "Placa Mãe", "Placa de Video"};

    public void setPnl(Pane pnlChildren){
        this.pnl = pnlChildren;
    }
    public void setManageStock(ManagerStockController managerStockController) {
        this.managerStockController = managerStockController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTypeProduct.getItems().addAll(productType);
    }
    @FXML
    void login(ActionEvent event) {


    }

}
