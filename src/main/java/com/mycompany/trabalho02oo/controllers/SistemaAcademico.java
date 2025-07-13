package com.mycompany.trabalho02oo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.trabalho02oo.exceptions.CargaHorariaExcedidaException;
import com.mycompany.trabalho02oo.exceptions.CoRequisitoNaoAtendidoException;
import com.mycompany.trabalho02oo.exceptions.ConflitoDeHorarioException;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
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

    public Aluno cadastrarAluno(String nome, String matricula) {
        Aluno aluno = new Aluno(nome, matricula);

        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
            return aluno;
        }

        return null;
    }

    public Disciplina cadastrarDisciplinaObrigatoria(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaObrigatoria(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            return disciplina;
        }

        return null;
    }

    public Disciplina cadastrarDisciplinaEletiva(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaEletiva(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            return disciplina;
        }

        return null;
    }

    public Disciplina cadastrarDisciplinaOptativa(String codigo, String nome, int cargaHoraria) {
        Disciplina disciplina = new DisciplinaOptativa(codigo, nome, cargaHoraria);

        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            return disciplina;
        }

        return null;
    }

    public Turma cadastrarTurma(String codigo, Disciplina disciplina, String professor, int capacidadeMaxima, String horario) {
        Turma turma = new Turma(codigo, disciplina, capacidadeMaxima, professor, horario);

        if (turma != null && !turmas.contains(turma)) {
            turmas.add(turma);
            return turma;
        }

        return null;
    }

    public void matricularAlunoEmTurma(Aluno aluno, Turma turma) throws MatriculaException {
        if (!alunos.contains(aluno)) {
            throw new IllegalArgumentException("Aluno nao encontrado no sistema.");
        }
        
        if (!turmas.contains(turma)) {
            throw new IllegalArgumentException("Turma nao encontrada no sistema.");
        }
        
        if (aluno.getPlanejamentoFuturo().contains(turma)) {
            throw new IllegalArgumentException("Aluno ja esta planejado para esta turma.");
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
        
        verificarPreRequisitos(aluno, turma.getDisciplina());
        verificarCoRequisitos(aluno, turma.getDisciplina());
        
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

    public void verificarPreRequisitos(Aluno aluno, Disciplina disciplina) throws PreRequisitoNaoCumpridoException {
        for (Disciplina preRequisito : disciplina.getPreRequisitos()) {
            if (!aluno.cumpriuPreRequisito(preRequisito)) {
                throw new PreRequisitoNaoCumpridoException("O aluno nao cumpriu o pre-requisito: " + 
                    preRequisito.getNome() + " para a disciplina " + disciplina.getNome());
            }
        }
    }

    public void verificarCoRequisitos(Aluno aluno, Disciplina disciplina) throws CoRequisitoNaoAtendidoException {
        for (Disciplina coRequisito : disciplina.getCoRequisitos()) {
            if(aluno.getPlanejamentoFuturo().stream()
                .noneMatch(turma -> turma.getDisciplina().equals(coRequisito))) {
                throw new CoRequisitoNaoAtendidoException("O aluno precisa estar planejado para o co-requisito: " +
                    coRequisito.getNome() + " para a disciplina " + disciplina.getNome());
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

    public void addPreRequisito(String codigoDisciplina, String codigoPreRequisito) {
        Disciplina disciplina = buscarDisciplinaPorCodigo(codigoDisciplina);
        Disciplina preRequisito = buscarDisciplinaPorCodigo(codigoPreRequisito);
        
        if (disciplina == null || preRequisito == null) {
            throw new IllegalArgumentException("Disciplina ou pre-requisito nao encontrado.");
        }
        
        disciplina.addPreRequisito(preRequisito);
    }

    public void addCoRequisito(String codigoDisciplina, String codigoCoRequisito) {
        Disciplina disciplina = buscarDisciplinaPorCodigo(codigoDisciplina);
        Disciplina coRequisito = buscarDisciplinaPorCodigo(codigoCoRequisito);
        
        if (disciplina == null || coRequisito == null) {
            throw new IllegalArgumentException("Disciplina ou co-requisito nao encontrado.");
        }
        
        disciplina.addCoRequisito(coRequisito);
    }
}
