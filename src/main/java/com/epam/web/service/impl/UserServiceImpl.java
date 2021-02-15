package com.epam.web.service.impl;

import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.util.PasswordEncrypter;

import java.util.Optional;
//todo validator
public class UserServiceImpl implements UserService {
    private static final UserDao USER_DAO_IMPL = UserDaoImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl(){
    }

    public static UserServiceImpl getInstance(){
        return instance;
    }

    public User login(String login, String password) throws ServiceException {
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()){
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        Optional<User> optionalUser = USER_DAO_IMPL.login(login, password);
        if (!optionalUser.isPresent()){
            throw new ServiceException("invalid login or password");
        }
        return optionalUser.get();
    }

    public void register(String name, String mail, String login, String password) throws ServiceException {
        Optional<String> optionalPassword = PasswordEncrypter.encrypt(password);
        if (!optionalPassword.isPresent()){
            throw new ServiceException("Unknown algorithm for encrypting password");
        }
        USER_DAO_IMPL.register(name, mail, login, optionalPassword.get());
    }
}
