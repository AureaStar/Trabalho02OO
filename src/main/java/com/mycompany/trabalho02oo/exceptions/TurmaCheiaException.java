package com.mycompany.trabalho02oo.exceptions;

public class TurmaCheiaException extends GerenciamentoVagasException {
    
    public TurmaCheiaException(String message) {
        super(message);
    }
    
    public TurmaCheiaException(String message, Throwable cause) {
        super(message, cause);
    }
}
