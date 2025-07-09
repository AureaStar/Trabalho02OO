package com.mycompany.trabalho02oo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitarios para a classe Historico
 */
public class HistoricoTest {
    
    private Aluno aluno;
    private Historico historico;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria prog1;
    private DisciplinaEletiva algoritmos;
    
    @BeforeEach
    void setUp() {
        // Criar aluno
        aluno = new Aluno("Joao Silva", "202401001", 24);
        
        // Criar disciplinas
        calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        prog1 = new DisciplinaObrigatoria("INF001", "Programacao I", 6);
        algoritmos = new DisciplinaEletiva("INF002", "Algoritmos", 4);
        
        // Adicionar disciplinas cursadas
        aluno.adicionarDisciplinaCursada(calculo1, 85.0);
        aluno.adicionarDisciplinaCursada(prog1, 55.0); // Reprovado
        aluno.adicionarDisciplinaCursada(algoritmos, 75.0);
        
        // Criar historico
        historico = new Historico(aluno);
    }
    
    @Test
    @DisplayName("Deve verificar se aluno foi aprovado em disciplina com nota >= 60")
    void testeFoiAprovado() {
        assertTrue(historico.foiAprovado(calculo1), "Aluno deve estar aprovado em Calculo I com nota 85");
        assertFalse(historico.foiAprovado(prog1), "Aluno deve estar reprovado em Programacao I com nota 55");
        assertTrue(historico.foiAprovado(algoritmos), "Aluno deve estar aprovado em Algoritmos com nota 75");
    }
    
    @Test
    @DisplayName("Deve verificar creditos minimos corretamente")
    void testeTemCreditosMinimos() {
        // Creditos aprovados: Calculo I (4) + Algoritmos (4) = 8
        assertTrue(historico.temCreditosMinimos(8), "Aluno deve ter pelo menos 8 creditos");
        assertTrue(historico.temCreditosMinimos(4), "Aluno deve ter pelo menos 4 creditos");
        assertFalse(historico.temCreditosMinimos(10), "Aluno nao deve ter 10 creditos");
    }
    
    @Test
    @DisplayName("Deve gerar relatorio resumido sem erros")
    void testeGerarRelatorioResumo() {
        String relatorio = historico.gerarRelatorioResumo();
        
        assertNotNull(relatorio, "Relatorio nao deve ser nulo");
        assertFalse(relatorio.isEmpty(), "Relatorio nao deve estar vazio");
        assertTrue(relatorio.contains("HISTORICO ACADEMICO"), "Relatorio deve conter titulo");
        assertTrue(relatorio.contains(aluno.getNome()), "Relatorio deve conter nome do aluno");
        assertTrue(relatorio.contains(aluno.getMatricula()), "Relatorio deve conter matricula do aluno");
    }
    
    @Test
    @DisplayName("Deve retornar numero de tentativas corretamente")
    void testeGetNumeroTentativas() {
        // No modelo atual, sempre retorna 1
        assertEquals(1, historico.getNumeroTentativas(calculo1));
        assertEquals(1, historico.getNumeroTentativas(prog1));
        assertEquals(1, historico.getNumeroTentativas(algoritmos));
    }
}
