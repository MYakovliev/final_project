package com.epam.web.service;

/**
 * The exception-wrapper for service layer
 */
public class ServiceException extends Exception{
    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable throwable){
        super(throwable);
    }

    public ServiceException(String message, Throwable throwable){
        super(message, throwable);
    }
}
