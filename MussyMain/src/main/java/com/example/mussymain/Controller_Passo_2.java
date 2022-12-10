package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller_Passo_2 implements Initializable{
    // color button feminino: #af7ac5
    // color button masculino: #3498DB
    @FXML
    private Button passo2_Button_Voltar;
    @FXML
    private Button passo2_Button_Proximo;
    @FXML
    private RadioButton passo2_RButton_Masculino;
    @FXML
    private RadioButton passo2_RButton_Feminino;
    @FXML
    private TextField passo2_TextField_Peso;
    @FXML
    private TextField passo2_TextField_Altura;
    @FXML
    private DatePicker passo2_DatePicker_Nascimento;
    @FXML
    private RadioButton passo2_RButton_Nenhuma;
    @FXML
    private RadioButton passo2_RButton_Moderada;
    @FXML
    private RadioButton passo2_RButton_Intensa;
    @FXML
    private RadioButton passo2_RButton_Iniciante;
    @FXML
    private RadioButton passo2_RButton_Intermediario;
    @FXML
    private RadioButton passo2_RButton_Avancado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroupSexo = new ToggleGroup();
        passo2_RButton_Masculino.setToggleGroup(toggleGroupSexo);
        passo2_RButton_Feminino.setToggleGroup(toggleGroupSexo);

        ToggleGroup toggleGroupAtvFisica = new ToggleGroup();
        passo2_RButton_Nenhuma.setToggleGroup(toggleGroupAtvFisica);
        passo2_RButton_Moderada.setToggleGroup(toggleGroupAtvFisica);
        passo2_RButton_Intensa.setToggleGroup(toggleGroupAtvFisica);

        ToggleGroup toggleGroupNivelAcad = new ToggleGroup();
        passo2_RButton_Iniciante.setToggleGroup(toggleGroupNivelAcad);
        passo2_RButton_Intermediario.setToggleGroup(toggleGroupNivelAcad);
        passo2_RButton_Avancado.setToggleGroup(toggleGroupNivelAcad);

        passo2_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_1.fxml");
            }
        });
        passo2_Button_Proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String toggleStringSexo = ((RadioButton) toggleGroupSexo.getSelectedToggle()).getText();
                    if (toggleStringSexo.equalsIgnoreCase("masculino")){
                        toggleStringSexo = "M";
                    } else {
                        toggleStringSexo = "F";
                    }
                    String toggleStringAtvFisica = ((RadioButton) toggleGroupAtvFisica.getSelectedToggle()).getText();
                    String toggleStringTempoTreino = ((RadioButton) toggleGroupNivelAcad.getSelectedToggle()).getText();

                    LocalDate date = passo2_DatePicker_Nascimento.getValue();
                    String nascimento = date.toString();

                    if (!passo2_TextField_Peso.getText().trim().isEmpty()
                            && !passo2_TextField_Altura.getText().trim().isEmpty()
                            && !toggleStringSexo.isEmpty()
                            && !toggleStringAtvFisica.isEmpty()
                            && !toggleStringTempoTreino.isEmpty()
                            && !nascimento.isEmpty()){

                        float peso = Float.parseFloat(passo2_TextField_Peso.getText());
                        float altura = Float.parseFloat(passo2_TextField_Altura.getText());

                        Data_Base_Utils.updateStatementDecimal(actionEvent,"perfil","peso", peso);
                        Data_Base_Utils.updateStatementDecimal(actionEvent,"perfil", "altura", altura);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "sexo", toggleStringSexo);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "qnt_atividade_fisica", toggleStringAtvFisica);
                        Data_Base_Utils.updateStatementString(actionEvent, "perfil", "tempo_de_treino", toggleStringTempoTreino );
                        Data_Base_Utils.updateStatementDate(actionEvent, "perfil", "nascimento", date);

                        Data_Base_Utils.changeScreen(actionEvent, "Screen_Passo_3.fxml");
                    } else {
                        System.out.println("Preencha todos os campos.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Você esqueceu de algo :)");
                        alert.show();
                    }
                } catch (NullPointerException e) {
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
