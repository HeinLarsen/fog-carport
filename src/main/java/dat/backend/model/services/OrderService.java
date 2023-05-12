package dat.backend.model.services;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.Status;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.ArrayList;


public class OrderService {

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {

        //Hente ÉN Order først, hente ved hjælp af OrderFacaden først:
        Order order = OrderFacade.getOrderById(id, connectionPool);


        //Hente ordreelementerne ved hjælp af OrderItemFacaden (byID):
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(id, connectionPool);

        //Loop igennem orderItems:
        for (OrderItem item : orderItems) {
            item.addMaterial(MaterialFacade.getMaterialById(id, connectionPool));
        }

        //addOrderItems til denne order:
        order.addOrderItems(orderItems);

        //Returnere objektet.
        return order;
    }


    //Ny metode her, hvor den skal hente ordren med status.
    public static ArrayList<Order> getOrderByStatus(Status status, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Order> orders = OrderFacade.getOrdersByStatus(status, connectionPool);


        //Loop igennem Ordrene og hente ordreelementerne samt materialerne:
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

    //Her kalder vi metoder fra Facaderne, for at Admin kan Approve en ordre.
    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        //Ind I Ordren og kalde status metode, setStatus(status)
        order.setStatus("approved");

        //Kald den fra Orderfacade med ordre objektet.
        OrderFacade.approveOrder(order, connectionPool);
    }

}
