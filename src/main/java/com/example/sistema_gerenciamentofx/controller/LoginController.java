package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.HelloApplication;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.example.sistema_gerenciamentofx.controller.RegisterController;

import java.io.IOException;

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
        String passwordText = password1.getText();
        Tecnico tecnico1 = new Tecnico("Italo Sobrenome", "Coité, Bahia",
                "196.814.670-94", 75);
        DAO.getTecnicoDAO().create(tecnico1);
        if (!passwordText.isEmpty() && DAO.getTecnicoDAO().findByCPFIsTrue(password1.getText())) {
            System.out.println("Pode passar para a próxima tela");
        } else {
            System.out.println("CPF não existe no sistema ou CPF vazio");
        }
    }

    @FXML
    void showRegisterStage(MouseEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();

            RegisterController registerController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a cena anterior
            ((Node) event.getSource()).getScene().getWindow().hide();
        }

}

