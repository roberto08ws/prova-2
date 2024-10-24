package com.example.prova4;

public class BodyCadastro {

    private String nome_completo, cidade;

    public BodyCadastro(String nome_completo, String cidade) {
        this.nome_completo = nome_completo;
        this.cidade = cidade;
    }

    public BodyCadastro() {
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
}
