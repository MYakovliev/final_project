package com.epam.web.service;

import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;

import java.util.List;

public interface UserService {
    User login(String login, String password) throws ServiceException;

    void register(String name, String mail, String login, String password, UserRole role) throws ServiceException;

    void makeBid(User user, String bid, Lot lot) throws ServiceException;

    User findUserById(long id) throws ServiceException;

    List<User> findUserByName(String name, int pageNumber, int amountPerPage) throws ServiceException;

    List<User> findBuyersHistory(long lotId, int pageNumber, int amountPerPage) throws ServiceException;

    boolean isBanned(long userId);

    List<User> findAll(int pageNumber, int amountPerPage) throws ServiceException;
}
