package com.mycompany.trabalho02oo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitarios para a classe Turma
 */
public class TurmaTest {
    
    private Turma turma;
    private DisciplinaObrigatoria disciplina;
    private Aluno aluno1;
    private Aluno aluno2;
    
    @BeforeEach
    void setUp() {
        disciplina = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        turma = new Turma("T001", disciplina, "Prof. Silva", 2, "Segunda 08:00-10:00");
        
        aluno1 = new Aluno("Joao Silva", "202401001", 20);
        aluno2 = new Aluno("Maria Santos", "202401002", 20);
    }
    
    @Test
    @DisplayName("Deve criar turma com parametros corretos")
    void testeCriarTurma() {
        assertEquals("T001", turma.getId(), "ID da turma deve ser T001");
        assertEquals(disciplina, turma.getDisciplina(), "Disciplina deve coincidir");
        assertEquals("Prof. Silva", turma.getProfessor(), "Professor deve coincidir");
        assertEquals(2, turma.getVagas(), "Capacidade deve ser 2");
        assertEquals("Segunda 08:00-10:00", turma.getHorario(), "Horario deve coincidir");
        assertEquals(0, turma.getVagasOcupadas(), "Vagas ocupadas deve ser 0 inicialmente");
    }
    
    @Test
    @DisplayName("Deve adicionar aluno com sucesso quando ha vagas")
    void testeAdicionarAlunoComSucesso() {
        boolean resultado = turma.adicionarAluno(aluno1);
        
        assertTrue(resultado, "Deve adicionar aluno com sucesso");
        assertEquals(1, turma.getVagasOcupadas(), "Vagas ocupadas deve ser 1");
        assertTrue(turma.isAlunoMatriculado(aluno1.getMatricula()), 
                  "Aluno deve estar matriculado na turma");
    }
    
    @Test
    @DisplayName("Deve falhar ao adicionar aluno quando turma esta cheia")
    void testeAdicionarAlunoTurmaCheia() {
        // Ocupar todas as vagas
        turma.adicionarAluno(aluno1);
        turma.adicionarAluno(aluno2);
        
        // Tentar adicionar terceiro aluno
        Aluno aluno3 = new Aluno("Pedro Costa", "202401003", 20);
        boolean resultado = turma.adicionarAluno(aluno3);
        
        assertFalse(resultado, "Nao deve adicionar aluno quando turma esta cheia");
        assertEquals(2, turma.getVagasOcupadas(), "Vagas ocupadas deve permanecer 2");
        assertFalse(turma.isAlunoMatriculado(aluno3.getMatricula()), 
                   "Terceiro aluno nao deve estar matriculado");
    }
    
    @Test
    @DisplayName("Deve remover aluno com sucesso")
    void testeRemoverAluno() {
        // Adicionar aluno primeiro
        turma.adicionarAluno(aluno1);
        assertTrue(turma.isAlunoMatriculado(aluno1.getMatricula()));
        
        // Remover aluno
        boolean resultado = turma.removerAluno(aluno1);
        
        assertTrue(resultado, "Deve remover aluno com sucesso");
        assertEquals(0, turma.getVagasOcupadas(), "Vagas ocupadas deve voltar a 0");
        assertFalse(turma.isAlunoMatriculado(aluno1.getMatricula()), 
                   "Aluno nao deve estar mais matriculado");
    }
    
    @Test
    @DisplayName("Deve falhar ao remover aluno nao matriculado")
    void testeRemoverAlunoNaoMatriculado() {
        boolean resultado = turma.removerAluno(aluno1);
        
        assertFalse(resultado, "Nao deve remover aluno que nao esta matriculado");
        assertEquals(0, turma.getVagasOcupadas(), "Vagas ocupadas deve permanecer 0");
    }
    
    @Test
    @DisplayName("Deve verificar vagas disponiveis corretamente")
    void testeTemVagasDisponiveis() {
        assertTrue(turma.temVagasDisponiveis(), "Deve ter vagas disponiveis inicialmente");
        
        // Ocupar uma vaga
        turma.adicionarAluno(aluno1);
        assertTrue(turma.temVagasDisponiveis(), "Deve ainda ter vagas disponiveis");
        
        // Ocupar segunda vaga
        turma.adicionarAluno(aluno2);
        assertFalse(turma.temVagasDisponiveis(), "Nao deve ter vagas disponiveis quando cheia");
    }
    
    @Test
    @DisplayName("Deve calcular vagas restantes corretamente")
    void testeGetVagasRestantes() {
        assertEquals(2, turma.getVagasRestantes(), "Deve ter 2 vagas restantes inicialmente");
        
        turma.adicionarAluno(aluno1);
        assertEquals(1, turma.getVagasRestantes(), "Deve ter 1 vaga restante apos adicionar um aluno");
        
        turma.adicionarAluno(aluno2);
        assertEquals(0, turma.getVagasRestantes(), "Deve ter 0 vagas restantes quando cheia");
    }
    
    @Test
    @DisplayName("Deve verificar se aluno esta matriculado")
    void testeIsAlunoMatriculado() {
        assertFalse(turma.isAlunoMatriculado(aluno1.getMatricula()), 
                   "Aluno nao deve estar matriculado inicialmente");
        
        turma.adicionarAluno(aluno1);
        assertTrue(turma.isAlunoMatriculado(aluno1.getMatricula()), 
                  "Aluno deve estar matriculado apos adicao");
        
        assertFalse(turma.isAlunoMatriculado(aluno2.getMatricula()), 
                   "Segundo aluno nao deve estar matriculado");
    }
    
    @Test
    @DisplayName("Deve calcular utilizacao da turma corretamente")
    void testeGetUtilizacao() {
        assertEquals(0.0, turma.getUtilizacao(), 0.01, "Utilizacao deve ser 0% inicialmente");
        
        turma.adicionarAluno(aluno1);
        assertEquals(50.0, turma.getUtilizacao(), 0.01, "Utilizacao deve ser 50% com um aluno");
        
        turma.adicionarAluno(aluno2);
        assertEquals(100.0, turma.getUtilizacao(), 0.01, "Utilizacao deve ser 100% quando cheia");
    }
}
