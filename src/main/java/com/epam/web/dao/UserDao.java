package com.epam.web.dao;

import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password) throws DaoException;

    void register(String name, String mail, String login, String password, UserRole role) throws DaoException;
//fixme
    void makeBid(User user, BigDecimal bid, Lot lot) throws DaoException;

    Optional<User> findUserById(int id) throws DaoException;

    List<User> findAll() throws DaoException;

    List<User> findUserByName(String name) throws DaoException;

    List<User> findBuyersHistory(long lotId) throws DaoException;
}
