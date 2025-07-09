package com.mycompany.trabalho02oo;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;
import com.mycompany.trabalho02oo.validators.*;
import com.mycompany.trabalho02oo.exceptions.*;

/**
 * Classe principal que demonstra o funcionamento do sistema de simulacao de matricula
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Simulacao de Matricula Academica ===\n");
        
        try {
            // Demonstracao do sistema
            demonstrarSistema();
        } catch (Exception e) {
            System.err.println("Erro durante a execucao: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrarSistema() throws MatriculaException {
        // Criar sistema academico
        SistemaAcademico sistema = new SistemaAcademico();
        RelatorioSimulacao relatorio = new RelatorioSimulacao();
        
        // Criar disciplinas
        System.out.println("1. Criando disciplinas...");
        criarDisciplinas(sistema);
        
        // Criar turmas
        System.out.println("2. Criando turmas...");
        criarTurmas(sistema);
        
        // Criar aluno
        System.out.println("3. Criando aluno...");
        Aluno aluno = criarAluno(sistema);
        
        // Demonstrar matricula
        System.out.println("4. Simulando processo de matricula...");
        simularMatricula(sistema, aluno, relatorio);
        
        // Gerar relatorio final
        System.out.println("\n5. Relatorio final:");
        relatorio.exibirRelatorioAluno(aluno);
    }
    
    private static void criarDisciplinas(SistemaAcademico sistema) {
        // Disciplinas obrigatorias
        DisciplinaObrigatoria calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        DisciplinaObrigatoria calculo2 = new DisciplinaObrigatoria("MAT002", "Calculo II", 4);
        DisciplinaObrigatoria prog1 = new DisciplinaObrigatoria("INF001", "Programacao I", 6);
        DisciplinaObrigatoria prog2 = new DisciplinaObrigatoria("INF002", "Programacao II", 6);
        
        // Adicionar pre-requisito: Calculo II precisa de Calculo I
        ValidadorSimples validadorCalculo = new ValidadorSimples(calculo1);
        calculo2.adicionarValidadorPreRequisito(validadorCalculo);
        
        // Adicionar pre-requisito: Programacao II precisa de Programacao I
        ValidadorSimples validadorProg = new ValidadorSimples(prog1);
        prog2.adicionarValidadorPreRequisito(validadorProg);
        
        // Disciplinas eletivas
        DisciplinaEletiva ia = new DisciplinaEletiva("INF101", "Inteligencia Artificial", 4);
        DisciplinaEletiva bd = new DisciplinaEletiva("INF102", "Banco de Dados", 4);
        
        // Disciplinas optativas
        DisciplinaOptativa musica = new DisciplinaOptativa("ART001", "Musica", 2);
        DisciplinaOptativa filosofia = new DisciplinaOptativa("FIL001", "Filosofia", 2);
        
        // Adicionar disciplinas ao sistema
        sistema.adicionarDisciplina(calculo1);
        sistema.adicionarDisciplina(calculo2);
        sistema.adicionarDisciplina(prog1);
        sistema.adicionarDisciplina(prog2);
        sistema.adicionarDisciplina(ia);
        sistema.adicionarDisciplina(bd);
        sistema.adicionarDisciplina(musica);
        sistema.adicionarDisciplina(filosofia);
        
        System.out.println("   - 8 disciplinas criadas com sucesso!");
    }
    
    private static void criarTurmas(SistemaAcademico sistema) {
        // Buscar disciplinas
        Disciplina calculo1 = sistema.buscarDisciplina("MAT001");
        Disciplina prog1 = sistema.buscarDisciplina("INF001");
        Disciplina ia = sistema.buscarDisciplina("INF101");
        Disciplina musica = sistema.buscarDisciplina("ART001");
        
        // Criar turmas
        Turma turmaCalculo1 = new Turma("T001", calculo1, "Prof. Silva", 30, "Segunda 08:00-12:00");
        Turma turmaProg1 = new Turma("T002", prog1, "Prof. Santos", 25, "Terca 14:00-18:00");
        Turma turmaIA = new Turma("T003", ia, "Prof. Costa", 20, "Quarta 08:00-12:00");
        Turma turmaMusica = new Turma("T004", musica, "Prof. Oliveira", 15, "Quinta 16:00-18:00");
        
        // Adicionar turmas ao sistema
        sistema.adicionarTurma(turmaCalculo1);
        sistema.adicionarTurma(turmaProg1);
        sistema.adicionarTurma(turmaIA);
        sistema.adicionarTurma(turmaMusica);
        
        System.out.println("   - 4 turmas criadas com sucesso!");
    }
    
    private static Aluno criarAluno(SistemaAcademico sistema) {
        Aluno aluno = new Aluno("Joao Silva", "202501001", 20); // 20 horas maximas
        
        // Adicionar historico (disciplinas ja cursadas)
        Disciplina calculo1 = sistema.buscarDisciplina("MAT001");
        if (calculo1 != null) {
            aluno.adicionarDisciplinaCursada(calculo1, 85.0); // Nota 85 - aprovado
        }
        
        sistema.adicionarAluno(aluno);
        System.out.println("   - Aluno " + aluno.getNome() + " criado com matricula " + aluno.getMatricula());
        
        return aluno;
    }
    
    private static void simularMatricula(SistemaAcademico sistema, Aluno aluno, RelatorioSimulacao relatorio) {
        System.out.println("\n   Tentando matricular o aluno nas turmas:");
        
        try {
            // Tentar matricular em Programacao I
            sistema.matricularAluno(aluno.getMatricula(), "T002");
            System.out.println("   * Matricula em Programacao I realizada com sucesso!");
            
        } catch (MatriculaException e) {
            System.out.println("   X Erro na matricula em Programacao I: " + e.getMessage());
        }
        
        try {
            // Tentar matricular em IA (pode dar conflito de horario)
            sistema.matricularAluno(aluno.getMatricula(), "T003");
            System.out.println("   * Matricula em IA realizada com sucesso!");
            
        } catch (MatriculaException e) {
            System.out.println("   X Erro na matricula em IA: " + e.getMessage());
        }
        
        try {
            // Tentar matricular em Musica
            sistema.matricularAluno(aluno.getMatricula(), "T004");
            System.out.println("   * Matricula em Musica realizada com sucesso!");
            
        } catch (MatriculaException e) {
            System.out.println("   X Erro na matricula em Musica: " + e.getMessage());
        }
    }
}
