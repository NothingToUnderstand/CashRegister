package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.UserService;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import com.example.cashregister.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

class UserServiceImplTest {
    private static UserService userService;
    private static UserDao userDao;
    private static User user;

    @BeforeAll
    public static void init() {
        userDao = mock(UserDaoImpl.class);
        userService = new UserServiceImpl(userDao);
        user = new User(1, "ABC", "ABC", "ABC", new byte[1], new byte[1], "ABC", "abc");

    }

    @Test
    void getAll() throws SQLException {
        ArrayList<User> list=new ArrayList<>();
        list.add(user);
        assertThrows(NumberFormatException.class, () -> {
            userService.getAll(null,null,null,null);
        });
        when(userDao.getAll(any(String.class),any(String.class),
                any(Integer.class),any(Integer.class))).thenReturn(list);
        assertTrue(userService.getAll("abc","abc",1,1).contains(user));
        assertEquals(1,userService.getAll("abc","abc",1,1).size());
    }

    @Test
    void countRows() throws SQLException {
        when(userService.countRows()).thenReturn(2);
        assertEquals(2, userService.countRows());
        assertNotEquals(5, userService.countRows());
    }

    @Test
    void get() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            userService.get("null");
        });
        when(userDao.get(any(Integer.class))).thenReturn(user);
        assertEquals(user, userService.get("1"));
        assertEquals(1, userService.get("1").getId());
    }

    @Test
    void updatePasswordByEmail() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            userService.updatePasswordByEmail("null", "null");
        });
        when(userDao.updatePasswordByEmail(any(Integer.class), any(byte[].class))).thenReturn(true);
        when(userDao.get(any(Integer.class))).thenReturn(user);
        assertTrue(userService.updatePasswordByEmail("1", "a"));
    }

    @Test
    void createUser() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            userService.createUser("ABC", "ABC", "ABC", "ABC", "ABC");
        });
        when(userDao.createUser(any(String.class), any(String.class), any(byte[].class),
                any(byte[].class), any(Integer.class), any(String.class))).thenReturn(1);
        assertEquals(1, userService.createUser("ABC", "ABC", "ABC", "1", "abc"));
    }

    @Test
    void deleteUser() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            userService.deleteUser("null");
        });
        when(userDao.deleteUser(any(Integer.class))).thenReturn(true);
        assertTrue(userService.deleteUser("1"));
    }

    @Test
    void updateUser() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            userService.updateUser("ABC", "ABC", "ABC", "ABC", "ABC", "ABC");
        });
        when(userDao.updateUser(any(Integer.class), any(String.class), any(String.class), any(byte[].class),
                any(String.class), any(String.class))).thenReturn(true);
        when(userDao.get(any(Integer.class))).thenReturn(user);
        assertEquals(user, userService.updateUser("1", "ABC", "ABC", "ABC", "1", "abc"));
    }

    @Test
    void searchUser() throws SQLException {
        assertNull(userService.searchUser(null));
        when(userDao.searchUser(any(String.class))).thenReturn(user);
        assertEquals(user, userService.searchUser("abc"));
    }
}