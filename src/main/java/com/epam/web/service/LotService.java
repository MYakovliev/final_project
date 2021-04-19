package com.epam.web.service;

import com.epam.web.entity.Lot;
import java.sql.Timestamp;
import java.util.List;

public interface LotService {
    Lot createNewLot(String name, String description, String startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws ServiceException;

    Lot findLotById(long id) throws ServiceException;

    List<Lot> findLotByName(String name, int pageNumber, int amountPerPage) throws ServiceException;

    List<Lot> findLotByBuyerId(long buyerId, int pageNumber, int amountPerPage) throws ServiceException;

    List<Lot> findLotBySellerId(long sellerId, int pageNumber, int amountPerPage) throws ServiceException;

    List<Lot> findActive(int pageNumber, int amountPerPage) throws ServiceException;

    List<Lot> findAll(int pageNumber, int amountPerPage) throws ServiceException;
}
