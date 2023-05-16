package dat.backend.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.UserMapper;
import dat.backend.model.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest
{
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://167.71.46.141/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }
    @Test
    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from user");
                stmt.execute("ALTER TABLE user AUTO_INCREMENT = 1;");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into user (first_name, last_name, email, password, address, phone_number, role, membership, zip) " +
                        "values ('admin', 'admin', 'admin', '1234', 'admin', 1234, 2, 3, 3200)," +
                        "('user', 'user', 'user', '1234', '1', 1234, 1, 1, 3200), " +
                        "('Tobias', 'Tonndorff', 'Tobias@Tonndorff.dk', '1234', 'kollegiebakken 15A', 21177311, 1, 2, 2800)");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException, SQLException {
        User expectedUser = new User(1, "user", "user", "user", "1234", "1", 1234, 1, 1, 3200);
        User actualUser = UserService.login("user", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);

    }

    @Test
    void invalidPasswordLogin() throws DatabaseException, SQLException {

          User loginAttempt = UserService.login("user", "12345", connectionPool);
          User expectedLogin = UserService.login("user", "1234", connectionPool);
          assertNotEquals(expectedLogin, loginAttempt);
    }

    @Test
    void invalidEmailLogin() throws DatabaseException, SQLException {
      User loginTry = UserService.login("Tobias@Tonndorff.kd", "1234", connectionPool);
      User expectedLogin = UserService.login("Tobias@Tonndorff.dk", "1234", connectionPool);
        assertNotEquals(expectedLogin, loginTry);


    }

    @Test
    void createUser() throws DatabaseException
    {
        User newUser = UserMapper.createUser("Tobias", "Tonndorff", "Tobias@Tonndorff.dk", "1234", "kollegiebakken 15A", 21177311,  2800, connectionPool);
        User expectedUser = UserMapper.getUser(4, connectionPool);
        assertEquals(expectedUser, newUser);


    }

   @Test
    void updateUserTest() throws DatabaseException
   {
         User expectedUser = UserService.getUser(3, connectionPool);
         User updatedUser = UserService.updateUser(3, "Anders", "larsen", "Ander@Larsen.dk", "Lars1234", "Somewhere in allerød", 123456789, 1, 0, 2800, connectionPool);
       System.out.println(expectedUser);
         System.out.println(updatedUser);
            assertNotEquals(expectedUser, updatedUser);
   }






}