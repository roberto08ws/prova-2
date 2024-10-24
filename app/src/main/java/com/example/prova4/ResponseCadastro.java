package com.example.prova4;

public class ResponseCadastro {

    private Usuarios aluno;

    public ResponseCadastro(Usuarios aluno) {
        this.aluno = aluno;
    }

    public ResponseCadastro() {
    }

    public Usuarios getAluno() {
        return aluno;
    }

    public void setAluno(Usuarios aluno) {
        this.aluno = aluno;
    }
}
