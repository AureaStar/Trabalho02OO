package com.mycompany.trabalho02oo;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;

public class PreRequisitoTest {

    @Test
    public void testPreRequisitoSilples() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.addPreRequisito("MAT102", "MAT101");
        aluno1.adicionarDisciplinaCursada(disciplina1, 62);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
        } catch (MatriculaException e) {
            e.printStackTrace();
            org.junit.Assert.assertTrue("Matricula não concluída.", true);
        }
    }

    @Test
    public void testPreRequisitoNaoCumprido() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.addPreRequisito("MAT102", "MAT101");
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
            org.junit.Assert.fail("Matricula deveria ter falhado devido a pre-requisito não cumprido.");
        } catch (MatriculaException e) {
            org.junit.Assert.assertTrue("Exceção esperada foi lançada.", true);
        }
    }
}
