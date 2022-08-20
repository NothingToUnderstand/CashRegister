package com.example.cashregister.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryptionService {

    public static boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt){
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public static byte[] getEncryptedPassword(String password, byte[] salt) {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = null;
        byte[] a = null;
        try {
            f = SecretKeyFactory.getInstance(algorithm);
            a= f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static byte[] generateSalt() {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
        }
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }
}
