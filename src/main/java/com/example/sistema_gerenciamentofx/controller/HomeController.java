package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label OrdersWaiting;

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
    private Label ordersConcluded;

    @FXML
    private Label ordersPeding;

    @FXML
    private Label ordersTotal;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlManageOrders;

    @FXML
    private Pane pnlManageStock;

    @FXML
    private Pane pnlManagerClients;

    @FXML
    private Pane pnlManageTec;
    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pane;

    @FXML
    private TextField search_order;

    @FXML
    private Label techinicianName;

    private TechnicianController technicianViewController;
    private ClientsController clientsController;
    private static String cpfTecnico;

    public static String getCpfTecnico(){
        return cpfTecnico;
    }


    public void setTechinicianCpf(String cpf) {
        this.cpfTecnico = cpf;
        try {
            this.techinicianName.setText(DAO.getTecnicoDAO().findByCPF(cpf).getFullName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<OrdemServico> ordersData;


    /*@Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            this.ordersData = FXCollections.observableArrayList();
            this.ordersData.addAll(DAO.getOrdemServicoDAO().getListOpening());
            this.ordersTotal.setText(Integer.toString(DAO.getOrdemServicoDAO().amountItems()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // ISSO AQUI É APENAS PARA FINS DE TESTE
        Node[] nodes = new Node[ordersData.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                //nodes[i] = FXMLLoader.load(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                nodes[i] = loader.load();
                ElementController elementController = loader.getController();
                elementController.setClientName(DAO.getClienteDAO().findById(ordersData.get(i).getClientId()).getFullName());
                elementController.setDateOrder(ordersData.get(i).getStart());
                elementController.setIdOrder(ordersData.get(i).getId());
                elementController.setStatusOrder(ordersData.get(i).getStatus());
                elementController.setValueOrder(Double.toString(ordersData.get(i).getPrice()));
                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fffafa");
                });
                pnItems.getChildren().add(nodes[i]);
                pnlOverview.toFront();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        }



    }*/
    void initialize2() throws Exception {
        DAO.getOrdemServicoDAO().deleteMany();
        DAO.getClienteDAO().deleteMany();
        Connect.generateCache();

        Cliente cliente1 = new Cliente("Latiele Sobrenome", "Rua A", "770.603.570-09", 72);

        Tecnico tecnico1 = new Tecnico("Mirela Sobrenome", "Coité, Bahia",
                "227.605.1650-92", 75);
        Cliente cliente2 = new Cliente("Lara Sobrenome", "Rua ABC, Bahia",
                "610.819.650-53", 81);
        Cliente cliente3 = new Cliente("Julia Sobrenome", "Rua ABC, Bahia",
                "257.705.020-88", 81);
        Cliente cliente4 = new Cliente("Yasmim Sobrenome", "Rua ABC, Bahia",
                "640.540.980-53", 81);
        DAO.getClienteDAO().create(cliente1);
        DAO.getClienteDAO().create(cliente2);
        DAO.getClienteDAO().create(cliente4);
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getClienteDAO().create(cliente3);
        DAO.getOrdemServicoDAO().deleteMany();
        OrdemServico ordem1 = new OrdemServico();
        OrdemServico ordem2 = new OrdemServico();
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().create(ordem2, cliente2.getId(), Produto.servicoFormatar());


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //initialize2();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            this.ordersData = FXCollections.observableArrayList();
            this.ordersData.addAll(DAO.getOrdemServicoDAO().getListOpening());
            this.ordersTotal.setText(Integer.toString(DAO.getOrdemServicoDAO().amountItems()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // ISSO AQUI É APENAS PARA FINS DE TESTE
        Node[] nodes = new Node[ordersData.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                nodes[i] = loader.load();
                ElementController elementController = loader.getController();
                OrdemServico order = ordersData.get(i);
                Cliente cliente = DAO.getClienteDAO().findById(order.getClientId());
                if (cliente != null) {
                        elementController.setClientName(DAO.getClienteDAO().findById(ordersData.get(i).getClientId()).getFullName());
                }
                else {
                        elementController.setClientName("Sem cliente até o momento.");
                    }
                if (order.getStart() != null) {
                    elementController.setDateOrder(order.getStart());
                }

                elementController.setIdOrder(order.getId());

                if (order.getStatus() != null) {
                    elementController.setStatusOrder(order.getStatus());
                }
                elementController.setValueOrder(Double.toString(order.getPrice()));
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fffafa");
                });
                pnItems.getChildren().add(nodes[i]);
                pnlOverview.toFront();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void handleClicks(ActionEvent event) {
            if (event.getSource() == btnManagerStock) {
                pnlManageStock.setStyle("-fx-background-color : #8228D1");
                pnlManageStock.toFront();
            }
            if (event.getSource() == btnManageOrders) {
                pnlManageOrders.setStyle("-fx-background-color : #fffafa");
                pnlManageOrders.toFront();
                System.out.println(cpfTecnico);
                try {
                    //Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //currentScreen.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/manage-orders-view.fxml"));
                    Pane pane2 = loader.load();
                    ManagerOrdersController managerOrdersController = loader.getController();
                    managerOrdersController.setHomeController(this);
                    pnlManageOrders.getChildren().clear();
                    pnlManageOrders.getChildren().add(pane2);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (event.getSource() == btnOverview) {
                pnlOverview.setStyle("-fx-background-color : #fffafa");
                pnlOverview.toFront();
            }
            if(event.getSource() == btnManageClients)
            {
                pnlManagerClients.setStyle("-fx-background-color : #fffafa");
                pnlManagerClients.toFront();
                try {
                    //Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //currentScreen.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/clients-view.fxml"));
                    Pane pane2 = loader.load();{
                        pnlManagerClients.getChildren().clear();
                        pnlManagerClients.getChildren().add(pane2);
                        //clientsController = loader.getController();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        if (event.getSource() == btnManageTec) {
            pnlManageTec.setStyle("-fx-background-color : #fffafa");
            pnlManageTec.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/technician-view.fxml"));
                Pane pane1 = loader.load();{
                    pnlManageTec.getChildren().clear();
                    pnlManageTec.getChildren().add(pane1);
                    //technicianViewController = loader.getController();
                    TechnicianController technicianController = loader.getController();
                    technicianController.setInformationsBase(this.cpfTecnico, this.techinicianName.getText(), Integer.toString(DAO.getTecnicoDAO().findByCPF(cpfTecnico).getTelephone()), DAO.getTecnicoDAO().findByCPF(cpfTecnico).getAddress());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    @FXML
    void showLoginStage(ActionEvent event){
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
    @FXML
    void showClientsStage(ActionEvent event){
        /*
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/clients-view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            Scene scene = new Scene(root);
            loginStage.setResizable(false);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception excep) {
            excep.printStackTrace();
        }
        */
        pnlManagerClients.setStyle("-fx-background-color : #fffafa");
        pnlManagerClients.toFront();
        try {
            //Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //currentScreen.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/clients-view.fxml"));
            Pane pane2 = loader.load();{
                pnlManagerClients.getChildren().clear();
                pnlManagerClients.getChildren().add(pane2);
                ClientsController clientsController = loader.getController();
                clientsController.showAddPane();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/" + view + "-view.fxml"));
            Parent root = loader.load();
            pane.getChildren().clear();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}