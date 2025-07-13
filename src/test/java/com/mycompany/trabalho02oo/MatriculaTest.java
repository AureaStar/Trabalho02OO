package com.mycompany.trabalho02oo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class MatriculaTest {

    @Test
    public void testMatriculaBemSucedida() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        aluno1.adicionarDisciplinaCursada(disciplina1, 62);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102A", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        // Registrar a turma que o aluno pretende cursar
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        
        // Simular a matricula
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        // Verificar se a matricula foi bem-sucedida
        assertEquals(1, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(0, relatorio.getQuantidadeTurmasRejeitadas());
        assertTrue(relatorio.getTurmasAceitas().get(0).getMotivo().contains("sucesso"));
    }
}
