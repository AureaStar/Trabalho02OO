package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma oferta especifica de uma disciplina.
 * Contem informacoes como ID da turma, capacidade maxima, 
 * numero atual de alunos matriculados e o horario fixo da aula.
 */
public class Turma {
    private String id; // ID da turma
    private Disciplina disciplina;
    private String professor;
    private int capacidadeMaxima; // capacidade maxima de alunos
    private int alunosMatriculados; // numero atual de matriculados
    private String horario; // horario fixo da aula (ex: segunda 08h–10h)
    private List<String> matriculasAlunos; // matriculas dos alunos
    
    public Turma() {
        this.matriculasAlunos = new ArrayList<>();
        this.alunosMatriculados = 0;
    }
    
    public Turma(String id, Disciplina disciplina, String professor, int capacidadeMaxima, String horario) {
        this.id = id;
        this.disciplina = disciplina;
        this.professor = professor;
        this.capacidadeMaxima = capacidadeMaxima;
        this.horario = horario;
        this.matriculasAlunos = new ArrayList<>();
        this.alunosMatriculados = 0;
    }
    
    public boolean temVagas() {
        return alunosMatriculados < capacidadeMaxima;
    }
    
    public void adicionarMatricula(String matriculaAluno) {
        if (temVagas() && !matriculasAlunos.contains(matriculaAluno)) {
            matriculasAlunos.add(matriculaAluno);
            alunosMatriculados++;
        }
    }
    
    public void removerMatricula(String matriculaAluno) {
        if (matriculasAlunos.remove(matriculaAluno)) {
            alunosMatriculados--;
        }
    }
    
    /**
     * Adiciona um aluno a turma
     */
    public void adicionarAluno(Aluno aluno) {
        if (!matriculasAlunos.contains(aluno.getMatricula())) {
            matriculasAlunos.add(aluno.getMatricula());
            alunosMatriculados++;
        }
    }
    
    /**
     * Remove um aluno da turma
     */
    public void removerAluno(Aluno aluno) {
        if (matriculasAlunos.remove(aluno.getMatricula())) {
            alunosMatriculados--;
        }
    }
    
    /**
     * Verifica se ha vagas disponiveis
     */
    public boolean temVagasDisponiveis() {
        return alunosMatriculados < capacidadeMaxima;
    }
    
    /**
     * Retorna o numero de vagas restantes
     */
    public int getVagasRestantes() {
        return capacidadeMaxima - alunosMatriculados;
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    public String getProfessor() {
        return professor;
    }
    
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    
    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public List<String> getMatriculasAlunos() {
        return new ArrayList<>(matriculasAlunos);
    }
    
    public int getVagas() {
        return capacidadeMaxima;
    }
    
    public int getVagasOcupadas() {
        return alunosMatriculados;
    }
}
