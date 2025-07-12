package com.mycompany.trabalho02oo.controllers;

import com.mycompany.trabalho02oo.exceptions.PreRequisitoNaoCumpridoException;
import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.validators.ValidadorSimples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PreRequisitoValidacaoTest {
    
    private SistemaAcademico sistema;
    private Aluno aluno;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private Turma turmaCalculo2;
    
    @BeforeEach
    void setUp() {
        sistema = new SistemaAcademico();
        
        aluno = new Aluno("João Silva", "202401001");
        aluno.setHorasMaximas(120);
        sistema.cadastrarAluno("João Silva", "202401001");
        
        calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 60);
        calculo2 = new DisciplinaObrigatoria("MAT002", "Calculo II", 60);
        
        calculo2.addPreRequisito(calculo1);
        calculo2.addValidadorPreRequisito(new ValidadorSimples(calculo1));
        
        turmaCalculo2 = new Turma("T002", calculo2, 30, "Prof. B", "TER 08:00-10:00");
        
        sistema.cadastrarDisciplinaObrigatoria("MAT001", "Calculo I", 60);
        sistema.cadastrarDisciplinaObrigatoria("MAT002", "Calculo II", 60);
        sistema.cadastrarTurma("T002", calculo2, "Prof. B", 30, "TER 08:00-10:00");
    }
    
    @Test
    void testPreRequisitoNaoCumprido() {
        assertThrows(PreRequisitoNaoCumpridoException.class, () -> {
            sistema.matricularAlunoEmTurma(aluno, turmaCalculo2);
        });
    }
    
    @Test
    void testPreRequisitoCumprido() throws Exception {
        aluno.adicionarDisciplinaCursada(calculo1, 80.0);
        
        assertDoesNotThrow(() -> {
            sistema.matricularAlunoEmTurma(aluno, turmaCalculo2);
        });
        
        assertTrue(aluno.getPlanejamentoFuturo().contains(turmaCalculo2));
    }
    
    @Test
    void testPreRequisitoComNotaInsuficiente() {
        aluno.adicionarDisciplinaCursada(calculo1, 50.0);
        
        assertThrows(PreRequisitoNaoCumpridoException.class, () -> {
            sistema.matricularAlunoEmTurma(aluno, turmaCalculo2);
        });
    }
}
