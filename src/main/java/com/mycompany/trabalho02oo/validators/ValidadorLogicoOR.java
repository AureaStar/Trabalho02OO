package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import java.util.List;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private List<ValidadorPreRequisito> validadores;
    
    public ValidadorLogicoOR(List<ValidadorPreRequisito> validadores) {
        this.validadores = validadores;
    }
    
    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        // Implementation later - at least one validator must return true
        return false;
    }
    
    @Override
    public String getMensagemErro() {
        // Implementation later
        return "";
    }
}
