package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest
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
                stmt.execute("CREATE DATABASE IF NOT EXISTS carport_test;");

                // TODO: Create Order table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order LIKE carport.order;");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from `order`");

                // TODO: Insert a few Orders - insert rows into your own tables here
                stmt.execute("ALTER TABLE carport_test.order AUTO_INCREMENT = 1");
                stmt.execute("insert into `order` (user_id) values (1)");
                stmt.execute("insert into `order` (user_id) values (1)");

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            fail("Database connection failed");
        }
    }


    @Test
    void getAllOrders()
    {

    }

    @Test
    void getOrderById() throws DatabaseException
    {
        Order expectedorder = OrderFacade.getOrderById(1,connectionPool);
        int expected = expectedorder.getOrderID();
        int actual = 1;
        assertEquals(expected,actual);

    }

    @Test
    void getOrdersByUserId() throws DatabaseException
    {
        Order expectedorder = OrderFacade.getOrdersByUserId(1,connectionPool).get(0);
        int expected = expectedorder.getOrderID();
        int actual = 1;
        assertEquals(expected,actual);
    }

    @Test
    void approveOrder() throws DatabaseException
    {
        Order expectedorder = OrderFacade.getOrderById(1,connectionPool);
        OrderFacade.approveOrder(expectedorder,connectionPool);
        Enum expected = expectedorder.getStatus();
        String actual = "APPROVED";
        assertEquals(expected,actual);


    }

    @Test
    void getOrdersByStatus()
    {

    }
}