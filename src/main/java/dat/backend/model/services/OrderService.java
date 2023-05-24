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
        List<RoofTile> roofTiles = MaterialFacade.getAllRoofTiles(connectionPool);
        List<Screw> screws = MaterialFacade.getAllScrews(connectionPool);
        List<Fitting> fittings = MaterialFacade.getAllFittings(connectionPool);
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

        // Get and put roof tiles
        orderItems.add(getRoofTiles(carport, roofTiles, OrderItemTask.ROOF_TILE));

        // Get and put water board
        orderItems.addAll(getWaterBoard(carport.getLength(), woods, OrderItemTask.WATERBOARD_SIDES));
        orderItems.addAll(getWaterBoard(carport.getWidth(), woods, OrderItemTask.WATERBOARD_ENDS));

        // Get screws for roof tile
        orderItems.add(getRoofScrews(screws , OrderItemTask.ROOF_TILE_SCREWS));

        // Get metalbands for spars
        orderItems.add(getMetalBand(fittings, OrderItemTask.CROSSWIND));







        if (carport.hasShed()) {
            orderItems.add(getShedClothing(carport.getShed(), woods, OrderItemTask.SHED_CLOTHING));
            orderItems.add(getRims(carport.getLength() - carport.getShed().getLength(), woods, OrderItemTask.RIM));
            orderItems.add(getRims(carport.getShed().getLength(), woods, OrderItemTask.RIM_SHED));
            orderItems.addAll(getShedSupports(carport.getShed().getLength(), woods, OrderItemTask.SHED_SUPPORT_POSTS_SIDES));
            orderItems.addAll(getShedSupports(carport.getWidth(), woods, OrderItemTask.SHED_SUPPORT_POSTS_GABLE));
            orderItems.add(calculateWoodPlankLengthForZShape(woods, OrderItemTask.SHED_Z));
        } else {
            orderItems.add(getRims(carport.getLength(), woods, OrderItemTask.RIM));
        }

        return orderItems;
    }

    private static OrderItem calculateWoodPlankLengthForZShape(List<Wood> woods, OrderItemTask task) {
        // Constants for door height and width
        int height = 210; // cm
        int width = 50; // cm

        // Calculate the length of the horizontal sections
        int horizontalLength = width * 2;

        // Calculate the length of the vertical section
        int verticalLength = height - width;

        // Calculate the total length of the wood plank
        int totalLength = horizontalLength + verticalLength;

        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.getCategory().equals("lægte") && wood.getLength() >= totalLength);

        // Find the shortest wood plank that can be used
        Wood wood = Collections.min(filteredWoods, Comparator.comparing(Wood::getLength));
        OrderItem orderItem = new OrderItem(1, wood.getPrice(), task.getTask());
        orderItem.setMaterial(wood);
        return  orderItem;

    }



    private static List<OrderItem> getShedSupports(int target, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.getCategory().equals("brædt"));
        List<Wood> result = findSterns(target, filteredWoods);
        List<OrderItem> orderItems = new ArrayList<>();
        Set<Wood> processedWoods = new LinkedHashSet<>();

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

    private static List<OrderItem> getWaterBoard(int target, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.isPressureTreated() && wood.getCategory().equals("brædt") && wood.getWidth() == 100);
        return getShedSupports(target, filteredWoods, task);
    }


    private static OrderItem getRoofTiles(Carport carport, List<RoofTile> roofTiles, OrderItemTask task) {
        TreeMap<Integer, RoofTile> roofTileMap = new TreeMap<>();
        for (RoofTile roofTile : roofTiles) {
            int tilesRequired = (int) Math.ceil((double) carport.getLength() / roofTile.getWidth());
            int coverage = tilesRequired * roofTile.getLength() * roofTile.getWidth();
            roofTileMap.put(coverage, roofTile);
        }
        RoofTile bestRoofTile = roofTileMap.firstEntry().getValue();
        int tilesRequired = (int) Math.ceil((double) carport.getLength() / bestRoofTile.getWidth());
        double totalPrice = tilesRequired * bestRoofTile.getPrice();
        OrderItem orderItem = new OrderItem(tilesRequired, totalPrice, task.getTask());
        orderItem.setMaterial(bestRoofTile);
        return orderItem;
    }

    private static OrderItem getRoofScrews(List<Screw> screws, OrderItemTask task) {
        List<Screw> filteredScrews = filterScrews(screws, screw -> screw.getName().equals("plastmo bundskruer 200 stk"));
        int amountOfTiles = 4;
        int amountOfScrews = amountOfTiles* 12;
        List<Screw> roofscrews = new ArrayList<>();
        double price = 0;
        price = filteredScrews.get(0).getPrice();

        for (Screw filteredscrews2 : filteredScrews)
        {
            for (int i = 0; i < amountOfScrews; i++)
            {
                roofscrews.add(filteredscrews2);
            }
        }

        OrderItem orderItem = new OrderItem(filteredScrews.size(), price, task.getTask());
        orderItem.setMaterial(filteredScrews.get(0));
        return orderItem;
    }

    private static OrderItem getMetalBand(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> filteredFittings = filterFittings(fittings, fitting -> fitting.getName().equals("hulbånd"));
        double amountOfMetalBand = 7.25;
        double totalAmountOfMetalBand = amountOfMetalBand * 2;
        double price = 0;
        List<Fitting> metalBand = new ArrayList<>();
        price = filteredFittings.get(0).getPrice();
        for (Fitting filteredFitting : filteredFittings)
        {
            metalBand.add(filteredFitting);
        }
        OrderItem orderItem = new OrderItem(filteredFittings.size(), price, task.getTask());
        orderItem.setMaterial(filteredFittings.get(0));
        return orderItem;
    }

    private static OrderItem getSternScrews(List<Screw> screws, OrderItemTask task){
    List<Screw> fliteredScrews = filterScrews(screws, screw -> screw.getName().equals("skruer 200 stk."));
    double amountOfSternScrews = 3;
    double price = 0;
    List<Screw> sternScrews = new ArrayList<>();
    price = fliteredScrews.get(0).getPrice();
    for(Screw filteredScrew : fliteredScrews){
        sternScrews.add(filteredScrew);
    }

    OrderItem orderItem = new OrderItem(fliteredScrews.size(), price, task.getTask());
    orderItem.setMaterial(fliteredScrews.get(0));
    return orderItem;
    }

    private static OrderItem getFittingScrews(List<Screw> screws, OrderItemTask task){
        List<Screw> filteredScrews = filterScrews(screws, screw -> screw.getName().equals("beslagskruer 250 stk."));
        double amountOfFittingScrews = 2;
        double price = 0;
        List<Screw> fittingScrews = new ArrayList<>();
        price = filteredScrews.get(0).getPrice();
        for (Screw filteredScrew : filteredScrews)
        {
            fittingScrews.add(filteredScrew);
        }

        OrderItem orderItem = new OrderItem(filteredScrews.size(), price, task.getTask());
        orderItem.setMaterial(filteredScrews.get(0));
        return orderItem;
    }

    private static OrderItem getBolts(List<Screw> screws, OrderItemTask task){
        List<Screw> filteredScrews = filterScrews(screws, screw -> screw.getName().equals("bræddebolt"));
        double amountOfBolts = 18;
        double price = 0;
        List<Screw> bolts = new ArrayList<>();
        price = filteredScrews.get(0).getPrice();
        for (Screw filteredScrew : filteredScrews)
        {
            bolts.add(filteredScrew);
        }

        OrderItem orderItem = new OrderItem(filteredScrews.size(), price, task.getTask());
        orderItem.setMaterial(filteredScrews.get(0));
        return orderItem;
    }

    private static OrderItem getDiscs(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> fittings1 = filterFittings(fittings, fitting -> fitting.getName().equals("firkantskiver"));
        double amountOfDiscs = 12;
        double price = 0;
        List<Fitting> discs = new ArrayList<>();
        price = fittings1.get(0).getPrice();
        for (Fitting fitting : fittings1)
        {
            discs.add(fitting);
        }

        OrderItem orderItem = new OrderItem(fittings1.size(), price, task.getTask());
        orderItem.setMaterial(fittings1.get(0));
        return orderItem;
    }

    private static OrderItem getScrewsForOuterClothing(List<Screw> screws, OrderItemTask task){
        List<Screw> filteredScrews = filterScrews(screws, screw -> screw.getName().equals("Skruer 400 stk."));
        double amountOfScrews = 800;
        double price = 0;
        List<Screw> screwsForOuterClothing = new ArrayList<>();
        price = filteredScrews.get(0).getPrice();
        for (Screw filteredScrew : filteredScrews)
        {
            screwsForOuterClothing.add(filteredScrew);
        }

        OrderItem orderItem = new OrderItem(filteredScrews.size(), price, task.getTask());
        orderItem.setMaterial(filteredScrews.get(0));
        return orderItem;
    }

    private static OrderItem getScrewsForInnerClothing(List<Screw> screws, OrderItemTask task){
        List<Screw> filteredScrews = filterScrews(screws, screw -> screw.getName().equals("Skruer 300 stk."));
        double amountOfScrews = 600;
        double price = 0;
        List<Screw> screwsForOuterClothing = new ArrayList<>();
        price = filteredScrews.get(0).getPrice();
        for (Screw filteredScrew : filteredScrews)
        {
            screwsForOuterClothing.add(filteredScrew);
        }

        OrderItem orderItem = new OrderItem(filteredScrews.size(), price, task.getTask());
        orderItem.setMaterial(filteredScrews.get(0));
        return orderItem;
    }


    private static OrderItem getDoorHandle(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> filteredFittings = filterFittings(fittings, fitting -> fitting.getName().equals("stalddørsgreb"));
        double amountOfDoorHandle = 1;
        double price = 0;
        List<Fitting> doorHandle = new ArrayList<>();
        price = filteredFittings.get(0).getPrice();
        for (Fitting filteredFitting : filteredFittings)
        {
            doorHandle.add(filteredFitting);
        }

        OrderItem orderItem = new OrderItem(filteredFittings.size(), price, task.getTask());
        orderItem.setMaterial(filteredFittings.get(0));
        return orderItem;
    }

    private static OrderItem getHinges(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> filteredFittings = filterFittings(fittings, fitting -> fitting.getName().equals("t hængsel"));
        double amountOfHinges = 2;
        double price = 0;
        List<Fitting> hinges = new ArrayList<>();
        price = filteredFittings.get(0).getPrice();
        for (Fitting filteredFitting : filteredFittings)
        {
            hinges.add(filteredFitting);
        }

        OrderItem orderItem = new OrderItem(filteredFittings.size(), price, task.getTask());
        orderItem.setMaterial(filteredFittings.get(0));
        return orderItem;

    }

    private static OrderItem getAngleFittings(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> filteredFittings = filterFittings(fittings, fitting -> fitting.getName().equals("vinkelbeslag"));
        double amountOfAngleFittings = 8;
        double price = 0;
        List<Fitting> angleFittings = new ArrayList<>();
        price = filteredFittings.get(0).getPrice();
        for (Fitting filteredFitting : filteredFittings)
        {
            angleFittings.add(filteredFitting);
        }

        OrderItem orderItem = new OrderItem(filteredFittings.size(), price, task.getTask());
        orderItem.setMaterial(filteredFittings.get(0));
        return orderItem;
    }



    private static OrderItem getUniversialFitting(List<Fitting> fittings, OrderItemTask task){
        List<Fitting> filteredFittings = filterFittings(fittings, fitting -> fitting.getName().equals("4,5 x 60 mm. skruer 200 stk."));
        double amountOfUniversialFitting = 3;
        double price = 0;
        List<Fitting> universialFitting = new ArrayList<>();
        price = filteredFittings.get(0).getPrice();
        for (Fitting filteredFitting : filteredFittings)
        {
            universialFitting.add(filteredFitting);
        }
        OrderItem orderItem = new OrderItem(filteredFittings.size(), price, task.getTask());
        orderItem.setMaterial(filteredFittings.get(0));
        return orderItem;
    }

    private static OrderItem getRims(int length, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood -> wood.getCategory().equals("spærtræ"));

        TreeMap<Integer, Wood> woodMap = new TreeMap<>();
        for (Wood filteredWood : filteredWoods) {
            int difference = Math.abs(filteredWood.getLength() - length);
            woodMap.put(difference, filteredWood);
        }

        Wood bestWood = woodMap.firstEntry().getValue();
        int quantity = (bestWood.getLength() / 2 >= length) ? 1 : 2;
        double price = bestWood.getPrice() * quantity;

        OrderItem orderItem = new OrderItem(quantity, price, task.getTask());
        orderItem.setMaterial(bestWood);
        return orderItem;
    }

    private static OrderItem getShedClothing(Shed shed, List<Wood> woods, OrderItemTask task) {
        List<Wood> filteredWoods = filterWoods(woods, wood ->
                wood.isPressureTreated() && wood.getCategory().equals("brædt") && wood.getLength() == 210
        );

        int roofOverhang = 30; // 15 cm overlap on each side
        int totalLength = shed.getLength() * 2 + ((shed.getWidth() * 2) - roofOverhang);
        int amountOfClothing = (int) Math.ceil(totalLength / 7.5);
        double price = calculatePrice(filteredWoods.get(0), amountOfClothing);

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

        int actualAmountOfSpars = (int) Math.ceil(length / 90.0);
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

    private static List<Screw> filterScrews(List<Screw> screws, Predicate<Screw> predicate) {
        return screws.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static List<Fitting> filterFittings(List<Fitting> fittings, Predicate<Fitting> predicate) {
        return fittings.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static double calculatePrice(Wood wood, int quantity) {
        return wood.getPrice() * quantity;
    }


    //I dette metode skal vi have regnet carports bredde samt længde ud, beregningsmetode kommer her.
    public void addOrder(Order order) {

    }

    public void updateOrder(Order order, String status, ConnectionPool connectionPool) throws DatabaseException {
        order.setStatus("approved");

        OrderFacade.approveOrder(order, connectionPool);
    }

}
