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
        // Implementation later - check if student has minimum credits
        return false;
    }
    
    @Override
    public String getMensagemErro() {
        // Implementation later
        return "";
    }
}
