package com.example.cashregister.entity;


import java.io.Serializable;

/**
 * Entity of user
 * */
public class User implements Serializable {
    private final int id;

    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final String email;
    private final byte[] password;
    private final String role;
    private final byte[] sole;


    public User(int id,String firstName,String lastname,String fullName, byte[] password,byte[] sole,String role,String email) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.role=role;
        this.firstName=firstName;
        this.lastName=lastname;
        this.sole=sole;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {return id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getRole() {
        return this.role;
    }

    public byte[] getSole() {
        return sole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
