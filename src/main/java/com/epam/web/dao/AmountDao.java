package com.epam.web.dao;

public interface AmountDao {
    int findAllLotAmount() throws DaoException;

    int findActiveLotAmount() throws  DaoException;

    int findLotByNameAmount(String name) throws DaoException;

    int findLotBySellerIdAmount(long sellerId) throws DaoException;

    int findLotByBuyerIdAmount(long buyerId) throws DaoException;

    int findAllUserAmount() throws DaoException;

    int findUserByNameAmount(String name) throws DaoException;

    int findBuyersHistoryAmount(long lotId) throws DaoException;
}
