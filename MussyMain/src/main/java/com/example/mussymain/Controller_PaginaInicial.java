package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class Controller_PaginaInicial implements Initializable {
    @FXML
    private ImageView tabHome_ImageView_Ramon;
    @FXML
    private ImageView tabHome_ImageView_Cbum;
    @FXML
    private MenuButton pgInicial_MenuButton_Ola;
    @FXML
    private MenuItem MenuItem_Ajuda;
    @FXML
    private MenuItem MenuItem_Sair;
    @FXML
    private Button tabTreino_Button_FotoTreino;
    @FXML
    private Button tabTreino_Button_PdfTreino;
    @FXML
    private Button tabTreino_Button_TaPago;
    @FXML
    private Label tabComida_Label_Basal;
    @FXML
    private Label tabComida_Label_GastoMedio;
    @FXML
    private Label tabComida_Label_Consumir;
    @FXML
    private Label tabComida_Label_CarboCount;
    @FXML
    private Label tabComida_Label_ProteinCount;
    @FXML
    private Label tabComida_Label_FatCount;
    @FXML
    private TextField tabComida_TextField_CaloriasDia;
    @FXML
    private TextField tabComida_TextField_CarboDia;
    @FXML
    private TextField tabComida_TextField_ProteinDia;
    @FXML
    private TextField tabComida_TextField_FatDia;
    @FXML
    private Button tabComida_Button_Birl;
    @FXML
    private Button tabProgresso_Button_Amei;
    @FXML
    private Label tabPerfil_Label_Nome;
    @FXML
    private Label tabPerfil_Label_Email;
    @FXML
    private Label tabPerfil_Label_Meta;
    @FXML
    private Label tabPerfil_Label_Sexo;
    @FXML
    private Label tabPerfil_Label_Altura;
    @FXML
    private Label tabPerfil_Label_Idade;
    @FXML
    private Label tabPerfil_Label_Peso;
    @FXML
    private Label tabPerfil_Label_NDC;
    @FXML
    private Label tabPerfil_Label_NivelAcad;
    @FXML
    private Label tabPerfil_Label_Gordura;
    @FXML
    private Label tabPerfil_Label_Regularidade;
    @FXML
    private Label tabPerfil_Label_Qualidade;
    @FXML
    private Label tabPerfil_Label_Agua;
    @FXML
    private Label tabPerfil_Label_Estresse;
    @FXML
    private Label tabPerfil_Label_TempoPTreinar;
    @FXML
    private Label tabPerfil_Label_Biotipo;
    @FXML
    private Label tabPerfil_Label_Suplemento;
    @FXML
    private Label tabPerfil_Label_Anabol;
    @FXML
    private Label tabPerfil_Label_Doenca;
    @FXML
    private Label tabPerfil_Label_TMB;
    @FXML
    private Label tabPerfil_Label_Ficha;

    File fileRamon = new File("@../../../../Images/Ramon.jpg");
    Image imageRamon = new Image(fileRamon.toURI().toString());
    File fileCbum = new File("@../../../../Images/Cbum.jpg");
    Image imageCbum = new Image(fileCbum.toURI().toString());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginUserInformation(Data_Base_Utils.lista_Perfil.get(0));
        tabHome_ImageView_Ramon.setImage(imageRamon);
        tabHome_ImageView_Cbum.setImage(imageCbum);

        tabTreino_Button_PdfTreino.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
        MenuItem_Sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        MenuItem_Ajuda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Sem ajuda");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ainda não temos uma equipe de Suporte, desculpa :(");
                alert.show();
            }
        });
    }
    @SuppressWarnings("deprecation")
    public void loginUserInformation(Perfil perfil){

        tabPerfil_Label_Nome.setText("Nome: " + perfil.getNome());
        tabPerfil_Label_Altura.setText("Altura: " + perfil.getAltura());
        tabPerfil_Label_Sexo.setText("Sexo: " + perfil.getSexo());
        tabPerfil_Label_Email.setText("Email: " + perfil.getEmail());
        tabPerfil_Label_Meta.setText("Meta: " + perfil.getMeta());
        tabPerfil_Label_Peso.setText("Peso: " + perfil.getPeso());
        tabPerfil_Label_NDC.setText("NDC: " + perfil.getQnt_Atividade_Fisica());
        tabPerfil_Label_NivelAcad.setText("Nivel Acad: " + perfil.getTempo_de_Treino());
        tabPerfil_Label_Gordura.setText("Gordura: " + perfil.getGordura_Corporal());
        tabPerfil_Label_Regularidade.setText("Regularidade : " + perfil.getComida_Regularidade());
        tabPerfil_Label_Qualidade.setText("Qualidade: " + perfil.getComida_Qualidade());
        tabPerfil_Label_Agua.setText("Agua: " + perfil.getComida_Agua());
        tabPerfil_Label_Estresse.setText("Estresse: " + perfil.getRotina_Estresse());
        tabPerfil_Label_TempoPTreinar.setText("Tempo p Treinar: " + perfil.getRotina_Tempo_Para_Treinar());
        tabPerfil_Label_Biotipo.setText("Biotipo: " + perfil.getBiotipo());
        tabPerfil_Label_Suplemento.setText("Usa Suplemento: " + perfil.getSuplementos());
        tabPerfil_Label_Anabol.setText("Usa Veneninhos(rs): " + perfil.getHormonios());
        tabPerfil_Label_Doenca.setText("Doença: " + perfil.getDoente());

        int idade = calculoIdade(perfil.getNascimento().getDate(),
                perfil.getNascimento().getMonth(),
                perfil.getNascimento().getYear());
        tabPerfil_Label_Idade.setText("Idade: " + idade);

        double TMB = calculoTMB(perfil.getPeso(),
                perfil.getAltura(),
                idade,
                perfil.getSexo());
        DecimalFormat format = new DecimalFormat();
        tabPerfil_Label_TMB.setText(String.format("TMB: " + format.format(TMB)));
    }
    public static int calculoIdade(int dia, int mes, int ano) {
        int idade = (int) ChronoUnit.YEARS.between(LocalDate.of(ano, mes, dia), LocalDate.now());
        if (dia == LocalDate.now().getDayOfMonth() && mes == LocalDate.now().getMonthValue() - 1){
            System.out.println("PARABÉNS É SEU ANIVERSÁRIO!!!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PARABÉNS É SEU ANIVERSÁRIO!!!");
            alert.show();
        }
        return idade - 1900;
    }
    public static double calculoTMB(double peso, double altura, double idade, String sexo){
        double taxa_metabolica_basal;

        if (sexo.equals("M")){
            taxa_metabolica_basal = 66.5 + (peso * 13.8) + (altura * 5) - (idade * 6.8);
        } else {
            taxa_metabolica_basal = 655.1 + (peso * 9.6) + (altura * 1.8) - (idade * 4.7);
        }
        return taxa_metabolica_basal;
    }
}
