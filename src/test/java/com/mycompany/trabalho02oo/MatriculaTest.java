package com.mycompany.trabalho02oo;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;

public class MatriculaTest {

    @Test
    public void testMatriculaBemSucedida() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        aluno1.adicionarDisciplinaCursada(disciplina1, 62);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
        } catch (MatriculaException e) {
            e.printStackTrace();
            org.junit.Assert.fail("Matricula não deveria ter falhado: " + e.getMessage());
        }
    }
}
