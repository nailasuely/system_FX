package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class NewOrderController implements Initializable {



    @FXML
    private Label cpfSelected;

    @FXML
    private VBox ClientsList;

    @FXML
    private Button clearData;

    @FXML
    private Button createOrder;

    @FXML
    private Button loginButton11;

    @FXML
    private VBox pnItems;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchClient;

    @FXML
    private Pane pnlNewOrder;

    @FXML
    private ChoiceBox<String> setTypeService;

    private String typeService[] = {"Montagem", "Formatação", "Instalação", "Limpeza"};

    private ObservableList<Cliente> clientsData;

    private static String cpfClient;

    private ManagerOrdersController managerOrdersController;
    private HomeController homeController;

    public void setManagerOrdersController(ManagerOrdersController managerOrdersController) {
        this.managerOrdersController = managerOrdersController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTypeService.getItems().addAll(typeService);
        updateListClients();
    }

    public void updateListClients(){
        clientsData = FXCollections.observableArrayList();
        try {
            clientsData.addAll(DAO.getClienteDAO().getList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[clientsData.size()];
        ClientsList.getChildren().clear();
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                //nodes[i] = FXMLLoader.load(getClass().getResource("/com/example/sistema_gerenciamentofx/element-view.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/client-element-new-order-view.fxml"));
                nodes[i] = loader.load();
                ClientElementNewOrderController clientElementNewOrderController = loader.getController();
                clientElementNewOrderController.setClientInfos(clientsData.get(i).getFullName(), clientsData.get(i).getCpf());
                clientElementNewOrderController.setNewOrderController(this);

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #7c57d1");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fffafa");
                });
                if(searchClient.getText().isEmpty()){
                    System.out.println("211"+searchClient.getText());
                    ClientsList.getChildren().add(nodes[i]);
                } else if (!searchClient.getText().isEmpty() && clientsData.get(i).getCpf().equals(searchClient.getText())) {
                    System.out.println("56" +searchClient.getText());
                    ClientsList.getChildren().add(nodes[i]);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static void setCpfClient(String cpf) {
        cpfClient = cpf;

    }

    public String getCpfClient() {
        return cpfClient;
    }


    @FXML
    public void setCpfSelected(String selected) {
        System.out.println(selected);

        cpfSelected.setText(selected);
    }

    @FXML
    void handleClicks(ActionEvent event) {
        if(event.getSource() == createOrder){
            OrdemServico ordemServico = new OrdemServico();
            try {
                ordemServico.setTechnicianID(DAO.getTecnicoDAO().findIdbyCPF(HomeController.getCpfTecnico()));
                if(setTypeService.getValue() == null){
                    AlertMessageController alertMessageController = new AlertMessageController();
                    alertMessageController.showAlertMensage("Selecione um tipo de serviço antes!");
                } else{
                    if(setTypeService.getValue().equals("Montagem")){
                        ordemServico.setType(Produto.servicoMontagem());
                    } else if (setTypeService.getValue().equals("Formatação")) {
                        ordemServico.setType(Produto.servicoFormatar());
                    }else if(setTypeService.getValue().equals("Instalação")){
                        ordemServico.setType(Produto.servicoInstalar());
                    } else if (setTypeService.getValue().equals("Limpeza")) {
                        ordemServico.setType(Produto.servicoLimpeza());
                    }
                }
                ordemServico.setClientId(DAO.getClienteDAO().findIdbyCPF(getCpfClient()));
                DAO.getOrdemServicoDAO().create(ordemServico);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(event.getSource()==clearData){
            cpfSelected.setText("Nobody");
            setTypeService.setValue("");
        }

    }

    @FXML
    void newClientCreate(ActionEvent event) {
        homeController.showClientsStage(event);



    }

    @FXML
    void searchClient(ActionEvent event) {
        clientsData.removeAll();
        updateListClients();
    }


}
