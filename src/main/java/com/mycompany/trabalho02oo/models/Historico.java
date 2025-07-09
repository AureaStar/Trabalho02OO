package com.mycompany.trabalho02oo.models;

import java.util.List;
import java.util.Map;

public class Historico {
    private Aluno aluno;
    private Map<Disciplina, Double> notasPorDisciplina;
    private List<Disciplina> disciplinasAprovadas;
    private List<Disciplina> disciplinasReprovadas;
    private double coeficienteRendimento;
    
    public Historico() {
    }
    
    public Historico(Aluno aluno) {
        this.aluno = aluno;
    }
    
    // Methods will be implemented later
}
