package com.epam.web.service;


public interface AdminService {

    void ban(long userId) throws ServiceException;

    void unban(long userId) throws ServiceException;

    void submitWinner(long userId, long lotId) throws ServiceException;
}
