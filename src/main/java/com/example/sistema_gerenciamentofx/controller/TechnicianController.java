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
    private Label ordersInProgress;

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

    private HomeController homeController;

    public void setHomeController(HomeController controller){
        this.homeController = controller;
    }

    public void clearViewPane() {
        pnItems.getChildren().clear();
    }

    public void setInformationsBase(String technicianCpf, String technicianFullName, String telephone, String address) throws Exception {
        this.fullname1.setText(technicianFullName);
        this.cpf.setText(technicianCpf);
        this.telephone.setText(telephone);
        this.address.setText(address);
        this.technicianName.setText(technicianFullName);
        this.cpfTecnico.setText(cpf.getText());
        try {
            this.ordersInProgress.setText(String.valueOf(DAO.getOrdemServicoDAO().getQuantidadeOrdensEmAndamento(cpfTecnico.getText())));
            this.completedOrders.setText(String.valueOf(DAO.getOrdemServicoDAO().getQuantidadeOrdensConcluidas(cpfTecnico.getText())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //this.ordersInProgress.setText(String.valueOf(DAO.getOrdemServicoDAO().getQuantidadeOrdensEmAndamento(technicianCpf)));

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
                technicianElementController.setHomeController(this.homeController);
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
        String cpfAntigo = cpfTecnico.getText();
        //System.out.println(cpf.getText());
        try {
            tecnico = DAO.getTecnicoDAO().findByCPF(cpfTecnico.getText());
            //System.out.println(tecnico.getCpf());
            String cpfText =cpf.getText();
            String adressText = address.getText();
            String fullNameText = fullname1.getText();
            String telephoneText = telephone.getText();
            if(!cpfText.isEmpty() && tecnico != null){
                tecnico.setCpf(cpfText);
                //DAO.getTecnicoDAO().update(tecnico);
            }
            if (!adressText.isEmpty() && tecnico != null) {
                tecnico.setAddress(this.address.getText());
            }
            if (!fullNameText.isEmpty() && tecnico != null) {
                tecnico.setFullName(this.fullname1.getText());
            }
            if (!telephoneText.isEmpty() && tecnico != null) {
                tecnico.setTelephone(Integer.parseInt(this.telephone.getText()));
            }
            DAO.getTecnicoDAO().update(tecnico);
            clearViewPane();
            updateTechnicianList();
            pnlView.toFront();
            technicianName.setText(fullname1.getText());
            cpfTecnico.setText(cpf.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
