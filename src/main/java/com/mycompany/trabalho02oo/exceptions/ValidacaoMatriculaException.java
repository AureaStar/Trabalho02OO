package com.mycompany.trabalho02oo.exceptions;

public class ValidacaoMatriculaException extends MatriculaException {
    
    public ValidacaoMatriculaException(String message) {
        super(message);
    }
    
    public ValidacaoMatriculaException(String message, Throwable cause) {
        super(message, cause);
    }
}
