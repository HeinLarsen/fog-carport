package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{

    static User login(String email, String password, ConnectionPool connectionpool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionpool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
               ps.setString(1, email);
               ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String address = rs.getString("address");
                    int phone_number = rs.getInt("phone_number");
                    int role_id = rs.getInt("role_id");
                    int membership_id = rs.getInt("membership_id");
                    int zip = rs.getInt("zip");
                    user = new User(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip);
                } else
                {
                    throw new DatabaseException("Wrong email or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }



    static User createUser(int id, String first_name, String last_name, String email, String password, String address, int phone_number, int role_id, int membership_id, int zip, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip) values (?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ps.setString(2, first_name);
                ps.setString(3, last_name);
                ps.setString(4, email);
                ps.setString(5, password);
                ps.setString(6, address);
                ps.setInt(7, phone_number);
                ps.setInt(8, role_id);
                ps.setInt(9, membership_id);
                ps.setInt(10, zip);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(id, first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip);
                }else
                {
                    throw new DatabaseException("the user with email = " + email + "could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert user into database");
        }
        return user;
    }


}
