package com.mycompany.trabalho02oo;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.PreRequisitoNaoCumpridoException;
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

        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT102", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        PreRequisitoNaoCumpridoException exception = assertThrows(PreRequisitoNaoCumpridoException.class, () -> {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
        });

        assertTrue(exception.getMessage().contains("Pre-requisito nao cumprido"));
    }

}
