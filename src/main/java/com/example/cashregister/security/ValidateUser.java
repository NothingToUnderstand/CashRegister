package com.example.cashregister.security;

import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.User;
import static com.example.cashregister.security.PasswordEncryptionService.*;

public class ValidateUser {

    public static User validateUser(String fullname,String password){
        User user=new UserDaoImpl().searchUser(fullname);
        if(authenticate(password, user.getPassword(), user.getSole())){
            return user;
        }
        return null;
        }

}
