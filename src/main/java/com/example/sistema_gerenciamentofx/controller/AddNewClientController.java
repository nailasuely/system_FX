package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewClientController {

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
    @FXML
    void login(ActionEvent event) throws Exception {
        String addressText = address.getText();
        String cpfText = cpf.getText();
        String fullnameText = fullname1.getText();
        String telephoneText = telephone.getText();

        if (addressText.isEmpty() || cpfText.isEmpty() || fullnameText.isEmpty() || telephoneText.isEmpty()) {
            System.out.println("Algum campo não foi digitado.");
        } else {
            Cliente cliente = new Cliente(fullnameText, addressText, cpfText,  Integer.parseInt(telephoneText));
            DAO.getClienteDAO().create(cliente);

            if (clientsController != null) {
                clientsController.clearViewPane();
                clientsController.updateClientList();
                clientsController.showViewPane();

            }
        }

    }
}