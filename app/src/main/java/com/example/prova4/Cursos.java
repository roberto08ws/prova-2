package com.example.prova4;

public class Cursos {

    private int id;
    private String nome_curso;

    public Cursos(int id, String nome_curso) {
        this.id = id;
        this.nome_curso = nome_curso;
    }

    public Cursos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }
}
