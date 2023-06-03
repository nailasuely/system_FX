package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ManagerOrders {

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
    private static String technicianCPF;

    public static String getTechnicianCPF() {
        return technicianCPF;
    }

    public void setTechnicianCPF(String technicianCPF) {
        this.technicianCPF = technicianCPF;
    }

    @FXML
    void createNewOrder(ActionEvent event) {

    }

    @FXML
    void openMangeOrder(ActionEvent event) {
        pnlManageActual.setStyle("-fx-background-color : #fffafa");
        pnlManageActual.toFront();


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/actua-order.fxml"));
            Pane pane2 = loader.load();
            ActualOrderController actualOrderController = loader.getController();

            pnlGeral.getChildren().clear();
            pnlManageActual.getChildren().clear();
            pnlManageActual.getChildren().add(pane2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
