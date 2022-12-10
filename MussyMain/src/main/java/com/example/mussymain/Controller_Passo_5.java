package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Passo_5 implements Initializable {
    @FXML
    private Button passo5_Button_Voltar;
    @FXML
    private Button passo5_Button_Finalizar;
    @FXML
    private RadioButton passo5_RButton_SimSuple;
    @FXML
    private RadioButton passo5_RButton_NaoSuple;
    @FXML
    private RadioButton passo5_RButton_SimAnabol;
    @FXML
    private RadioButton passo5_RButton_NaoAnabol;
    @FXML
    private CheckBox passo5_CheckBox_Nenhuma;
    @FXML
    private CheckBox passo5_CheckBox_Diabetes;
    @FXML
    private CheckBox passo5_CheckBox_Hipertensao;
    @FXML
    private CheckBox passo5_CheckBox_Cardio;
    @FXML
    private CheckBox passo5_CheckBox_Respiratorio;
    @FXML
    private CheckBox passo5_CheckBox_Osseo;
    private String checkBoxSelection = "+";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup tgSuplemento = new ToggleGroup();
        passo5_RButton_SimSuple.setToggleGroup(tgSuplemento);
        passo5_RButton_NaoSuple.setToggleGroup(tgSuplemento);

        ToggleGroup tgAnabol = new ToggleGroup();
        passo5_RButton_SimAnabol.setToggleGroup(tgAnabol);
        passo5_RButton_NaoAnabol.setToggleGroup(tgAnabol);

        passo5_Button_Voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Data_Base_Utils.changeScreen(actionEvent,"Screen_Passo_4.fxml");
            }
        });
        passo5_Button_Finalizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String suplemento = ((RadioButton) tgSuplemento.getSelectedToggle()).getText();
                    String anabol = ((RadioButton) tgAnabol.getSelectedToggle()).getText();

                    if (passo5_CheckBox_Nenhuma.isSelected()){
                        checkBoxSelection = "N";
                    } else {
                        if (passo5_CheckBox_Diabetes.isSelected()) {
                            checkBoxSelection += "D";
                        }
                        if (passo5_CheckBox_Hipertensao.isSelected()) {
                            checkBoxSelection += "H";
                        }
                        if (passo5_CheckBox_Cardio.isSelected()) {
                            checkBoxSelection += "C";
                        }
                        if (passo5_CheckBox_Respiratorio.isSelected()) {
                            checkBoxSelection += "R";
                        }
                        if (passo5_CheckBox_Osseo.isSelected()) {
                            checkBoxSelection += "O";
                        }
                    }
                    if (!checkBoxSelection.isEmpty()
                    && !suplemento.isEmpty()
                    && !anabol.isEmpty()){
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "suplementos", suplemento);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "hormonios", anabol);
                        Data_Base_Utils.updateStatementString(actionEvent,"perfil", "doente", checkBoxSelection);

                        Data_Base_Utils.changeScreen(actionEvent,"Screen_PaginaInicial.fxml");
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
