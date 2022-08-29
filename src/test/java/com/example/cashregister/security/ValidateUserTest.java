package com.example.cashregister.security;

import com.example.cashregister.Service.UserService;
import com.example.cashregister.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static com.example.cashregister.security.PasswordEncryptionService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidateUserTest {
    private static UserService userService;
    private static ValidateUser vl;
    private static User user;
    private static String password="123";
    private static byte[] salt=generateSalt();
    private static byte[] pass=getEncryptedPassword(password,salt);

    @BeforeAll
    public static void init() {
        userService = mock(UserService.class);
        vl = new ValidateUser(userService);
        user = new User(1, "ABC", "ABC", "ABC", pass, salt, "ABC", "abc");
    }

    @Test
    void validateUser() throws SQLException {
        assertNull(vl.validateUser(null, null));
        when(userService.searchUser(any(String.class))).thenReturn(user);
        assertEquals(user, vl.validateUser(user.getFullName(), password));
    }
}