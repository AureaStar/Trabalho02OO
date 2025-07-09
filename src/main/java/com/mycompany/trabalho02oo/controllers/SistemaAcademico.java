package com.mycompany.trabalho02oo.controllers;

import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.exceptions.*;
import com.mycompany.trabalho02oo.validators.ValidadorPreRequisito;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Orquestrador central da simulacao. Responsavel por:
 * - Carregar dados de disciplinas, turmas e alunos
 * - Gerenciar o processo de planejamento de matricula para os alunos
 * - Aplicar todas as regras de validacao (pre-requisitos, co-requisitos, carga horaria, vagas, conflitos)
 * - Atualizar o historico do aluno e gerar os relatorios da simulacao
 */
public class SistemaAcademico {
    private Map<String, Aluno> alunos;
    private Map<String, Disciplina> disciplinas;
    private Map<String, Turma> turmas;
    
    public SistemaAcademico() {
        this.alunos = new HashMap<>();
        this.disciplinas = new HashMap<>();
        this.turmas = new HashMap<>();
    }
    
    // CRUD operations for Aluno
    public void adicionarAluno(Aluno aluno) {
        alunos.put(aluno.getMatricula(), aluno);
    }
    
    public void removerAluno(String matricula) {
        alunos.remove(matricula);
    }
    
    public Aluno buscarAluno(String matricula) {
        return alunos.get(matricula);
    }
    
    // CRUD operations for Disciplina
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(), disciplina);
    }
    
    public void removerDisciplina(String codigo) {
        disciplinas.remove(codigo);
    }
    
    public Disciplina buscarDisciplina(String codigo) {
        return disciplinas.get(codigo);
    }
    
    // CRUD operations for Turma
    public void adicionarTurma(Turma turma) {
        turmas.put(turma.getId(), turma);
    }
    
    public void removerTurma(String id) {
        turmas.remove(id);
    }
    
    public Turma buscarTurma(String id) {
        return turmas.get(id);
    }
    
    /**
     * Processa a simulacao de matricula para um aluno
     */
    public RelatorioMatricula simularMatricula(String matriculaAluno) throws MatriculaException {
        Aluno aluno = buscarAluno(matriculaAluno);
        if (aluno == null) {
            throw new ValidacaoMatriculaException("Aluno nao encontrado: " + matriculaAluno);
        }
        
        RelatorioMatricula relatorio = new RelatorioMatricula(matriculaAluno);
        List<Turma> turmasDesejadas = new ArrayList<>(aluno.getPlanejamentoFuturo());
        List<Turma> turmasAceitas = new ArrayList<>();
        
        // Ordena turmas por prioridade (obrigatorias > eletivas > optativas)
        turmasDesejadas.sort((t1, t2) -> 
            Integer.compare(t1.getDisciplina().getPrioridade(), t2.getDisciplina().getPrioridade()));
        
        int cargaHorariaAtual = 0;
        
        for (Turma turma : turmasDesejadas) {
            try {
                // Verifica se nao excede carga horaria maxima
                if (cargaHorariaAtual + turma.getDisciplina().getCargaHoraria() > aluno.getCargaHorariaMaxima()) {
                    relatorio.adicionarItem(turma, RelatorioMatricula.StatusMatricula.REJEITADA, 
                        "Carga horaria maxima excedida");
                    continue;
                }
                
                // Valida pre-requisitos
                validarPreRequisitos(aluno, turma.getDisciplina());
                
                // Valida co-requisitos
                validarCoRequisitos(aluno, turma.getDisciplina(), turmasDesejadas);
                
                // Verifica vagas
                if (!turma.temVagas()) {
                    throw new TurmaCheiaException("Turma " + turma.getId() + " está cheia");
                }
                
                // Verifica conflitos de horario
                validarConflitoHorario(turma, turmasAceitas);
                
                // Se chegou ate aqui, a matricula e valida
                turmasAceitas.add(turma);
                cargaHorariaAtual += turma.getDisciplina().getCargaHoraria();
                turma.adicionarMatricula(matriculaAluno);
                
                relatorio.adicionarItem(turma, RelatorioMatricula.StatusMatricula.ACEITA, 
                    "Matricula realizada com sucesso");
                
            } catch (MatriculaException e) {
                relatorio.adicionarItem(turma, RelatorioMatricula.StatusMatricula.REJEITADA, 
                    e.getMessage());
            }
        }
        
        return relatorio;
    }
    
    // Matricula operations
    public void matricularAluno(String matriculaAluno, String codigoTurma) throws MatriculaException {
        Aluno aluno = buscarAluno(matriculaAluno);
        if (aluno == null) {
            throw new ValidacaoMatriculaException("Aluno nao encontrado: " + matriculaAluno);
        }
        
        Turma turma = buscarTurma(codigoTurma);
        if (turma == null) {
            throw new ValidacaoMatriculaException("Turma nao encontrada: " + codigoTurma);
        }
        
        // Verificar se a turma tem vagas
        if (turma.getVagasOcupadas() >= turma.getVagas()) {
            throw new TurmaCheiaException("Turma " + codigoTurma + " esta cheia");
        }
        
        // Verificar carga horaria
        if (!aluno.podeAdicionarCargaHoraria(turma.getDisciplina().getCargaHoraria())) {
            throw new CargaHorariaExcedidaException("Carga horaria maxima excedida");
        }
        
        // Verificar pre-requisitos
        for (ValidadorPreRequisito validador : turma.getDisciplina().getValidadoresPreRequisito()) {
            if (!validador.validar(aluno, turma.getDisciplina())) {
                throw new PreRequisitoNaoCumpridoException(validador.getMensagemErro());
            }
        }
        
        // Verificar co-requisitos
        for (Disciplina coRequisito : turma.getDisciplina().getCoRequisitos()) {
            boolean temCoRequisito = aluno.getPlanejamentoFuturo().stream()
                    .anyMatch(t -> t.getDisciplina().equals(coRequisito));
            if (!temCoRequisito) {
                throw new CoRequisitoNaoAtendidoException("Co-requisito nao atendido: " + coRequisito.getNome());
            }
        }
        
        // Verificar conflitos de horario
        verificarConflitoHorario(aluno, turma);
        
        // Realizar matricula
        aluno.adicionarTurmaPlanejamento(turma);
        turma.adicionarAluno(aluno);
    }
    
    private void validarPreRequisitos(Aluno aluno, Disciplina disciplina) throws PreRequisitoNaoCumpridoException {
        for (ValidadorPreRequisito validador : disciplina.getValidadoresPreRequisito()) {
            if (!validador.validar(aluno, disciplina)) {
                throw new PreRequisitoNaoCumpridoException(validador.getMensagemErro());
            }
        }
    }
    
    private void validarCoRequisitos(Aluno aluno, Disciplina disciplina, List<Turma> turmasDesejadas) 
            throws CoRequisitoNaoAtendidoException {
        List<Disciplina> coRequisitos = disciplina.getCoRequisitos();
        if (!coRequisitos.isEmpty()) {
            List<Disciplina> disciplinasDesejadas = turmasDesejadas.stream()
                .map(Turma::getDisciplina)
                .collect(Collectors.toList());
            
            for (Disciplina coRequisito : coRequisitos) {
                if (!disciplinasDesejadas.contains(coRequisito)) {
                    throw new CoRequisitoNaoAtendidoException(
                        "Co-requisito nao atendido: " + coRequisito.getCodigo());
                }
            }
        }
    }
    
    private void validarConflitoHorario(Turma novaTurma, List<Turma> turmasAceitas) 
            throws ConflitoDeHorarioException {
        for (Turma turmaExistente : turmasAceitas) {
            if (novaTurma.getHorario().equals(turmaExistente.getHorario())) {
                // Verifica precedencia
                int prioridadeNova = novaTurma.getDisciplina().getPrioridade();
                int prioridadeExistente = turmaExistente.getDisciplina().getPrioridade();
                
                if (prioridadeNova == prioridadeExistente) {
                    throw new ConflitoDeHorarioException(
                        "Conflito de horario entre disciplinas de mesma precedencia");
                } else if (prioridadeNova > prioridadeExistente) {
                    // Nova turma tem menor prioridade, rejeita
                    throw new ConflitoDeHorarioException(
                        "Conflito de horario: disciplina com menor precedencia rejeitada");
                }
                // Se nova turma tem maior prioridade, remove a existente
                turmasAceitas.remove(turmaExistente);
                break;
            }
        }
    }
    
    private void verificarConflitoHorario(Aluno aluno, Turma novaTurma) throws ConflitoDeHorarioException {
        for (Turma turmaExistente : aluno.getPlanejamentoFuturo()) {
            if (turmasConflitam(turmaExistente, novaTurma)) {
                // Verificar precedencia
                int prioridadeExistente = turmaExistente.getDisciplina().getPrioridade();
                int prioridadeNova = novaTurma.getDisciplina().getPrioridade();
                
                if (prioridadeExistente == prioridadeNova) {
                    // Mesmo nivel de prioridade - rejeitar
                    throw new ConflitoDeHorarioException("Conflito de horario entre disciplinas de mesma precedencia");
                } else if (prioridadeExistente > prioridadeNova) {
                    // Disciplina existente tem maior prioridade - rejeitar nova
                    throw new ConflitoDeHorarioException("Conflito de horario - disciplina existente tem maior precedencia");
                } else {
                    // Nova disciplina tem maior prioridade - remover existente
                    aluno.removerTurmaPlanejamento(turmaExistente);
                    turmaExistente.removerAluno(aluno);
                }
            }
        }
    }
    
    private boolean turmasConflitam(Turma turma1, Turma turma2) {
        // Implementacao simplificada - assumindo que horarios conflitam se contem o mesmo dia
        String horario1 = turma1.getHorario().toLowerCase();
        String horario2 = turma2.getHorario().toLowerCase();
        
        String[] dias = {"segunda", "terca", "quarta", "quinta", "sexta", "sabado"};
        
        for (String dia : dias) {
            if (horario1.contains(dia) && horario2.contains(dia)) {
                return true; // Mesmo dia - pode ter conflito
            }
        }
        return false;
    }
    
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos.values());
    }
    
    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }
    
    public List<Turma> listarTurmas() {
        return new ArrayList<>(turmas.values());
    }
}
