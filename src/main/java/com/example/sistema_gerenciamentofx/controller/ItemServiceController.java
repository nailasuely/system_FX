package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class ItemServiceController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label priceLabel;

    @FXML
    private Spinner<Integer> qtdRequested;

    @FXML
    private CheckBox selected;

    private NewOrderController newOrderController;


    @FXML
    void click(MouseEvent event) {

    }


    public void setInfos(String name, String price, String imageName, NewOrderController controller){
        nameLabel.setText(name);
        priceLabel.setText(price);
        Image image = new Image(getClass().getResourceAsStream(imageName));
        img.setImage(image);
        this.newOrderController = controller;
        attSpinner();
    }

    public void addItens(ActionEvent event){
        if(nameLabel.getText().equals("RAM memory")){
            newOrderController.putItens(Produto.novaRam(),qtdRequested.getValue());
        } else if (nameLabel.getText().equals("Mother Board")) {
            newOrderController.putItens(Produto.novaPlacaMae(),qtdRequested.getValue());
        } else if(nameLabel.getText().equals("Fonte")){
            newOrderController.putItens(Produto.novaFonte(),qtdRequested.getValue());
        } else if (nameLabel.getText().equals("Graphic Board")) {
            newOrderController.putItens(Produto.novaPlacaDeVideo(),qtdRequested.getValue());
        } else if (nameLabel.getText().equals("HDD")) {
            newOrderController.putItens(Produto.novoHDSSD(),qtdRequested.getValue());
        } else if (nameLabel.getText().equals("SSD")) {
            newOrderController.putItens(Produto.novoHDSSD(),qtdRequested.getValue());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attSpinner();

    }

    public void attSpinner(){
        try {
            System.out.println(nameLabel.getText());
            ArrayList<Integer> values = new ArrayList<Integer>();
            values.add(0);
            values.add(0);
            values.add(0);
            values.add(0);
            for (Map.Entry<Produto, Integer> entry : DAO.getEstoqueDAO().getList().entrySet()) {
                Produto produtinho = entry.getKey();
                int quantidade = entry.getValue();
                if(produtinho.getNome().equals("ram")){
                    values.add(0,quantidade);
                } else if(produtinho.getNome().equals("placa mae")){
                    values.add(1,quantidade);
                } else if (produtinho.getNome().equals("fonte")) {
                    values.add(2,quantidade);
                } else if (produtinho.getNome().equals("placa de video")) {
                    values.add(3,quantidade);
                } else if (produtinho.getNome().equals("hd/ssd")) {
                    values.add(4,quantidade);
                }
            }
            if(nameLabel.getText().equals("RAM memory")){
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(0));
                this.qtdRequested.setValueFactory(valueFactory);
            } else if (nameLabel.getText().equals("Mother Board")) {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(1));
                this.qtdRequested.setValueFactory(valueFactory);
            } else if(nameLabel.getText().equals("Fonte")){
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(2));
                this.qtdRequested.setValueFactory(valueFactory);
            } else if (nameLabel.getText().equals("Graphic Board")) {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(3));
                this.qtdRequested.setValueFactory(valueFactory);
            } else if (nameLabel.getText().equals("HDD")) {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(4));
                this.qtdRequested.setValueFactory(valueFactory);
            } else if (nameLabel.getText().equals("SSD")) {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, values.get(4));
                this.qtdRequested.setValueFactory(valueFactory);
            } else{
                System.out.println("ahg");
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);
                this.qtdRequested.setValueFactory(valueFactory);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}