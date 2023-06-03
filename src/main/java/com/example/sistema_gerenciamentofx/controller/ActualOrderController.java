package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActualOrderController implements Initializable {

    @FXML
    private Label adressClient;

    @FXML
    private Button cancelOrder;

    @FXML
    private Label cpfClient;

    @FXML
    private Button deleteClient1;

    @FXML
    private Button finalizeOrder;

    @FXML
    private RadioButton fiveStar;

    @FXML
    private RadioButton fourStar;

    @FXML
    private ImageView imgClient;

    @FXML
    private ImageView imgStartDate;

    @FXML
    private ImageView imgTypeService;

    @FXML
    private Label nameClient;

    @FXML
    private RadioButton oneStar;

    @FXML
    private ChoiceBox<?> setPaymentMethod;

    @FXML
    private Label startDate;

    @FXML
    private Label telephoneClient;

    @FXML
    private RadioButton threeStar;

    @FXML
    private RadioButton twoStar;

    @FXML
    private Label typeService;

    @FXML
    private Button updateOrder;

    private OrdemServico order;

    public OrdemServico getOrder() {
        return order;
    }


    public void setOrder(){
        try {
            this.order = DAO.getOrdemServicoDAO().openOrderByTechnician(ManagerOrders.getTechnicianCPF());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setInformations(){
        try {
            this.adressClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getAddress());
            this.cpfClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getCpf());
            this.telephoneClient.setText(Integer.toString(DAO.getClienteDAO().findById(order.getClientId()).getTelephone()));
            this.nameClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getFullName());
            this.startDate.setText(String.valueOf(order.getStart()));
            this.typeService.setText(order.getType().getNome());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrder();
        if(order == null){
            AlertMessageController alertMessageController = new AlertMessageController();
            try {
                alertMessageController.showAlertMensage("asgfegw");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            setInformations();
        }

    }
}
