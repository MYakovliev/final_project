package com.epam.web.service;

import com.epam.web.entity.Lot;
import com.epam.web.entity.User;

import java.math.BigDecimal;

public interface UserService {
    User login(String login, String password) throws ServiceException;

    void register(String name, String mail, String login, String password) throws ServiceException;

    void makeBid(User user, BigDecimal bid, Lot lot) throws ServiceException;
}
