package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnManageClients;

    @FXML
    private Button btnManageOrders;

    @FXML
    private Button btnManageReports;

    @FXML
    private Button btnManageTec;

    @FXML
    private Button btnManagerStock;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnSignout;

    @FXML
    private Label techinicianName;

    @FXML
    private VBox pnItems;

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void showHomeStage(ActionEvent event) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/home-view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            Scene scene = new Scene(root);
            loginStage.setResizable(false);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    @FXML
    void showLoginStage(ActionEvent event) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/login-view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            Scene scene = new Scene(root);
            loginStage.setResizable(false);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }
    private ObservableList<Cliente> clientsData;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
