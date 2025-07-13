package com.mycompany.trabalho02oo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class CoRequisitoTest {

    @Test
    public void testCoRequisito() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC001", "Algoritmos I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC002", "Algoritmos Pratica", 60);
        sistemaAcademico.addCoRequisito("DCC002", "DCC001");
        Turma turma1 = sistemaAcademico.cadastrarTurma("DCC001A", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        Turma turma2 = sistemaAcademico.cadastrarTurma("DCC002A", disciplina2, "Prof. Silva", 30, "Segunda-feira, 10h - 12h");

        // Registrar ambas as turmas (co-requisito atendido)
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma2);
        
        // Simular a matricula
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        // Verificar se ambas foram aceitas
        assertEquals(2, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(0, relatorio.getQuantidadeTurmasRejeitadas());
    }

    @Test
    public void testCoRequisitoNaoAtendido() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC001", "Algoritmos I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC002", "Algoritmos Pratica", 60);
        sistemaAcademico.addCoRequisito("DCC002", "DCC001");
        Turma turma1 = sistemaAcademico.cadastrarTurma("DCC002A", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        // Registrar apenas uma turma (co-requisito nao atendido)
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        
        // Simular a matricula
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        // Verificar se foi rejeitada por co-requisito
        assertEquals(0, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(1, relatorio.getQuantidadeTurmasRejeitadas());
        assertTrue(relatorio.getTurmasRejeitadas().get(0).getMotivo().contains("co-requisito"));
    }
}
