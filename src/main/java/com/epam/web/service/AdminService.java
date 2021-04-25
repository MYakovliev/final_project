package com.epam.web.service;


import com.epam.web.entity.Lot;

public interface AdminService {

    void ban(long userId) throws ServiceException;

    void unban(long userId) throws ServiceException;

    void submitWinner(long userId, Lot lot) throws ServiceException;
}
