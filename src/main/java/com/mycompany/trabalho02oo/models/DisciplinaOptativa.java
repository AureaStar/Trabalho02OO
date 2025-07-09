package com.mycompany.trabalho02oo.models;

/**
 * Representa uma disciplina optativa.
 * Tem a menor precedência em conflitos de horário.
 */
public class DisciplinaOptativa extends Disciplina {
    
    public DisciplinaOptativa(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 3; // Menor prioridade (3 = mais baixa)
    } 
}