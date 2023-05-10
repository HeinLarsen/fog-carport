package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{



    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    int phone_number = rs.getInt("phone_number");
                    int role_id = rs.getInt("role_id");
                    int membership_id = rs.getInt("membership_id");
                    int zip = rs.getInt("zip");
                    User user = new User(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip);
                    users.add(user);
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not get all users from database");
        }
        return users;
    }

    public static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    int phone_number = rs.getInt("phone_number");
                    int role_id = rs.getInt("role_id");
                    int membership_id = rs.getInt("membership_id");
                    int zip = rs.getInt("zip");
                    user = new User(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip);
                } else
                {
                    throw new DatabaseException("No user with id = " + id + " found in database");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not get user from database");
        }
        return user;
    }

    public static void updateUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, password = ?, address = ?, phone_number = ?, role_id = ?, membership_id = ?, zip = ? WHERE id = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, first_name);
                ps.setString(2, last_name);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                ps.setInt(6, phone_number);
                ps.setInt(7, role_id);
                ps.setInt(8, membership_id);
                ps.setInt(9, zip);
                ps.setInt(10, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1)
                {
                    throw new DatabaseException("No user with id = " + id + " found in database");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not update user in database");
        }
    }

    public static void createUser(String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "INSERT INTO user (first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, first_name);
                ps.setString(2, last_name);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                ps.setInt(6, phone_number);
                ps.setInt(7, role_id);
                ps.setInt(8, membership_id);
                ps.setInt(9, zip);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1)
                {
                    throw new DatabaseException("No user with email = " + email + " found in database");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not create user in database");
        }
    }

    public static void login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (!rs.next())
                {
                    throw new DatabaseException("No user with email = " + email + " found in database");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not login user in database");
        }
    }


}
