package com.example.prova4;

public class Ranking {

    private int id, pontos;
    private String estado, aluno, foto;

    public Ranking(int id, int pontos, String estado, String aluno, String foto) {
        this.id = id;
        this.pontos = pontos;
        this.estado = estado;
        this.aluno = aluno;
        this.foto = foto;
    }

    public Ranking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
