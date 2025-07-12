package com.mycompany.trabalho02oo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.DisciplinaEletiva;
import com.mycompany.trabalho02oo.models.DisciplinaObrigatoria;
import com.mycompany.trabalho02oo.models.DisciplinaOptativa;
import com.mycompany.trabalho02oo.models.Turma;

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
        if (alunos.contains(aluno) && turmas.contains(turma)) {
            turma.reservarVaga(aluno);
        }
    }
   
    public List<Aluno> getAlunos() { return alunos; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }

    public List<Turma> getTurmas() { return turmas; }
}
