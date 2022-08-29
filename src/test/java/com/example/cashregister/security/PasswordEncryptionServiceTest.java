package com.example.cashregister.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class PasswordEncryptionServiceTest {
    String pass = "123";
    String wrongPass = "333";
    byte[] salt = PasswordEncryptionService.generateSalt();
    byte[] encrPass = PasswordEncryptionService.getEncryptedPassword(pass, salt);

    @Test
    void authenticate() {
        assertTrue( PasswordEncryptionService.authenticate(pass,encrPass,salt));
        assertFalse( PasswordEncryptionService.authenticate(wrongPass,encrPass,salt));
    }

    @Test
    void generateSalt(){
        assertEquals(8, PasswordEncryptionService.generateSalt().length);
        assertEquals(byte[].class,PasswordEncryptionService.generateSalt().getClass());
    }
    @Test
    void getEncryptedPassword(){
        assertEquals(20, PasswordEncryptionService.getEncryptedPassword(pass,salt).length);
        assertEquals(byte[].class,PasswordEncryptionService.getEncryptedPassword(pass,salt).getClass());
    }

}