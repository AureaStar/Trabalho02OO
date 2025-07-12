package com.mycompany.trabalho02oo.models;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.exceptions.TurmaCheiaException;

public class Turma {
    private String codigo;
    private int capacidadeMaxima;
    private String professor;
    private String horario;
    private Disciplina disciplina;
    private int alunosMatriculados;
    private List<Aluno> alunos;

    public Turma(String codigo, Disciplina disciplina, int capacidadeMaxima, String professor, String horario) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.capacidadeMaxima = capacidadeMaxima;
        this.professor = professor;
        this.horario = horario;
        this.alunosMatriculados = 0;
        this.alunos = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }

    public Disciplina getDisciplina() { return disciplina; }

    public String getHorario() { return horario; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }

    public String getProfessor() { return professor; }

    public void reservarVaga(Aluno aluno) throws MatriculaException {
        if (!alunos.contains(aluno)) {
            if (alunosMatriculados < capacidadeMaxima) {
                alunosMatriculados++;
                alunos.add(aluno);
                aluno.adicionarTurmaPlanejamento(this);
            } else {
                throw new TurmaCheiaException("Não há vagas disponíveis na turma.");
            }
        } else {
            throw new IllegalArgumentException("Aluno já cadastrado na turma.");
        }
    }
}
