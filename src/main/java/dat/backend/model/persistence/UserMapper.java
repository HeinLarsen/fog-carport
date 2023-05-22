package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper {

// TODO refactor return types to return the data we receive from the database instead of void

    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    int phoneNumber = rs.getInt("phone_number");
                    int roleId = rs.getInt("role");
                    int membershipId = rs.getInt("membership");
                    int zip = rs.getInt("zip");
                    User user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get all users from database");
        }
        return users;
    }

    public static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    int phoneNumber = rs.getInt("phone_number");
                    int roleId = rs.getInt("role");
                    int membershipId = rs.getInt("membership");
                    int zip = rs.getInt("zip");
                    user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip);
                } else {
                    throw new DatabaseException("No user with id = " + id + " found in database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get user from database");
        }
        return user;
    }

    public static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, password = ?, address = ?, phone_number = ?, zip = ?, membership = ? WHERE id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                ps.setInt(6, phoneNumber);
                ps.setInt(7, zip);
                ps.setInt(8, membershipId);
                ps.setInt(9, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("No user with id = " + id + " found in database");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not update user in database");
        }
        return new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip);
    }


    public static User createUser(String firstName, String lastName, String email, String password, String address, int phoneNumber, int zip, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "INSERT INTO user (first_name, last_name, email, password, address, phone_number, zip) VALUES (?,?,?,?,?,?,?)";
        User user;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                ps.setInt(6, phoneNumber);
                ps.setInt(7, zip);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("No user with email = " + email + " found in database");
                }
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        int roleId = 1;
                        int membershipId = 1;
                        user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip);
                    } else {
                        throw new DatabaseException("Failed to get ID for created user");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not create user in database");
        }
        return user;
    }

    public static void createUser2(String firstName, String lastName, String email, String password, String address, int phoneNumber, int zip, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "INSERT INTO user (first_name, last_name, email, password, address, phone_number, zip) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                ps.setInt(6, phoneNumber);
                ps.setInt(7, zip);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new DatabaseException("User with email" + email + "was not created");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not create user in database");
        }
    }


    //call this with a false login in userMapperTest
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        User user = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String address = rs.getString("address");
                    int phoneNumber = rs.getInt("phone_number");
                    int roleId = rs.getInt("role");
                    int membershipId = rs.getInt("membership");
                    int zip = rs.getInt("zip");
                    user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip);


                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not login user in database");

        }
        return user;

    }
}
