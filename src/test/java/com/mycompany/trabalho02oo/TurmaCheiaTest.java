package com.mycompany.trabalho02oo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class TurmaCheiaTest {

    @Test
    public void testTurmaCheia() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT101A", disciplina1, "Prof. Silva", 0, "Segunda-feira, 14h - 16h");

        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        assertEquals(0, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(1, relatorio.getQuantidadeTurmasRejeitadas());
        assertTrue(relatorio.getTurmasRejeitadas().get(0).getMotivo().contains("vagas"));
    }
}
