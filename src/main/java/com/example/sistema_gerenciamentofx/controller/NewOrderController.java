package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.ProdutoErradoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class NewOrderController implements Initializable {



    @FXML
    private GridPane grid;
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

    private ObservableMap<Produto, Integer> itensData;


    private String[] imageNames = {
            "/com/example/sistema_gerenciamentofx/images/ram2.png",
            "/com/example/sistema_gerenciamentofx/images/placamae.png",
            "/com/example/sistema_gerenciamentofx/images/fonte.png",
            "/com/example/sistema_gerenciamentofx/images/placavideo.png",
            "/com/example/sistema_gerenciamentofx/images/hd.png",
            "/com/example/sistema_gerenciamentofx/images/ssd.png"
    };
    private String[] names = {
            "RAM memory",
            "Mother Board",
            "Fonte",
            "Graphic Board",
            "HDD",
            "SSD"
    };

    private String[] names2 = {
            "ram",
            "placa mae",
            "fonte",
            "placa de video",
            "hd/ssd",
            "hd/ssd"
    };


    private double[] prices = new double[6];

    private Integer[] qntd = new Integer[6];

    private HashMap<Produto, Integer> produtoLists = new HashMap<>();

    public void putItens(Produto produto, Integer quant){
        produtoLists.put(produto, quant);
    }

    public void fillPrices() throws Exception {
        Map<Produto, Integer> estoque = DAO.getEstoqueDAO().getList();
        for (int i = 0; i < names2.length; i++) {
            boolean itemFound = false;
            for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                if (produto.getNome().equalsIgnoreCase(names2[i])) {
                    prices[i] = produto.getPreco();
                    qntd[i] = quantidade;
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                prices[i] = 0.0;
                qntd[i] = 0;
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTypeService.getItems().addAll(typeService);
        updateListClients();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 6; ++i) {
                itensData = FXCollections.observableMap(DAO.getEstoqueDAO().getList());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/example/sistema_gerenciamentofx/item-service-view.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                ItemServiceController itemServiceController = (ItemServiceController) fxmlLoader.getController();
                itemServiceController.setInfos(names[i % imageNames.length] , String.valueOf(prices[i]), imageNames[i % imageNames.length], this);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                this.grid.add(anchorPane, column++, row);
                this.grid.setMinWidth(-1.0);
                this.grid.setPrefWidth(-1.0);
                this.grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                this.grid.setMinHeight(-1.0);
                this.grid.setPrefHeight(-1.0);
                this.grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(setTypeService.getValue() == null){
                AlertMessageController alertMessageController = new AlertMessageController();
                try {
                    alertMessageController.showAlertMensage("Selecione um tipo de serviço antes!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else{
                if(setTypeService.getValue().equals("Montagem")){
                    ordemServico.setType(Produto.servicoMontagem());
                    for (Map.Entry<Produto, Integer> entry : produtoLists.entrySet()) {
                        Produto produto = entry.getKey();
                        int quantidade = entry.getValue();
                        try {
                            ordemServico.setListaProdutos(produto, quantidade);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (setTypeService.getValue().equals("Formatação")) {
                    ordemServico.setType(Produto.servicoFormatar());
                }else if(setTypeService.getValue().equals("Instalação")){
                    ordemServico.setType(Produto.servicoInstalar());
                } else if (setTypeService.getValue().equals("Limpeza")) {
                    ordemServico.setType(Produto.servicoLimpeza());
                }
            }
            try {
                ordemServico.setClientId(DAO.getClienteDAO().findIdbyCPF(getCpfClient()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                DAO.getOrdemServicoDAO().create(ordemServico);
                System.out.println(ordemServico);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            cpfSelected.setText("Nobody");
            setTypeService.setValue("");
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
    @FXML
    void searchClients(KeyEvent event) {
        clientsData.removeAll();
        updateListClients();
    }

}
