package com.mycompany.trabalho02oo.exceptions;

public class GerenciamentoVagasException extends MatriculaException {
    
    public GerenciamentoVagasException(String message) {
        super(message);
    }
    
    public GerenciamentoVagasException(String message, Throwable cause) {
        super(message, cause);
    }
}
