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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label ordersWaiting;

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
    private Label ordersProgress;

    @FXML
    private Label ordersTotal;

    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlGenerateReport;

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

    @FXML
    private Button searchBtn;

    private String relatorio;

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

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
    @FXML
    void simulateButtonClick() throws Exception {
        ActionEvent event = new ActionEvent(btnOverview, null);
        handleClicks(event);
    }


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
            this.ordersTotal.setText(Integer.toString(DAO.getOrdemServicoDAO().amountItems()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // ISSO AQUI É APENAS PARA FINS DE TESTE
        updateListOrders(false);
        //loadData();
    }


    @FXML
    void handleClicks(ActionEvent event) throws Exception {
            if (event.getSource() == btnManagerStock) {
                pnlManageStock.setStyle("-fx-background-color : #fffafa");
                pnlManageStock.toFront();
                try {
                    //Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //currentScreen.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/manage-stock2-view.fxml"));
                    Pane pane2 = loader.load();{
                        pnlManageStock.getChildren().clear();
                        pnlManageStock.getChildren().add(pane2);
                        //clientsController = loader.getController();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (event.getSource() == btnManageOrders) {
                pnlManageOrders.setStyle("-fx-background-color : #fffafa");
                pnlManageOrders.toFront();
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
                updateListOrders(false);
                // teste
                loadData();
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
        if (event.getSource() == btnManageReports) {
            pnlGenerateReport.setStyle("-fx-background-color : #fffafa");
            pnlGenerateReport.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/generate-report-view.fxml"));
                Pane pane1 = loader.load();{
                    pnlGenerateReport.getChildren().clear();
                    pnlGenerateReport.getChildren().add(pane1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }}

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
            loginStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/sistema_gerenciamentofx/images/1.png")));

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

    public void updateListOrders(boolean validator){
        if(this.ordersData != null){
            this.ordersData.removeAll();
        }

        this.ordersData = FXCollections.observableArrayList();
        try {
            this.ordersData.addAll(DAO.getOrdemServicoDAO().getListOpening());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        pnItems.getChildren().clear();
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

                elementController.setIdOrder(DAO.getTecnicoDAO().findById(order.getTechnicianID()).getCpf());

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
                if(search_order.getText().isEmpty()){
                    pnItems.getChildren().add(nodes[i]);
                } else if (!search_order.getText().isEmpty() && DAO.getClienteDAO().findIdbyCPF(search_order.getText())!=null && ordersData.get(i).getClientId().equals(DAO.getClienteDAO().findIdbyCPF(search_order.getText()))) {
                    pnItems.getChildren().add(nodes[i]);
                }

                pnlOverview.toFront();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pnlOverview.toFront();
        if(pnItems.getChildren().size()==0 && validator){
            AlertMessageController alertMessageController = new AlertMessageController();
            try {
                alertMessageController.showAlertMensage("Nenhuma ordem em espera foi encontrada para esse CPF");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void loadData(){
        //String cpfTecnico =
       // System.out.println(cpfTecnico);
        String relatorio;
        try {
            relatorio = DAO.getTecnicoDAO().findByCPF(cpfTecnico).gerarRelatorioFinal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<String> valoresRelatorio = ReceberValoresRelatorio(relatorio);

        ordersConcluded.setText(valoresRelatorio.get(0));
        ordersProgress.setText(valoresRelatorio.get(1));
        ordersWaiting.setText(valoresRelatorio.get(2));
    }


    @FXML
    void searchClient(ActionEvent event) {
        updateListOrders(true);
    }

    @FXML
    void searchClients(KeyEvent event) {
        updateListOrders(false);
    }

    public List<String> ReceberValoresRelatorio(String relatorioString) {
        List<String> valoresRelatorio = new ArrayList<>();

        String[] linhas = relatorioString.split("\n");
        for (String linha : linhas) {
            String[] partes = linha.split(":");
            if (partes.length == 2) {
                String valor = partes[1].trim();
                valoresRelatorio.add(valor);
            }
        }
        for (String valor : valoresRelatorio) {
            System.out.println(valor);
        }
        return valoresRelatorio;
    }


}