package com.mycompany.trabalho02oo;

import com.mycompany.trabalho02oo.controllers.DataBase;
import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class App {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.inicializaSistema();
        SistemaAcademico sistemaAcademico = dataBase.getSistemaAcademico();

        Aluno aluno1 = sistemaAcademico.getAlunos().get(0);
        
        for(Turma turma : sistemaAcademico.getTurmas()) {
            try {
                sistemaAcademico.registrarTurmasEmAluno(aluno1, turma);
            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao registrar turma " + turma.getCodigo() + ": " + e.getMessage());
            }
        }

        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        System.out.println(relatorio.gerarRelatorioCompleto());

        System.out.println("Aluno: " + aluno1.getNome() + " (" + aluno1.getMatricula() + ")");
        System.out.println("Disciplinas Planejadas:");

        for(Turma turma : aluno1.getPlanejamentoFuturo()) {
            System.out.println("Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina().getNome() + ", Professor: " + turma.getProfessor() + ", Horario: " + turma.getHorario());
        }
    }
}
