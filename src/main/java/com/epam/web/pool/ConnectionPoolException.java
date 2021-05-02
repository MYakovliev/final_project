package com.epam.web.pool;

/**
 * The exception-wrapper for connection pool layer
 */
public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(){
        super();
    }

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(Throwable throwable){
        super(throwable);
    }

    public ConnectionPoolException(String message, Throwable throwable){
        super(message, throwable);
    }
}
