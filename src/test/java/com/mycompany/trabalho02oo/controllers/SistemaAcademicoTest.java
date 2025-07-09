package com.mycompany.trabalho02oo.controllers;

import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitarios para a classe SistemaAcademico
 */
public class SistemaAcademicoTest {
    
    private SistemaAcademico sistema;
    private Aluno aluno;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private DisciplinaObrigatoria prog1;
    private Turma turmaCalculo1;
    private Turma turmaCalculo2;
    private Turma turmaProg1;
    
    @BeforeEach
    void setUp() {
        sistema = new SistemaAcademico();
        
        // Criar aluno
        aluno = new Aluno("Joao Silva", "202401001", 20);
        
        // Criar disciplinas
        calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        calculo2 = new DisciplinaObrigatoria("MAT002", "Calculo II", 4);
        prog1 = new DisciplinaObrigatoria("INF001", "Programacao I", 6);
        
        // Criar turmas
        turmaCalculo1 = new Turma("T001", calculo1, "Prof. Ana", 30, "Segunda 08:00-10:00");
        turmaCalculo2 = new Turma("T002", calculo2, "Prof. Carlos", 25, "Terca 10:00-12:00");
        turmaProg1 = new Turma("T003", prog1, "Prof. Pedro", 20, "Quarta 14:00-16:00");
        
        // Adicionar ao sistema
        sistema.adicionarAluno(aluno);
        sistema.adicionarDisciplina(calculo1);
        sistema.adicionarDisciplina(calculo2);
        sistema.adicionarDisciplina(prog1);
        sistema.adicionarTurma(turmaCalculo1);
        sistema.adicionarTurma(turmaCalculo2);
        sistema.adicionarTurma(turmaProg1);
    }
    
    @Test
    @DisplayName("Deve matricular aluno com sucesso quando requisitos sao atendidos")
    void testeMatricularComSucesso() throws MatriculaException {
        // Matricula em disciplina sem pre-requisitos
        boolean resultado = sistema.matricularAluno(aluno.getMatricula(), turmaCalculo1.getId());
        
        assertTrue(resultado, "Matricula deve ser bem-sucedida");
        assertTrue(aluno.getPlanejamentoFuturo().contains(turmaCalculo1), 
                  "Turma deve estar no planejamento do aluno");
    }
    
    @Test
    @DisplayName("Deve lancar excecao quando turma nao tem vagas")
    void testeMatricularTurmaSemVagas() {
        // Criar turma com capacidade 0
        Turma turmaSemVagas = new Turma("T999", calculo1, "Prof. Teste", 0, "Sexta 08:00-10:00");
        sistema.adicionarTurma(turmaSemVagas);
        
        assertThrows(MatriculaException.class, () -> {
            sistema.matricularAluno(aluno.getMatricula(), turmaSemVagas.getId());
        }, "Deve lancar excecao para turma sem vagas");
    }
    
    @Test
    @DisplayName("Deve lancar excecao quando aluno excede carga horaria")
    void testeMatricularExcedeCargaHoraria() {
        // Criar aluno com carga horaria baixa
        Aluno alunoLimitado = new Aluno("Maria", "202401002", 5); // Apenas 5h
        sistema.adicionarAluno(alunoLimitado);
        
        assertThrows(MatriculaException.class, () -> {
            sistema.matricularAluno(alunoLimitado.getMatricula(), turmaCalculo1.getId()); // 4h - OK
            sistema.matricularAluno(alunoLimitado.getMatricula(), turmaProg1.getId()); // +6h = 10h total > 5h limite
        }, "Deve lancar excecao quando excede carga horaria");
    }
    
    @Test
    @DisplayName("Deve buscar aluno por matricula")
    void testeBuscarAluno() {
        Aluno alunoEncontrado = sistema.buscarAluno(aluno.getMatricula());
        
        assertNotNull(alunoEncontrado, "Aluno deve ser encontrado");
        assertEquals(aluno.getMatricula(), alunoEncontrado.getMatricula(), 
                    "Matricula deve coincidir");
        assertEquals(aluno.getNome(), alunoEncontrado.getNome(), 
                    "Nome deve coincidir");
    }
    
    @Test
    @DisplayName("Deve retornar null para aluno inexistente")
    void testeBuscarAlunoInexistente() {
        Aluno alunoInexistente = sistema.buscarAluno("999999999");
        assertNull(alunoInexistente, "Deve retornar null para aluno inexistente");
    }
    
    @Test
    @DisplayName("Deve buscar turma por ID")
    void testeBuscarTurma() {
        Turma turmaEncontrada = sistema.buscarTurma(turmaCalculo1.getId());
        
        assertNotNull(turmaEncontrada, "Turma deve ser encontrada");
        assertEquals(turmaCalculo1.getId(), turmaEncontrada.getId(), 
                    "ID da turma deve coincidir");
        assertEquals(turmaCalculo1.getDisciplina().getCodigo(), 
                    turmaEncontrada.getDisciplina().getCodigo(), 
                    "Disciplina deve coincidir");
    }
    
    @Test
    @DisplayName("Deve retornar null para turma inexistente")
    void testeBuscarTurmaInexistente() {
        Turma turmaInexistente = sistema.buscarTurma("T999");
        assertNull(turmaInexistente, "Deve retornar null para turma inexistente");
    }
    
    @Test
    @DisplayName("Deve listar todas as disciplinas")
    void testeListarDisciplinas() {
        var disciplinas = sistema.listarDisciplinas();
        
        assertNotNull(disciplinas, "Lista de disciplinas nao deve ser nula");
        assertEquals(3, disciplinas.size(), "Deve ter 3 disciplinas cadastradas");
        assertTrue(disciplinas.contains(calculo1), "Deve conter Calculo I");
        assertTrue(disciplinas.contains(calculo2), "Deve conter Calculo II");
        assertTrue(disciplinas.contains(prog1), "Deve conter Programacao I");
    }
    
    @Test
    @DisplayName("Deve listar todas as turmas")
    void testeListarTurmas() {
        var turmas = sistema.listarTurmas();
        
        assertNotNull(turmas, "Lista de turmas nao deve ser nula");
        assertEquals(3, turmas.size(), "Deve ter 3 turmas cadastradas");
        assertTrue(turmas.contains(turmaCalculo1), "Deve conter turma de Calculo I");
        assertTrue(turmas.contains(turmaCalculo2), "Deve conter turma de Calculo II");
        assertTrue(turmas.contains(turmaProg1), "Deve conter turma de Programacao I");
    }
    
    @Test
    @DisplayName("Deve simular matricula sem realizar efetivamente")
    void testeSimularMatricula() {
        RelatorioMatricula relatorio = sistema.simularMatricula(aluno.getMatricula(), 
                                                               turmaCalculo1.getId());
        
        assertNotNull(relatorio, "Relatorio de simulacao nao deve ser nulo");
        assertTrue(relatorio.isMatriculaPossivel(), "Matricula deve ser possivel");
        assertFalse(aluno.getPlanejamentoFuturo().contains(turmaCalculo1), 
                   "Turma nao deve estar no planejamento apos simulacao");
    }
}
