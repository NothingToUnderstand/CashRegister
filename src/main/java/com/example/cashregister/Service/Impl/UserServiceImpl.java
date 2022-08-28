package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.UserService;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.cashregister.security.PasswordEncryptionService.generateSalt;
import static com.example.cashregister.security.PasswordEncryptionService.getEncryptedPassword;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    public ArrayList<User> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        return this.userDao.getAll(column, direction, limitfrom, limitquantity);
    }

    @Override
    public int countRows() throws SQLException {
        return this.userDao.countRows();
    }

    @Override
    public User get(String idUser) throws SQLException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(idUser);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.userDao.get(id);
    }

    @Override
    public boolean updatePasswordByEmail(String idUser, String password) throws SQLException, NumberFormatException {
        if (password == null || idUser == null) {
            throw new NumberFormatException();
        }
        User user = get(idUser);
        byte[] salt = user.getSole();
        byte[] pass = getEncryptedPassword(password, salt);
        int id;
        try {
            id = Integer.parseInt(idUser);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.userDao.updatePasswordByEmail(id, pass);
    }

    @Override
    public int createUser(String firstName, String lastName, String password, String roleId, String email) throws SQLException, NumberFormatException {
        if ((firstName == null || lastName == null ||
                password == null || roleId == null || email == null)) {
            throw new NumberFormatException();
        }
        byte[] salt = generateSalt();
        byte[] pass = getEncryptedPassword(password, salt);
        int role = 0;
        try {
            role = Integer.parseInt(roleId);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.userDao.createUser(firstName, lastName, pass, salt, role, email);
    }

    @Override
    public boolean deleteUser(String idUser) throws SQLException, NumberFormatException {
        int id = 0;
        try {
            id = Integer.parseInt(idUser);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.userDao.deleteUser(id);
    }

    @Override
    public User updateUser(String idUser, String firstName, String lastName, String pass, String role, String email) throws SQLException, NumberFormatException {
        if ((idUser == null || firstName == null || lastName == null ||
                pass == null || role == null || email == null)) {
            throw new NumberFormatException();
        }
        User user = get(idUser);
        byte[] salt = user.getSole();
        byte[] password = getEncryptedPassword(pass, salt);
        if (this.userDao.updateUser(user.getId(), firstName, lastName, password, role, email)) {
            return user;
        }
        return null;
    }

    @Override
    public User searchUser(String name) throws SQLException {
        if (name == null) {
            return null;
        }
        return this.userDao.searchUser(name);
    }
}
