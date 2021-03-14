package com.epam.web.dao;

public interface AdminDao {
    void ban(long userId) throws DaoException;

    void unban(long userId) throws DaoException;
}
