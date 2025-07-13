package com.mycompany.trabalho02oo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class PreRequisitoTest {

    @Test
    public void testPreRequisitoSimples() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.addPreRequisito("MAT102", "MAT101");
        aluno1.adicionarDisciplinaCursada(disciplina1, 62);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102A", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        assertEquals(1, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(0, relatorio.getQuantidadeTurmasRejeitadas());
    }

    @Test
    public void testPreRequisitoNaoCumprido() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.addPreRequisito("MAT102", "MAT101");
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102A", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        assertEquals(0, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(1, relatorio.getQuantidadeTurmasRejeitadas());
        assertTrue(relatorio.getTurmasRejeitadas().get(0).getMotivo().contains("pre-requisito"));
    }
}
