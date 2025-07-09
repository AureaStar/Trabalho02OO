package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa o planejamento academico de um aluno.
 * Gerencia o planejamento de disciplinas para semestres futuros e
 * calcula estimativas para formatura.
 */
public class Planejamento {
    private Aluno aluno;
    private List<Turma> disciplinasPlanejadasProximoSemestre;
    private Map<Integer, List<Turma>> planejamentoFuturo; // Semestre -> Lista de Turmas
    private int semestresParaFormatura;
    private List<String> observacoes;
    private List<Disciplina> disciplinasObrigatoriasRestantes;
    private int creditosNecessariosFormatura;
    private int creditosJaCursados;
    
    public Planejamento() {
        this.disciplinasPlanejadasProximoSemestre = new ArrayList<>();
        this.planejamentoFuturo = new HashMap<>();
        this.observacoes = new ArrayList<>();
        this.disciplinasObrigatoriasRestantes = new ArrayList<>();
        this.semestresParaFormatura = 0;
        this.creditosNecessariosFormatura = 240; // Padrao para bacharelado
        this.creditosJaCursados = 0;
    }
    
    public Planejamento(Aluno aluno) {
        this();
        this.aluno = aluno;
        if (aluno != null) {
            inicializarPlanejamento();
        }
    }
    
    /**
     * Inicializa o planejamento baseado no historico do aluno
     */
    private void inicializarPlanejamento() {
        creditosJaCursados = aluno.calcularCargaHorariaCursada();
        // Copia o planejamento atual do aluno
        disciplinasPlanejadasProximoSemestre.addAll(aluno.getPlanejamentoFuturo());
    }
    
    /**
     * Adiciona uma turma ao planejamento do proximo semestre
     */
    public void adicionarTurmaProximoSemestre(Turma turma) {
        if (!disciplinasPlanejadasProximoSemestre.contains(turma)) {
            disciplinasPlanejadasProximoSemestre.add(turma);
            recalcularSemestresFormatura();
        }
    }
    
    /**
     * Remove uma turma do planejamento do proximo semestre
     */
    public void removerTurmaProximoSemestre(Turma turma) {
        disciplinasPlanejadasProximoSemestre.remove(turma);
        recalcularSemestresFormatura();
    }
    
    /**
     * Adiciona turmas ao planejamento de um semestre especifico
     */
    public void adicionarTurmasSemestre(int semestre, List<Turma> turmas) {
        planejamentoFuturo.computeIfAbsent(semestre, k -> new ArrayList<>()).addAll(turmas);
        recalcularSemestresFormatura();
    }
    
    /**
     * Adiciona uma unica turma a um semestre especifico
     */
    public void adicionarTurmaSemestre(int semestre, Turma turma) {
        planejamentoFuturo.computeIfAbsent(semestre, k -> new ArrayList<>()).add(turma);
        recalcularSemestresFormatura();
    }
    
    /**
     * Remove uma turma de um semestre especifico
     */
    public void removerTurmaSemestre(int semestre, Turma turma) {
        List<Turma> turmasSemestre = planejamentoFuturo.get(semestre);
        if (turmasSemestre != null) {
            turmasSemestre.remove(turma);
            if (turmasSemestre.isEmpty()) {
                planejamentoFuturo.remove(semestre);
            }
        }
        recalcularSemestresFormatura();
    }
    
    /**
     * Calcula quantos semestres faltam para a formatura
     */
    private void recalcularSemestresFormatura() {
        int creditosPlaneados = calcularCreditosPlaneados();
        int creditosRestantes = creditosNecessariosFormatura - creditosJaCursados - creditosPlaneados;
        
        if (creditosRestantes <= 0) {
            semestresParaFormatura = obterMaiorSemestrePlaneado();
        } else {
            // Estima com base na carga horaria maxima do aluno
            int creditosPorSemestre = aluno != null ? aluno.getCargaHorariaMaxima() : 24;
            int semestresAdicionais = (int) Math.ceil((double) creditosRestantes / creditosPorSemestre);
            semestresParaFormatura = obterMaiorSemestrePlaneado() + semestresAdicionais;
        }
    }
    
    /**
     * Calcula o total de creditos planejados
     */
    private int calcularCreditosPlaneados() {
        int creditos = 0;
        
        // Creditos do proximo semestre
        for (Turma turma : disciplinasPlanejadasProximoSemestre) {
            creditos += turma.getDisciplina().getCargaHoraria();
        }
        
        // Creditos dos semestres futuros
        for (List<Turma> turmas : planejamentoFuturo.values()) {
            for (Turma turma : turmas) {
                creditos += turma.getDisciplina().getCargaHoraria();
            }
        }
        
        return creditos;
    }
    
    /**
     * Obtém o maior número de semestre planejado
     */
    private int obterMaiorSemestrePlaneado() {
        int maiorSemestre = disciplinasPlanejadasProximoSemestre.isEmpty() ? 0 : 1;
        for (Integer semestre : planejamentoFuturo.keySet()) {
            if (semestre > maiorSemestre) {
                maiorSemestre = semestre;
            }
        }
        return maiorSemestre;
    }
    
    /**
     * Adiciona uma observação ao planejamento
     */
    public void adicionarObservacao(String observacao) {
        observacoes.add(observacao);
    }
    
    /**
     * Verifica se o planejamento está dentro da carga horária máxima por semestre
     */
    public boolean validarCargaHorariaSemestre(int semestre) {
        List<Turma> turmas;
        if (semestre == 1) {
            turmas = disciplinasPlanejadasProximoSemestre;
        } else {
            turmas = planejamentoFuturo.get(semestre);
        }
        
        if (turmas == null || aluno == null) return true;
        
        int cargaTotal = turmas.stream()
                .mapToInt(turma -> turma.getDisciplina().getCargaHoraria())
                .sum();
        
        return cargaTotal <= aluno.getCargaHorariaMaxima();
    }
    
    /**
     * Gera um relatório do planejamento
     */
    public String gerarRelatorioPlanejamento() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PLANEJAMENTO ACADÊMICO ===\n");
        sb.append("Aluno: ").append(aluno != null ? aluno.getNome() : "N/A").append("\n");
        sb.append("Créditos já cursados: ").append(creditosJaCursados).append("\n");
        sb.append("Créditos planejados: ").append(calcularCreditosPlaneados()).append("\n");
        sb.append("Créditos restantes: ").append(
                Math.max(0, creditosNecessariosFormatura - creditosJaCursados - calcularCreditosPlaneados())).append("\n");
        sb.append("Semestres para formatura: ").append(semestresParaFormatura).append("\n\n");
        
        // Próximo semestre
        if (!disciplinasPlanejadasProximoSemestre.isEmpty()) {
            sb.append("PRÓXIMO SEMESTRE:\n");
            for (Turma turma : disciplinasPlanejadasProximoSemestre) {
                sb.append("- ").append(turma.getDisciplina().getNome())
                  .append(" (").append(turma.getDisciplina().getCargaHoraria()).append("h)\n");
            }
            sb.append("\n");
        }
        
        // Semestres futuros
        for (Map.Entry<Integer, List<Turma>> entry : planejamentoFuturo.entrySet()) {
            sb.append("SEMESTRE ").append(entry.getKey()).append(":\n");
            for (Turma turma : entry.getValue()) {
                sb.append("- ").append(turma.getDisciplina().getNome())
                  .append(" (").append(turma.getDisciplina().getCargaHoraria()).append("h)\n");
            }
            sb.append("\n");
        }
        
        // Observações
        if (!observacoes.isEmpty()) {
            sb.append("OBSERVAÇÕES:\n");
            for (String obs : observacoes) {
                sb.append("- ").append(obs).append("\n");
            }
        }
        
        return sb.toString();
    }
    
    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        if (aluno != null) {
            inicializarPlanejamento();
        }
    }
    
    public List<Turma> getDisciplinasPlanejadasProximoSemestre() {
        return new ArrayList<>(disciplinasPlanejadasProximoSemestre);
    }
    
    public Map<Integer, List<Turma>> getPlanejamentoFuturo() {
        Map<Integer, List<Turma>> copia = new HashMap<>();
        for (Map.Entry<Integer, List<Turma>> entry : planejamentoFuturo.entrySet()) {
            copia.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copia;
    }
    
    public int getSemestresParaFormatura() {
        return semestresParaFormatura;
    }
    
    public List<String> getObservacoes() {
        return new ArrayList<>(observacoes);
    }
    
    public List<Disciplina> getDisciplinasObrigatoriasRestantes() {
        return new ArrayList<>(disciplinasObrigatoriasRestantes);
    }
    
    public void setDisciplinasObrigatoriasRestantes(List<Disciplina> disciplinasObrigatoriasRestantes) {
        this.disciplinasObrigatoriasRestantes = new ArrayList<>(disciplinasObrigatoriasRestantes);
    }
    
    public int getCreditosNecessariosFormatura() {
        return creditosNecessariosFormatura;
    }
    
    public void setCreditosNecessariosFormatura(int creditosNecessariosFormatura) {
        this.creditosNecessariosFormatura = creditosNecessariosFormatura;
    }
    
    public int getCreditosJaCursados() {
        return creditosJaCursados;
    }
    
    @Override
    public String toString() {
        return "Planejamento{" +
                "aluno=" + (aluno != null ? aluno.getNome() : "null") +
                ", semestresParaFormatura=" + semestresParaFormatura +
                ", creditosJaCursados=" + creditosJaCursados +
                ", creditosPlaneados=" + calcularCreditosPlaneados() +
                ", proximoSemestre=" + disciplinasPlanejadasProximoSemestre.size() + " turmas" +
                '}';
    }
}
