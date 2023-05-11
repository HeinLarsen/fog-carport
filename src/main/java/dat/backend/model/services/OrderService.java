package dat.backend.model.services;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.OrderItemFacade;

import java.util.ArrayList;

public class OrderService {

    public static Order getOrder(int id, ConnectionPool connectionPool) throws DatabaseException {

        //Hente ÉN Order først, hente ved hjælp af OrderFacaden først:
        Order order = OrderFacade.getOrderById(id, connectionPool);


        //Hente ordreelementerne ved hjælp af OrderItemFacaden (byID):
        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(id, connectionPool);

        //Loop igennem orderItems:
        for(OrderItem item: orderItems){
            item.addMaterial(MaterialFacade.getMaterialById(id,connectionPool));
        }

        //addOrderItems til denne order:
        order.addOrderItems(orderItems);

        //Returnere objektet.
        return order;
    }

    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    //Her kalder vi metoder fra Facaderne og Mapperne, for at Admin kan Approve en ordre.
    public void updateOrder(Order order) {

    }
}
