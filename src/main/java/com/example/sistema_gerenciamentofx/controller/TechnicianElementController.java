package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TechnicianElementController {
    @FXML
    private Button deleteClient;

    @FXML
    private HBox itemC;

    @FXML
    private Label techinicianCPF;

    @FXML
    private Label techinicianName;

    @FXML
    void techinicianDelete(ActionEvent event) {
        String cpfText = techinicianCPF.getText();
        try {
            DAO.getTecnicoDAO().delete(DAO.getTecnicoDAO().findIdbyCPF(cpfText));
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
