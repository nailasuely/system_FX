package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FuctureOrdersController implements Initializable{

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlGeral;

    private ObservableList<OrdemServico> ordensData;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordensData = FXCollections.observableArrayList();
        try {
            for (OrdemServico ordem: DAO.getOrdemServicoDAO().getList()) {
                if (ordem.getTechnicianID() != null){
                if(ordem.getStatus().equals("espera") && ordem.getTechnicianID().equals(DAO.getTecnicoDAO().findIdbyCPF(HomeController.getCpfTecnico()))) {
                    ordensData.add(ordem);
                }}
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {

            Node[] nodes = new Node[ordensData.size()];
            for (int i = 0; i < nodes.length; i++) {

                    final int j = i;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/future-orders-element-view.fxml"));
                    nodes[i] = loader.load();
                    OrdemServico ordem = ordensData.get(i);
                    FutureOrdersElementController futureOrdersElementController = loader.getController();
                    futureOrdersElementController.setInfos(DAO.getClienteDAO().findById(ordem.getClientId()).getFullName(), DAO.getClienteDAO().findById(ordem.getClientId()).getCpf(),ordem.getStart().toString(), ordem.getType().getNome());
                    nodes[i].setOnMouseEntered(event -> {
                        nodes[j].setStyle("-fx-background-color : #0A0E3F");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[j].setStyle("-fx-background-color : #fffafa");
                    });
                    pnItems.getChildren().add(nodes[i]);
                    pnlGeral.toFront();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
