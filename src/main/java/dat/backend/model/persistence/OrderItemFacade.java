package dat.backend.model.persistence;

import dat.backend.model.entities.OrderItem;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderItemFacade {
    public static ArrayList<OrderItem> getOrderItemsByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        return OrderItemMapper.getOrderItemsByOrderId(id, connectionPool);
    }

    public static void createOrderItem(OrderItem orderItem, int orderId, ConnectionPool connectionPool) throws DatabaseException {
        OrderItemMapper.createOrderItem(orderItem, orderId, connectionPool);
    }
}
