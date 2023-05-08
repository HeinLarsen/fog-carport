package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    user = new User(email, password, role);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String username, String password, String role, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (username, password, role) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(username, password, role);
                } else
                {
                    throw new DatabaseException("The user with username = " + username + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }


    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT user.first_name, user.last_name, user.address, user.phone_number, user.role_id, user.membership_id, zip.zip, zip.city FROM user inner join zip on user.zip = zip.zip";
        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("email");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone_number");
                    int role = rs.getInt("role_id");
                    int membership = rs.getInt("membership_id");
                    int zip = rs.getInt("zip");
                    String city = rs.getString("city");
                    User user = new User();
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get all users from database");
        }
        return users;
    }

    public static void updateUser(User user, ConnectionPool connectionPool) throws  DatabaseException {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, address = ?, phone_number = ?, role_id = ?, membership_id = ?, zip = ? WHERE email = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getAddress());
                ps.setInt(4, user.getPhoneNumber());
                ps.setInt(5, user.getRole());
                ps.setInt(6, user.getMembership());
                ps.setInt(7, user.getZip());
                ps.setString(8, user.getEmail());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
