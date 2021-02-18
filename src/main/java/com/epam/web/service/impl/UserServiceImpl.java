package com.epam.web.service.impl;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.util.PasswordEncrypter;
import com.epam.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserDao dao = UserDaoImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();
    private static UserValidator validator = new UserValidator();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        if (!(validator.isValidLogin(login) && validator.isValidPassword(password))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        Optional<User> optionalUser = Optional.empty();
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
    public void register(String name, String mail, String login, String password) throws ServiceException {
        if (!(validator.isValidLogin(login) && validator.isValidPassword(password)
                && validator.isValidMail(mail) && validator.isValidName(name))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        try {
            dao.register(name, mail, login, optionalPassword.get());
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void makeBid(User user, BigDecimal bid, Lot lot) throws ServiceException {
        try {
            if (user.getBalance().compareTo(bid) > 0 && lot.getCurrentCost().compareTo(bid) < 0) {
                if (!lot.getBuyer().equals(user)) {
                    dao.makeBid(user, bid, lot);
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
