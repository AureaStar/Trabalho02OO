package com.mycompany.trabalho02oo.models;

/**
 * Representa uma disciplina eletiva.
 * Tem precedência média em conflitos de horário.
 */
public class DisciplinaEletiva extends Disciplina {
    public DisciplinaEletiva(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 2; // Prioridade média
    }
}
