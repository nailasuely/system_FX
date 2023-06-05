package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerOrdersController implements Initializable {

    @FXML
    private Button actualOrder;

    @FXML
    private Button futureOrders;


    @FXML
    private Button newOrder;

    @FXML
    private Button acessBtn;

    @FXML
    private Button manageOrderActual;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlGeral;

    @FXML
    private Pane pnlManageActual;

    @FXML
    private Pane pnlCreateNewOrder;

    private static String technicianCPF;

    private HomeController homeController;

    public static String getTechnicianCPF() {
        return technicianCPF;
    }

    public void setTechnicianCPF(String technicianCPF) {
        this.technicianCPF = technicianCPF;
    }

    public Pane getPnlCreateNewOrder() {
        return pnlCreateNewOrder;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    void createNewOrder(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            if(DAO.getOrdemServicoDAO().openOrderByTechnician(HomeController.getCpfTecnico()) !=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/actual-order-view.fxml"));
                Pane pane2 = loader.load();
                ActualOrderController actualOrderController = loader.getController();
                actualOrderController.setManagerOrdersController(this);
                pnlGeral.getChildren().clear();
                pnlManageActual.getChildren().clear();
                pnlManageActual.getChildren().add(pane2);
                pnlManageActual.toFront();
            }
            else{
                pnlGeral.setStyle("-fx-background-color : #fffafa");
                pnlGeral.toFront();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/fucture-orders-view.fxml"));
                    Pane pane2 = loader.load();
                    pnlGeral.getChildren().clear();
                    pnlGeral.getChildren().add(pane2);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == newOrder){
            pnlCreateNewOrder.setStyle("-fx-background-color : #fffafa");
            pnlCreateNewOrder.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/new-order-service-view.fxml"));
                Pane pane2 = loader.load();
                NewOrderController newOrderController = loader.getController();
                newOrderController.setManagerOrdersController(this);
                newOrderController.setHomeController(homeController);

                pnlGeral.getChildren().clear();
                pnlCreateNewOrder.getChildren().clear();
                pnlCreateNewOrder.getChildren().add(pane2);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == actualOrder){
            pnlManageActual.setStyle("-fx-background-color : #fffafa");
            pnlManageActual.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/actual-order-view.fxml"));
                Pane pane2 = loader.load();
                pnlGeral.getChildren().clear();
                pnlManageActual.getChildren().clear();
                pnlManageActual.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(event.getSource() == futureOrders){
            pnlGeral.setStyle("-fx-background-color : #fffafa");
            pnlGeral.toFront();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/fucture-orders-view.fxml"));
                Pane pane2 = loader.load();

                pnlGeral.getChildren().clear();
                pnlGeral.getChildren().add(pane2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
}
