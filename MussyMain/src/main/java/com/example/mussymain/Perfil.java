package com.example.mussymain;

import java.util.Date;

public class Perfil {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    private String meta;

    private String sexo;
    private float peso;
    private float altura;
    private Date nascimento;
    private String qnt_Atividade_Fisica;
    private String tempo_de_Treino;
    private int gordura_Corporal;

    private String comida_Regularidade;
    private String comida_Qualidade;
    private String comida_Agua;

    private String rotina_Estresse;
    private String rotina_Tempo_Para_Treinar;

    private String biotipo;
    private String suplementos;
    private String hormonios;
    private String doente;
    private String corpo_Desejado;

    public Perfil() {
    }

    public Perfil(int id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Perfil(int id,
                  String nome,
                  String cpf,
                  String email,
                  String senha,
                  String meta,
                  String sexo,
                  float peso,
                  float altura,
                  Date nascimento,
                  String qnt_Atividade_Fisica,
                  String tempo_de_Treino,
                  int gordura_Corporal,
                  String comida_Regularidade,
                  String comida_Qualidade,
                  String comida_Agua,
                  String rotina_Estresse,
                  String rotina_Tempo_Para_Treinar,
                  String biotipo,
                  String suplementos,
                  String hormonios,
                  String doente,
                  String corpo_Desejado) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.meta = meta;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.nascimento = nascimento;
        this.qnt_Atividade_Fisica = qnt_Atividade_Fisica;
        this.tempo_de_Treino = tempo_de_Treino;
        this.gordura_Corporal = gordura_Corporal;
        this.comida_Regularidade = comida_Regularidade;
        this.comida_Qualidade = comida_Qualidade;
        this.comida_Agua = comida_Agua;
        this.rotina_Estresse = rotina_Estresse;
        this.rotina_Tempo_Para_Treinar = rotina_Tempo_Para_Treinar;
        this.biotipo = biotipo;
        this.suplementos = suplementos;
        this.hormonios = hormonios;
        this.doente = doente;
        this.corpo_Desejado = corpo_Desejado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public Date getNascimento() {
        return nascimento;
    }
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getQnt_Atividade_Fisica() {
        return qnt_Atividade_Fisica;
    }

    public void setQnt_Atividade_Fisica(String qnt_Atividade_Fisica) {
        this.qnt_Atividade_Fisica = qnt_Atividade_Fisica;
    }

    public String getTempo_de_Treino() {
        return tempo_de_Treino;
    }

    public void setTempo_de_Treino(String tempo_de_Treino) {
        this.tempo_de_Treino = tempo_de_Treino;
    }

    public int getGordura_Corporal() {
        return gordura_Corporal;
    }

    public void setGordura_Corporal(int gordura_Corporal) {
        this.gordura_Corporal = gordura_Corporal;
    }

    public String getComida_Regularidade() {
        return comida_Regularidade;
    }

    public void setComida_Regularidade(String comida_Regularidade) {
        this.comida_Regularidade = comida_Regularidade;
    }

    public String getComida_Qualidade() {
        return comida_Qualidade;
    }

    public void setComida_Qualidade(String comida_Qualidade) {
        this.comida_Qualidade = comida_Qualidade;
    }

    public String getComida_Agua() {
        return comida_Agua;
    }

    public void setComida_Agua(String comida_Agua) {
        this.comida_Agua = comida_Agua;
    }

    public String getRotina_Estresse() {
        return rotina_Estresse;
    }

    public void setRotina_Estresse(String rotina_Estresse) {
        this.rotina_Estresse = rotina_Estresse;
    }

    public String getRotina_Tempo_Para_Treinar() {
        return rotina_Tempo_Para_Treinar;
    }

    public void setRotina_Tempo_Para_Treinar(String rotina_Tempo_Para_Treinar) {
        this.rotina_Tempo_Para_Treinar = rotina_Tempo_Para_Treinar;
    }

    public String getBiotipo() {
        return biotipo;
    }

    public void setBiotipo(String biotipo) {
        this.biotipo = biotipo;
    }

    public String getSuplementos() {
        return suplementos;
    }

    public void setSuplementos(String suplementos) {
        this.suplementos = suplementos;
    }

    public String getHormonios() {
        return hormonios;
    }

    public void setHormonios(String hormonios) {
        this.hormonios = hormonios;
    }

    public String getDoente() {
        return doente;
    }

    public void setDoente(String doente) {
        this.doente = doente;
    }

    public String getCorpo_Desejado() {
        return corpo_Desejado;
    }

    public void setCorpo_Desejado(String corpo_Desejado) {
        this.corpo_Desejado = corpo_Desejado;
    }
}
