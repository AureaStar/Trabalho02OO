package com.mycompany.trabalho02oo;

import com.mycompany.trabalho02oo.controllers.DataBase;
import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Turma;

public class App {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.inicializaSistema();
        SistemaAcademico sistemaAcademico = dataBase.getSistemaAcademico();

        Aluno aluno1 = sistemaAcademico.getAlunos().get(0);

        for(Turma turma : sistemaAcademico.getTurmas()) {
            try {
                sistemaAcademico.matricularAlunoEmTurma(aluno1, turma);
            } catch (MatriculaException e) {
                System.err.println("Erro ao matricular aluno na turma " + turma.getCodigo() + ": " + e.getMessage());
            }
        }

        System.out.println("Aluno: " + aluno1.getNome() + " (" + aluno1.getMatricula() + ")");
        System.out.println("Disciplinas Matriculadas:");

        for(Turma turma : aluno1.getPlanejamentoFuturo()) {
            System.out.println("Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina().getNome() + ", Professor: " + turma.getProfessor() + ", Horario: " + turma.getHorario());
        }

        System.out.println("Sistema Academico inicializado com sucesso!");
    }
}
