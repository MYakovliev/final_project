package com.epam.web.service.impl;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.LotDao;
import com.epam.web.dao.impl.LotDaoImpl;
import com.epam.web.entity.Lot;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.util.ErrorMessage;
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
        logger.debug("{}, {}, {}, {}, {}, {}, {}",name, description, startBid, startTime, finishTime, sellerId, images);
        if (!(LotValidator.isValidName(name) && LotValidator.isValidTime(startTime, finishTime)
                && LotValidator.isValidDescription(description) && UserValidator.isValidBid(startBid))) {
            throw new ServiceException(ErrorMessage.INVALID_DATA_FORMAT);
        }
        if (startTime == null) {
            startTime = new Timestamp(System.currentTimeMillis());
        }
        logger.debug("{}, {}, {}, {}, {}, {}, {}",name, description, startBid, startTime, finishTime, sellerId, images);
        try {
            BigDecimal startBidDecimal = new BigDecimal(startBid);
            lot = dao.createNewLot(name, description, startBidDecimal, startTime, finishTime, sellerId, images);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!lot.isPresent()) {
            throw new ServiceException(ErrorMessage.FAILED_TO_CREATE_LOT);
        }
        return lot.get();
    }

    @Override
    public Lot findLotById(long id) throws ServiceException {
        Optional<Lot> lot = Optional.empty();
        try {
            lot = dao.findLotById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!lot.isPresent()) {
            throw new ServiceException(ErrorMessage.UNKNOWN_LOT);
        }
        return lot.get();
    }

    @Override
    public List<Lot> findLotByName(String name, int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            lot = dao.findLotByName(name, start, amountPerPage);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findLotByBuyerId(long buyerId, int pageNumber, int amountPerPage) throws ServiceException {
        List<Lot> lot;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            lot = dao.findLotByBuyerId(buyerId, start, amountPerPage);
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
            lot = dao.findLotBySellerId(sellerId, start, amountPerPage);
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
            lot = dao.findActive(start, amountPerPage);
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
            lot = dao.findAll(start, amountPerPage);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lot;
    }

    @Override
    public boolean isLotSubmitted(long lotId) throws ServiceException {
        boolean result = false;
        try {
            result = dao.isLotSubmitted(lotId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }
}
