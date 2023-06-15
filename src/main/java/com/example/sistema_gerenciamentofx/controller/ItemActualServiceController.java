package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ItemActualServiceController{

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label quantLabel;

    @FXML
    void click(MouseEvent event) {

    }

    public void setInfos(String quant, String name, String imageName){
        Image image = new Image(getClass().getResourceAsStream(imageName));
        img.setImage(image);
        quantLabel.setText(quant);
        nameLabel.setText(name);
    }
}
