package com.mycompany.trabalho02oo.exceptions;

public class CoRequisitoNaoAtendidoException extends ValidacaoMatriculaException {
    
    public CoRequisitoNaoAtendidoException(String message) {
        super(message);
    }
    
    public CoRequisitoNaoAtendidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
