package com.eldarian.solvdelivery.exceptions;

public class NoEmployeeException extends Exception {
    public NoEmployeeException(){
        super();
    }

    public NoEmployeeException(String message) {
        super(message);
    }

    public NoEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
