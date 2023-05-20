package dat.backend.model.services;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.*;


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


    public static ArrayList<Order> getOrdersByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Order> orderByUserId = OrderFacade.getOrdersByUserId(userId, connectionPool);

        for (Order order : orderByUserId) {
            ArrayList<OrderItem> orderItems = OrderItemFacade.getOrderItemsByOrderId(order.getOrderID(), connectionPool);
            order.addOrderItems(orderItems);
        }
        return orderByUserId;
    }

    public static HashMap<String, ArrayList<Wood>> generateOrder(int length, int width, boolean shed, ConnectionPool connectionPool) throws DatabaseException {
/*        ArrayList<Wood> woods = MaterialFacade.getAllWood(connectionPool);

        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.isPressureTreated() && wood.getCategory().equals("brædt")) {
                filteredWoods.add(wood);
            }
        }

        TreeMap<Integer, ArrayList<Wood>> woodMap = new TreeMap<>();
        for (Wood wood1 : filteredWoods) {
            if (wood1.getLength() >= length) {
                int difference = wood1.getLength() - length;
                ArrayList<Wood> woodList = new ArrayList<>();
                woodList.add(wood1);
                woodMap.put(difference, woodList);
            } else {
                for (Wood wood2 : filteredWoods) {
                    if (wood1.getLength() + wood2.getLength() >= length &&
                            wood1.getWidth() == wood2.getWidth() &&
                            wood1.getHeight() == wood2.getHeight()) {
                        int difference = wood1.getLength() + wood2.getLength() - length;
                        ArrayList<Wood> woodList = new ArrayList<>();
                        woodList.add(wood1);
                        woodList.add(wood2);
                        woodMap.put(difference, woodList);
                    }
                }
            }
        }
        return woodMap;*/
        ArrayList<Wood> woods = MaterialFacade.getAllWood(connectionPool);

        HashMap<String, ArrayList<Wood>> materialList = new HashMap<>();

        // get and put sterns
        ArrayList<Wood> upperSterns = getSterns(length, woods);
        ArrayList<Wood> lowerSterns = getSterns(width, woods);
        materialList.put("upperSterns", upperSterns);
        materialList.put("lowerSterns", lowerSterns);

        // get and put spars
        ArrayList<Wood> spars = getSpars(length, width, woods);
        materialList.put("spars", spars);




        return materialList;
    }

    public static ArrayList<Wood> getSpars(int length, int width, ArrayList<Wood> woods) {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.getCategory().equals("spærtræ")) {
                filteredWoods.add(wood);
            }
        }

        double amountOfSpars = length/90.0;
        int actualAmountOfSpars = (int) Math.ceil(amountOfSpars);
        ArrayList<Wood> spars = new ArrayList<>();
        for (Wood filteredWood : filteredWoods) {
            if (filteredWood.getLength() >= width) {
                for (int i = 0; i < actualAmountOfSpars; i++) {
                    spars.add(filteredWood);
                }
                break;
            }
        }
        return spars;
    }

    public static ArrayList<Wood> getSterns(int target, ArrayList<Wood> woods)  {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.isPressureTreated() && wood.getCategory().equals("brædt")) {
                filteredWoods.add(wood);
            }
        }
        return findSterns(target, filteredWoods);
    }

    private static ArrayList<Wood> findSterns(int target, ArrayList<Wood> woods) {
        TreeMap<Integer, ArrayList<Wood>> woodMap = new TreeMap<>();
        for (Wood wood : woods) {
            // single wood plank is enough for both sides
            if (wood.getLength() >= (target * 2)) {
                ArrayList<Wood> woodCombination = new ArrayList<>();
                woodCombination.add(wood);
                int difference = wood.getLength() - (target * 2);
                woodMap.put(difference, woodCombination);
            } // single wood plank is enough for each side
            else if (wood.getLength() >= target) {
                ArrayList<Wood> woodCombination = new ArrayList<>();
                woodCombination.add(wood);
                woodCombination.add(wood);
                int difference = (wood.getLength() * 2) - (target * 2);
                woodMap.put(difference, woodCombination);
            }

            for (Wood wood2 : woods) {
                if (wood2.getHeight() == wood.getHeight() && wood2.getWidth() == wood.getWidth()) {
                int remaining = target - wood.getLength();
                ArrayList<Wood> woodCombination = new ArrayList<>();
                // check if one wood plank can cover the remaining on both sides
                if ((wood2.getLength() - remaining) >= remaining) {
                    woodCombination.add(wood);
                    woodCombination.add(wood);
                    woodCombination.add(wood2);
                    int difference = ((wood.getLength() * 2) + wood2.getLength()) - (target * 2);
                    woodMap.put(difference, woodCombination);
                }
                // find full combo
                else if (wood.getLength() + wood2.getLength() >= target) {
                    woodCombination.add(wood);
                    woodCombination.add(wood);
                    woodCombination.add(wood2);
                    woodCombination.add(wood2);
                    int difference = ((wood.getLength() * 2) + (wood2.getLength() * 2)) - (target * 2);
                    woodMap.put(difference, woodCombination);
                }
                }


            }

        }
        return woodMap.firstEntry().getValue();

    }


    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        order.setStatus("approved");

        OrderFacade.approveOrder(order, connectionPool);
    }

}
