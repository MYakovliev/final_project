package com.epam.web.service.impl;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.LotDao;
import com.epam.web.dao.impl.LotDaoImpl;
import com.epam.web.entity.Lot;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.validator.LotValidator;
import com.epam.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class LotServiceImpl implements LotService {
    private static final Logger logger = LogManager.getLogger();
    private static LotDao dao = LotDaoImpl.getInstance();
    private static LotService instance = new LotServiceImpl();

    private LotServiceImpl() {
    }

    public static LotService getInstance() {
        return instance;
    }

    @Override
    public Lot createNewLot(String name, String description, String startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws ServiceException {
        Optional<Lot> lot;
        if (!(LotValidator.isValidName(name) && LotValidator.isValidTime(startTime, finishTime)
                && UserValidator.isValidBid(startBid))) {
            throw new ServiceException("wrong data in form(s)");
        }
        try {
            BigDecimal startBidDecimal = new BigDecimal(startBid);
            lot = dao.createNewLot(name, description, startBidDecimal, startTime, finishTime, sellerId, images);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!lot.isPresent()){
            throw new ServiceException("failed to create a lot");
        }
        return lot.get();
    }

    @Override
    public Lot findLotById(long id) throws ServiceException {
        Optional<Lot> lot;
        try {
            lot = dao.findLotById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!lot.isPresent()){
            throw new ServiceException("failed to create a lot");
        }
        return lot.get();
    }

    @Override
    public List<Lot> findLotByName(String name, int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            int finish = pageNumber * amountPerPage;
            lot = dao.findLotByName(name, start, finish);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findWonLotByBuyerId(long buyerId, int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            int finish = pageNumber * amountPerPage;
            lot = dao.findWonLotByBuyerId(buyerId, start, finish);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findLotBySellerId(long sellerId, int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            int finish = pageNumber * amountPerPage;
            lot = dao.findLotBySellerId(sellerId, start, finish);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findActive(int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            int finish = pageNumber * amountPerPage;
            lot = dao.findActive(start, finish);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findAll(int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            int finish = pageNumber * amountPerPage;
            lot = dao.findAll(start, finish);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }
}
