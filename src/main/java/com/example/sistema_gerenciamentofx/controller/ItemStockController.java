package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemStockController {

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;
    @FXML
    private Label nameLabel1;

    @FXML
    private Label qtdItem;

    public void setData(String imageName) {
        Image image = new Image(getClass().getResourceAsStream(imageName));
        img.setImage(image);
        //nameLabel.setText("Item Name");
    }

    public void setInfos(String name, String price, String qtd){
        nameLabel.setText(name);
        priceLabel.setText(price);
        qtdItem.setText(qtd);
    }

    @FXML
    void click(MouseEvent event) {
    }
}