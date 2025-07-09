package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa o historico academico de um aluno.
 * Mantem registro das disciplinas cursadas, notas, aprovacoes e reprovacoes.
 */
public class Historico {
    private Aluno aluno;
    private Map<Disciplina, Double> notasPorDisciplina;
    private List<Disciplina> disciplinasAprovadas;
    private List<Disciplina> disciplinasReprovadas;
    private double coeficienteRendimento;
    private int totalCreditos;
    private int totalCreditosAprovados;
    
    public Historico() {
        this.notasPorDisciplina = new HashMap<>();
        this.disciplinasAprovadas = new ArrayList<>();
        this.disciplinasReprovadas = new ArrayList<>();
        this.coeficienteRendimento = 0.0;
        this.totalCreditos = 0;
        this.totalCreditosAprovados = 0;
    }
    
    public Historico(Aluno aluno) {
        this();
        this.aluno = aluno;
        // Inicializar com as disciplinas ja cursadas pelo aluno
        if (aluno != null) {
            inicializarComDisciplinasAluno();
        }
    }
    
    /**
     * Inicializa o historico com as disciplinas ja cursadas pelo aluno
     */
    private void inicializarComDisciplinasAluno() {
        Map<Disciplina, Double> disciplinasCursadas = aluno.getDisciplinasCursadas();
        for (Map.Entry<Disciplina, Double> entry : disciplinasCursadas.entrySet()) {
            adicionarDisciplina(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Adiciona uma disciplina ao historico com sua nota
     */
    public void adicionarDisciplina(Disciplina disciplina, double nota) {
        notasPorDisciplina.put(disciplina, nota);
        totalCreditos += disciplina.getCargaHoraria();
        
        if (nota >= 60.0) {
            if (!disciplinasAprovadas.contains(disciplina)) {
                disciplinasAprovadas.add(disciplina);
                totalCreditosAprovados += disciplina.getCargaHoraria();
            }
            // Remove da lista de reprovadas se estava la
            disciplinasReprovadas.remove(disciplina);
        } else {
            if (!disciplinasReprovadas.contains(disciplina)) {
                disciplinasReprovadas.add(disciplina);
            }
            // Remove da lista de aprovadas se estava la
            disciplinasAprovadas.remove(disciplina);
        }
        
        calcularCoeficienteRendimento();
    }
    
    /**
     * Calcula o coeficiente de rendimento baseado nas notas e carga horaria
     */
    private void calcularCoeficienteRendimento() {
        if (totalCreditos == 0) {
            coeficienteRendimento = 0.0;
            return;
        }
        
        double somaNotasPonderadas = 0.0;
        for (Map.Entry<Disciplina, Double> entry : notasPorDisciplina.entrySet()) {
            somaNotasPonderadas += entry.getValue() * entry.getKey().getCargaHoraria();
        }
        
        coeficienteRendimento = somaNotasPonderadas / totalCreditos;
    }
    
    /**
     * Verifica se o aluno foi aprovado em uma disciplina especifica
     */
    public boolean foiAprovado(Disciplina disciplina) {
        Double nota = notasPorDisciplina.get(disciplina);
        return nota != null && nota >= 60.0;
    }
    
    /**
     * Retorna a nota obtida em uma disciplina
     */
    public Double getNota(Disciplina disciplina) {
        return notasPorDisciplina.get(disciplina);
    }
    
    /**
     * Retorna o numero de tentativas em uma disciplina
     */
    public int getNumeroTentativas(Disciplina disciplina) {
        // Simplificado - assume 1 tentativa por enquanto
        // Em um sistema real, manteria historico de tentativas
        return notasPorDisciplina.containsKey(disciplina) ? 1 : 0;
    }
    
    /**
     * Verifica se o aluno tem creditos minimos suficientes
     */
    public boolean temCreditosMinimos(int creditosMinimos) {
        return totalCreditosAprovados >= creditosMinimos;
    }
    
    /**
     * Gera um relatorio resumido do historico
     */
    public String gerarRelatorioResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== HISTORICO ACADEMICO ===\n");
        sb.append("Aluno: ").append(aluno != null ? aluno.getNome() : "N/A").append("\n");
        sb.append("Matricula: ").append(aluno != null ? aluno.getMatricula() : "N/A").append("\n");
        sb.append("Coeficiente de Rendimento: ").append(String.format("%.2f", coeficienteRendimento)).append("\n");
        sb.append("Total de Creditos Cursados: ").append(totalCreditos).append("\n");
        sb.append("Total de Creditos Aprovados: ").append(totalCreditosAprovados).append("\n");
        sb.append("Disciplinas Aprovadas: ").append(disciplinasAprovadas.size()).append("\n");
        sb.append("Disciplinas Reprovadas: ").append(disciplinasReprovadas.size()).append("\n");
        return sb.toString();
    }
    
    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Map<Disciplina, Double> getNotasPorDisciplina() {
        return new HashMap<>(notasPorDisciplina);
    }
    
    public List<Disciplina> getDisciplinasAprovadas() {
        return new ArrayList<>(disciplinasAprovadas);
    }
    
    public List<Disciplina> getDisciplinasReprovadas() {
        return new ArrayList<>(disciplinasReprovadas);
    }
    
    public double getCoeficienteRendimento() {
        return coeficienteRendimento;
    }
    
    public int getTotalCreditos() {
        return totalCreditos;
    }
    
    public int getTotalCreditosAprovados() {
        return totalCreditosAprovados;
    }
    
    @Override
    public String toString() {
        return "Historico{" +
                "aluno=" + (aluno != null ? aluno.getNome() : "null") +
                ", coeficienteRendimento=" + coeficienteRendimento +
                ", totalCreditos=" + totalCreditos +
                ", totalCreditosAprovados=" + totalCreditosAprovados +
                ", disciplinasAprovadas=" + disciplinasAprovadas.size() +
                ", disciplinasReprovadas=" + disciplinasReprovadas.size() +
                '}';
    }
}
