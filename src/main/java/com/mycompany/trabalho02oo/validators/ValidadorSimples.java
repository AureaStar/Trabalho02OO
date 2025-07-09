package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    
    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        // Implementation later
        return false;
    }
    
    @Override
    public String getMensagemErro() {
        // Implementation later
        return "";
    }
}
