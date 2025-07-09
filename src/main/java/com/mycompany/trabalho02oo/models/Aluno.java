package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um estudante no sistema.
 * Armazena o nome, matricula, as disciplinas cursadas com suas notas,
 * a quantidade de horas maxima que ele pode cursar e o planejamento futuro.
 */
public class Aluno {
    private String nome;
    private String matricula; // Formato AAAACCMMM
    private Map<Disciplina, Double> disciplinasCursadas; // Disciplina -> Nota
    private int cargaHorariaMaxima;
    private List<Turma> planejamentoFuturo; // Turmas desejadas para o proximo semestre
    
    public Aluno(String nome, String matricula, int cargaHorariaMaxima) {
        this.nome = nome;
        this.matricula = matricula;
        this.cargaHorariaMaxima = cargaHorariaMaxima;
        this.disciplinasCursadas = new HashMap<>();
        this.planejamentoFuturo = new ArrayList<>();
    }
    
    /**
     * Adiciona uma disciplina cursada com sua respectiva nota
     */
    public void adicionarDisciplinaCursada(Disciplina disciplina, double nota) {
        disciplinasCursadas.put(disciplina, nota);
    }
    
    /**
     * Verifica se o aluno cumpriu o pre-requisito de uma disciplina
     * (nota >= 60)
     */
    public boolean cumpriuPreRequisito(Disciplina disciplina) {
        Double nota = disciplinasCursadas.get(disciplina);
        return nota != null && nota >= 60.0;
    }
    
    /**
     * Adiciona uma turma ao planejamento futuro
     */
    public void adicionarTurmaPlanejamento(Turma turma) {
        if (!planejamentoFuturo.contains(turma)) {
            planejamentoFuturo.add(turma);
        }
    }
    
    /**
     * Remove uma turma do planejamento futuro
     */
    public void removerTurmaPlanejamento(Turma turma) {
        planejamentoFuturo.remove(turma);
    }
    
    /**
     * Calcula a carga horaria total das disciplinas cursadas aprovadas
     */
    public int calcularCargaHorariaCursada() {
        return disciplinasCursadas.entrySet().stream()
                .filter(entry -> entry.getValue() >= 60.0)
                .mapToInt(entry -> entry.getKey().getCargaHoraria())
                .sum();
    }
    
    /**
     * Calcula a carga horaria do planejamento atual
     */
    public int calcularCargaHorariaPlanejamento() {
        return planejamentoFuturo.stream()
                .mapToInt(turma -> turma.getDisciplina().getCargaHoraria())
                .sum();
    }
    
    /**
     * Retorna as disciplinas aprovadas (nota >= 60)
     */
    public List<Disciplina> getDisciplinasAprovadas() {
        List<Disciplina> aprovadas = new ArrayList<>();
        for (Map.Entry<Disciplina, Double> entry : disciplinasCursadas.entrySet()) {
            if (entry.getValue() >= 60.0) {
                aprovadas.add(entry.getKey());
            }
        }
        return aprovadas;
    }
    
    /**
     * Verifica se o aluno pode se matricular considerando a carga horaria maxima
     */
    public boolean podeAdicionarCargaHoraria(int cargaHoraria) {
        return calcularCargaHorariaPlanejamento() + cargaHoraria <= cargaHorariaMaxima;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public Map<Disciplina, Double> getDisciplinasCursadas() {
        return new HashMap<>(disciplinasCursadas);
    }
    
    public int getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }
    
    public void setCargaHorariaMaxima(int cargaHorariaMaxima) {
        this.cargaHorariaMaxima = cargaHorariaMaxima;
    }
    
    public List<Turma> getPlanejamentoFuturo() {
        return new ArrayList<>(planejamentoFuturo);
    }
    
    public void setPlanejamentoFuturo(List<Turma> planejamentoFuturo) {
        this.planejamentoFuturo = new ArrayList<>(planejamentoFuturo);
    }
    
    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", cargaHorariaMaxima=" + cargaHorariaMaxima +
                ", disciplinasCursadas=" + disciplinasCursadas.size() +
                ", planejamentoFuturo=" + planejamentoFuturo.size() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno aluno = (Aluno) obj;
        return matricula.equals(aluno.matricula);
    }
    
    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
}
