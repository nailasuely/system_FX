package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {

    @FXML
    private Button loginButton1;

    @FXML
    private TextField peca;

    @FXML
    private TextField price;
    @FXML
    private TextField qntd;
    Produto pd1 = new Produto();
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

        setTypeProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            peca.setText(newValue);

            try {
                Map<Produto, Integer> estoqueDAO = DAO.getEstoqueDAO().getList();
                if (newValue.equals("RAM")) {
                    qntd.setText(String.valueOf(DAO.getEstoqueDAO().getQuantidade(Produto.novaRam())));
                    price.setText(String.valueOf(Produto.novaRam().getPreco()));
                } else if (newValue.equals("SSD/HD")) {
                    qntd.setText(String.valueOf(DAO.getEstoqueDAO().getQuantidade(Produto.novoHDSSD())));
                    price.setText(String.valueOf(Produto.novoHDSSD().getPreco()));
                } else if (newValue.equals("Fonte")) {
                    qntd.setText(String.valueOf(DAO.getEstoqueDAO().getQuantidade(Produto.novaFonte())));
                    price.setText(String.valueOf(Produto.novaFonte().getPreco()));
                } else if (newValue.equals("Placa Mãe")) {
                    qntd.setText(String.valueOf(DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaMae())));
                    price.setText(String.valueOf(Produto.novaPlacaMae().getPreco()));
                } else if (newValue.equals("Placa de Video")) {
                    qntd.setText(String.valueOf(DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaDeVideo())));
                    price.setText(String.valueOf(Produto.novaPlacaDeVideo().getPreco()));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        AlertMessageController alertMessageController = new AlertMessageController();
        String productName = peca.getText();
        String productPrice = price.getText();
        String productType = setTypeProduct.getValue();
        String newQuantityStr = qntd.getText();

        try {
            int newQuantity = Integer.parseInt(newQuantityStr);

            if (productType != null && !productType.isEmpty() && productName != null && !productName.isEmpty()) {
                try {
                    Map<Produto, Integer> estoqueDAO = DAO.getEstoqueDAO().getList();
                    Produto produto = null;
                    if (productName.equals("RAM")) {
                        produto = Produto.novaRam();
                    } else if (productName.equals("SSD/HD")) {
                        produto = Produto.novoHDSSD();
                    } else if (productName.equals("Fonte")) {
                        produto = Produto.novaFonte();
                    } else if (productName.equals("Placa Mãe")) {
                        produto = Produto.novaPlacaMae();
                    } else if (productName.equals("Placa de Video")) {
                        produto = Produto.novaPlacaDeVideo();
                    } else {
                        alertMessageController.showAlertMensage("Você não pode alterar o nome do produto.");
                        return;
                    }

                    if (produto != null) {
                        double currentPrice = produto.getPreco();

                        if (!productPrice.equals(String.valueOf(currentPrice))) {
                            alertMessageController.showAlertMensage("Você não pode alterar o preço do produto.");
                            return;
                        }

                        DAO.getEstoqueDAO().atualizarProduto(produto, newQuantity);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                alertMessageController.showAlertMensage("Tipo de produto e/ou nome do produto inválido(s).");
            }
        } catch (NumberFormatException | IOException e) {
            alertMessageController.showAlertMensage("Quantidade inválida: " + newQuantityStr);
        }
    }
}
