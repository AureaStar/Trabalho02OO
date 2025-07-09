package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;
import java.util.List;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    private List<ValidadorPreRequisito> validadores;
    
    public ValidadorLogicoAND(List<ValidadorPreRequisito> validadores) {
        this.validadores = validadores;
    }
    
    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        // Implementation later - all validators must return true
        return false;
    }
    
    @Override
    public String getMensagemErro() {
        // Implementation later
        return "";
    }
}
