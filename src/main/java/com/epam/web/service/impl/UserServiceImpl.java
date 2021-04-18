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
            throw new ServiceException("invalid_data_format");
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
            throw new ServiceException("invalid_login_or_password");
        }
        return optionalUser.get();

    }

    @Override
    public void register(String name, String mail, String login, String password, UserRole role) throws ServiceException {
        if (!(UserValidator.isValidLogin(login) && UserValidator.isValidPassword(password)
                && UserValidator.isValidMail(mail) && UserValidator.isValidName(name))) {
            throw new ServiceException("invalid_data_format");
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
    public User findUserById(long id) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = dao.findUserById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (!optionalUser.isPresent()) {
            throw new ServiceException("unknown_user");
        }
        return optionalUser.get();
    }

    @Override
    public void changeUserData(long userId, String avatar, String name, String mail) throws ServiceException {
        if (!(UserValidator.isValidMail(mail) && UserValidator.isValidName(name))){
            throw new ServiceException("invalid_data_format");
        }
        try{
            dao.changeUserData(userId, avatar, name, mail);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeUserPassword(long userId, String oldPassword, String newPassword) throws ServiceException {
        if (!(UserValidator.isValidPassword(oldPassword) && UserValidator.isValidPassword(newPassword))){
            throw new ServiceException("invalid_data_format");
        }
        try{
            dao.changeUserPassword(userId, oldPassword, newPassword);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUserByName(String name, int pageNumber, int amountPerPage) throws ServiceException {
        List<User> users;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            users = dao.findUserByName(name, start, amountPerPage);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findBuyersHistory(long lotId, int pageNumber, int amountPerPage) throws ServiceException {
        List<User> users;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            users = dao.findBuyersHistory(lotId, start, amountPerPage);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public boolean isBanned(long userId) {
        boolean result = false;
        try {
            result = dao.isBanned(userId);
        } catch (DaoException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<User> findAll(int pageNumber, int amountPerPage) throws ServiceException {
        List<User> users;
        try {
            int start = (pageNumber - 1) * amountPerPage;
            users = dao.findAll(start, amountPerPage);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public void makeBid(User buyer, String stringBid, Lot lot) throws ServiceException {
        if (!UserValidator.isValidBid(stringBid)) {
            throw new ServiceException("incorrect_bid");
        }
        BigDecimal bid = new BigDecimal(stringBid);
        if (!(lot.getCurrentCost().compareTo(bid) < 0)) {
            throw new ServiceException("small_bid");
        }
        if (!(buyer.getBalance().compareTo(bid) > 0)) {
            throw new ServiceException("not_enough_money");
        }
        try {
            dao.makeBid(buyer.getId(), bid, lot.getId());
            lot.setCurrentCost(bid);
            lot.setBuyerId(buyer.getId());
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


}
