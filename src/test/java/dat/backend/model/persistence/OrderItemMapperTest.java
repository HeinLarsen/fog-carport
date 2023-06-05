package dat.backend.model.persistence;

import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.OrderItemTask;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderItemFacade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "password";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test";

    private static ConnectionPool connectionPool;


    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {

                // Create test database - if not exist


                stmt.execute("delete from `order`");
                stmt.execute("delete from order_item_fitting");
                stmt.execute("delete from order_item_screw");
                stmt.execute("delete from order_item_roof_tile");
                stmt.execute("delete from order_item_wood");

                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_roof_tile AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_screw AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_wood AUTO_INCREMENT = 1;");
                stmt.execute("ALTER TABLE order_item_fitting AUTO_INCREMENT = 1;");

                //order_item_fitting
                stmt.execute("insert into carport_test.order_item_fitting values (1, 1, 1, 'firkantskiver', 149.5, 1)");
                stmt.execute("insert into carport_test.order_item_fitting values (2, 2, 1, 'firkantskiver', 149.5, 1)");
                //order_item_screw
                stmt.execute("insert into carport_test.order_item_screw values (1, 1, 1, 'skruer 200', 307.95, 2)");
                stmt.execute("insert into carport_test.order_item_screw values (2, 2, 1, 'skruer 200', 307.95, 2)");
                //order_item_roof_tile
                stmt.execute("insert into carport_test.order_item_roof_tile values (1, 1, 1, 'Plastmo Ecolite blåtonet', 633, 1)");
                stmt.execute("insert into carport_test.order_item_roof_tile values (2, 2, 1, 'Plastmo Ecolite blåtonet', 266, 2)");

                //order_item_wood
                stmt.execute("insert into carport_test.order_item_wood values (1, 1, 1, 'stolpe', 262.03, 2)");
                stmt.execute("insert into carport_test.order_item_wood values (2, 2, 1,'brædt', 174.43, 1)");

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
        ArrayList<OrderItem> orderItems2 = OrderItemFacade.getOrderItemsByOrderId(2, connectionPool);
        assertEquals(4, orderItems.size());
        assertEquals(4, orderItems2.size());
        System.out.println("order items for order 1");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
        System.out.println("order items for order 2");
        for (OrderItem orderItem : orderItems2) {
            System.out.println(orderItem);
        }

    }

    @Test
    void createOrderItem() throws DatabaseException {
        OrderItem orderItem = new OrderItem(1, 1, 1, OrderItemTask.RIM);
        ArrayList<OrderItem> itemList = new ArrayList<>();
        OrderItemFacade.createOrderItem(itemList, 1, connectionPool);
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(1, connectionPool);
        assertEquals(4, orderItems.size());
    }

    @Test
    void createWrongOrderItem() throws DatabaseException{
        OrderItem orderItem = new OrderItem(1, 1, 1, OrderItemTask.RIM);
        ArrayList<OrderItem> itemList = new ArrayList<>();
        OrderItemFacade.createOrderItem(itemList,1, connectionPool);
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(1, connectionPool);
        assertEquals(4, orderItems.size());
        for (OrderItem item : orderItems) {
            System.out.println(item);
        }
    }
}
