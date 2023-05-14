package dat.backend.model.persistence;

import dat.backend.model.entities.AMaterial;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderItemFacade {
    public static ArrayList<OrderItem> getOrderItemsByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<OrderItem> orderItems = OrderItemMapper.getOrderItemsWoodByOrderId(id, connectionPool);
        orderItems.addAll(OrderItemMapper.getOrderItemsFittingByOrderId(id, connectionPool));

        return orderItems;
    }

    public static void createOrderItem(OrderItem orderItem, int orderId, int woodId, ConnectionPool connectionPool) throws DatabaseException {
        OrderItemMapper.createOrderItemWood(orderItem, orderId, woodId, connectionPool);
    }
}
