package com.example.cashregister.Service;

import com.example.cashregister.entity.User;


import java.sql.SQLException;

public interface UserService extends MainService<User>{

    boolean updatePasswordByEmail(String id,String password) throws SQLException;
    int createUser(String firstName, String lastName, String password, String roleId,String email) throws SQLException;
    boolean deleteUser(String id) throws SQLException;
    User updateUser(String id, String firstName, String lastName, String pass, String role,String email) throws SQLException;
    User searchUser(String name) throws SQLException;
}
