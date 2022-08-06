package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.UserDao;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.property.Properties.getProperty;


/**
 * DAO layer for program interaction with the user table in the database
 */
public class UserDaoImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    /**
     * method for validating a user at login
     *
     * @param fullName user's full name
     * @param pass     password
     * @return user
     */
    @Override
    public  User validate(String fullName, String pass) {
        log.info("Validation for user: " + fullName + " with password: " + pass);
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_valid_user"))) {
            ps.setString(1, fullName);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                 user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("role_name"));
            }
        } catch (SQLException  e) {
            log.error("Error during user validation", e);
            e.printStackTrace();
        }
        if (user.getId()!=0) {
            log.info("User validation is successfully");
        } else {
            log.warn("User is not valid");
        }
        return user;
    }

    /**
     * method adds new user and sets user's role
     *
     * @param firstName user first name
     * @param lastName  user last name
     * @param password  user password
     * @param roleId    role id
     * @return int new user's id
     */
    @Override
    public  int createUser(String firstName, String lastName, String password, int roleId) {
        log.info("Add user to DB: " + firstName + " " + lastName + " " + password);
        int id = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("create_user"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, password);
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }

        } catch (SQLException  e) {
            log.error("Error during user creation", e);
            e.printStackTrace();
        }
        if (id != 0) {
            log.info("User added successfully");
            setRole(id, roleId);
        } else {
            log.warn("User not added");
        }
        return id;
    }

    /**
     * method gets all users
     *
     * @return List<User> List of all users
     */
    @Override
    public  List<User> getAllUsers(String column, String direction, Integer limitfrom, Integer limitquantity) {
        log.info("Get all users");
        String query = String.format(getProperty("get_all_users"), column + " " + direction);
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limitfrom);
            ps.setInt(2, limitquantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("role_name"));
                users.add(user);
            }
        } catch (SQLException  e) {
            log.error("Error during getting all users");
            e.printStackTrace();
        }
        return users;
    }

    /**
     * method for removing user
     *
     * @param id user's id
     * @return boolean status
     */
    @Override
    public  boolean deleteUser(int id) {
        log.info("Delete user with id: " + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_user"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during user validation", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("User delete is successfully");
        } else {
            log.warn("User delete failed");
        }
        return status;
    }

    /**
     * method for changing user's data
     *
     * @param id        user's id
     * @param role    user's role
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param pass      password
     * @return boolean status
     */
    @Override
    public  boolean updateUser(int id, String firstName, String lastName, String pass, String role) {
        log.info("Update user with id: " + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("update_user"))) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, pass);
            ps.setInt(4, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during updating user", e);
            e.printStackTrace();
        }
        if (status) {
            updateRole(id, role);
            log.info("User update is successfully");
        } else {
            log.warn("User update failed");
        }
        return status;
    }

    /**
     * method for getting a user
     *
     * @param id user's id
     * @return user
     */
    @Override
    public  User getUser(int id) {
        log.info("Get user with id: " + id);

        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_user"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("role_name"));
            }
        } catch (SQLException e) {
            log.error("Error during getting user", e);
            e.printStackTrace();
        }
        if (user.getId() != 0) {
            log.info("User found");
        } else {
            log.warn("User not found");
        }
        return user;
    }

    /**
     * private method for setting a role for an existing user
     *
     * @param userId user id
     * @param roleId role id
     */
    private void setRole(int userId, int roleId) {
        log.info("Set role with id:" + roleId + " for user with id: " + userId);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("set_role"))) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during setting user role", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("User role established successfully");
        } else {
            log.warn("User  role was not established");
        }
    }

    /**
     * method to get the amount of a users in db
     *
     * @return int amount
     */
    @Override
    public int countRows() {
        int amount = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("count_rows_in_users"))) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Error during getting amount of users", e);
            e.printStackTrace();
        }
        return amount;
    }

    /**
     * the method receives information about product
     *
     * @param fullname product's name
     * @return product
     */
    @Override
    public User searchUser(String fullname) {
        log.info("Get user with fullname: " + fullname);
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_user_by_fullname"))) {
            ps.setString(1, fullname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("role"));

            }
        } catch (SQLException e) {
            log.error("Error during getting user", e);
            e.printStackTrace();
        }
        if (user.getId() != 0) {
            log.info("User found");
        } else {
            log.warn("User not found");
        }
        return user;
    }



    /**
     * user role updating method
     *
     * @param idUser user's id
     * @param role user's role
     * @return boolean status
     */

    private  boolean updateRole(int idUser, String role) {
        log.info("Update role for user with id: " + idUser);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("update_role"))) {
            ps.setString(1, role);
            ps.setInt(2, idUser);
            status = ps.executeUpdate() == 1;
        } catch (SQLException  e) {
            log.error("Error during updating user role", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("Role updated for user with id:" + idUser);
        } else {
            log.warn("Role not updated for user with id: " + idUser);
        }
        return status;
    }

}
