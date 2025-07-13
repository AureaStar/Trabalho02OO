package com.mycompany.trabalho02oo;

import org.junit.Test;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.exceptions.MatriculaException;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;

public class CoRequisitoTest {

    @Test
    public void testCoRequisito() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC001", "Algoritmos I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC002", "Algoritmos Pratica", 60);
        sistemaAcademico.addCoRequisito("DCC002", "DCC001");
        Turma turma1 = sistemaAcademico.cadastrarTurma("DCC001", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        Turma turma2 = sistemaAcademico.cadastrarTurma("DCC002", disciplina2, "Prof. Silva", 30, "Segunda-feira, 10h - 12h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma2);
            org.junit.Assert.assertTrue("Matricula concluída com sucesso.", true);
        } catch (MatriculaException e) {
            e.printStackTrace();
            org.junit.Assert.fail("Matricula não deveria ter falhado: " + e.getMessage());
        }
    }

    @Test
    public void testCoRequisitoNaoAtendido() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC001", "Algoritmos I", 60);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("DCC002", "Algoritmos Pratica", 60);
        sistemaAcademico.addCoRequisito("DCC002", "DCC001");
        Turma turma1 = sistemaAcademico.cadastrarTurma("DCC002", disciplina2, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");

        try {
            sistemaAcademico.matricularAlunoEmTurma(aluno1, turma1);
            org.junit.Assert.fail("Matricula deveria ter falhado devido a co-requisito não atendido.");
        } catch (MatriculaException e) {
            org.junit.Assert.assertTrue("Exceção esperada foi lançada.", true);
        }
    }
}
