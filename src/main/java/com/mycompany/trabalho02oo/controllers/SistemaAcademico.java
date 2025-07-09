package com.mycompany.trabalho02oo.controllers;

import com.mycompany.trabalho02oo.models.*;
import java.util.List;

public class SistemaAcademico {
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;
    
    public SistemaAcademico() {
    }
    
    // CRUD operations for Aluno
    public void adicionarAluno(Aluno aluno) {
        // Implementation later
    }
    
    public void removerAluno(String matricula) {
        // Implementation later
    }
    
    public Aluno buscarAluno(String matricula) {
        // Implementation later
        return null;
    }
    
    // CRUD operations for Disciplina
    public void adicionarDisciplina(Disciplina disciplina) {
        // Implementation later
    }
    
    public void removerDisciplina(String codigo) {
        // Implementation later
    }
    
    public Disciplina buscarDisciplina(String codigo) {
        // Implementation later
        return null;
    }
    
    // CRUD operations for Turma
    public void adicionarTurma(Turma turma) {
        // Implementation later
    }
    
    public void removerTurma(String codigo) {
        // Implementation later
    }
    
    public Turma buscarTurma(String codigo) {
        // Implementation later
        return null;
    }
    
    // Matricula operations
    public void matricularAluno(String matriculaAluno, String codigoTurma) {
        // Implementation later
    }
    
    public void desmatricularAluno(String matriculaAluno, String codigoTurma) {
        // Implementation later
    }
    
    // Planning operations
    public Planejamento gerarPlanejamento(String matriculaAluno) {
        // Implementation later
        return null;
    }
}
