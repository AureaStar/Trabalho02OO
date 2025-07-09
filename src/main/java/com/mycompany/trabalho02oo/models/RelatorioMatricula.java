package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o resultado da simulação de matrícula, 
 * incluindo disciplinas aceitas, rejeitadas e seus motivos.
 */
public class RelatorioMatricula {
    private String matriculaAluno;
    private List<ItemRelatorio> itensRelatorio;
    
    public RelatorioMatricula(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
        this.itensRelatorio = new ArrayList<>();
    }
    
    public void adicionarItem(Turma turma, StatusMatricula status, String motivo) {
        itensRelatorio.add(new ItemRelatorio(turma, status, motivo));
    }
    
    public String getMatriculaAluno() {
        return matriculaAluno;
    }
    
    public List<ItemRelatorio> getItensRelatorio() {
        return new ArrayList<>(itensRelatorio);
    }
    
    public static class ItemRelatorio {
        private Turma turma;
        private StatusMatricula status;
        private String motivo;
        
        public ItemRelatorio(Turma turma, StatusMatricula status, String motivo) {
            this.turma = turma;
            this.status = status;
            this.motivo = motivo;
        }
        
        public Turma getTurma() {
            return turma;
        }
        
        public StatusMatricula getStatus() {
            return status;
        }
        
        public String getMotivo() {
            return motivo;
        }
    }
    
    public enum StatusMatricula {
        ACEITA, REJEITADA
    }
}
