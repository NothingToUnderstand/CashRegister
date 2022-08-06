package com.example.cashregister.dao;

import com.example.cashregister.entity.User;

import java.util.List;

public interface UserDao {
    User validate(String fullName, String pass);

     int createUser(String firstName, String lastName, String password, int roleId);

    List<User> getAllUsers(String column, String direction, Integer limitfrom, Integer limitquantity);

    boolean deleteUser(int id);

    boolean updateUser(int id, String firstName, String lastName, String pass, String role);

    User getUser(int id);


    int countRows();
    User searchUser(String name);
}
