package com.example.prova4;

public class BodyAtualiza {

    private String nome_completo, cidade;

    public BodyAtualiza(String nome_completo, String cidade) {
        this.nome_completo = nome_completo;
        this.cidade = cidade;
    }

    public BodyAtualiza() {
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
