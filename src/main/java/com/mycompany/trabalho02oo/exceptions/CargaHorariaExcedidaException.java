package com.mycompany.trabalho02oo.exceptions;

public class CargaHorariaExcedidaException extends ValidacaoMatriculaException {
    
    public CargaHorariaExcedidaException(String message) {
        super(message);
    }
    
    public CargaHorariaExcedidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
