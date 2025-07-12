package com.mycompany.trabalho02oo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.exceptions.*;
import com.mycompany.trabalho02oo.validators.*;

/**
 * Testes unitarios para a classe SistemaAcademico
 */
public class SistemaAcademicoTest {
    
    private SistemaAcademico sistema;
    private Aluno aluno;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private Turma turmaCalculo1;
    private Turma turmaCalculo2;
    
    @BeforeEach
    void setUp() {
        sistema = new SistemaAcademico();
        aluno = new Aluno("Joao Silva", "202401001", 20);
        
        // Criar disciplinas
        calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        calculo2 = new DisciplinaObrigatoria("MAT002", "Calculo II", 4);
        
        // Adicionar pre-requisito
        calculo2.adicionarValidadorPreRequisito(new ValidadorSimples(calculo1));
        
        // Criar turmas
        turmaCalculo1 = new Turma("T001", calculo1, "Prof. Ana", 30, "Segunda 08:00-10:00");
        turmaCalculo2 = new Turma("T002", calculo2, "Prof. Carlos", 25, "Terca 10:00-12:00");
        
        // Adicionar ao sistema
        sistema.adicionarAluno(aluno);
        sistema.adicionarDisciplina(calculo1);
        sistema.adicionarDisciplina(calculo2);
        sistema.adicionarTurma(turmaCalculo1);
        sistema.adicionarTurma(turmaCalculo2);
    }
    
    @Test
    @DisplayName("Deve matricular aluno com sucesso quando requisitos sao atendidos")
    void testeMatricularComSucesso() throws MatriculaException {
        // Matricula em disciplina sem pre-requisitos
        sistema.matricularAluno(aluno.getMatricula(), turmaCalculo1.getId());
        
        // Verificar se foi matriculado
        assertTrue(turmaCalculo1.getMatriculasAlunos().contains(aluno.getMatricula()),
                  "Aluno deve estar matriculado na turma");
    }
    
    @Test
    @DisplayName("Deve lancar excecao quando turma nao tem vagas")
    void testeMatricularTurmaSemVagas() {
        // Criar turma com capacidade 0
        Turma turmaSemVagas = new Turma("T999", calculo1, "Prof. Teste", 0, "Sexta 08:00-10:00");
        sistema.adicionarTurma(turmaSemVagas);
        
        assertThrows(MatriculaException.class, () -> {
            sistema.matricularAluno(aluno.getMatricula(), turmaSemVagas.getId());
        }, "Deve lancar excecao quando turma nao tem vagas");
    }
    
    @Test
    @DisplayName("Deve gerar relatorio de simulacao de matricula")
    void testeSimularMatricula() throws MatriculaException {
        // Adicionar turma ao planejamento do aluno
        aluno.adicionarTurmaPlanejamento(turmaCalculo1);
        
        // Simular matricula
        RelatorioMatricula relatorio = sistema.simularMatricula(aluno.getMatricula());
        
        assertNotNull(relatorio, "Relatorio nao deve ser nulo");
        assertEquals(aluno.getMatricula(), relatorio.getMatriculaAluno(), 
                    "Matricula do aluno deve coincidir");
        // Pode estar vazio se nao ha turmas no planejamento
        assertNotNull(relatorio.getItensRelatorio(), "Lista de itens nao deve ser nula");
    }
    
    @Test
    @DisplayName("Deve adicionar e buscar aluno corretamente")
    void testeAdicionarBuscarAluno() throws Exception {
        Aluno novoAluno = new Aluno("Maria Santos", "202401002", 18);
        sistema.adicionarAluno(novoAluno);
        
        Aluno alunoEncontrado = sistema.buscarAluno("202401002");
        assertNotNull(alunoEncontrado, "Aluno deve ser encontrado");
        assertEquals("Maria Santos", alunoEncontrado.getNome(), "Nome deve coincidir");
    }
    
    @Test
    @DisplayName("Deve retornar null ao buscar aluno inexistente")
    void testeBuscarAlunoInexistente() {
        Aluno alunoEncontrado = sistema.buscarAluno("999999999");
        assertNull(alunoEncontrado, "Deve retornar null para aluno inexistente");
    }
}
