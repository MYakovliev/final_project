package com.epam.web.dao;

import com.epam.web.entity.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password);

    void register(String name, String mail, String login, String password);
}
