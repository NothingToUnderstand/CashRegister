package com.example.cashregister.dao.impl;


import com.example.cashregister.dao.UserDao;
import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.cashregister.connection.ApacheConPool.getConnection;
import static com.example.cashregister.Service.extra.Properties.getProperty;


/**
 * DAO layer for program interaction with the user table in the database
 */
@RequestScoped
public class UserDaoImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    @Override
    public boolean updatePasswordByEmail(int id, byte[] password) throws SQLException {
        log.info("Update user password with id: " + id);
        boolean status = false;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("update_user_password"))) {
            ps.setBytes(1, password);
            ps.setInt(2, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during updating user", e);
            e.printStackTrace();
            throw e;
        }
        log.info("User update is successfully");
        return status;
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
    public int createUser(String firstName, String lastName, byte[] password, byte[] sole, int roleId, String email) throws SQLException {
        log.info("Add user to DB: " + firstName + " " + lastName);
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKey = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("create_user"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setBytes(3, password);
            ps.setBytes(4, sole);
            ps.setString(5, email);
            ps.executeUpdate();
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            setRole(con, id, roleId);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during user creation", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (generatedKey != null) {
                generatedKey.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return id;
    }

    /**
     * method gets all users
     *
     * @return List<User> List of all users
     */
    @Override
    public ArrayList<User> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        log.info("Get all users");
        String query = String.format(getProperty("get_all_users"), column + " " + direction);
        ArrayList<User> users = new ArrayList<>();
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
                        rs.getBytes("password"),
                        rs.getBytes("sole"),
                        rs.getString("role_name"),
                        rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Error during getting all users");
            e.printStackTrace();
            throw e;
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
    public boolean deleteUser(int id) throws SQLException {
        log.info("Delete user with id: " + id);
        boolean status;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("delete_user"))) {
            ps.setInt(1, id);
            status = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error("Error during user validation", e);
            e.printStackTrace();
            throw e;
        }
        return status;
    }

    /**
     * method for changing user's data
     *
     * @param id        user's id
     * @param role      user's role
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param pass      password
     * @return boolean status
     */
    @Override
    public boolean updateUser(int id, String firstName, String lastName, byte[] pass, String role, String email) throws SQLException {
        log.info("Update user with id: " + id);
        boolean status;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(getProperty("update_user"));
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setBytes(3, pass);
            ps.setString(4, email);
            ps.setInt(5, id);
            status = ps.executeUpdate() == 1;
            updateRole(con, id, role);
            con.commit();
        } catch (SQLException e) {
            log.error("Error during updating user", e);
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
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
    public User get(int id) throws SQLException {
        log.info("Get user with id: " + id);
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_user"))) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getBytes("password"),
                        rs.getBytes("sole"),
                        rs.getString("role_name"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            log.error("Error during getting user", e);
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    /**
     * private method for setting a role for an existing user
     *
     * @param userId user id
     * @param roleId role id
     */
    private void setRole(Connection con, int userId, int roleId) throws SQLException {
        log.info("Set role with id:" + roleId + " for user with id: " + userId);
        try (PreparedStatement ps = con.prepareStatement(getProperty("set_role"))) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during setting user role", e);
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * method to get the amount of a users in db
     *
     * @return int amount
     */
    @Override
    public int countRows() throws SQLException {
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
            throw e;
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
    public User searchUser(String fullname) throws SQLException {
        log.info("Get user with fullname: " + fullname);
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(getProperty("get_user_by_fullname"))) {
            ps.setString(1, fullname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("full_name"),
                        rs.getBytes("password"),
                        rs.getBytes("sole"),
                        rs.getString("role_name"),
                        rs.getString("email")
                );

            }
        } catch (SQLException e) {
            log.error("Error during getting user", e);
            e.printStackTrace();
            throw e;
        }
        return user;
    }


    /**
     * user role updating method
     *
     * @param idUser user's id
     * @param role   user's role
     */

    private void updateRole(Connection con, int idUser, String role) throws SQLException {
        log.info("Update role for user with id: " + idUser);
        System.out.println(role);
        try (PreparedStatement ps = con.prepareStatement(getProperty("update_role"))) {
            ps.setString(1, role);
            ps.setInt(2, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during updating user role", e);
            e.printStackTrace();
            throw e;
        }
    }

}
