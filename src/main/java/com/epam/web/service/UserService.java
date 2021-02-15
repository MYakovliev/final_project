package com.epam.web.service;

import com.epam.web.entity.User;

public interface UserService {
    User login(String login, String password) throws ServiceException;

    void register(String name, String mail, String login, String password) throws ServiceException;
}
