package com.epam.web.service.impl;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.util.PasswordEncrypter;
import com.epam.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserDao dao = UserDaoImpl.getInstance();
    private static UserService instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        if (!(UserValidator.isValidLogin(login) && UserValidator.isValidPassword(password))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        Optional<User> optionalUser;
        try {
            optionalUser = dao.login(login, optionalPassword.get());
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!optionalUser.isPresent()) {
            throw new ServiceException("invalid login or password");
        }
        return optionalUser.get();

    }

    @Override
    public void register(String name, String mail, String login, String password, UserRole role) throws ServiceException {
        if (!(UserValidator.isValidLogin(login) && UserValidator.isValidPassword(password)
                && UserValidator.isValidMail(mail) && UserValidator.isValidName(name))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        try {
            dao.register(name, mail, login, optionalPassword.get(), role);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = dao.findUserById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!optionalUser.isPresent()){
            throw new ServiceException("there's no such user");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> findUserByName(String name) throws ServiceException {
        List<User> users;
        try {
            users = dao.findUserByName(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findBuyersHistory(long lotId) throws ServiceException {
        List<User> users;
        try {
            users = dao.findBuyersHistory(lotId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public boolean isBanned(long userId){
        boolean result = false;
        try {
            result = dao.isBanned(userId);
        } catch (DaoException e){
            logger.error(e);
        }
        return result;
    }

    @Override
    public void makeBid(User buyer, String stringBid, Lot lot) throws ServiceException {
        if (buyer == null) {
            throw new ServiceException("you have to log in");
        }
        if (buyer.getUserRole() != UserRole.BUYER) {
            throw new ServiceException("you have no such rights, please log in as a buyer");
        }
        if (!UserValidator.isValidBid(stringBid)) {
            throw new ServiceException("incorrect bid");
        }
        BigDecimal bid = new BigDecimal(stringBid);
        try {
            if (buyer.getBalance().compareTo(bid) > 0 && lot.getCurrentCost().compareTo(bid) < 0) {
                if (!(lot.getBuyerId() == buyer.getId())) {
                    dao.makeBid(buyer, bid, lot);
                    lot.setCurrentCost(bid);
                    lot.setBuyerId(buyer.getId());
                } else {
                    throw new ServiceException("this user is already a buyer");
                }
            } else {
                throw new ServiceException("this user have no enough money");
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


}
