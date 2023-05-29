package dat.backend.model.services;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static dat.backend.model.services.OrderService.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrdreServiceTest {

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://167.71.46.141/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    public OrdreServiceTest() throws SQLException {
    }


    @BeforeAll
    public static void setUpClass() throws SQLException {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {

                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order LIKE carport.order;");

                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order_item_fitting LIKE carport.order_item_fitting;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order_item_roof_tile LIKE carport.order_item_roof_tile;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order_item_screw LIKE carport.order_item_screw;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order_item_wood LIKE carport.order_item_wood;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.unit LIKE carport.unit;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.category LIKE carport.category;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.wood LIKE carport.wood;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.screw LIKE carport.screw;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.roof_tile LIKE carport.roof_tile;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.fitting LIKE carport.fitting;");

                stmt.execute("delete from unit");
                stmt.execute("delete from category");
                stmt.execute("delete from wood");
                stmt.execute("delete from screw");
                stmt.execute("delete from roof_tile");
                stmt.execute("delete from fitting");
                stmt.execute("delete from order_item_fitting");
                stmt.execute("delete from order_item_roof_tile");
                stmt.execute("delete from order_item_screw");
                stmt.execute("delete from order_item_wood");
                stmt.execute("delete from `order`");

                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_fitting AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_roof_tile AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_screw AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_wood AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE unit AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE category AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE wood AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE screw AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE roof_tile AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE fitting AUTO_INCREMENT = 1;");

                stmt.execute("INSERT INTO `carport_test`.`unit` (`unit`) VALUES ('stk'), ('pakke'), ('rulle'), ('sæt')");

                stmt.execute("insert into carport_test.category(category) values ('brædt'), ('lægte'), ('reglar'), ('spærtræ'), ('stolpe');");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 20.0, 2.5, 1, 1, 1, 174.43);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 20.0, 2.5, 1, 1, 1, 262.03);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 12.5, 2.5, 1, 1, 1, 108.90);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 12.5, 25, 1, 1, 1, 163.35);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (420, 7.3, 3.8, 0, 2, 1, 120.13);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (270, 9.5, 4.5, 0, 3, 1, 5628);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (240, 9.5, 4.5, 0, 3, 1, 56.28);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (480, 19.5, 4.5, 0, 4, 1, 205.44);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (600, 19.5, 4.5, 0, 4, 1, 257.70);");

                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (300, 9.7, 97, 1, 5, 1, 134.85);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (210, 10.0, 1.9, 1, 1, 1, 20.16);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (540, 10.0, 1.9, 1, 1, 1, 51.84);");
                stmt.execute("INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`) VALUES (360, 10.0, 1.9, 1, 1, 1, 34.56);");

                stmt.execute("INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`) VALUES ('Plastmo Ecolite blåtonet', 600, 10.9, 1, 633.00);");
                stmt.execute("INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`) VALUES ('Plastmo Ecolite blåtonet', 360, 10.9, 1, 266);");

                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('plastmo bundskruer 200 stk', 0, 30, 2, 441);");
                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('skruer 200 stk.', 4.5, 60, 2, 307.95);");
                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('Skruer 400 stk.', 4.5, 70, 2, 149.95);");
                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('beslagskruer 250 stk.', 4.5, 50, 2, 160.95);");
                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('Skruer 300 stk.', 4.0, 50, 2, 263.95);");
                stmt.execute( "INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`) VALUES ('bræddebolt', 10, 120, 2, 464);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`) VALUES ('firkantskiver', 40, 40, 11, 1, 149.50);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)VALUES ('hulbånd', 20, 1000, 1, 3, 339);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`,`height`, `unit`, `price`) VALUES ('universal',190, 1, 76.95);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`,`height`, `unit`, `price`) VALUES ('universal',190, 1, 76.95);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`, `width`, `height`, `unit`, `price`) VALUES ('stalddørsgreb', 75, 50, 4, 183.95);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`, `height`, `unit`, `price`) VALUES ('t hængsel', 390, 2, 156.95);");
                stmt.execute( "INSERT INTO `carport_test`.`fitting` (`name`, `height`, `unit`, `price`) VALUES ('vinkelbeslag', 35, 1, 13.95);");


                stmt.execute("INSERT INTO `carport_test`.`order` (`user_id`, `length`, `width`, `shed`) VALUES (1, '20', '5', '0');");
                stmt.execute("INSERT INTO `carport_test`.`order_item_wood` (`order_id`, `quantity`, `description`, `price`, `item_id` ) VALUES (1, '3', 'hejhej', '10', '1');");
                stmt.execute("INSERT INTO `carport_test`.`order_item_fitting` (`order_id`, `quantity`, `description`, `price`, `item_id` ) VALUES (1, '3', 'hejhej', '10', '1');");
                stmt.execute("INSERT INTO `carport_test`.`order_item_screw` (`order_id`, `quantity`, `description`, `price`, `item_id` ) VALUES (1, '3', 'hejhej', '10', '1');");
                stmt.execute("INSERT INTO `carport_test`.`order_item_roof_tile` (`order_id`, `quantity`, `description`, `price`, `item_id` ) VALUES (1, '3', 'hejhej', '10', '1');");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }


    @Test
    void GetOrderById() throws DatabaseException {
        Order order = OrderService.getOrderById(1, connectionPool);
        int expectedId = order.getOrderID();
        int actualId = 1;
        assertEquals(expectedId, actualId);
    }

    @Test
    void GetOrdersByUserId() throws DatabaseException {
        ArrayList<Order> ordersByUserId = OrderFacade.getOrdersByUserId(1, connectionPool);
        int expectedId = ordersByUserId.get(0).getOrderID();
        int actualId = 1;
        assertEquals(expectedId, actualId);
    }

    @Test
    void GetOrdersByStatus() throws DatabaseException {
        ArrayList<Order> ordersByStatus = OrderService.getOrdersByStatus(Status.PENDING, connectionPool);
        int expectedId = ordersByStatus.get(0).getOrderID();
        int actualId = 1;
        assertEquals(expectedId, actualId);
    }

    @Test
    void createOrder() throws DatabaseException {
        Carport carport = new Carport(780, 600, new Shed(210, 570));
        OrderService.addOrder(1, carport, connectionPool);
        Order order = OrderService.getOrderById(2, connectionPool);
        int expectedId = order.getOrderID();
        int actualId = 2;
        assertEquals(expectedId, actualId);
    }


    @Test
    void GetAllOrders() throws DatabaseException {

        ArrayList<Order> expected = OrderService.getAllOrders(connectionPool);
        int actual = expected.size();
        assertEquals(2, actual);
    }

}




