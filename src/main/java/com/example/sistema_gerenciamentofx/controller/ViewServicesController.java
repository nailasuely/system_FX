package com.example.sistema_gerenciamentofx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewServicesController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Pane pnlText;

    @FXML
    private Pane pnlView;

    @FXML
    private ScrollPane scroll;

    private String[] names = {
            "Montagem",
            "Instalação de Sistema Operacional",
            "Limpeza",
            "Instalação de programa",
    };

    private String[] prices = {
            "A definir",
            "R$ 50",
            "R$ 10",
            "R$ 70"
    };
    private ManagerStockController managerStockController;

    public void setManageStock(ManagerStockController managerStockController) {
        this.managerStockController = managerStockController;
    }
    private String[] imageNames = {
            "/com/example/sistema_gerenciamentofx/images/6.png"
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < 4; ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/example/sistema_gerenciamentofx/item-stock-view.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                ItemStockController itemController = (ItemStockController) fxmlLoader.getController();
                itemController.setData(imageNames[i % imageNames.length]);
                itemController.setInfos(names[i], String.valueOf(prices[i]), "");
                if (column == 3) {
                    column = 0;
                    ++row;
                }

                this.grid.add(anchorPane, column++, row);
                this.grid.setMinWidth(-1.0);
                this.grid.setPrefWidth(-1.0);
                this.grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                this.grid.setMinHeight(-1.0);
                this.grid.setPrefHeight(-1.0);
                this.grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }
    }

    }
