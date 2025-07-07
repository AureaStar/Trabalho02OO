package com.mycompany.trabalho02oo.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private List<Disciplina> coRequisitos;
    
    public Disciplina(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.coRequisitos = new ArrayList<>();
    }
    
    public void addCoRequisito(Disciplina disciplina) {
        coRequisitos.add(disciplina);
    }
    
    public abstract int getPrioridade();
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public List<Disciplina> getCoRequisitos() {
        return new ArrayList<>(coRequisitos);
    }
}