package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private Label pgInicial_Label_MussyCoins;
    @FXML
    private Text tabTreino_Text_TreinoDia;

    int idade = 0;
    double TMB = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Perfil perfil = Data_Base_Utils.lista_Perfil.get(0);

        loginUserInformation(Data_Base_Utils.lista_Perfil.get(0));

        final int[] coins = {perfil.getMussycoins()};

        //Image imageRamon = new Image(getClass().getResourceAsStream("../main/Images/Ramon.jpg"));
        //Image imageCbum = new Image(getClass().getResourceAsStream("../main/Images/Cbum.jpg"));
        //tabHome_ImageView_Ramon.setImage(imageRamon);
        //tabHome_ImageView_Cbum.setImage(imageCbum);

        tabTreino_Button_PdfTreino.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Perfil perfil = Data_Base_Utils.lista_Perfil.get(0);

                    String path = "C:\\Mussy JavaFX\\MussyMain\\src\\main\\pdfs\\treino.pdf";
                    File file = new File(path);

                    PDDocument document = new PDDocument();
                    PDPage page1 = new PDPage();
                    PDPage page2 = new PDPage();
                    document.addPage(page1);
                    document.addPage(page2);

                    PDPageContentStream csPage1 = new PDPageContentStream(document, page1);
                    csPage1.beginText();
                    csPage1.setFont(PDType1Font.TIMES_ROMAN,14);
                    csPage1.setLeading(16.0f);
                    csPage1.newLineAtOffset(25,page1.getTrimBox().getHeight() - 25);

                    csPage1.showText("Nome: " + perfil.getNome());
                    csPage1.newLine();
                    csPage1.showText("Idade: " + idade);
                    csPage1.newLine();
                    csPage1.showText("Meta: " + perfil.getMeta());
                    csPage1.newLine();
                    csPage1.showText("Nivel Acad: " + perfil.getTempo_de_Treino());
                    csPage1.newLine();
                    csPage1.showText("NDC: " + perfil.getQnt_Atividade_Fisica());
                    csPage1.newLine();
                    csPage1.showText("Usa Suplemento: " + perfil.getSuplementos());
                    csPage1.newLine();
                    csPage1.showText("Usa Veneninhos(rs): " + perfil.getHormonios());
                    csPage1.newLine();
                    csPage1.showText("Doen??a: " + perfil.getDoente());
                    csPage1.newLine();
                    DecimalFormat format = new DecimalFormat();
                    csPage1.showText("Taxa Metab??lica Basal: " + format.format(TMB));
                    csPage1.newLine();

                    csPage1.endText();
                    csPage1.close();

                    PDPageContentStream csPage2 = new PDPageContentStream(document, page2);
                    csPage2.beginText();
                    csPage2.setFont(PDType1Font.TIMES_ROMAN,14);
                    csPage2.setLeading(16.0f);
                    csPage2.newLineAtOffset(25,page1.getTrimBox().getHeight() - 25);

                    csPage2.showText("Ficha de Treino: Default");
                    csPage2.newLine();
                    csPage2.showText("A (Quadriceps)");
                    csPage2.newLine();
                    csPage2.showText("0 - Aquecimento: Passada");
                    csPage2.newLine();
                    csPage2.showText("1 - Adutora 4xfalha // pr?? exaust??o");
                    csPage2.newLine();
                    csPage2.showText("2 - Bulgarian Squat (ou Agach. livre) 4xfalha");
                    csPage2.newLine();
                    csPage2.showText("3 - Leg Press 6xfalha // com dropset nas 2 ultimas");
                    csPage2.newLine();
                    csPage2.showText("4 - Agachamento na maq 4xfalha");
                    csPage2.newLine();
                    csPage2.showText("5 - Extensora 6xfalha // com isometria");
                    csPage2.newLine();
                    csPage2.showText("Legenda: pr?? exaust??o = tecnica para isolar um m??sculo cansando os seus auxiliares");
                    csPage2.newLine();
                    csPage2.showText("dropset = ?? tirada alguma carga consideravel no meio da s??rie para aumentar a intensidade");
                    csPage2.newLine();
                    csPage2.showText("isometria = voc?? deve segurar o peso no pico de contra????o do m??sculo, aumentando o tempo de tens??o");
                    csPage2.newLine();

                    csPage2.endText();
                    csPage2.close();

                    document.save(file);
                    document.close();

                    System.out.println("Seu PDF");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Seu pdf est?? em: \n" +
                    "\\MussyMain\\src\\main\\pdfs");
                    alert.show();

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        tabTreino_Button_TaPago.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                coins[0] += 100;
                pgInicial_Label_MussyCoins.setText("MussyCoins = " + coins[0]);
                Data_Base_Utils.updateStatementInt(actionEvent,"perfil", "mussycoins", coins[0]);
                tabTreino_Text_TreinoDia.setText("D (Quadriceps) \n" +
                        ". Aquecimento: Passada;\n" +
                        ". Adutora 4xfalha; // pre exaust\n" +
                        ". Bulgarian Squat (ou Agach. livre) 4xfalha;\n" +
                        ". Leg Press 6xfalha; //drop\n" +
                        ". Agachamento na maq 4xfalha;\n" +
                        ". Extensora 6xfalha; // isometria");
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
                alert.setContentText("Ainda n??o temos uma equipe de Suporte, desculpa :(");
                alert.show();
            }
        });
        tabComida_Button_Birl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String calorias = tabComida_TextField_CaloriasDia.getText();
                    String carbo = tabComida_TextField_CarboDia.getText();
                    String protein = tabComida_TextField_ProteinDia.getText();
                    String gordura = tabComida_TextField_FatDia.getText();

                    if (!calorias.isEmpty()
                    && !carbo.isEmpty()
                    && !protein.isEmpty()
                    && !gordura.isEmpty()){
                        double totalCarbo = Double.parseDouble(tabComida_Label_CarboCount.getText());
                        double totalProtein = Double.parseDouble(tabComida_Label_ProteinCount.getText());
                        double totalFat = Double.parseDouble(tabComida_Label_FatCount.getText());

                        double carboDia = Double.parseDouble(carbo);
                        double proteinDia = Double.parseDouble(protein);
                        double gorduraDia = Double.parseDouble(gordura);

                        if (totalProtein <= proteinDia
                        && totalCarbo <= carboDia
                        && totalFat <= gorduraDia){
                            coins[0] += 50;
                            pgInicial_Label_MussyCoins.setText("MussyCoins = " + coins[0]);
                            Data_Base_Utils.updateStatementInt(actionEvent,"perfil", "mussycoins", coins[0]);
                            System.out.println("Parab??ns, voc?? conseguiu!!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Todos os objetivos de calorias e macronutrientes foram alcan??ados, parab??ns!");
                            alert.show();
                        } else {
                            System.out.println("Acontece :(");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("N??o Desanime, Acorde amanh?? e tente de novo!");
                            alert.show();
                        }
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("Preencha todos os campos.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Voc?? esqueceu de algo :)");
                    alert.show();
                }


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
        tabPerfil_Label_Doenca.setText("Doen??a: " + perfil.getDoente());
        pgInicial_Label_MussyCoins.setText("MussyCoins = " + perfil.getMussycoins());
        tabPerfil_Label_Ficha.setText("Ficha de Treino: Default");
        pgInicial_MenuButton_Ola.setText("Ol?? " + perfil.getNome());

        idade = calculoIdade(perfil.getNascimento().getDate(),
                perfil.getNascimento().getMonth(),
                perfil.getNascimento().getYear());
        tabPerfil_Label_Idade.setText("Idade: " + idade);

        TMB = calculoTMB(perfil.getPeso(),
                perfil.getAltura(),
                idade,
                perfil.getSexo());
        DecimalFormat format = new DecimalFormat();
        tabPerfil_Label_TMB.setText(String.format("TMB: " + format.format(TMB)));

        tabComida_Label_Basal.setText(format.format(TMB));

        if (perfil.getQnt_Atividade_Fisica().equalsIgnoreCase("intensa")){
            tabComida_Label_GastoMedio.setText(format.format(TMB + 750));
        }else if (perfil.getQnt_Atividade_Fisica().equalsIgnoreCase("moderada")){
            tabComida_Label_GastoMedio.setText(format.format(TMB + 450));
        } else {
            tabComida_Label_GastoMedio.setText(format.format(TMB + 75));
        }
        if (perfil.getMeta().equalsIgnoreCase("perder gordura")) {
            tabComida_Label_Consumir.setText(format.format(TMB - 425));
            tabComida_Label_CarboCount.setText(String.valueOf(perfil.getPeso() * 2));
            tabComida_Label_ProteinCount.setText(String.valueOf(perfil.getPeso() * 2));
            tabComida_Label_FatCount.setText(String.valueOf(perfil.getPeso() * 0.75));
        }else if (perfil.getMeta().equalsIgnoreCase("ganhar massa")) {
            tabComida_Label_Consumir.setText(format.format(TMB + 425 + 600));
            tabComida_Label_CarboCount.setText(String.valueOf(perfil.getPeso() * 4));
            tabComida_Label_ProteinCount.setText(String.valueOf(perfil.getPeso() * 2));
            tabComida_Label_FatCount.setText(String.valueOf(perfil.getPeso() * 1.25));
        } else {
            tabComida_Label_Consumir.setText(format.format(TMB - 250));
            tabComida_Label_CarboCount.setText(String.valueOf(perfil.getPeso() * 2.5));
            tabComida_Label_ProteinCount.setText(String.valueOf(perfil.getPeso() * 2));
            tabComida_Label_FatCount.setText(String.valueOf(perfil.getPeso() * 1));
            }
        }
    public static int calculoIdade(int dia, int mes, int ano) {
        int idade = (int) ChronoUnit.YEARS.between(LocalDate.of(ano, mes, dia), LocalDate.now());
        if (dia == LocalDate.now().getDayOfMonth() && mes == LocalDate.now().getMonthValue() - 1){
            System.out.println("PARAB??NS ?? SEU ANIVERS??RIO!!!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PARAB??NS ?? SEU ANIVERS??RIO!!!");
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
