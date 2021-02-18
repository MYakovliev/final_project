package com.epam.web.dao;

import com.epam.web.entity.Lot;
import com.epam.web.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password) throws DaoException;

    void register(String name, String mail, String login, String password) throws DaoException;

    void makeBid(User user, BigDecimal bid, Lot lot) throws DaoException;
}
