package com.mycompany.trabalho02oo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.exceptions.CargaHorariaExcedidaException;
import com.mycompany.trabalho02oo.exceptions.ConflitoDeHorarioException;
import com.mycompany.trabalho02oo.exceptions.PreRequisitoNaoCumpridoException;
import com.mycompany.trabalho02oo.exceptions.TurmaCheiaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.DisciplinaEletiva;
import com.mycompany.trabalho02oo.models.DisciplinaObrigatoria;
import com.mycompany.trabalho02oo.models.DisciplinaOptativa;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.validators.ValidadorPreRequisito;

public class SistemaAcademico {
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;

    public SistemaAcademico() {
        this.alunos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void cadastrarAluno(String nome, String matricula) {
        Aluno aluno = new Aluno(nome, matricula);

        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

    public void cadastrarDisciplinaObrigatoria(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaObrigatoria(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void cadastrarDisciplinaEletiva(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaEletiva(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void cadastrarDisciplinaOptativa(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaOptativa(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void cadastrarTurma(String codigo, Disciplina disciplina, String professor, int capacidadeMaxima, String horario) {
        Turma turma = new Turma(codigo, disciplina, capacidadeMaxima, professor, horario);

        if (turma != null && !turmas.contains(turma)) {
            turmas.add(turma);
        }
    }

    public void matricularAlunoEmTurma (Aluno aluno, Turma turma) throws MatriculaException {
        if (!alunos.contains(aluno)) {
            throw new IllegalArgumentException("Aluno nao encontrado no sistema.");
        }
        
        if (!turmas.contains(turma)) {
            throw new IllegalArgumentException("Turma nao encontrada no sistema.");
        }
        
        if (aluno.getPlanejamentoFuturo().contains(turma)) {
            throw new IllegalArgumentException("Aluno ja cadastrado na turma.");
        }
        
        if (turma.getCapacidadeMaxima() <= 0) {
            throw new TurmaCheiaException("Turma nao tem vagas disponiveis.");
        }
        
        int cargaHorariaAtual = calcularCargaHorariaPlanejamento(aluno);
        if (cargaHorariaAtual + turma.getDisciplina().getCargaHoraria() > aluno.getHorasMaximas()) {
            throw new CargaHorariaExcedidaException("Carga horaria excedida. Atual: " + cargaHorariaAtual + 
                ", Tentativa: " + turma.getDisciplina().getCargaHoraria() + 
                ", Maximo: " + aluno.getHorasMaximas());
        }
        
        for (Disciplina preRequisito : turma.getDisciplina().getPreRequisitos()) {
            if (!aluno.cumpriuPreRequisito(preRequisito)) {
                throw new PreRequisitoNaoCumpridoException("\n Pre-requisito nao cumprido: " + 
                    preRequisito.getNome() + " para disciplina " + turma.getDisciplina().getNome());
            }
        }
        
        for (ValidadorPreRequisito validador : turma.getDisciplina().getValidadoresPreRequisito()) {
            if (!validador.validar(aluno, turma.getDisciplina())) {
                throw new PreRequisitoNaoCumpridoException(validador.getMensagemErro());
            }
        }
        
        verificarConflitoHorario(aluno, turma);
        
        aluno.adicionarTurmaPlanejamento(turma);
        turma.reservarVaga(aluno);
    }
   
    private int calcularCargaHorariaPlanejamento(Aluno aluno) {
        return aluno.getPlanejamentoFuturo().stream()
            .mapToInt(turma -> turma.getDisciplina().getCargaHoraria())
            .sum();
    }
    
    private void verificarConflitoHorario(Aluno aluno, Turma novaTurma) throws ConflitoDeHorarioException {
        for (Turma turmaExistente : aluno.getPlanejamentoFuturo()) {
            if (turmaExistente.getHorario().equals(novaTurma.getHorario())) {
                int precedenciaNova = novaTurma.getDisciplina().getPrioridade();
                int precedenciaExistente = turmaExistente.getDisciplina().getPrioridade();
                
                if (precedenciaNova > precedenciaExistente) {
                    aluno.removerTurmaPlanejamento(turmaExistente);
                    return;
                } else if (precedenciaNova < precedenciaExistente) {
                    throw new ConflitoDeHorarioException("Conflito de horario: \n - " + 
                        novaTurma.getDisciplina().getNome() + " (" + novaTurma.getHorario() + ") " +
                        "conflita com " + turmaExistente.getDisciplina().getNome() + 
                        " que tem maior precedencia.");
                } else {
                    throw new ConflitoDeHorarioException("Conflito de horario irresolvivel: \n - " + 
                        novaTurma.getDisciplina().getNome() + " e " + 
                        turmaExistente.getDisciplina().getNome() + 
                        " tem a mesma precedencia no horario " + novaTurma.getHorario());
                }
            }
        }
    }

    public List<Aluno> getAlunos() { return alunos; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }

    public List<Turma> getTurmas() { return turmas; }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
    
    public Turma buscarTurmaPorCodigo(String codigo) {
        return turmas.stream()
                .filter(turma -> turma.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
