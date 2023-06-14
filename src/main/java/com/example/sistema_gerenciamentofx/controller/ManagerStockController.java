package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
public class ManagerStockController implements Initializable {
    @FXML
    private Button bttViewServices;

    @FXML
    private Button bttEdit;

    @FXML
    private GridPane grid;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlViewServices;

    @FXML
    private Pane pnlView;
    @FXML
    private Button bttView;
    @FXML
    private Pane pnlText;

    @FXML
    private Pane pnlEditProduct;
    @FXML
    private ScrollPane scroll;



    @FXML
    void login(ActionEvent event) {
        if (event.getSource() == bttViewServices) {
            pnlViewServices.setStyle("-fx-background-color : #fffafa");
            pnlViewServices.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/view-services-view.fxml"));
                Pane pane2 = loader.load();

                ViewServicesController viewServicesController = loader.getController();
                viewServicesController.setManageStock(this);
                pnlViewServices.getChildren().clear();
                pnlViewServices.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == bttEdit) {
            pnlEditProduct.setStyle("-fx-background-color : #fffafa");
            pnlEditProduct.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/edit-product-view.fxml"));
                Pane pane2 = loader.load();
                EditProductController editProductController = loader.getController();
                editProductController.setManageStock(this);
                pnlEditProduct.getChildren().clear();
                pnlEditProduct.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == bttView){
            update();
            pnlView.setStyle("-fx-background-color : #fffafa");
            pnlView.toFront();
        }



    }

    private String[] imageNames = {
            "/com/example/sistema_gerenciamentofx/images/ram2.png",
            "/com/example/sistema_gerenciamentofx/images/placamae.png",
            "/com/example/sistema_gerenciamentofx/images/fonte.png",
            "/com/example/sistema_gerenciamentofx/images/placavideo.png",
            "/com/example/sistema_gerenciamentofx/images/hd.png",
            "/com/example/sistema_gerenciamentofx/images/ssd.png"
    };
    private String[] names = {
            "RAM memory",
            "Mother Board",
            "Fonte",
            "Graphic Board",
            "HDD",
            "SSD"
    };

    private String[] names2 = {
            "ram",
            "placa mae",
            "fonte",
            "placa de video",
            "hd/ssd",
            "hd/ssd"
    };


    private double[] prices = new double[6];

    private Integer[] qntd = new Integer[6];

    public void fillPrices() throws Exception {
        Map<Produto, Integer> estoque = DAO.getEstoqueDAO().getList();
        for (int i = 0; i < names2.length; i++) {
            boolean itemFound = false;
            for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                if (produto.getNome().equalsIgnoreCase(names2[i])) {
                    prices[i] = produto.getPreco();
                    qntd[i] = quantidade;
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                prices[i] = 0.0;
                qntd[i] = 0;
            }
        }
    }


    private ObservableMap<Produto, Integer> itensData;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        update();
    }

    public void update(){
        pnlView.toFront();
        int column = 0;
        int row = 1;
        try {
            fillPrices();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            for (int i = 0; i < 6; ++i) {
                itensData = FXCollections.observableMap(DAO.getEstoqueDAO().getList());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/example/sistema_gerenciamentofx/item-stock-view.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                ItemStockController itemController = (ItemStockController) fxmlLoader.getController();
                itemController.setData(imageNames[i % imageNames.length]);
                itemController.setInfos(names[i % imageNames.length], String.valueOf(prices[i]), String.valueOf(qntd[i]));

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}