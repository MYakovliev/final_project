package com.epam.web.service.impl;

import com.epam.web.dao.AmountDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.impl.AmountDaoImpl;
import com.epam.web.service.AmountService;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AmountServiceImpl implements AmountService {
    private static final Logger logger = LogManager.getLogger();
    private static AmountDao dao = AmountDaoImpl.getInstance();
    private static AmountServiceImpl instance = new AmountServiceImpl();

    private AmountServiceImpl() {
    }

    public static AmountServiceImpl getInstance() {
        return instance;
    }


    @Override
    public int findAllLotAmount() throws ServiceException {
        int amount;
        try {
            amount = dao.findAllLotAmount();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findActiveLotAmount() throws ServiceException {
        int amount;
        try {
            amount = dao.findActiveLotAmount();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findLotByNameAmount(String name) throws ServiceException {
        int amount;
        try {
            amount = dao.findLotByNameAmount(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findLotBySellerIdAmount(long sellerId) throws ServiceException {
        int amount;
        try {
            amount = dao.findLotBySellerIdAmount(sellerId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findLotByBuyerIdAmount(long buyerId) throws ServiceException {
        int amount;
        try {
            amount = dao.findLotByBuyerIdAmount(buyerId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findAllUserAmount() throws ServiceException {
        int amount;
        try {
            amount = dao.findAllUserAmount();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }

    @Override
    public int findUserByNameAmount(String name) throws ServiceException {
        int amount;
        try {
            amount = dao.findUserByNameAmount(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return amount;
    }
}
