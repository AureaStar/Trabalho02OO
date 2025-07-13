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
        
        try {
            relatorio.exportarCSV("relatorio_simulacao.csv");
            System.out.println("Relatorio exportado para CSV: relatorio_simulacao.csv");
            
            relatorio.exportarJSON("relatorio_simulacao.json");
            System.out.println("Relatorio exportado para JSON: relatorio_simulacao.json");
        } catch (Exception e) {
            System.err.println("Erro ao exportar relatorio: " + e.getMessage());
        }

        System.out.println("Aluno: " + aluno1.getNome() + " (" + aluno1.getMatricula() + ")");
        System.out.println("Disciplinas Planejadas:");

        for(Turma turma : aluno1.getPlanejamentoFuturo()) {
            System.out.println("Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina().getNome() + ", Professor: " + turma.getProfessor() + ", Horario: " + turma.getHorario());
        }
    }
}
