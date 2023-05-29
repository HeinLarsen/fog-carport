package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderFacade {

    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrders(connectionPool);
    }

    public static ArrayList<Order> getOrdersByStatus(Status status, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrdersByStatus(status, connectionPool);
    }

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrderById(id, connectionPool);
    }

    public static ArrayList<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrdersByUserId(userId, connectionPool);
    }

    public static void createOrder(Order order, int userId, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.createOrder(order, userId, connectionPool);
    }

    public static void approveOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.approveOrder(order, connectionPool);
    }

    public static void cancelOrder(int id, Enum Status, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.cancelOrder(id, Status, connectionPool);
    }
}
