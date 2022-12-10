package com.example.mussymain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Passo_4 implements Initializable {
    @FXML
    private Button passo4_Button_Voltar;
    @FXML
    private Button passo4_Button_Proximo;
    @FXML
    private RadioButton passo4_RButton_Mental;
    @FXML
    private RadioButton passo4_RButton_Fisico;
    @FXML
    private RadioButton passo4_RButton_Ambos;
    @FXML
    private Slider passo4_Slider_GrauEstresse;
    @FXML
    private Label passo4_Label_GrauEstresse;
    @FXML
    private TextField passo4_TextField_Horas;
    @FXML
    private TextField passo4_TextField_Dias;
    private int myEstresse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup tgEstresse = new ToggleGroup();
        passo4_RButton_Mental.setToggleGroup(tgEstresse);
        passo4_RButton_Fisico.setToggleGroup(tgEstresse);
        passo4_RButton_Ambos.setToggleGroup(tgEstresse);

        passo4_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_3.fxml");
            }
        });

        passo4_Slider_GrauEstresse.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                myEstresse = (int) passo4_Slider_GrauEstresse.getValue();
                if(myEstresse <= 25){
                    passo4_Label_GrauEstresse.setText("Baixo");
                } else if (myEstresse >= 25 && myEstresse < 50) {
                    passo4_Label_GrauEstresse.setText("Médio");
                } else if (myEstresse >= 50 && myEstresse < 75) {
                    passo4_Label_GrauEstresse.setText("Alto");
                } else {
                    passo4_Label_GrauEstresse.setText("Muito Alto");
                }
            }
        });
        passo4_Button_Proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String horas = passo4_TextField_Horas.getText();
                    String dias = passo4_TextField_Dias.getText();
                    String grauEstresse = passo4_Label_GrauEstresse.getText();
                    String rbSelected = ((RadioButton) tgEstresse.getSelectedToggle()).getText();

                    if (!horas.isEmpty()
                            && !dias.isEmpty()
                            && !grauEstresse.isEmpty()
                            && !rbSelected.isEmpty()) {

                        String rotinaEstresse = rbSelected + ";" + grauEstresse;
                        Data_Base_Utils.updateStatementString(actionEvent, "perfil", "rotina_estresse", rotinaEstresse);

                        char verify = horas.charAt(0);
                        char verify2 = dias.charAt(0);
                        if (isNumber(verify) && isNumber(verify2)) {
                            String tempoParaTreinar = verify + ";" + verify2;
                            Data_Base_Utils.updateStatementString(actionEvent, "perfil", "rotina_tempo_para_treinar", tempoParaTreinar);
                            Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_5.fxml");
                        } else {
                            System.out.println("Caractere inválido");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Provavelmente você inseriu algum caractere inválido \n" +
                                    "nós aceitamos somente números de 0 a 7.");
                            alert.show();
                        }
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
    private boolean isNumber(char verify){
        return verify >= '0' && verify <= '7';
    }
}
