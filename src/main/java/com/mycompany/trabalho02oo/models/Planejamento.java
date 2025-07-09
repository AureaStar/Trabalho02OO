package com.mycompany.trabalho02oo.models;

import java.util.List;
import java.util.Map;

public class Planejamento {
    private Aluno aluno;
    private List<Disciplina> disciplinasPlanejadasProximoSemestre;
    private Map<Integer, List<Disciplina>> planejamentoFuturo;
    private int semestresParaFormatura;
    
    public Planejamento() {
    }
    
    public Planejamento(Aluno aluno) {
        this.aluno = aluno;
    }
    
    // Methods will be implemented later
}
