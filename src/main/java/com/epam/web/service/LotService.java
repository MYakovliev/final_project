package com.epam.web.service;

import com.epam.web.entity.Lot;
import java.sql.Timestamp;
import java.util.List;

public interface LotService {
    Lot createNewLot(String name, String description, String startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws ServiceException;

    Lot findLotById(long id) throws ServiceException;

    List<Lot> findLotByName(String name) throws ServiceException;

    List<Lot> findWonLotByBuyerId(long buyerId) throws ServiceException;

    List<Lot> findLotBySellerId(long sellerId) throws ServiceException;
}
