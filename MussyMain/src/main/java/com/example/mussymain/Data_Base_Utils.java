package com.example.mussymain;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

//Essa classe me ajuda a pegar informações do Banco de dados
public class Data_Base_Utils {
    //Lista que salva o perfil logado
    public static ArrayList<Perfil> lista_Perfil;

    public static void changeScreenLogin(ActionEvent actionEvent, String fxmlFile, Perfil perfil) {
        Parent root = null;
        if (perfil.getNome() != null && perfil.getCpf() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Data_Base_Utils.class.getResource(fxmlFile));
                root = loader.load();
                Controller_Perfil controllerPerfil = loader.getController();
                controllerPerfil.loginUserInformation(perfil);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void changeScreen(ActionEvent actionEvent, String fxmlFile) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Data_Base_Utils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void fazerCadastro(ActionEvent actionEvent,
                                     String nome,
                                     String sobrenome,
                                     String cpf,
                                     String email,
                                     String senha,
                                     String verify_Senha) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psVerifyUser = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psVerifyUser = connection.prepareStatement("SELECT * FROM perfil WHERE email = ?");
            psVerifyUser.setString(1, email);
            resultSet = psVerifyUser.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("e-mail já foi cadastrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO perfil (nome,cpf,email,senha) VALUES (?,?,?,?)");
                String nomeCompleto = nome + " " + sobrenome;
                psInsert.setString(1, nomeCompleto);
                psInsert.setString(2, cpf);
                psInsert.setString(3, email);
                if (senha.equals(verify_Senha)) {
                    psInsert.setString(4, senha);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Tente Novamente :)");
                    alert.show();
                }
                psInsert.executeUpdate();
                changeScreen(actionEvent, "Screen_Login.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psVerifyUser != null) {
                try {
                    psVerifyUser.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fazerLogin(ActionEvent actionEvent, String email, String senha) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        lista_Perfil = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            preparedStatement = connection.prepareStatement("SELECT id, senha, nome, cpf, email, meta, doente FROM perfil WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Usuário não encontrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                while (resultSet.next()) {
                    // fromDB means From the Data Base
                    // traduzindo: veio da base de dados
                    String fromDB_senha = resultSet.getString("senha");
                    if (fromDB_senha.equals(senha)) {

                        Perfil perfil = new Perfil(
                                resultSet.getInt("id"),
                                resultSet.getString("nome"),
                                resultSet.getString("cpf"),
                                resultSet.getString("email"),
                                resultSet.getString("senha"));

                        lista_Perfil.add(0,perfil);

                        String notNew = resultSet.getString("meta");
                        String notNew2 = resultSet.getString("doente");

                        if (!notNew.equals("setMeta") && !notNew2.equals("notDoente")){
                            getAllInfo("perfil");
                            changeScreen(actionEvent, "Screen_PaginaInicial.fxml");
                        } else {
                            changeScreenLogin(actionEvent, "Screen_Perfil.fxml", perfil);
                        }
                    } else {
                        System.out.println("Senha Inválida");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Tente Novamente :)");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateStatementString(ActionEvent actionEvent, String table, String row, String update) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        Perfil perfil = lista_Perfil.get(0);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");

            psUpdate = connection.prepareStatement("UPDATE "+ table +" SET "+ row +" = \'"+ update +"\' WHERE id = \'" + perfil.getId() +"\'");
            psUpdate.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    public static void updateStatementDate(ActionEvent actionEvent, String table, String row, LocalDate date) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        Perfil perfil = lista_Perfil.get(0);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");

            psUpdate = connection.prepareStatement("UPDATE "+ table +" SET "+ row +" = \'"+ date +"\' WHERE id = \'" + perfil.getId() +"\'");
            psUpdate.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void updateStatementDecimal(ActionEvent actionEvent, String table, String row, float update) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        Perfil perfil = lista_Perfil.get(0);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");

            psUpdate = connection.prepareStatement("UPDATE "+ table +" SET "+ row +" = \'"+ update +"\' WHERE id = \'" + perfil.getId() +"\'");
            psUpdate.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void updateStatementInt(ActionEvent actionEvent, String table, String row, int update) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        Perfil perfil = lista_Perfil.get(0);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");

            psUpdate = connection.prepareStatement("UPDATE "+ table +" SET "+ row +" = \'"+ update +"\' WHERE id = \'" + perfil.getId() +"\'");
            psUpdate.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getInfoString(String column, String table) {
        Connection connection = null;
        PreparedStatement psGetInfo = null;
        ResultSet resultSet = null;

        Perfil perfil = lista_Perfil.get(0);
        String info = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psGetInfo = connection.prepareStatement("SELECT " + column + " FROM " + table + " WHERE id = \'" + perfil.getId() + "\'");
            resultSet = psGetInfo.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Usuário não encontrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                while(resultSet.next()){
                    info = resultSet.getString(column);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetInfo != null) {
                try {
                    psGetInfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
    public static int getInfoInt(String column, String table) {
        Connection connection = null;
        PreparedStatement psGetInfo = null;
        ResultSet resultSet = null;

        Perfil perfil = lista_Perfil.get(0);
        int info = 0;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psGetInfo = connection.prepareStatement("SELECT " + column + " FROM " + table + " WHERE id = \'" + perfil.getId() + "\'");
            resultSet = psGetInfo.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Usuário não encontrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                while(resultSet.next()){
                    info = resultSet.getInt(column);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetInfo != null) {
                try {
                    psGetInfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
    public static Date getInfoDate(String column, String table) {
        Connection connection = null;
        PreparedStatement psGetInfo = null;
        ResultSet resultSet = null;

        Perfil perfil = lista_Perfil.get(0);
        Date info = new Date(0000);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psGetInfo = connection.prepareStatement("SELECT " + column + " FROM " + table + " WHERE id = \'" + perfil.getId() + "\'");
            resultSet = psGetInfo.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Usuário não encontrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                while(resultSet.next()) {
                    info = resultSet.getDate(column);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetInfo != null) {
                try {
                    psGetInfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
    public static float getInfoFloat(String column, String table) {
        Connection connection = null;
        PreparedStatement psGetInfo = null;
        ResultSet resultSet = null;

        Perfil perfil = lista_Perfil.get(0);
        float info = 0;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psGetInfo = connection.prepareStatement("SELECT " + column + " FROM " + table + " WHERE id = \'" + perfil.getId() + "\'");
            resultSet = psGetInfo.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("Usuário não encontrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tente Novamente :)");
                alert.show();
            } else {
                while(resultSet.next()){
                    info = resultSet.getFloat(column);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetInfo != null) {
                try {
                    psGetInfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
    public static void getAllInfo(String table) {
        Connection connection = null;
        PreparedStatement psGetInfo = null;
        ResultSet resultSet = null;

        Perfil perfil = lista_Perfil.get(0);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mussy_database", "root", "");
            psGetInfo = connection.prepareStatement("SELECT * FROM "+ table +" WHERE id = ?");
            psGetInfo.setInt(1, perfil.getId());
            resultSet = psGetInfo.executeQuery();

            while(resultSet.next()) {
                perfil.setId(resultSet.getInt("id"));
                perfil.setNome(resultSet.getString("nome"));
                perfil.setEmail(resultSet.getString("email"));
                perfil.setCpf(resultSet.getString("cpf"));
                perfil.setSenha(resultSet.getString("senha"));
                perfil.setAltura(resultSet.getFloat("altura"));
                perfil.setPeso(resultSet.getFloat("peso"));
                perfil.setMeta(resultSet.getString("meta"));
                perfil.setSexo(resultSet.getString("sexo"));
                perfil.setNascimento(resultSet.getDate("nascimento"));
                perfil.setQnt_Atividade_Fisica(resultSet.getString("qnt_atividade_fisica"));
                perfil.setTempo_de_Treino(resultSet.getString("tempo_de_treino"));
                perfil.setGordura_Corporal(resultSet.getInt("gordura_corporal"));
                perfil.setComida_Regularidade(resultSet.getString("comida_regularidade"));
                perfil.setComida_Qualidade(resultSet.getString("comida_qualidade"));
                perfil.setComida_Agua(resultSet.getString("comida_agua"));
                perfil.setRotina_Estresse(resultSet.getString("rotina_estresse"));
                perfil.setRotina_Tempo_Para_Treinar(resultSet.getString("rotina_tempo_para_treinar"));
                perfil.setBiotipo(resultSet.getString("biotipo"));
                perfil.setSuplementos(resultSet.getString("suplementos"));
                perfil.setHormonios(resultSet.getString("hormonios"));
                perfil.setDoente(resultSet.getString("doente"));
                perfil.setCorpo_Desejado(resultSet.getString("corpo_desejado"));
                }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psGetInfo != null) {
                try {
                    psGetInfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}