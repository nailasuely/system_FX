package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateClientController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField cpf;

    @FXML
    private TextField fullname1;

    @FXML
    private Button loginButton1;

    @FXML
    private TextField telephone;
    private ClientsController clientsController;

    public void setClientsController(ClientsController clientsController) {
        this.clientsController = clientsController;
    }

    public void setInfos(String currentAddress, String currentCpf, String currentFullName, String CurrentTelephone) {
        this.address.setText(currentAddress);
        this.cpf.setText(currentCpf);
        this.fullname1.setText(currentFullName);
        this.telephone.setText(CurrentTelephone);
    }
    @FXML
    void updateClient(ActionEvent event) {
        try {
            String nameText = fullname1.getText();
            String adressText = address.getText();
            String cpfText = cpf.getText();
            String telephoneText = telephone.getText();
            Cliente cliente = DAO.getClienteDAO().findByCPF(cpfText);
            cliente.setAddress(adressText);
            cliente.setTelephone(Integer.parseInt(telephoneText));
            cliente.setCpf(cpfText);
            cliente.setFullName(nameText);
            DAO.getClienteDAO().update(cliente);
            clientsController.getPnlUpdate().getChildren().clear();
            clientsController.clearViewPane();
            clientsController.updateClientList();
            clientsController.showViewPane();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setFullname1("fala vida");
    }
}
