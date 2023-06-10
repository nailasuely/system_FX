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

    public void setData(String imageName) {
        Image image = new Image(getClass().getResourceAsStream(imageName));
        img.setImage(image);
        nameLabel.setText("Item Name");
    }

    @FXML
    void click(MouseEvent event) {
    }
}