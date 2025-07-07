package com.mycompany.trabalho02oo.models;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 3;
    }
}
