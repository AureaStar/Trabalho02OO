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
        for (ValidadorPreRequisito validador : validadores) {
            if (validador.validar(aluno, disciplina)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getMensagemErro() {
        StringBuilder mensagem = new StringBuilder("O aluno deve cumprir pelo menos um dos pre-requisitos: ");
        for (ValidadorPreRequisito validador : validadores) {
            mensagem.append("\n- ").append(validador.getMensagemErro());
        }
        return mensagem.toString();
    }
}
