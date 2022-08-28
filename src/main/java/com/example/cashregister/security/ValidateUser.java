package com.example.cashregister.security;

import com.example.cashregister.Service.UserService;
import com.example.cashregister.entity.User;

import javax.inject.Inject;
import java.sql.SQLException;

import static com.example.cashregister.security.PasswordEncryptionService.*;
public class ValidateUser {
    @Inject
   private UserService userService;

    public  User validateUser(String fullname,String password) throws SQLException {
        System.out.println(userService);
        User user=userService.searchUser(fullname);
        if(password.isBlank()){
            return  null;
        }
        if(user.getId()!=0&&authenticate(password, user.getPassword(), user.getSole())){
            return user;
        }
        return null;
        }

}
