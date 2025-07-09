package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitarios para a classe ValidadorSimples
 */
public class ValidadorSimplesTest {
    
    private ValidadorSimples validador;
    private Aluno aluno;
    private DisciplinaObrigatoria preRequisito;
    private DisciplinaObrigatoria disciplina;
    
    @BeforeEach
    void setUp() {
        preRequisito = new DisciplinaObrigatoria("MAT001", "Calculo I", 4);
        validador = new ValidadorSimples(preRequisito);
        
        aluno = new Aluno("Joao Silva", "202401001", 20);
        disciplina = new DisciplinaObrigatoria("MAT002", "Calculo II", 4);
    }
    
    @Test
    @DisplayName("Deve validar quando aluno foi aprovado no pre-requisito")
    void testeValidarComAprovacao() {
        // Adicionar pre-requisito com nota de aprovacao
        aluno.adicionarDisciplinaCursada(preRequisito, 75.0);
        
        boolean resultado = validador.validar(aluno, disciplina);
        assertTrue(resultado, "Deve validar quando aluno foi aprovado no pre-requisito");
    }
    
    @Test
    @DisplayName("Deve falhar quando aluno foi reprovado no pre-requisito")
    void testeValidarComReprovacao() {
        // Adicionar pre-requisito com nota de reprovacao
        aluno.adicionarDisciplinaCursada(preRequisito, 45.0);
        
        boolean resultado = validador.validar(aluno, disciplina);
        assertFalse(resultado, "Nao deve validar quando aluno foi reprovado no pre-requisito");
    }
    
    @Test
    @DisplayName("Deve falhar quando aluno nao cursou o pre-requisito")
    void testeValidarSemCursarPreRequisito() {
        // Nao adicionar o pre-requisito ao historico
        
        boolean resultado = validador.validar(aluno, disciplina);
        assertFalse(resultado, "Nao deve validar quando aluno nao cursou o pre-requisito");
    }
    
    @Test
    @DisplayName("Deve validar com nota exatamente 60 (limite de aprovacao)")
    void testeValidarComNotaLimite() {
        // Adicionar pre-requisito com nota exatamente no limite
        aluno.adicionarDisciplinaCursada(preRequisito, 60.0);
        
        boolean resultado = validador.validar(aluno, disciplina);
        assertTrue(resultado, "Deve validar com nota exatamente 60 (limite de aprovacao)");
    }
    
    @Test
    @DisplayName("Deve falhar com nota abaixo do limite")
    void testeValidarComNotaAbaixoLimite() {
        // Adicionar pre-requisito com nota abaixo do limite
        aluno.adicionarDisciplinaCursada(preRequisito, 59.9);
        
        boolean resultado = validador.validar(aluno, disciplina);
        assertFalse(resultado, "Nao deve validar com nota abaixo do limite de aprovacao");
    }
    
    @Test
    @DisplayName("Deve obter mensagem de erro correta")
    void testeObterMensagemErro() {
        String mensagem = validador.obterMensagemErro();
        
        assertNotNull(mensagem, "Mensagem de erro nao deve ser nula");
        assertTrue(mensagem.contains("Pre-requisito nao cumprido"), 
                  "Mensagem deve indicar pre-requisito nao cumprido");
        assertTrue(mensagem.contains(preRequisito.getNome()), 
                  "Mensagem deve conter nome da disciplina pre-requisito");
        assertTrue(mensagem.contains(preRequisito.getCodigo()), 
                  "Mensagem deve conter codigo da disciplina pre-requisito");
    }
}
