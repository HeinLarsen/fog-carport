package dat.backend.model.services;

import com.thoughtworks.qdox.model.expression.Or;
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

    public static ArrayList<OrderItem> generateOrder(Carport carport, ConnectionPool connectionPool) throws DatabaseException {
        ArrayList<Wood> woods = MaterialFacade.getAllWood(connectionPool);
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        // get and put sterns
        orderItems.addAll(getSterns(carport.getLength(), woods, OrderItemTask.STERN_UPPER_SIDES));
        orderItems.addAll(getSterns(carport.getWidth(), woods, OrderItemTask.STERN_UPPER_ENDS));

        orderItems.addAll(getSterns(carport.getLength(), woods, OrderItemTask.STERN_LOWER_SIDES));
        orderItems.addAll(getSterns(carport.getWidth(), woods, OrderItemTask.STERN_LOWER_ENDS));

        // get and put spars
       orderItems.add(getSpars(carport.getLength(), carport.getWidth(), woods, OrderItemTask.SPAR));

       // get and put poles
       orderItems.add(getPoles(carport, woods, OrderItemTask.POLE));

       if (carport.hasShed()) {
           orderItems.add(getShedClothing(carport.getShed(), woods, OrderItemTask.SHED_CLOTHING));
       }

        return orderItems;
    }


    private static OrderItem getShedClothing(Shed shed, ArrayList<Wood> woods, OrderItemTask shedClothing) {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.isPressureTreated() && wood.getCategory().equals("brædt") && wood.getLength() == 210) {
                filteredWoods.add(wood);
            }
        }
        int roofOverhang = 30; // 15 cm overlap on each side
        int totalLength = shed.getLength() * 2 + ((shed.getWidth() * 2) - roofOverhang);
        double overlap = 7.5; // 3.75 cm overlap on each side
        int amountOfClothing = (int) Math.ceil(totalLength / overlap);
        double price = filteredWoods.get(0).getPrice() * amountOfClothing;
        OrderItem orderItem = new OrderItem(amountOfClothing, price, shedClothing.getTask());
        orderItem.setMaterial(filteredWoods.get(0));
        return orderItem;
    }

    private static OrderItem getPoles(Carport carport, ArrayList<Wood> woods, OrderItemTask task) {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.getCategory().equals("stolpe")) {
                filteredWoods.add(wood);
            }
        }

        if (carport.hasShed()) {
            Shed shed = carport.getShed();
            int shedPoles = 5;
            int actualLength = carport.getLength() - shed.getLength();
            int amountOfPoles = ((actualLength/180) * 2) + shedPoles;
            double price = 0;
            ArrayList<Wood> poles = new ArrayList<>();
            for (Wood filteredWood : filteredWoods) {
                if (filteredWood.getLength() >= shed.getLength()) {
                    for (int i = 0; i < amountOfPoles; i++) {
                        price += filteredWood.getPrice();
                        poles.add(filteredWood);
                    }
                    break;
                }
            }
            OrderItem orderItem = new OrderItem(poles.size(), price, task.getTask());
            orderItem.setMaterial(poles.get(0));

            return orderItem;
        } else {
            int amountOfPoles = (carport.getLength()/180) * 2;
            double price = 0;
            ArrayList<Wood> poles = new ArrayList<>();
            for (Wood filteredWood : filteredWoods) {
                if (filteredWood.getLength() >= carport.getLength()) {
                    for (int i = 0; i < amountOfPoles; i++) {
                        price += filteredWood.getPrice();
                        poles.add(filteredWood);
                    }
                    break;
                }
            }
            OrderItem orderItem = new OrderItem(poles.size(), price, task.getTask());
            orderItem.setMaterial(poles.get(0));

            return orderItem;
        }

    }

    private static OrderItem getSpars(int length, int width, ArrayList<Wood> woods, OrderItemTask task) {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.getCategory().equals("spærtræ")) {
                filteredWoods.add(wood);
            }
        }

        double amountOfSpars = length/90.0;
        int actualAmountOfSpars = (int) Math.ceil(amountOfSpars);
        ArrayList<Wood> spars = new ArrayList<>();
        double price = 0;
        for (Wood filteredWood : filteredWoods) {
            if (filteredWood.getLength() >= width) {
                for (int i = 0; i < actualAmountOfSpars; i++) {
                    price += filteredWood.getPrice();
                    spars.add(filteredWood);
                }
                break;
            }
        }
        OrderItem orderItem = new OrderItem(spars.size(), price, task.getTask());
        orderItem.setMaterial(spars.get(0));

        return orderItem;
    }

    private static ArrayList<OrderItem> getSterns(int target, ArrayList<Wood> woods, OrderItemTask task)  {
        ArrayList<Wood> filteredWoods = new ArrayList<>();
        for (Wood wood : woods) {
            if (wood.isPressureTreated() && wood.getCategory().equals("brædt")) {
                filteredWoods.add(wood);
            }
        }
        ArrayList<Wood> result = findSterns(target, filteredWoods);
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        Set<Wood> processedWoods = new HashSet<>();
        // count duplicates in result set that as quantity in a new OrderItem object
        if (result != null) {
            for (Wood wood : result) {
                // Skip if the wood has already been processed
                if (processedWoods.contains(wood)) {
                    continue;
                }

                // Count the occurrence of the wood in the result set
                int quantity = Collections.frequency(result, wood);
                double price = wood.getPrice() * quantity;
                OrderItem orderItem = new OrderItem(quantity, price, task.getTask());
                orderItem.setMaterial(wood);
                orderItems.add(orderItem);

                // Add the wood to the set of processed woods
                processedWoods.add(wood);
            }
        }

        return orderItems;
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
