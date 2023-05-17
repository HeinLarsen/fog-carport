package dat.backend.model.services;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.Wood;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OrderService {

    public static Order getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {

        Order orderById = OrderFacade.getOrderById(id, connectionPool);

        ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(id, connectionPool);

        orderById.addOrderItems(orderItems);

        return orderById;
    }


    public static ArrayList<Order> getOrdersByStatus(Status status, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Order> orderByStatus = OrderFacade.getOrdersByStatus(status, connectionPool);

        for (Order order : orderByStatus) {
            ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(order.getOrderID(), connectionPool);
            order.addOrderItems(orderItems);
        }
        return orderByStatus;
    }

    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Order> allOrders = OrderFacade.getAllOrders(connectionPool);

        for (Order order : allOrders) {
            ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(order.getOrderID(), connectionPool);
            order.addOrderItems(orderItems);
        }
        return allOrders;
    }


    public static ArrayList<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException{
        ArrayList<Order> orderByUserId = OrderFacade.getOrdersByUserId(userId, connectionPool);

        for (Order order : orderByUserId) {
            ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(order.getOrderID(), connectionPool);
            order.addOrderItems(orderItems);
        }
        return orderByUserId;
    }

    public static void generateOrder(int length, int width, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Wood> woods = MaterialFacade.getAllWood(connectionPool);
        HashMap<Integer, ArrayList<Wood>> woodHashMap = new HashMap<>();
        for (int i = 0; i < woods.size(); i++) {
            if (woods.get(i).isPressureTreated() && woods.get(i).getCategory().equals("brædt")) {
                if (woods.get(i).getLength() >= length) {
                    int difference = woods.get(i).getLength() - length;
                    ArrayList<Wood> woodList = new ArrayList<>();
                    woodList.add(woods.get(i));
                    woodHashMap.put(difference, woodList);
                } else {
                    for (int j = 0; j < woods.get(i).getLength(); j++) {
                        if (woods.get(j).isPressureTreated() && woods.get(j).getCategory().equals("brædt")) {
                            if(woods.get(i).getLength() + woods.get(j).getLength() >= length && woods.get(i).getWidth() == woods.get(j).getWidth() && woods.get(i).getHeight() == woods.get(j).getHeight()) {
                                int difference = woods.get(i).getLength() + woods.get(j).getLength() - length;
                                ArrayList<Wood> woodList = new ArrayList<>();
                                woodList.add(woods.get(i));
                                woodList.add(woods.get(j));
                                woodHashMap.put(difference, woodList);
                            }
                        }
                    }
                }
            }
        }



    }


    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        order.setStatus("approved");

        OrderFacade.approveOrder(order, connectionPool);
    }

}
