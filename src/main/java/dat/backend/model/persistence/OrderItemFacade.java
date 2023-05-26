package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class OrderItemFacade
{
    public static ArrayList<OrderItem> getOrderItemsByOrderId(int id, ConnectionPool connectionPool) throws DatabaseException
    {
        ArrayList<OrderItem> orderItems = OrderItemMapper.getOrderItemsWoodByOrderId(id, connectionPool);
        orderItems.addAll(OrderItemMapper.getOrderItemsFittingByOrderId(id, connectionPool));
        orderItems.addAll(OrderItemMapper.getOrderItemsScrewByOrderId(id, connectionPool));
        orderItems.addAll(OrderItemMapper.getOrderItemsRoofTileByOrderId(id, connectionPool));
        return orderItems;
    }

    public static void createOrderItem(OrderItem orderItem, ArrayList<OrderItem> orderItems, int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
        for (OrderItem item : orderItems)
        {
            int itemId = item.getMaterial().getId();
            if (item.getMaterial() instanceof Wood)
            {
                OrderItemMapper.createOrderItemWood(orderItem, orderItems, orderId, itemId, connectionPool);
            } else if (item.getMaterial() instanceof Screw)
            {
                OrderItemMapper.createOrderItemScrew(orderItem ,orderItems, orderId, itemId, connectionPool);
            } else if (item.getMaterial() instanceof Fitting)
            {
                OrderItemMapper.createOrderItemFitting(orderItem, orderItems, orderId, itemId, connectionPool);
            } else if (item.getMaterial() instanceof RoofTile)
            {
                OrderItemMapper.createOrderItemRoofTile(orderItem, orderItems, orderId, itemId, connectionPool);
            }


        }
    }
}
