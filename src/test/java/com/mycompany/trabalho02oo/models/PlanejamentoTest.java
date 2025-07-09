package com.mycompany.trabalho02oo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitarios para a classe Planejamento
 */
public class PlanejamentoTest {
    
    private Aluno aluno;
    private Planejamento planejamento;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private DisciplinaObrigatoria estruturas;
    private DisciplinaEletiva web;
    private Turma turmaCalculo2;
    private Turma turmaEstruturas;
    private Turma turmaWeb;
    
    @BeforeEach
    void setUp() {
        // Criar aluno
        aluno = new Aluno("Maria Santos", "202401002", 20);
        
        // Criar disciplinas
        calculo1 = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        calculo2 = new DisciplinaObrigatoria("MAT002", "Calculo II", 4);
        estruturas = new DisciplinaObrigatoria("INF003", "Estruturas de Dados", 6);
        web = new DisciplinaEletiva("INF004", "Desenvolvimento Web", 4);
        
        // Criar turmas
        turmaCalculo2 = new Turma("T001", calculo2, "Prof. Ana", 30, "Segunda 08:00-10:00");
        turmaEstruturas = new Turma("T002", estruturas, "Prof. Carlos", 25, "Terca 10:00-12:00");
        turmaWeb = new Turma("T003", web, "Prof. Pedro", 20, "Quarta 14:00-16:00");
        
        // Adicionar disciplina ja cursada
        aluno.adicionarDisciplinaCursada(calculo1, 80.0);
        
        // Criar planejamento
        planejamento = new Planejamento(aluno);
    }
    
    @Test
    @DisplayName("Deve adicionar turma ao proximo semestre")
    void testeAdicionarTurmaProximoSemestre() {
        planejamento.adicionarTurmaProximoSemestre(turmaCalculo2);
        
        // Verificar se foi adicionada ao planejamento do aluno
        assertTrue(aluno.getPlanejamentoFuturo().contains(turmaCalculo2), 
                  "Turma deve estar no planejamento futuro do aluno");
    }
    
    @Test
    @DisplayName("Deve remover turma do proximo semestre")
    void testeRemoverTurmaProximoSemestre() {
        // Adicionar primeiro
        planejamento.adicionarTurmaProximoSemestre(turmaCalculo2);
        assertTrue(aluno.getPlanejamentoFuturo().contains(turmaCalculo2));
        
        // Remover
        planejamento.removerTurmaProximoSemestre(turmaCalculo2);
        assertFalse(aluno.getPlanejamentoFuturo().contains(turmaCalculo2), 
                   "Turma nao deve estar mais no planejamento futuro do aluno");
    }
    
    @Test
    @DisplayName("Deve adicionar turma a semestre especifico")
    void testeAdicionarTurmaSemestre() {
        planejamento.adicionarTurmaSemestre(2, turmaWeb);
        
        // Verificar se foi adicionada internamente (nao ha getter publico para verificar)
        // Entao vamos testar atraves do relatorio
        String relatorio = planejamento.gerarRelatorioPlanejamento();
        assertTrue(relatorio.contains("Semestre 2"), "Relatorio deve mencionar Semestre 2");
    }
    
    @Test
    @DisplayName("Deve adicionar observacoes")
    void testeAdicionarObservacao() {
        String observacao = "Verificar pre-requisitos para Estruturas de Dados";
        planejamento.adicionarObservacao(observacao);
        
        String relatorio = planejamento.gerarRelatorioPlanejamento();
        assertTrue(relatorio.contains(observacao), "Relatorio deve conter a observacao adicionada");
    }
    
    @Test
    @DisplayName("Deve validar carga horaria do semestre")
    void testeValidarCargaHorariaSemestre() {
        // Adicionar turmas ao proximo semestre (semestre 1)
        planejamento.adicionarTurmaProximoSemestre(turmaCalculo2); // 4h
        planejamento.adicionarTurmaProximoSemestre(turmaEstruturas); // 6h
        // Total: 10h, que e menor que o limite de 20h do aluno
        
        assertTrue(planejamento.validarCargaHorariaSemestre(1), 
                  "Carga horaria de 10h deve ser valida para limite de 20h");
    }
    
    @Test
    @DisplayName("Deve gerar relatorio de planejamento")
    void testeGerarRelatorioPlanejamento() {
        // Adicionar algumas turmas e observacoes
        planejamento.adicionarTurmaProximoSemestre(turmaCalculo2);
        planejamento.adicionarTurmaSemestre(2, turmaWeb);
        planejamento.adicionarObservacao("Teste de observacao");
        
        String relatorio = planejamento.gerarRelatorioPlanejamento();
        
        assertNotNull(relatorio, "Relatorio nao deve ser nulo");
        assertFalse(relatorio.isEmpty(), "Relatorio nao deve estar vazio");
        assertTrue(relatorio.contains(aluno.getNome()), "Relatorio deve conter nome do aluno");
        assertTrue(relatorio.contains("Teste de observacao"), "Relatorio deve conter observacao");
    }
    
    @Test
    @DisplayName("Deve calcular total de creditos planejados")
    void testeCalcularTotalCreditosPlanejados() {
        planejamento.adicionarTurmaProximoSemestre(turmaCalculo2); // 4h
        planejamento.adicionarTurmaProximoSemestre(turmaEstruturas); // 6h
        
        int totalCreditos = planejamento.calcularTotalCreditosPlanejados();
        assertEquals(10, totalCreditos, "Total de creditos deve ser 10 (4+6)");
    }
}
