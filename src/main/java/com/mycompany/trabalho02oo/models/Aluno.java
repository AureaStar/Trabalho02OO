package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aluno {
    private String nome;
    private String matricula;
    private Map<Disciplina, Double> disciplinasCursadas;
    private int horasMaximas;
    private List<Turma> planejamentoFuturo;
    
    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.horasMaximas = 360;
        this.disciplinasCursadas = new HashMap<>();
        this.planejamentoFuturo = new ArrayList<>();
    }
    
    public void adicionarDisciplinaCursada(Disciplina disciplina, double nota) {
        disciplinasCursadas.put(disciplina, nota);
    }

    public void adicionarTurmaPlanejamento(Turma turma) {
        if (!planejamentoFuturo.contains(turma)) {
            planejamentoFuturo.add(turma);
        }
    }

    public void removerTurmaPlanejamento(Turma turma) {
        planejamentoFuturo.remove(turma);
    }
    
    public boolean cursouDisciplina(Disciplina disciplina) {
        return disciplinasCursadas.containsKey(disciplina);
    }
    
    public Double getNotaDisciplina(Disciplina disciplina) {
        return disciplinasCursadas.get(disciplina);
    }
    
    public boolean foiAprovado(Disciplina disciplina) {
        Double nota = disciplinasCursadas.get(disciplina);
        return nota != null && nota >= 60;
    }
    
    public int calcularHorasPlanejadas() {
        return planejamentoFuturo.stream()
                .mapToInt(turma -> turma.getDisciplina().getCargaHoraria())
                .sum();
    }
    
    public boolean podeAdicionarDisciplina(Disciplina disciplina) {
        int horasAtuais = calcularHorasPlanejadas();
        return (horasAtuais + disciplina.getCargaHoraria()) <= horasMaximas;
    }

    public boolean cumpriuPreRequisitos(Disciplina disciplina) {
        Double nota = disciplinasCursadas.get(disciplina);
        return nota != null && nota >= 60;
    }

    public int getCreditosConcluidos() {
        return disciplinasCursadas.entrySet().stream()
                .filter(entry -> entry.getValue() >= 60)
                .mapToInt(entry -> entry.getKey().getCargaHoraria())
                .sum();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public Map<Disciplina, Double> getDisciplinasCursadas() { return new HashMap<>(disciplinasCursadas); }
    public int getHorasMaximas() { return horasMaximas; }
    public void setHorasMaximas(int horasMaximas) { this.horasMaximas = horasMaximas; }
    public List<Turma> getPlanejamentoFuturo() { return new ArrayList<>(planejamentoFuturo); }
}
