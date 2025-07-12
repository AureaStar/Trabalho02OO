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
        return aluno.cumpriuPreRequisitos(disciplinaPreRequisito);
    }

    @Override
    public String getMensagemErro() {
        return "O aluno não cumpriu os pré-requisitos para a disciplina " + disciplinaPreRequisito.getNome();
    }
    
}
