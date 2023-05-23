package dat.backend.model.services;

import com.thoughtworks.qdox.model.expression.Or;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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

    public static List<OrderItem> generateOrder(Carport carport, ConnectionPool connectionPool) throws DatabaseException {
        List<Wood> woods = MaterialFacade.getAllWood(connectionPool);
        List<Screw> screws = MaterialFacade.getAllScrews(connectionPool);
        List<OrderItem> orderItems = new ArrayList<>();

        // Get and put sterns
        orderItems.addAll(getSterns(carport.getLength(), woods, OrderItemTask.STERN_UPPER_SIDES));
        orderItems.addAll(getSterns(carport.getWidth(), woods, OrderItemTask.STERN_UPPER_ENDS));
        orderItems.addAll(getSterns(carport.getLength(), woods, OrderItemTask.STERN_LOWER_SIDES));
        orderItems.addAll(getSterns(carport.getWidth(), woods, OrderItemTask.STERN_LOWER_ENDS));

        // Get and put spars
        orderItems.add(getSpars(carport.getLength(), carport.getWidth(), woods, OrderItemTask.SPAR));

        // Get and put poles
        orderItems.add(getPoles(carport, woods, OrderItemTask.POLE));

        // get and put screws for the roof

        //orderItems.add(getRoofScrews(carport, screws, OrderItemTask.ROOF_TILE_SCREWS);


        if (carport.hasShed()) {
            orderItems.add(getShedClothing(carport.getShed(), woods, OrderItemTask.SHED_CLOTHING));
        }



        return orderItems;
    }

    private static OrderItem getShedClothing(Shed shed, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood ->
                wood.isPressureTreated() && wood.getCategory().equals("brædt") && wood.getLength() == 210
        );

        int roofOverhang = 30; // 15 cm overlap on each side
        int totalLength = shed.getLength() * 2 + ((shed.getWidth() * 2) - roofOverhang);
        double overlap = 7.5; // 3.75 cm overlap on each side
        int amountOfClothing = (int) Math.ceil(totalLength / overlap);
        double price = filteredWoods.get(0).getPrice() * amountOfClothing;

        OrderItem orderItem = new OrderItem(amountOfClothing, price, task.getTask());
        orderItem.setMaterial(filteredWoods.get(0));

        return orderItem;
    }

    private static OrderItem getPoles(Carport carport, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.getCategory().equals("stolpe"));

        List<Wood> poles = new ArrayList<>();
        int amountOfPoles;
        double price = 0;

        if (carport.hasShed()) {
            Shed shed = carport.getShed();
            int shedPoles = 5;
            int actualLength = carport.getLength() - shed.getLength();
            amountOfPoles = ((actualLength / 180) * 2) + shedPoles;

            for (Wood filteredWood : filteredWoods) {
                if (filteredWood.getLength() >= shed.getLength()) {
                    for (int i = 0; i < amountOfPoles; i++) {
                        price += filteredWood.getPrice();
                        poles.add(filteredWood);
                    }
                    break;
                }
            }
        } else {
            amountOfPoles = (carport.getLength() / 180) * 2;

            for (Wood filteredWood : filteredWoods) {
                if (filteredWood.getLength() >= carport.getLength()) {
                    for (int i = 0; i < amountOfPoles; i++) {
                        price += filteredWood.getPrice();
                        poles.add(filteredWood);
                    }
                    break;
                }
            }
        }

        OrderItem orderItem = new OrderItem(poles.size(), price, task.getTask());
        orderItem.setMaterial(poles.get(0));

        return orderItem;
    }

    private static OrderItem getSpars(int length, int width, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.getCategory().equals("spærtræ"));

        double amountOfSpars = length / 90.0;
        int actualAmountOfSpars = (int) Math.ceil(amountOfSpars);
        List<Wood> spars = new ArrayList<>();
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

    private static List<OrderItem> getSterns(int target, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood ->
                wood.isPressureTreated() && wood.getCategory().equals("brædt")
        );

        List<Wood> result = findSterns(target, filteredWoods);
        List<OrderItem> orderItems = new ArrayList<>();

        Set<Wood> processedWoods = new HashSet<>();
        for (Wood wood : result) {
            if (processedWoods.contains(wood)) {
                continue;
            }

            int quantity = Collections.frequency(result, wood);
            double price = wood.getPrice() * quantity;

            OrderItem orderItem = new OrderItem(quantity, price, task.getTask());
            orderItem.setMaterial(wood);
            orderItems.add(orderItem);

            processedWoods.add(wood);
        }

        return orderItems;
    }

    private static List<Wood> findSterns(int target, List<Wood> woods) {
        TreeMap<Integer, List<Wood>> woodMap = new TreeMap<>();

        for (Wood wood : woods) {
            if (wood.getLength() >= (target * 2)) {
                List<Wood> woodCombination = new ArrayList<>();
                woodCombination.add(wood);
                int difference = wood.getLength() - (target * 2);
                woodMap.put(difference, woodCombination);
            } else if (wood.getLength() >= target) {
                List<Wood> woodCombination = new ArrayList<>();
                woodCombination.add(wood);
                woodCombination.add(wood);
                int difference = (wood.getLength() * 2) - (target * 2);
                woodMap.put(difference, woodCombination);
            }

            for (Wood wood2 : woods) {
                if (wood2.getHeight() == wood.getHeight() && wood2.getWidth() == wood.getWidth()) {
                    int remaining = target - wood.getLength();
                    List<Wood> woodCombination = new ArrayList<>();

                    if ((wood2.getLength() - remaining) >= remaining) {
                        woodCombination.add(wood);
                        woodCombination.add(wood);
                        woodCombination.add(wood2);
                        int difference = ((wood.getLength() * 2) + wood2.getLength()) - (target * 2);
                        woodMap.put(difference, woodCombination);
                    } else if (wood.getLength() + wood2.getLength() >= target) {
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

    

    private static List<Wood> filterWoods(List<Wood> woods, Predicate<Wood> predicate) {
        return woods.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    //I denne metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        order.setStatus("approved");

        OrderFacade.approveOrder(order, connectionPool);
    }



}
