package com.example.cashregister.dao;

import com.example.cashregister.entity.User;

import java.util.List;

public interface UserDao extends MainDao<User> {
     int createUser(String firstName, String lastName, byte[] password,byte[] sole, int roleId);
    boolean deleteUser(int id);
    boolean updateUser(int id, String firstName, String lastName, byte[] pass, String role);
    User searchUser(String name);
}
