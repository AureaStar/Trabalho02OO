package com.mycompany.trabalho02oo.validators;

import com.mycompany.trabalho02oo.models.Aluno;
import com.mycompany.trabalho02oo.models.Disciplina;

public class ValidatorLogicoOR implements ValidadorPreRequisito {

    private ValidadorPreRequisito[] validadores;

    public ValidatorLogicoOR(ValidadorPreRequisito... validadores) {
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
        StringBuilder mensagem = new StringBuilder("O aluno não cumpriu os pré-requisitos: ");
        for (ValidadorPreRequisito validador : validadores) {
            mensagem.append("\n- ").append(validador.getMensagemErro());
        }
        return mensagem.toString();
    }
}
