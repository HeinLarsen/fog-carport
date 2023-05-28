package dat.backend.model.services;

import dat.backend.model.entities.*;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Modelling {
    private static JavaCSG csg = JavaCSGFactory.createDefault();;


    public static void generateFiles(Order order) {
//        generateBuildList(order);

//        generateMaterialList(order.getOrderItems());

        yAxisPacking(order.getOrderItems());

    }

    private static void generateBuildList(Order order) {
        List<Geometry3D> shapes = new ArrayList<>();

        // poles
        Geometry3D pole = generateCarportPoles(getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.POLE.getTask())), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.RIM.getTask())));
        for (int i = 0; i < 2; i++) {
            // rims
            shapes.add(generateBasicShape(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.RIM.getTask()))));
            // stern upper sides
            shapes.add(generateBasicShape(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_UPPER_SIDES.getTask()))));
            // stern lower sides
            shapes.add(generateBasicShape(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_LOWER_SIDES.getTask()))));
            // stern upper ends
            shapes.add(generateBasicShape(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_UPPER_ENDS.getTask()))));
            // stern lower ends
            shapes.add(generateBasicShape(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_LOWER_ENDS.getTask()))));
            // waterboard sides
            shapes.add(generateBasicShape(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.WATERBOARD_ENDS.getTask()))));
            // waterboard ends
            shapes.add(generateBasicShape(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.WATERBOARD_SIDES.getTask()))));
        }

        // spars
        OrderItem sparItem = getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.SPAR.getTask()));
        for (int i = 0; i < sparItem.getQuantity(); i++) {
            shapes.add(generateBasicShape(order.getWidth(), sparItem));
        }



    }

    private static Geometry3D generateBasicShape(double target, OrderItem item) {
        Wood wood = (Wood) item.getMaterial();
        Geometry3D shape = csg.box3D(wood.getWidth(), target, wood.getHeight(), false);
        shape = csg.translate3DY(target/2).transform(shape);
        csg.view(shape);
        return shape;
    }

    private static Geometry3D generateCarportPoles(OrderItem poleItem, OrderItem rimItem) {
        int cutOff = 90;
        Wood wood = (Wood) poleItem.getMaterial();
        Wood rimWood = (Wood) rimItem.getMaterial();
        Geometry3D pole = csg.box3D(wood.getWidth(), wood.getHeight(), wood.getLength() - cutOff, false);
        Geometry3D cutter = csg.box3D(rimWood.getWidth(), wood.getHeight() + 1, rimWood.getWidth() +1, false);
        cutter = csg.translate3D(0, -wood.getWidth()/2, -1).transform(cutter);
        var shape = csg.difference3D(pole, cutter);
        shape = csg.rotate3DX(csg.degrees(-90)).transform(shape);
        shape = csg.translate3DZ(wood.getHeight()/2).transform(shape);
        csg.view(shape);
        return pole;

    }


    private static OrderItem getItem(List<OrderItem> orderItems, Predicate<OrderItem> predicate) {
        for (OrderItem orderItem : orderItems) {
            if (predicate.test(orderItem)) {
                return orderItem;
            }
        }
        return null;
    }


    private static void yAxisPacking(List<OrderItem> orderItems) {
        double maxSpotLength = 1200;
        List<Spot> spots = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            for (int i = 0; i < orderItem.getQuantity(); i++) {
                if (orderItem.getMaterial() instanceof Wood) {
                    Wood wood = (Wood) orderItem.getMaterial();
                    boolean addedToExistingSpot = false;

                    Iterator<Spot> spotIterator = spots.iterator();
                    while (spotIterator.hasNext()) {
                        Spot spot = spotIterator.next();
                        List<Wood> spotWoods = spot.getWoods();

                        // calculate length of spot
                        double spotLength = 0;
                        for (Wood spotWood : spotWoods) {
                            spotLength += cmToMm(spotWood.getLength());
                        }

                        if (spotLength + wood.getLength() < maxSpotLength) {
                            spot.addWood(wood);
                            addedToExistingSpot = true;
                            break; // Exit the loop since wood was added to an existing spot
                        }
                    }

                    if (!addedToExistingSpot) {
                        Spot newSpot = new Spot(maxSpotLength, wood.getWidth(), wood);
                        spots.add(newSpot);
                    }
                }
            }
        }

        JavaCSG csg = JavaCSGFactory.createDefault();
        var shape = csg.box3D(0,0, 0, false); // var must be initialized but cannot be null: solution = create empty box

        double horizontalGap = 0;

        for (int i = 0; i < spots.size(); i++) {
            List<Wood> woods = spots.get(i).getWoods();
            double verticalGap = 0;
            for (int j = 0; j < woods.size(); j++) {
                Wood wood = woods.get(j);
                double spotLength = (cmToMm(wood.getLength())/2) + (maxSpotLength/2)/maxSpotLength;
                Geometry3D box = csg.box3D(wood.getWidth(), cmToMm(wood.getLength()), wood.getHeight(), false);
                var square = csg.translate3D(horizontalGap, spotLength + verticalGap, 0).transform(box);
                shape = csg.union3D(shape, square);
                verticalGap += cmToMm(wood.getLength()) + 5;
            }
            horizontalGap += spots.get(i).getWidth() + 10;
        }
        double scaleFactor = 0.18;
        shape = csg.scale3D(scaleFactor, scaleFactor, scaleFactor).transform(shape);
        csg.view(shape);

    }




    public static void generateMaterialList(List<OrderItem> orderItems) {
        // get max length;
        double maxSpotLength = 0;
        for (OrderItem orderItem : orderItems) {
            if (cmToMm(orderItem.getMaterial().getLength()) > maxSpotLength && orderItem.getMaterial() instanceof Wood) {
                maxSpotLength = cmToMm(orderItem.getMaterial().getLength());
            }
        }

        List<Spot> spots = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            for (int i = 0; i < orderItem.getQuantity(); i++) {
                if (orderItem.getMaterial() instanceof Wood) {
                Wood wood = (Wood) orderItem.getMaterial();
                boolean addedToExistingSpot = false;

                Iterator<Spot> spotIterator = spots.iterator();
                while (spotIterator.hasNext()) {
                    Spot spot = spotIterator.next();
                    List<Wood> spotWoods = spot.getWoods();

                    // calculate length of spot
                    double spotLength = 0;
                    for (Wood spotWood : spotWoods) {
                        spotLength += cmToMm(spotWood.getLength());
                    }

                    if (spotLength + cmToMm(wood.getLength()) + 10 < maxSpotLength) {
                        spot.addWood(wood);
                        addedToExistingSpot = true;
                        break; // Exit the loop since wood was added to an existing spot
                    }
                }

                if (!addedToExistingSpot) {
                    Spot newSpot = new Spot(maxSpotLength, wood.getWidth(), wood);
                    spots.add(newSpot);
                }
            }
            }
        }

        JavaCSG csg = JavaCSGFactory.createDefault();
        var shape = csg.box3D(0,0, 0, false); // var must be initialized but cannot be null: solution = create empty box

        double horizontalGap = 0;
        double spotRow = 0;

        for (int i = 0; i < spots.size(); i++) {
            if (horizontalGap + spots.get(i).getWidth() > 1700) {
                spotRow += maxSpotLength +5;
                horizontalGap = 0;
            }


            double verticalGap = 0;
            List<Wood> woods = spots.get(i).getWoods();
            if (woods.size() > 1) {
                for (int j = 0; j < woods.size(); j++) {
                    Wood wood = woods.get(j);
                    double spotLength = (cmToMm(wood.getLength())/2) + (maxSpotLength/2)/maxSpotLength;
                    Geometry3D box = csg.box3D(wood.getWidth(), cmToMm(wood.getLength()), wood.getHeight(), false);
                    var square = csg.translate3D(horizontalGap, spotLength + verticalGap + spotRow, 0).transform(box);
                    verticalGap += cmToMm(woods.get(j).getLength()) + 5;
                    shape = csg.union3D(shape, square);
                }
            } else {
                Wood wood = spots.get(i).getWoods().get(0);
                double spotLength = (cmToMm(wood.getLength())/2) + (maxSpotLength/2)/maxSpotLength;
                Geometry3D box = csg.box3D(wood.getWidth(), cmToMm(wood.getLength()), wood.getHeight(), false);
                var square = csg.translate3D(horizontalGap, spotLength + spotRow, 0).transform(box);
                shape = csg.union3D(shape, square);
            }
                horizontalGap += spots.get(i).getWidth() + 5;
        }
        double scaleFactor = 0.13;
        shape = csg.scale3D(scaleFactor, scaleFactor, scaleFactor).transform(shape);
        csg.view(shape);
    }

    private static double cmToMm(double cm) {
        return cm;
    }

}


