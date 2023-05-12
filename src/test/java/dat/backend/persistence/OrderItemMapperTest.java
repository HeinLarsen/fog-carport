package dat.backend.persistence;

import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderItemMapperTest {
    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://167.71.46.141/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;


    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {


                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order LIKE carport.order;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order_item LIKE carport.order_item;");

                stmt.execute("delete from order");
                stmt.execute("delete from order_item");

                stmt.execute("ALTER TABLE order AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item AUTO_INCREMENT = 1;");

//                stmt.execute("INSERT INTO order (user_id, created) VALUES (1, timestamp);");



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
}
