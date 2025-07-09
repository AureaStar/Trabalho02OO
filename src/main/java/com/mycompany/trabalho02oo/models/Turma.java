package com.mycompany.trabalho02oo.models;

import java.util.List;

public class Turma {
    private String codigo;
    private Disciplina disciplina;
    private String professor;
    private int vagas;
    private int vagasOcupadas;
    private List<String> horarios;
    private List<Aluno> alunosMatriculados;
    
    public Turma() {
    }
    
    public Turma(String codigo, Disciplina disciplina, String professor, int vagas) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.vagas = vagas;
    }
    
    // Getters and Setters will be implemented later
}
