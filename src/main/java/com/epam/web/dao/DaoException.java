package com.epam.web.dao;

public class DaoException extends Exception{
    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(Throwable throwable){
        super(throwable);
    }

    public DaoException(String message, Throwable throwable){
        super(message, throwable);
    }
}
