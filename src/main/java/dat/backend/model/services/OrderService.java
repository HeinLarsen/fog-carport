package dat.backend.model.services;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.ArrayList;


public class OrderService {

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {

        Order order = OrderFacade.getOrderById(id, connectionPool);

        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(id, connectionPool);

        for (OrderItem item : orderItems) {
            item.addMaterial(MaterialFacade.getMaterialById(id, connectionPool));
        }

        order.addOrderItems(orderItems);

        return order;

    }



    public static ArrayList<Order> getOrderByStatus(Status status, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Order> orders = OrderFacade.getOrdersByStatus(status, connectionPool);

        for (Order order : orders) {
            ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(order.getOrderID(), connectionPool);
            for (OrderItem item : orderItems) {
                item.addMaterial(MaterialFacade.getMaterialById(item.getID(), connectionPool));
            }
            order.addOrderItems(orderItems);
        }
        return orders;

    }

    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        order.setStatus("approved");

        OrderFacade.approveOrder(order, connectionPool);
    }

}
