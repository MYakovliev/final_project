package com.epam.web.dao;

import com.epam.web.entity.Lot;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface LotDao {
    Optional<Lot> createNewLot(String name, String description, BigDecimal startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws DaoException;

    Optional<Lot> findLotById(long id) throws DaoException;

    List<Lot> findAll(int start, int amount) throws DaoException;

    List<Lot> findLotByName(String name, int start, int amount) throws DaoException;

    List<Lot> findLotByBuyerId(long buyerId, int start, int amount) throws DaoException;

    List<Lot> findLotBySellerId(long sellerId, int start, int amount) throws DaoException;

    List<Lot> findActive(int start, int amount) throws DaoException;
}
