package com.epam.web.service.impl;

import com.epam.web.dao.AdminDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.impl.AdminDaoImpl;
import com.epam.web.service.AdminService;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger();
    private static AdminDao dao = AdminDaoImpl.getInstance();
    private static AdminService instance = new AdminServiceImpl();

    private AdminServiceImpl() {
    }

    public static AdminService getInstance() {
        return instance;
    }


    @Override
    public void ban(long userId) throws ServiceException {
        try {
            dao.ban(userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void unban(long userId) throws ServiceException {
        try {
            dao.unban(userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void submitWinner(long userId, long lotId) throws ServiceException {
        try {
            dao.submitWinner(userId, lotId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
