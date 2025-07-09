package com.mycompany.trabalho02oo.models;

import com.mycompany.trabalho02oo.validators.ValidadorPreRequisito;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que define as propriedades e comportamentos comuns a todas as disciplinas,
 * como código, nome, carga horária semanal, e listas de validadores de pré-requisitos e co-requisitos.
 */
public abstract class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria; // carga horária semanal
    private List<ValidadorPreRequisito> validadoresPreRequisito;
    private List<Disciplina> coRequisitos; // disciplinas que devem ser cursadas no mesmo semestre
    
    public Disciplina(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.validadoresPreRequisito = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
    }
    
    // Método abstrato que define a prioridade para resolução de conflitos de horário
    public abstract int getPrioridade();
    
    public void adicionarValidadorPreRequisito(ValidadorPreRequisito validador) {
        validadoresPreRequisito.add(validador);
    }
    
    public void adicionarCoRequisito(Disciplina disciplina) {
        if (!coRequisitos.contains(disciplina)) {
            coRequisitos.add(disciplina);
        }
    }
    
    public void removerCoRequisito(Disciplina disciplina) {
        coRequisitos.remove(disciplina);
    }
    
    // Getters
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
    
    public List<ValidadorPreRequisito> getValidadoresPreRequisito() {
        return new ArrayList<>(validadoresPreRequisito);
    }
    
    public List<Disciplina> getCoRequisitos() {
        return new ArrayList<>(coRequisitos);
    }
}