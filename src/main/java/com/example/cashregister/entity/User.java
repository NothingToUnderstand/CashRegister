package com.example.cashregister.entity;

/**
 * Entity of user
 * */
public class User {
    private int id;

    private String firstName;
    private String lastName;
    private String fullName;
    private String password;
    private String role;

     public User() {
        this.id=0;
    }
    public User(int id,String firstName,String lastname,String fullName, String password,String role) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.role=role;
        this.firstName=firstName;
        this.lastName=lastname;
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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return this.role;
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
