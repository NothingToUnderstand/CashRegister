package com.example.cashregister.dao;

import com.example.cashregister.entity.User;

import java.util.List;

public interface UserDao extends MainDao<User> {
    User validate(String fullName, String pass);
     int createUser(String firstName, String lastName, String password, int roleId);
    boolean deleteUser(int id);
    boolean updateUser(int id, String firstName, String lastName, String pass, String role);
    User searchUser(String name);
}
