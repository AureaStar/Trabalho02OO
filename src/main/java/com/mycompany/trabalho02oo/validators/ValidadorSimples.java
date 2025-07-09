package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    private Disciplina disciplinaPreRequisito;
    
    public ValidadorSimples(Disciplina disciplinaPreRequisito) {
        this.disciplinaPreRequisito = disciplinaPreRequisito;
    }
    
    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        // Verifica se o aluno cumpriu o pré-requisito (nota >= 60)
        return aluno.cumpriuPreRequisito(disciplinaPreRequisito);
    }
    
    @Override
    public String getMensagemErro() {
        return "Pré-requisito não cumprido: " + disciplinaPreRequisito.getNome() + 
               " (código: " + disciplinaPreRequisito.getCodigo() + ")";
    }
    
    public Disciplina getDisciplinaPreRequisito() {
        return disciplinaPreRequisito;
    }
}
