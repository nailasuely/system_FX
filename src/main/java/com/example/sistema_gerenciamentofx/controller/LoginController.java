package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

    private ObservableList<Tecnico> tecnicoData;
    @FXML
    void initialize() throws Exception {
        this.tecnicoData = FXCollections.observableArrayList();
        this.tecnicoData.addAll(DAO.getTecnicoDAO().getList());

    }

    //passaword eh o cpf
    @FXML
    void login(ActionEvent event) throws Exception {
        String passawordText = password1.getText();
        Tecnico tecnico1 = new Tecnico("Italo Sobrenome", "Coité, Bahia",
                "196.814.670-94", 75);
        DAO.getTecnicoDAO().create(tecnico1);
        if(!passawordText.isEmpty() && DAO.getTecnicoDAO().findByCPFIsTrue(password1.getText())){
            System.out.println("pode passar para prox tela");
        }
        else{
        System.out.println("cpf n existe no sistema ou cpf vazio");}


    }

    @FXML
    void showRegisterStage(MouseEvent event) {

    }

}
