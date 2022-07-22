package com.example.cashregister.dao;


import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * DAO layer for program interaction with the user table in the database
 */
public class UserDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    /**
     * static block for getting properties from app.properties
     */
    static Properties prop = new Properties();

    static {
        try {
            prop.load(UserDAO.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            log.error("Error in UserDao reading property file", ex);
            ex.printStackTrace();
        }
    }

    /**
     * method for validating a user at login
     *
     * @param fullName user's full name
     * @param pass     password
     * @return user
     */
    public static User validate(String fullName, String pass) {
        log.info("Validation for user: " + fullName + " with password: " + pass);
        User user = new User();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_valid_user"))) {
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
        } catch (SQLException | ClassNotFoundException e) {
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
    public static int createUser(String firstName, String lastName, String password, int roleId) {
        log.info("Add user to DB: " + firstName + " " + lastName + " " + password);
        int id = 0;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("create_user"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, password);
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
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
    public static List<User> getAllUsers() {
        log.info("Get all users");
        List<User> users = new ArrayList<>();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_all_users"))) {
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
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during getting all users");
            e.printStackTrace();
        }
        log.info("List with users: " + users);
        return users;
    }

    /**
     * method for removing user
     *
     * @param id user's id
     * @return boolean status
     */
    public static boolean deleteUser(int id) {
        log.info("Delete user with id: " + id);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("delete_user"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
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
     * @param idRole    role's id
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param pass      password
     * @return boolean status
     */
    public static boolean updateUser(int id, String firstName, String lastName, String pass, int idRole) {
        log.info("Update user with id: " + id);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("update_user"))) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, pass);
            ps.setInt(4, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during updating user", e);
            e.printStackTrace();
        }
        if (status) {
            updateRole(id, idRole);
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
    public static User getUser(int id) {
        log.info("Get user with id: " + id);

        User user = new User();
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_user"))) {
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
        } catch (SQLException | ClassNotFoundException e) {
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
     * @return boolean status
     */
    private static boolean setRole(int userId, int roleId) {
        log.info("Set role with id:" + roleId + " for user with id: " + userId);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("set_role"))) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error during setting user role", e);
            e.printStackTrace();
        }
        if (status) {
            log.info("User role established successfully");
        } else {
            log.warn("User  role was not established");
        }
        return status;
    }


    /**
     * user role updating method
     *
     * @param idUser user's id
     * @param idRole role's id
     * @return boolean status
     */

    private static boolean updateRole(int idUser, int idRole) {
        log.info("Update role for user with id: " + idUser);
        boolean status = false;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(prop.getProperty("update_role"))) {
            ps.setInt(1, idRole);
            ps.setInt(2, idUser);
            status = ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
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


//    /**
//     * user role get method
//     *
//     * @param id user's id
//     * @return String role name
//     */

//    public static String getRole(int id) {
//        log.info("Get role for user with id: "+id);
//        String role="";
//        try (Connection con = ManagerDB.getInstance().getConnection();
//             PreparedStatement ps = con.prepareStatement(prop.getProperty("get_role"))) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){

//                role=rs.getString(1);
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            log.error("Error during getting user role", e);
//            e.printStackTrace();
//        }
//        log.info("User role with id: "+id+" role: "+role);
//        return role;
//    }
}
