package com.epam.web.service.impl;

import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.util.PasswordEncrypter;
import com.epam.web.validator.UserValidator;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserDao USER_DAO_IMPL = UserDaoImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();
    private static UserValidator validator = new UserValidator();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    public User login(String login, String password) throws ServiceException {
        if (!(validator.isValidLogin(login) && validator.isValidPassword(password))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        Optional<User> optionalUser = USER_DAO_IMPL.login(login, optionalPassword.get());
        if (!optionalUser.isPresent()) {
            throw new ServiceException("invalid login or password");
        }
        return optionalUser.get();

    }

    public void register(String name, String mail, String login, String password) throws ServiceException {
        if (!(validator.isValidLogin(login) && validator.isValidPassword(password)
                && validator.isValidMail(mail) && validator.isValidName(name))) {
            throw new ServiceException("wrong data in form(s)");
        }
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()) {
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        USER_DAO_IMPL.register(name, mail, login, optionalPassword.get());
    }
}
