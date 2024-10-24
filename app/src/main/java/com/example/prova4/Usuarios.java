package com.example.prova4;

public class Usuarios {

    private int id;
    private String nome_completo, cidade, curso, email, senha;

    public Usuarios(int id, String nome_completo, String cidade, String curso, String email, String senha) {
        this.id = id;
        this.nome_completo = nome_completo;
        this.cidade = cidade;
        this.curso = curso;
        this.email = email;
        this.senha = senha;
    }

    public Usuarios() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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
}
