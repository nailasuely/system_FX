package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/register-view.fxml"));
            Parent root = loader.load();
            Stage registerStage = new Stage();
            Scene scene = new Scene(root);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception excep) {
            excep.printStackTrace();
        }


    }

}
