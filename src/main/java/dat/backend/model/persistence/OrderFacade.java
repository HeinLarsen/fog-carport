package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderFacade {

    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrders(connectionPool);
    }

    public static Order getOrder(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrder(id, connectionPool);
    }

    public static ArrayList<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrdersByUserId(userId, connectionPool);
    }

    public static void updateOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.updateOrder(order, connectionPool);
    }

    public static void createOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.createOrder(order, connectionPool);
    }

    public static void approveOrder(int id, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.approveOrder(id, connectionPool);
    }

    public static void deleteOrder(int id, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.deleteOrder(id, connectionPool);
    }
}
