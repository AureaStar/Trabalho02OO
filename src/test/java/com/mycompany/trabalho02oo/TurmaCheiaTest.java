package com.mycompany.trabalho02oo;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;

public class TurmaCheiaTest {

    @Test
    public void testTurmaCheia() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT101", disciplina1, "Prof. Silva", 2, "Segunda-feira, 14h - 16h");

        try {
            Aluno aluno2 = sistemaAcademico.cadastrarAluno("Estudante2", "202310445");
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
            sistemaAcademico.matricularAlunoEmTurma(aluno2, turma1);
        } catch (MatriculaException e) {
            org.junit.Assert.fail("Matricula deveria ter falhado devido a turma cheia.");
        }
    }
}
