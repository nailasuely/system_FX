package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GenerateReportsController implements Initializable {

    @FXML
    private Label averageDays;

    @FXML
    private Label completedServices;

    @FXML
    private Label customerSatisfaction;

    @FXML
    private Label invoicing;

    @FXML
    private Label ordersInProgress;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label waitingOrders;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String cpfTecnico = HomeController.getCpfTecnico();
        String relatorio;
        try {
            relatorio = DAO.getTecnicoDAO().findByCPF(cpfTecnico).gerarRelatorioFinal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<String> valoresRelatorio = ReceberValoresRelatorio(relatorio);

        completedServices.setText(valoresRelatorio.get(0));
        ordersInProgress.setText(valoresRelatorio.get(1));
        waitingOrders.setText(valoresRelatorio.get(2));
        invoicing.setText(valoresRelatorio.get(3));
        averageDays.setText(valoresRelatorio.get(4));
        customerSatisfaction.setText(valoresRelatorio.get(5).substring(0,4));



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
