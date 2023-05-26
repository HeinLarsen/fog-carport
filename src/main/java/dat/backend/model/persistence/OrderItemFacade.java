package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class OrderItemFacade {
    public static ArrayList<OrderItem> getOrderItemsByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<OrderItem> orderItems = OrderItemMapper.getOrderItemsWoodByOrderId(id, connectionPool);
        orderItems.addAll(OrderItemMapper.getOrderItemsFittingByOrderId(id, connectionPool));
        orderItems.addAll(OrderItemMapper.getOrderItemsScrewByOrderId(id, connectionPool));
        orderItems.addAll(OrderItemMapper.getOrderItemsRoofTileByOrderId(id, connectionPool));
        return orderItems;
    }

    public static void createOrderItem(OrderItem orderItem, int orderId, int itemId, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<OrderItem> items = new ArrayList<>();
        
        if (orderItem.getMaterial() instanceof Wood) {
            OrderItemMapper.createOrderItemWood(orderItem, orderId, itemId, connectionPool);
        } else if (orderItem.getMaterial() instanceof Screw) {
            OrderItemMapper.createOrderItemScrew(orderItem, orderId, itemId, connectionPool);
        } else if (orderItem.getMaterial() instanceof Fitting){
            OrderItemMapper.createOrderItemFitting(orderItem, orderId, itemId, connectionPool);
        } else if (orderItem.getMaterial() instanceof RoofTile){
            OrderItemMapper.createOrderItemRoofTile(orderItem, orderId, itemId, connectionPool);
        }


    }
}
