package com.mycompany.trabalho02oo.exceptions;

public class ConflitoDeHorarioException extends ValidacaoMatriculaException {
    
    public ConflitoDeHorarioException(String message) {
        super(message);
    }
    
    public ConflitoDeHorarioException(String message, Throwable cause) {
        super(message, cause);
    }
}
