package com.example.mussymain;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Login implements Initializable {
    @FXML
    private Label login_Label_Buildmuscle;
    @FXML
    private Label login_Label_Mussy;
    @FXML
    private TextField login_Email;
    @FXML
    private PasswordField login_Password;
    @FXML
    private Button login_Button_Login;
    @FXML
    private Button login_Button_Cadastro;
    @FXML
    private Button login_Button_Quit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_Button_Login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.fazerLogin(actionEvent, login_Email.getText(), login_Password.getText());
            }
        });

        login_Button_Cadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent, "Screen_Cadastro.fxml");
            }
        });
        login_Button_Quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}