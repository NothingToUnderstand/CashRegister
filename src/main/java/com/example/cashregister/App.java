package com.example.cashregister;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.example.cashregister.security.ValidateUser.validateUser;

public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pass= "Qwe1234";
        System.out.println(validateUser("Viktor Lys",pass));
    }

    }
