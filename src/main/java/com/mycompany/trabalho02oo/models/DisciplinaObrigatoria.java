package com.mycompany.trabalho02oo.models;

/**
 * Representa uma disciplina obrigatória do currículo.
 * Tem a maior precedência em conflitos de horário.
 */
public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 1; // Maior prioridade (1 = mais alta)
    }
}
