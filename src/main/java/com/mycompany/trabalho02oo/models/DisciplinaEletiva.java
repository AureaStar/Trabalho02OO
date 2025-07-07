package com.mycompany.trabalho02oo.models;

public class DisciplinaEletiva extends Disciplina {
    public DisciplinaEletiva(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 2;
    }
}
