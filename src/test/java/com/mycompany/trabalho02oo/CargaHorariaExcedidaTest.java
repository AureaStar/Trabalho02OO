package com.mycompany.trabalho02oo;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;

public class CargaHorariaExcedidaTest {

    @Test
    public void testCargaHorariaExcedida() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 200);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 180);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT101", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        Turma turma2 = sistemaAcademico.cadastrarTurma("MAT102", disciplina2, "Prof. Souza", 30, "Terça-feira, 14h - 16h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma2);
            org.junit.Assert.fail("Matricula deveria ter falhado devido a carga horária excedida.");
        } catch (MatriculaException e) {
            org.junit.Assert.assertTrue("Exceção esperada foi lançada.", true);
        }
    }
}
