package com.epam.web.service;


import com.epam.web.entity.User;


public interface AdminService {

    void ban(long userId) throws ServiceException;

    void unban(long userId) throws ServiceException;
}
