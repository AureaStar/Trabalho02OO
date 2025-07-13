package com.mycompany.trabalho02oo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.trabalho02oo.controllers.SistemaAcademico;
import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import com.mycompany.trabalho02oo.models.Turma;
import com.mycompany.trabalho02oo.views.RelatorioSimulacao;

public class CargaHorariaExcedidaTest {

    @Test
    public void testCargaHorariaExcedida() {
        SistemaAcademico sistemaAcademico = new SistemaAcademico();
        Aluno aluno1 = sistemaAcademico.cadastrarAluno("Estudante", "202310444");
        aluno1.setHorasMaximas(200); // Limite baixo para testar
        Disciplina disciplina1 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 150);
        Disciplina disciplina2 = sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 100);
        Turma turma1 = sistemaAcademico.cadastrarTurma("MAT101A", disciplina1, "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        Turma turma2 = sistemaAcademico.cadastrarTurma("MAT102A", disciplina2, "Prof. Souza", 30, "Terca-feira, 14h - 16h");

        // Registrar ambas as turmas (segunda excede limite)
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma1);
        sistemaAcademico.registrarTurmasEmAluno(aluno1, turma2);
        
        // Simular a matricula
        RelatorioSimulacao relatorio = sistemaAcademico.simularMatricula(aluno1);
        
        // Verificar se uma foi aceita e outra rejeitada por carga horaria
        assertEquals(1, relatorio.getQuantidadeTurmasAceitas());
        assertEquals(1, relatorio.getQuantidadeTurmasRejeitadas());
        assertTrue(relatorio.getTurmasRejeitadas().get(0).getMotivo().contains("Carga horaria excedida"));
    }
}
