package com.example.sistema_gerenciamentofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
public class ManagerStockController implements Initializable {
    @FXML
    private Button bttAdd;

    @FXML
    private Button bttEdit;

    @FXML
    private GridPane grid;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlAddProduct;

    @FXML
    private Pane pnlView;
    @FXML
    private Pane pnlText;

    @FXML
    private Pane pnlEditProduct;
    @FXML
    private ScrollPane scroll;



    @FXML
    void login(ActionEvent event) {
        if (event.getSource() == bttAdd) {
            pnlAddProduct.setStyle("-fx-background-color : #fffafa");
            pnlAddProduct.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/add-new-product-view.fxml"));
                Pane pane2 = loader.load();

                AddNewProductController addNewProductController = loader.getController();
                addNewProductController.setManageStock(this);
                //addNewProductController.setPnl(pnlAddProduct);
                pnlAddProduct.getChildren().clear();
                pnlAddProduct.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == bttEdit) {
            pnlEditProduct.setStyle("-fx-background-color : #fffafa");
            pnlEditProduct.toFront();
            System.out.println("oi");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/edit-product-view.fxml"));
                Pane pane2 = loader.load();
                EditProductController editProductController = loader.getController();
                editProductController.setManageStock(this);
                //addNewProductController.setPnl(pnlAddProduct);
                pnlEditProduct.getChildren().clear();
                pnlEditProduct.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private String[] imageNames = {
            "/com/example/sistema_gerenciamentofx/images/fonte.png",
            "/com/example/sistema_gerenciamentofx/images/ram2.png",
            "/com/example/sistema_gerenciamentofx/images/hd.png",
            "/com/example/sistema_gerenciamentofx/images/placamae.png",
            "/com/example/sistema_gerenciamentofx/images/placavideo.png",
            "/com/example/sistema_gerenciamentofx/images/ssd.png"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pnlView.toFront();
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < 6; ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/example/sistema_gerenciamentofx/item-stock-view.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                ItemStockController itemController = (ItemStockController) fxmlLoader.getController();
                itemController.setData(imageNames[i % imageNames.length]);
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