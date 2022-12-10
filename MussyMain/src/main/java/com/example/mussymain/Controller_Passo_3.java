package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Passo_3 implements Initializable {
    @FXML
    private Button passo3_Button_Voltar;
    @FXML
    private Button passo3_Button_Proximo;
    @FXML
    private RadioButton passo3_RButton_Regrado;
    @FXML
    private RadioButton passo3_RButton_LDesregrado;
    @FXML
    private RadioButton passo3_RButton_Desregrado;
    @FXML
    private RadioButton passo3_RButton_Saudavel;
    @FXML
    private RadioButton passo3_RButton_MSaudavel;
    @FXML
    private RadioButton passo3_RButton_NotSaudavel;
    @FXML
    private RadioButton passo3_RButton_Muita;
    @FXML
    private RadioButton passo3_RButton_Media;
    @FXML
    private RadioButton passo3_RButton_Pouca;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup tgRegularidade = new ToggleGroup();
        passo3_RButton_Regrado.setToggleGroup(tgRegularidade);
        passo3_RButton_LDesregrado.setToggleGroup(tgRegularidade);
        passo3_RButton_Desregrado.setToggleGroup(tgRegularidade);

        ToggleGroup tgQualidade = new ToggleGroup();
        passo3_RButton_Saudavel.setToggleGroup(tgQualidade);
        passo3_RButton_MSaudavel.setToggleGroup(tgQualidade);
        passo3_RButton_NotSaudavel.setToggleGroup(tgQualidade);

        ToggleGroup tgAgua = new ToggleGroup();
        passo3_RButton_Muita.setToggleGroup(tgAgua);
        passo3_RButton_Media.setToggleGroup(tgAgua);
        passo3_RButton_Pouca.setToggleGroup(tgAgua);

        passo3_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_2.fxml");
            }
        });
        passo3_Button_Proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    String tgStringRegularidade = ((RadioButton) tgRegularidade.getSelectedToggle()).getText();
                    String tgStringQualidade = ((RadioButton) tgQualidade.getSelectedToggle()).getText();
                    String tgStringAgua = ((RadioButton) tgAgua.getSelectedToggle()).getText();
                    if (!tgStringRegularidade.isEmpty()
                        && !tgStringQualidade.isEmpty()
                        && !tgStringAgua.isEmpty()){

                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "comida_regularidade", tgStringRegularidade);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "comida_qualidade", tgStringQualidade);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "comida_agua", tgStringAgua);

                        Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_4.fxml");

                    } else {
                        System.out.println("Preencha todos os campos.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Você esqueceu de algo :)");
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
