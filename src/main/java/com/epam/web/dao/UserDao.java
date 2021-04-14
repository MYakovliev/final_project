package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password) throws DaoException;

    void register(String name, String mail, String login, String password, UserRole role) throws DaoException;

    void makeBid(long userId, BigDecimal bid, long lot) throws DaoException;

    Optional<User> findUserById(long id) throws DaoException;

    List<User> findAll(int start, int amount) throws DaoException;

    List<User> findUserByName(String name, int start, int amount) throws DaoException;

    List<User> findBuyersHistory(long lotId, int start, int amount) throws DaoException;

    boolean isBanned(long userId) throws DaoException;
}
