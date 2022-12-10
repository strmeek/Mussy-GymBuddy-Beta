package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Passo_1 implements Initializable {
    @FXML
    private Button passo1_Button_Voltar;
    @FXML
    private Button passo1_Button_Proximo;
    @FXML
    private RadioButton passo1_RButton_Perder;
    @FXML
    private RadioButton passo1_RButton_Ganhar;
    @FXML
    private RadioButton passo1_RButton_Ambos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        passo1_RButton_Perder.setToggleGroup(toggleGroup);
        passo1_RButton_Ganhar.setToggleGroup(toggleGroup);
        passo1_RButton_Ambos.setToggleGroup(toggleGroup);

        passo1_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Login.fxml");
            }
        });
        passo1_Button_Proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                    if(toggleName != null){
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "meta", toggleName);
                        Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_2.fxml");
                    } else {
                        System.out.println("Escolha uma opção");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Escolha uma opção");
                        alert.show();
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("Preencha todos os campos.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Você esqueceu de algo :)");
                    alert.show();
                }
            }
        });
    }
}
