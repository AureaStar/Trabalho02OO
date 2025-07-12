package com.mycompany.trabalho02oo.models;

public class DisciplinaOptativa extends Disciplina {
    
    public DisciplinaOptativa(String codigo, String nome, int cargaHoraria) {
        super(codigo, nome, cargaHoraria);
    }

    @Override
    public int getPrioridade() {
        return 3;
    } 
}