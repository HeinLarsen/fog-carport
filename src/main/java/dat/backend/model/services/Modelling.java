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
        generateBuildList(order);

//        generateMaterialList(order.getOrderItems());

    }

    private static void generateBuildList(Order order) {
        var shape = csg.box3D(0,0, 0, false);
        generateCarportPoles(getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.POLE.getTask())));


    }

    private static Geometry3D generateCarportPoles(OrderItem item) {
        int cutOff = 90;
        Wood wood = (Wood) item.getMaterial();
        Geometry3D pole = csg.box3D(wood.getHeight(), wood.getWidth(), wood.getLength() - cutOff, false);
        Geometry3D cutter = csg.box3D(wood.getHeight()/2, wood.getWidth(), 45, false);
//        csg.translate3D(0, 0, cutOff/2 + 45);
//        var shape = csg.union3D(pole, cutter);
        csg.view(pole);
        return pole;

    }

    private static void generateSterns(List<OrderItem> items) {

    }

    private static OrderItem getItem(List<OrderItem> orderItems, Predicate<OrderItem> predicate) {
        for (OrderItem orderItem : orderItems) {
            if (predicate.test(orderItem)) {
                return orderItem;
            }
        }
        return null;
    }




    public static void generateMaterialList(List<OrderItem> orderItems) {
        // get max length;
        double maxSpotLength = 0;
        for (OrderItem orderItem : orderItems) {
            if (cmToMm(orderItem.getMaterial().getLength()) > maxSpotLength) {
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

                    if (spotLength + cmToMm(wood.getLength()) + 50 < maxSpotLength) {
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
            if (horizontalGap + spots.get(i).getWidth() > 15000) {
                spotRow += maxSpotLength + 25;
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
                    verticalGap += cmToMm(woods.get(j).getLength()) + 25;
                    shape = csg.union3D(shape, square);
                }
            } else {
                Wood wood = spots.get(i).getWoods().get(0);
                double spotLength = (cmToMm(wood.getLength())/2) + (maxSpotLength/2)/maxSpotLength;
                Geometry3D box = csg.box3D(wood.getWidth(), cmToMm(wood.getLength()), wood.getHeight(), false);
                var square = csg.translate3D(horizontalGap, spotLength + spotRow, 0).transform(box);
                shape = csg.union3D(shape, square);
            }
                horizontalGap += spots.get(i).getWidth();
        }
        double scaleFactor = 0.015;
        shape = csg.scale3D(scaleFactor, scaleFactor, scaleFactor).transform(shape);
        csg.view(shape);
    }

    private static double cmToMm(double cm) {
        return cm * 10;
    }

}


