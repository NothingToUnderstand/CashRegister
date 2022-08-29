package com.example.cashregister.security;

import com.example.cashregister.Service.UserService;
import com.example.cashregister.entity.User;

import javax.inject.Inject;
import java.sql.SQLException;

import static com.example.cashregister.security.PasswordEncryptionService.*;

public class ValidateUser {

    private UserService userService;

    @Inject
    public ValidateUser(UserService userService) {
        this.userService = userService;
    }

    public User validateUser(String fullname, String password) throws SQLException {
        User user = userService.searchUser(fullname);
        if (password == null) {
            return null;
        }
        if (user != null && authenticate(password, user.getPassword(), user.getSole())) {
            return user;
        }
        return null;
    }

}
