package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ActualOrderController implements Initializable{

    @FXML
    private Label priceOrder;
    @FXML
    private HBox pnlItens;
    @FXML
    private Pane pnlInfosOrder;
    @FXML
    private Pane pnlActualService;
    @FXML
    private Label adressClient;

    @FXML
    private Button cancelOrder;

    @FXML
    private Pane clientData;

    @FXML
    private Label cpfClient;

    @FXML
    private Button deleteOrder;

    @FXML
    private Button finalizeOrder;

    @FXML
    private RadioButton fiveStar;

    @FXML
    private RadioButton fourStar;



    @FXML
    private ImageView imgClient;

    @FXML
    private ImageView imgStartDate;

    @FXML
    private ImageView imgTypeService;

    @FXML
    private Label nameClient;



    @FXML
    private RadioButton oneStar;

    @FXML
    private ChoiceBox<String> setPaymentMethod;

    @FXML
    private Label startDate;

    @FXML
    private Label telephoneClient;

    @FXML
    private RadioButton threeStar;

    @FXML
    private RadioButton twoStar;

    @FXML
    private Label typeService;

    @FXML
    private Button updateOrder;

    @FXML
    private Pane pnlCreateNewOrder;

    private OrdemServico order;

    private String paymentsType[] = {"Transferecia", "Pix", "Cartão", "Dinheiro"};

    private ManagerOrdersController managerOrdersController;

    private AlertMessageController alertMessageController;

    private ObservableMap<Produto, Integer> itensData;

    private String[] imageNames = {
            "/com/example/sistema_gerenciamentofx/images/ram2.png",
            "/com/example/sistema_gerenciamentofx/images/placamae.png",
            "/com/example/sistema_gerenciamentofx/images/fonte.png",
            "/com/example/sistema_gerenciamentofx/images/placavideo.png",
            "/com/example/sistema_gerenciamentofx/images/hd.png",
            "/com/example/sistema_gerenciamentofx/images/ssd.png"
    };

    public void setAlertMessageController() {
        this.alertMessageController = new AlertMessageController();
    }

    public void setManagerOrdersController(ManagerOrdersController managerOrdersController) {
        this.managerOrdersController = managerOrdersController;
    }

    public OrdemServico getOrder() {
        return order;
    }


    public void setOrder(){

        try {
            if(DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico()) == null){
                this.order = null;
            } else{
                this.order = DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico()).get(0);
                this.order.setStatus("andamento");
                DAO.getOrdemServicoDAO().update(this.order);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setInformations() {
        try {
            this.adressClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getAddress());
            this.cpfClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getCpf());
            this.telephoneClient.setText(Integer.toString(DAO.getClienteDAO().findById(order.getClientId()).getTelephone()));
            this.nameClient.setText(DAO.getClienteDAO().findById(order.getClientId()).getFullName());
            this.startDate.setText(String.valueOf(order.getStart()));
            if (order != null && order.getType() != null) {
                this.typeService.setText(order.getType().getNome());
            }
            if(order.getPaymentType()!=null){
                setPaymentMethod.setValue(order.getPaymentType());
            }
            this.priceOrder.setText("R$"+String.valueOf(order.getPrice()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer setClientRate(){
        Integer rate=0;
        if(oneStar.isSelected()){
            rate=1;
        } else if (twoStar.isSelected()) {
            rate=2;
        } else if (threeStar.isSelected()) {
            rate=3;
        } else if (fourStar.isSelected()) {
            rate=4;
        } else if (fiveStar.isSelected()) {
            rate=5;
        }
        return rate;
    }

    @FXML
    void handleClicks(ActionEvent event) {
        AlertMessageController alertMessageController = new AlertMessageController();
        try {
            if(event.getSource()==cancelOrder){

            }
            if(event.getSource()==updateOrder){

                if(setPaymentMethod.getValue()!=null && setClientRate()==0){
                    this.order.setPaymentType(setPaymentMethod.getValue().toLowerCase());
                    DAO.getOrdemServicoDAO().update(this.order);
                } else if (setPaymentMethod.getValue()==null) {
                    alertMessageController.showAlertMensage("Metodo de pagamento não selecionado para poder atualizar");
                } else if (setClientRate()!=0) {
                    alertMessageController.showAlertMensage("Avaliação do cliente só está disponível ao finalizar a ordem");
                }
            }
            if(event.getSource()==deleteOrder){
                DAO.getOrdemServicoDAO().delete(this.order.getId());
            }
            if(event.getSource()==finalizeOrder){


                if(setPaymentMethod.getValue()!=null && (setClientRate()!=0)){

                    DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico()).get(0).finalize(setClientRate(),setPaymentMethod.getValue().toLowerCase());
                    setOrder();
                    if(this.order != null){
                        setInformations();
                        itensData.clear();
                        itensData = FXCollections.observableMap(DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico()).get(0).getProdutoLists());
                        insertNodes();
                        fiveStar.disarm();
                        fourStar.disarm();
                        threeStar.disarm();
                        twoStar.disarm();
                        oneStar.disarm();

                    } else {
                        managerOrdersController.seeFutureOrders();
                    }
                    setPaymentMethod.setValue("");
                } else{


                    alertMessageController.showAlertMensage("Forma de pagamento não selecionada");
                }



            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrder();

        clientData.setStyle("-fx-background-color : #FFF9F9");
        if(order == null){
            AlertMessageController alertMessageController = new AlertMessageController();
            try {
                alertMessageController.showAlertMensage("Não foi encontrada ordem em aberto");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            setInformations();
            try {
                if(DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico())!= null){
                    itensData = FXCollections.observableMap(DAO.getOrdemServicoDAO().ordersByTechnician(HomeController.getCpfTecnico()).get(0).getProdutoLists());
                    insertNodes();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        setPaymentMethod.getItems().addAll(paymentsType);



    }

    public void insertNodes(){
        ArrayList<Produto> itens = new ArrayList<>();
        itens.addAll(itensData.keySet());
        // ISSO AQUI É APENAS PARA FINS DE TESTE
        Node[] nodes = new Node[itensData.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                int indexItem =0;
                final int j = i;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/item-actual-service-view.fxml"));
                nodes[i] = loader.load();
                ItemActualServiceController itemActualServiceController = loader.getController();
                if(itens.get(i).getNome().equals("ram")){
                    indexItem = 0;
                } else if (itens.get(i).getNome().equals("placa mae")) {
                    indexItem = 1;
                } else if (itens.get(i).getNome().equals("fonte")) {
                    indexItem = 2;
                } else if (itens.get(i).getNome().equals("placa de video")) {
                    indexItem = 3;
                } else if (itens.get(i).getNome().equals("hd/ssd")) {
                    indexItem = 4;
                }
                itemActualServiceController.setInfos(Integer.toString(itensData.get(itens.get(i))), itens.get(i).getNome(), imageNames[indexItem]);
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fffafa");
                });
                pnlItens.getChildren().add(nodes[i]);
                pnlInfosOrder.toFront();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


