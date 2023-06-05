package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper {

// TODO refactor return types to return the data we receive from the database instead of void

    protected static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT user.*, zip.city FROM user join zip on user.zip = zip.zip";
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
                    String city = rs.getString("city");
                    User user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke hente alle brugere fra databasen");
        }
        return users;
    }

    protected static User getUser(int id, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        String sql = "SELECT user.*, zip.city FROM user join zip on user.zip = zip.zip WHERE id = ?";
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
                    String city = rs.getString("city");
                    user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city);
                } else {
                    throw new DatabaseException("Ingen bruger med id: = " + id + " blev fundet i databasen");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke hente brugeren fra databasen");
        }
        return user;
    }

    protected static User updateUser(int id, String firstName, String lastName, String email, String password, String address, int phoneNumber, int roleId, int membershipId, int zip, String city, ConnectionPool connectionPool) throws DatabaseException {
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
                    throw new DatabaseException("Ingen bruger med id: = " + id + " blev fundet i databasen");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Kunne ikke opdatere brugeren i databasen");
        }
        return new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city);
    }


   protected static User createUser(User u, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "INSERT INTO user (first_name, last_name, email, password, address, phone_number, zip) VALUES (?,?,?,?,?,?,?)";
        User user;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, u.getFirstName());
                ps.setString(2, u.getLastName());
                ps.setString(3, u.getEmail());
                ps.setString(4, u.getPassword());
                ps.setString(5, u.getAddress());
                ps.setInt(6, u.getPhoneNumber());
                ps.setInt(7, u.getZip());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("Ingen bruger med emailen: = " + u.getEmail() + " blev fundet i databasen");
                }
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        int roleId = 1;
                        int membershipId = 1;
                        user = new User(id, u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getAddress(), u.getPhoneNumber(), roleId, membershipId, u.getZip(), u.getCity());
                    } else {
                        throw new DatabaseException("kunne ikke oprette bruger i databasen");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Kunne ikke oprette brugeren i databasen");
        }
        return user;
    }

    //call this with a false login in userMapperTest
    protected static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT user.*, zip.city FROM user join zip on user.zip = zip.zip WHERE email = ? AND password = ?";
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
                    String city = rs.getString("city");
                    user = new User(id, firstName, lastName, email, password, address, phoneNumber, roleId, membershipId, zip, city);


                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Kunne ikke logge på, prøv igen");

        }
        return user;

    }

    protected static void createZipCity(User u, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM zip WHERE zip = ? AND city = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, u.getZip());
                ps.setString(2, u.getCity());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    String sql2 = "INSERT INTO zip (zip, city) VALUES (?,?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                        ps2.setInt(1, u.getZip());
                        ps2.setString(2, u.getCity());
                        int rowsAffected = ps2.executeUpdate();
                        if (rowsAffected != 1) {
                            throw new DatabaseException("Intet postnummer med id: = " + u.getZip() + " blev fundet i databasen");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Kunne ikke oprette postnummer i databasen");
        }
    }
}
