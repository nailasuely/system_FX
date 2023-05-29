package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ClientElementController {

    @FXML
    private Label adressClient;

    @FXML
    private Label cpfClient;

    @FXML
    private Button deleteClient;

    @FXML
    private HBox itemC;

    @FXML
    private Label nameClient;

    @FXML
    private Label telephoneClient;

    @FXML
    private Button updateClient;
    private ClientsController clientsController;

    public void setClientsController(ClientsController clientsController) {
        this.clientsController = clientsController;
    }
    public void setAdressClient(String adress) {
        this.adressClient.setText(adress);
    }

    public void setCpfClient(String cpf) {
        this.cpfClient.setText(cpf);
    }

    public Label getCpfClient() {
        return cpfClient;
    }

    public void setNameClient(String name) {
        this.nameClient.setText(name);
    }

    public void setTelephoneClient(String telephone) {
        this.telephoneClient.setText(telephone);
    }

    Cliente cliente;
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    void handleClicks(ActionEvent event) throws Exception {
        if (event.getSource() == deleteClient) {
            System.out.println(getCpfClient().getText());
            DAO.getClienteDAO().delete(getCpfClient().getText());
            clientsController.clearViewPane();
            clientsController.updateClientList();
        }
        if (event.getSource() == updateClient) {
            System.out.println("opa");
            clientsController.getPnlUpdate().setStyle("-fx-background-color : #fffafa");
            clientsController.getPnlUpdate().toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/update-client-view.fxml"));
                Pane pane2 = loader.load();
                clientsController.getPnlUpdate().getChildren().clear();
                clientsController.getPnlUpdate().getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

}


