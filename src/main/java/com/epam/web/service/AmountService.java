package com.epam.web.service;

public interface AmountService {
    int findAllLotAmount() throws ServiceException;

    int findActiveLotAmount() throws ServiceException;

    int findLotByNameAmount(String name) throws ServiceException;

    int findLotBySellerIdAmount(long sellerId) throws ServiceException;

    int findLotByBuyerIdAmount(long buyerId) throws ServiceException;

    int findAllUserAmount() throws ServiceException;

    int findUserByNameAmount(String name) throws ServiceException;

    int findBuyersHistoryAmount(long lotId) throws ServiceException;
}
