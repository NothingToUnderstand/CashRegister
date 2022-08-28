package com.example.cashregister.dao;

import com.example.cashregister.entity.User;
import java.sql.SQLException;


public interface UserDao extends MainDao<User> {

    boolean updatePasswordByEmail(int id,byte[] password) throws SQLException;
    int createUser(String firstName, String lastName, byte[] password,byte[] sole, int roleId,String email) throws SQLException;
    boolean deleteUser(int id) throws SQLException;
    boolean updateUser(int id, String firstName, String lastName, byte[] pass, String role,String email) throws SQLException;
    User searchUser(String name) throws SQLException;
}
