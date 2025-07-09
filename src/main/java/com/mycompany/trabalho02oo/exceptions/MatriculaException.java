package com.mycompany.trabalho02oo.exceptions;

public abstract class MatriculaException extends Exception {
    
    public MatriculaException(String message) {
        super(message);
    }
    
    public MatriculaException(String message, Throwable cause) {
        super(message, cause);
    }
}
