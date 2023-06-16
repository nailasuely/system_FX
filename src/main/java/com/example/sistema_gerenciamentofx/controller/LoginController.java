package com.example.sistema_gerenciamentofx.controller;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    void login(ActionEvent event) throws Exception {
        String passawordText = password1.getText();

        if (!passawordText.isEmpty() && DAO.getTecnicoDAO().findByCPFIsTrue(password1.getText())) {
            System.out.println("pode passar para prox tela");
            try {
                Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentScreen.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/home-view.fxml"));
                Parent root = loader.load();
                HomeController homeController = loader.getController();
                homeController.setTechinicianCpf(passawordText);
                Stage registerStage = new Stage();
                Scene scene = new Scene(root);
                registerStage.setResizable(false);
                registerStage.setScene(scene);
                registerStage.show();
                registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/sistema_gerenciamentofx/images/1.png")));

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        } else {
            AlertMessageController alertMessageController = new AlertMessageController();
            if(passawordText.isEmpty()){
                alertMessageController.showAlertMensage("Digite seu CPF para realizar o login");
            } else if (!DAO.getTecnicoDAO().findByCPFIsTrue(passawordText)) {
                alertMessageController.showAlertMensage("CPF não cadastrado\nPor favor registre-se");
            }

        }

    }
    @FXML
    void showRegisterStage(MouseEvent event) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistema_gerenciamentofx/register-view.fxml"));
            Parent root = loader.load();
            Stage registerStage = new Stage();
            Scene scene = new Scene(root);
            registerStage.setResizable(false);
            registerStage.setScene(scene);
            registerStage.show();
            registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/sistema_gerenciamentofx/images/1.png")));

        } catch (Exception excep) {
            excep.printStackTrace();
        }}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connect.generateCache();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
