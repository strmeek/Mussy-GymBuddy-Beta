package com.example.mussymain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Perfil implements Initializable {
    @FXML
    private Button perfil_Button_Logout;
    @FXML
    private Button perfil_Button_Start;
    @FXML
    private Label perfil_Label_Text;
    @FXML
    private Label perfil_Label_Ola;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        perfil_Button_Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Login.fxml");
            }
        });

        perfil_Button_Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_1.fxml");
            }
        });
    }
    public void loginUserInformation(Perfil perfil){
        perfil_Label_Ola.setText("Olá " + perfil.getNome() + "!");
    }
}
