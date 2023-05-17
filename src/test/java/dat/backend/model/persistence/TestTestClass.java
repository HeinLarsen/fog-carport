package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class TestTestClass {


        // TODO: Change mysql login credentials if needed below

        private final static String USER = "dev";
        private final static String PASSWORD = "3r!DE32*/fDe";
        private final static String URL = "jdbc:mysql://167.71.46.141/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
        private static ConnectionPool connectionPool;
        private static Status status;



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
        void setUp() {
            try (Connection testConnection = connectionPool.getConnection()) {
                try (Statement stmt = testConnection.createStatement()) {
                    // TODO: Remove all rows from all tables - add your own tables here
                    stmt.execute("delete from `order`");

                    // TODO: Insert a few Orders - insert rows into your own tables here
                    stmt.execute("ALTER TABLE carport_test.order AUTO_INCREMENT = 1");

                    stmt.execute("insert into `order` (user_id, length,width, shed)values (1,1,1,true)");
                    stmt.execute("insert into `order` (user_id, length,width, shed)values (1,1,1,true)");


                }
            } catch (SQLException e) {
                e.printStackTrace();
                fail("Database connection failed");
            }
        }



            @Test
            void testNeedsToFail() throws DatabaseException
            {
                Order expOrder = OrderFacade.getOrderById(1, connectionPool);
                Order actOrder = OrderFacade.getOrderById(2, connectionPool);
                assertEquals(expOrder, actOrder);
            }


}
