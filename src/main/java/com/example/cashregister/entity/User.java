package com.example.cashregister.entity;

/**
 * Entity of user
 * */
public class User {
    private int id;

    private String firstName;
    private String lastName;
    private String fullName;
    private byte[] password;
    private String role;
    private byte[] sole;

     public User() {
        this.id=0;
    }
    public User(int id,String firstName,String lastname,String fullName, byte[] password,byte[] sole,String role) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.role=role;
        this.firstName=firstName;
        this.lastName=lastname;
        this.sole=sole;
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
