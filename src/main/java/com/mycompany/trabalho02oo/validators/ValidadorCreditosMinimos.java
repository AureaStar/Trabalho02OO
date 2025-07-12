package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;

public class ValidadorCreditosMinimos implements ValidadorPreRequisito {

    private int creditosMinimos;

    public ValidadorCreditosMinimos(int creditosMinimos) {
        this.creditosMinimos = creditosMinimos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return aluno.getCreditosConcluidos() >= creditosMinimos;
    }

    @Override
    public String getMensagemErro() {
        return "O aluno não possui créditos mínimos necessários para a disciplina.";
    }
    
}
