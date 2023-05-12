package dat.backend.persistence;

import dat.backend.model.entities.OrderItem;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderItemFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material_type LIKE carport.material_type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.packaging LIKE carport.packaging;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.type LIKE carport.type;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.material LIKE carport.material;");

                stmt.execute("delete from `order`");
                stmt.execute("delete from order_item");
                stmt.execute("delete from material_type");
                stmt.execute("delete from packaging");
                stmt.execute("delete from type");
                stmt.execute("delete from material");

                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE material AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE material_type AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE packaging AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE type AUTO_INCREMENT = 1;");

                stmt.execute("INSERT INTO packaging (type) VALUES ('stk'), ('pakke'), ('rulle'), ('sæt');");
                stmt.execute("INSERT INTO type (type) VALUES ('trykimp. brædt'), ('lægte'), ('reglar ub.'), ('spærtræ ubh.'), ('trykimp. Brædt'), ('Plastmo Ecolite blåtonet'), ('Plastmo Trapez Brundskrue'), ('hulbånd'), ('universal'), ('universal'), ('skruer'), ('beslagskruer'), ('bræddebolt'), ('firkantskiver'), ('stalddørsgreb'), ('t hængse'), ('vinkelbeslag');");
                stmt.execute("insert into material_type (material) value ('wood'), ('screw'), ('screwPack'), ('roof tile'), ('fitting'),('door handle');");

                stmt.execute("INSERT INTO `material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('360', '200', '25', 'understærnbrædder til for og bag ende', '174.43', '1', '1', '1 ');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('120', '120', '10', 'Til montering af rem på stolper', '464.00', '13', '1', '2');");
                stmt.execute("INSERT INTO `material` (`length`, `description`, `quantity`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('30', 'skruer til tagplader', '200', '441.00', '7', '2', '3');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('600', '300', 'tagplader monteres på spær', '633.00', '6', '1', '4');");
                stmt.execute("INSERT INTO `material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`) VALUES ('1000', '20', '1', 'til vindkryds og spær', '339.00', '8', '3', '5');");

                stmt.execute("INSERT INTO `order` (user_id) VALUES (1);");
                stmt.execute("INSERT INTO `order` (user_id) VALUES (2);");

                stmt.execute("INSERT INTO `order_item` (order_id, type, total_price, material_id, material_type_id, amount) VALUES (1, 1, 200, 1, 1, 2);");
                stmt.execute("INSERT INTO `order_item` (order_id, type, total_price, material_id, material_type_id, amount) VALUES (1, 2, 200, 1, 1, 2);");

            }
        }
        catch (SQLException e) {
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
    void getAllOrderItemsByOrderId() throws DatabaseException {
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(1, connectionPool);
        assertEquals(2, orderItems.size());
    }

    @Test
    void createOrderItem() throws DatabaseException {
        OrderItem orderItem = new OrderItem(1, 1, 1);
        OrderItemFacade.createOrderItem(orderItem, 1, connectionPool);
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(1, connectionPool);
        assertEquals(3, orderItems.size());
    }
}
