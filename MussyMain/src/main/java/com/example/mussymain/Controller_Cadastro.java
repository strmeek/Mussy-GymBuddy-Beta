package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Cadastro implements Initializable {
    @FXML
    private Button cadastro_Button_Voltar;

    @FXML
    private Label cadastro_Label_Info;
    @FXML
    private TextField cadastro_Nome;
    @FXML
    private TextField cadastro_Sobrenome;
    @FXML
    private TextField cadastro_Email;
    @FXML
    private TextField cadastro_Cpf;

    @FXML
    private Label cadastro_Label_Senha;
    @FXML
    private TextField cadastro_Password;
    @FXML
    private TextField cadastro_Verify_Password;

    @FXML
    private Button cadastro_Button_Confirm_Cadastro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastro_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Login.fxml");
            }
        });
        cadastro_Button_Confirm_Cadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!cadastro_Nome.getText().trim().isEmpty()
                        && !cadastro_Sobrenome.getText().trim().isEmpty()
                        && !cadastro_Email.getText().trim().isEmpty()
                        && !cadastro_Cpf.getText().trim().isEmpty()
                        && !cadastro_Password.getText().trim().isEmpty()
                        && !cadastro_Verify_Password.getText().trim().isEmpty()) {
                    Data_Base_Utils.fazerCadastro(actionEvent,
                            cadastro_Nome.getText(),
                            cadastro_Sobrenome.getText(),
                            cadastro_Cpf.getText(),
                            cadastro_Email.getText(),
                            cadastro_Password.getText(),
                            cadastro_Verify_Password.getText());
                } else {
                    System.out.println("Preencha todos os campos.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Você esqueceu de algo :)");
                    alert.show();
                }
            }
        });
    }
}
