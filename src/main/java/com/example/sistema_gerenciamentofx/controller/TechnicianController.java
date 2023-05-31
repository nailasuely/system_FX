package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TechnicianController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private Label completedOrders;

    @FXML
    private TextField cpf;

    @FXML
    private TextField fullname1;

    @FXML
    private Button loginButton1;

    @FXML
    private Label openOrders;

    @FXML
    private Label ordersTotal;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlView;
    @FXML
    private TextField telephone;
    @FXML
    private Label technicianName;
    @FXML
    private Label cpfTecnico;

    public void setInformationsBase(String technicianCpf, String technicianFullName, String telephone, String address){
        this.fullname1.setText(technicianFullName);
        this.cpf.setText(technicianCpf);
        this.telephone.setText(telephone);
        this.address.setText(address);
        this.technicianName.setText(technicianFullName);
        this.cpfTecnico.setText(technicianFullName);


    }
    private ObservableList<Tecnico> technicianData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTechnicianList();
        pnlView.toFront();
    }
    public void showViewPane() {
        pnlView.toFront();

    }

    public void updateTechnicianList(){
        try {
            this.technicianData = FXCollections.observableArrayList();
            this.technicianData.addAll(DAO.getTecnicoDAO().getList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[technicianData.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                //nodes[i] = FXMLLoader.load(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/technician-element-view.fxml"));
                nodes[i] = loader.load();
                TechnicianElementController technicianElementController = loader.getController();
                technicianElementController.setTechinicianName(technicianData.get(i).getFullName());
                technicianElementController.setTechinicianCPF(technicianData.get(i).getCpf());
                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #7c57d1");
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
    @FXML
    void updateData(ActionEvent event) {
        Tecnico tecnico = new Tecnico();
        try {
            tecnico = DAO.getTecnicoDAO().findByCPF(this.cpf.getText());
            String cpfText =cpf.getText();
            String adressText = address.getText();
            String fullNameText = fullname1.getText();
            String telephoneText = telephone.getText();
            if(!cpfText.isEmpty()){
                tecnico.setCpf(cpfText);
            }
            if (!adressText.isEmpty()) {
                tecnico.setAddress(this.address.getText());
            }
            if (!fullNameText.isEmpty()) {
                tecnico.setFullName(this.fullname1.getText());
            }
            if (!telephoneText.isEmpty()) {
                tecnico.setTelephone(Integer.parseInt(this.telephone.getText()));
            }
            DAO.getTecnicoDAO().update(tecnico);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
