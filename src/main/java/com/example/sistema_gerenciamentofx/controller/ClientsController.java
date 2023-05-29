package com.example.sistema_gerenciamentofx.controller;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private Button bttAdd;

    @FXML
    private Button bttView;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlAddClient;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlView;
    @FXML
    private Pane pnlUpdate;

    private AddNewClientController addNewClientController;

    public void setAddNewClientController(AddNewClientController addNewClientController1) {
        this.addNewClientController= addNewClientController1;
    }
    public void showViewPane() {
        pnlView.toFront();

    }

    public void clearViewPane() {
        pnItems.getChildren().clear();
    }

    public void showUpdatePane() {
        pnlUpdate.toFront();

    }

    public Pane getPnlUpdate() {
        return pnlUpdate;
    }

    @FXML
    void login(ActionEvent event) {
        if (event.getSource() == bttAdd) {
            pnlAddClient.setStyle("-fx-background-color : #fffafa");
            pnlAddClient.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/add-new-client-view.fxml"));
                Pane pane2 = loader.load();

                AddNewClientController addNewClientController = loader.getController();
                addNewClientController.setClientsController(this);

                pnlAddClient.getChildren().clear();
                pnlAddClient.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (event.getSource() == bttView) {
            pnlView.toFront();
        }
    }

    private ObservableList<Cliente> clientsData;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateClientList();
        pnlView.toFront();
    }
    public void updateClientList() {
        try {
            this.clientsData = FXCollections.observableArrayList();
            this.clientsData.addAll(DAO.getClienteDAO().getList());
            //this.clientsData.setText(Integer.toString(DAO.getOrdemServicoDAO().amountItems()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[clientsData.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                //nodes[i] = FXMLLoader.load(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/client-element-view.fxml"));
                nodes[i] = loader.load();
                ClientElementController clientElementController = loader.getController();
                clientElementController.setNameClient(clientsData.get(i).getFullName());
                clientElementController.setCpfClient(clientsData.get(i).getCpf());
                clientElementController.setTelephoneClient(Integer.toString(clientsData.get(i).getTelephone()));
                clientElementController.setAdressClient(clientsData.get(i).getAddress());
                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fffafa");
                });
                pnItems.getChildren().add(nodes[i]);
                clientElementController.setClientsController(this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        }



       /* pnItems.getChildren().clear();

        for (Cliente client : clientsData) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/client-element-view.fxml"));
                Node node = loader.load();
                ClientElementController clientElementController = loader.getController();
                clientElementController.setNameClient(client.getFullName());
                clientElementController.setCpfClient(client.getCpf());
                clientElementController.setTelephoneClient(Integer.toString(client.getTelephone()));
                clientElementController.setAdressClient(client.getAddress());

                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #0A0E3F");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #fffafa");
                });
                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/



}}

