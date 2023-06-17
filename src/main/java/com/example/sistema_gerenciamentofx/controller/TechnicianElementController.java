package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TechnicianElementController {
    @FXML
    private Button deleteClient;

    @FXML
    private HBox itemC;

    @FXML
    private Label techinicianCPF;

    @FXML
    private Label techinicianName;

    private HomeController homeController;

    public void setHomeController(HomeController controller){
        this.homeController = controller;
    }

    @FXML
    void techinicianDelete(ActionEvent event) throws IOException {
        String cpfText = techinicianCPF.getText();
        AlertMessageController alertMessageController = new AlertMessageController();

        if (cpfText.equals(HomeController.getCpfTecnico())) {
            alertMessageController.showAlertMensage("Você não pode se deletar do sistema.");
            return;
        }

        try {
            List<OrdemServico> ordens = DAO.getOrdemServicoDAO().ordersByTechnician(cpfText);
            List<Tecnico> tecnicos = DAO.getTecnicoDAO().getList();
            Integer quantTechinician = DAO.getTecnicoDAO().getList().size();
            Integer quantOrdersPorTechnician = 0;

            if (quantTechinician == 1 && ordens != null && ordens.size() > 0) {
                alertMessageController.showAlertMensage("Você ainda tem ordens ativas, por favor, complete-as antes de realizar a operação");
            } else if (quantTechinician == 2) {
                if (ordens != null) {
                    for (OrdemServico ordem : ordens) {
                        ordem.setTechnicianID(DAO.getTecnicoDAO().findIdbyCPF(HomeController.getCpfTecnico()));
                        DAO.getOrdemServicoDAO().update(ordem);
                    }
                }

                DAO.getTecnicoDAO().delete(cpfText);
            } else if (quantTechinician == 1 && ordens == null) {
                alertMessageController.showAlertMensage("Foi um prazer ter você na nossa equipe!");
                DAO.getTecnicoDAO().delete(cpfText);
                homeController.showLoginStage(event);
            } else {
                if (ordens != null) {
                    quantOrdersPorTechnician = ordens.size() / (quantTechinician - 1);
                }

                for (Tecnico technician : tecnicos) {
                    if (!technician.getCpf().equals(cpfText)) {
                        for (int i = 0; i < quantOrdersPorTechnician; i++) {
                            ordens.get(i).setTechnicianID(technician.getId());
                            DAO.getOrdemServicoDAO().update(ordens.get(i));
                            ordens.remove(i);
                            i--; // Corrigir o índice após a remoção
                        }
                    }
                }
                if (ordens != null) {
                    if (ordens.size() > 0) {
                        for (int i = 0; i < ordens.size(); i++) {
                            ordens.get(i).setTechnicianID(DAO.getTecnicoDAO().findIdbyCPF(HomeController.getCpfTecnico()));
                            DAO.getOrdemServicoDAO().update(ordens.get(i));
                        }
                    }
                }

                DAO.getTecnicoDAO().delete(cpfText);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setTechinicianCPF(String cpf) {
        this.techinicianCPF.setText(cpf);
    }

    public void setTechinicianName(String name) {
        this.techinicianName.setText(name);
    }
}
