package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewClientController implements Initializable {

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

    private Pane pnl;

    public void setPnl(Pane pnlChildren){
        this.pnl = pnlChildren;
    }
    public void setClientsController(ClientsController clientsController) {
        this.clientsController = clientsController;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                pnl.getChildren().clear();
                clientsController.getPnlUpdate().getChildren().clear();
                clientsController.updateClientList();
                clientsController.showViewPane();

            }
        }

    }
}