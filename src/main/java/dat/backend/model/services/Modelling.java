package dat.backend.model.services;

import dat.backend.model.entities.*;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.*;
import java.util.function.Predicate;

public class Modelling {
    private static JavaCSG csg = JavaCSGFactory.createDefault();
    ;


    public static void generateFiles(Order order) {
//        generateBuildList(order);

        generateMaterialList(order);


    }

    private static void generateMaterialList(Order order) {
        List<Spot> spots = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            if (item.getMaterial() instanceof Wood) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    Wood wood = (Wood) item.getMaterial();
                    Geometry3D shape = generateBasicShape(wood.getLength(), item);
                    spots.add(new Spot(wood.getLength(), wood.getWidth(), shape));
                }
            }
        }
        yAxisPacking(spots);
    }

    private static void generateBuildList(Order order) {
        List<Spot> spots = new ArrayList<>();

        int iterations = 2;

        OrderItem rimOrderItem = getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.RIM.getTask()));
        spots.addAll(generateSpot(order.getLength(), rimOrderItem, iterations));

        spots.addAll(generateSpot(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_UPPER_SIDES.getTask())), iterations));

        spots.addAll(generateSpot(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_LOWER_SIDES.getTask())), iterations));

        spots.addAll(generateSpot(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_UPPER_ENDS.getTask())), iterations));

        spots.addAll(generateSpot(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.STERN_LOWER_ENDS.getTask())), iterations));

        spots.addAll(generateSpot(order.getLength(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.WATERBOARD_ENDS.getTask())), iterations));

        spots.addAll(generateSpot(order.getWidth(), getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.WATERBOARD_SIDES.getTask())), iterations));

        OrderItem sparOrderItem = getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.SPAR.getTask()));
        spots.addAll(generateSpot(order.getWidth(), sparOrderItem, sparOrderItem.getQuantity()));

        OrderItem poleOrderItem = getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.POLE.getTask()));

        for (int i = 0; i < poleOrderItem.getQuantity(); i++) {
            Wood wood = (Wood) poleOrderItem.getMaterial();
            var shape = generateCarportPoles(poleOrderItem, getItem(order.getOrderItems(), item -> item.getDescription().equals(OrderItemTask.RIM.getTask())));
            spots.add(new Spot(wood.getLength() - 90, wood.getWidth(), shape));
        }

        yAxisPacking(spots);
    }

    private static ArrayList<Spot> generateSpot(double target, OrderItem item, int iterations) {
        ArrayList<Spot> spots = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            Wood wood = (Wood) item.getMaterial();
            Geometry3D shape = generateBasicShape(target, item);
            spots.add(new Spot(target, wood.getWidth(), shape));
        }
        return spots;
    }

    private static Geometry3D generateBasicShape(double target, OrderItem item) {
        Wood wood = (Wood) item.getMaterial();
        Geometry3D shape = csg.box3D(wood.getWidth(), target, wood.getHeight(), false);
        shape = csg.translate3DY(target / 2).transform(shape);
        return shape;
    }

    private static Geometry3D generateCarportPoles(OrderItem poleItem, OrderItem rimItem) {
        int cutOff = 90;
        Wood wood = (Wood) poleItem.getMaterial();
        Wood rimWood = (Wood) rimItem.getMaterial();
        Geometry3D pole = csg.box3D(wood.getWidth(), wood.getHeight(), wood.getLength() - cutOff, false);
        Geometry3D cutter = csg.box3D(rimWood.getWidth(), wood.getHeight() + 1, rimWood.getWidth() + 1, false);
        cutter = csg.translate3D(0, -wood.getWidth() / 2, -1).transform(cutter);
        var shape = csg.difference3D(pole, cutter);
        shape = csg.rotate3DX(csg.degrees(-90)).transform(shape);
        shape = csg.translate3DZ(wood.getHeight() / 2).transform(shape);
        return shape;
    }

    private static OrderItem getItem(List<OrderItem> orderItems, Predicate<OrderItem> predicate) {
        return orderItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    private static void yAxisPacking(List<Spot> spots) {
        double maxSpotLength = 1200;

        List<List<Spot>> rows = new ArrayList<>();

        // sort spots by height and width combined (ascending)
        spots.sort((o1, o2) -> {
            double o1CombinedLength = o1.getHeight() + o1.getWidth();
            double o2CombinedLength = o2.getHeight() + o2.getWidth();

            if (o1CombinedLength < o2CombinedLength) {
                return 1;
            } else if (o1CombinedLength > o2CombinedLength) {
                return -1;
            } else {
                return 0;
            }
        });


        for (Spot spot:spots) {
            boolean spotPlaced = false;

            for (List<Spot> row:rows) {
                if (canSpotFitInRow(spot, row, maxSpotLength)) {
                    addSpotToRow(spot, row);
                    spotPlaced = true;
                    break;
                }
            }

            if (!spotPlaced) {
                List<Spot> row = new ArrayList<>();
                row.add(spot);
                rows.add(row);
            }
        }

        printing(rows);


    }

    private static void printing(List<List<Spot>> rows) {
        Geometry3D shape = csg.box3D(0, 0, 0, false);
        double horizontalOffset = 0;

        for (List<Spot> row:rows) {
            double verticalOffset = 0;
            for (Spot spot:row) {
                Geometry3D box = spot.getShape();
                box = csg.translate3D(horizontalOffset + spot.getWidth()/2, verticalOffset, 0).transform(box);
                shape = csg.union3D(shape, box);
                verticalOffset += spot.getHeight() + 10;
            }
            horizontalOffset += getLargestWidthInRow(row) + 10;
        }
        double scaleFactor = 0.18;
        shape = csg.scale3D(scaleFactor, scaleFactor, scaleFactor).transform(shape);

        csg.view(shape);
    }






    private static boolean canSpotFitInRow(Spot spot, List<Spot> row, double maxSpotLength) {
        double totalHeight = 0;
        for (Spot rowSpot : row) {
            totalHeight += rowSpot.getHeight() + 10;
        }
        return totalHeight + spot.getHeight() <= maxSpotLength;
    }

    private static void addSpotToRow(Spot spot, List<Spot> row) {
        row.add(spot);
    }

    private static Double getLargestWidthInRow(List<Spot> row) {
        Double largestWidth = Double.NEGATIVE_INFINITY;
        for (Spot spot : row) {
            if (spot.getWidth() > largestWidth) {
                largestWidth = spot.getWidth();
            }
        }
        return largestWidth;
    }


//    private static void yAxisPacking(List<OrderItem> orderItems) {
//        double maxSpotLength = 1200;
//        List<Spot> spots = new ArrayList<>();
//
//        for (OrderItem orderItem : orderItems) {
//            for (int i = 0; i < orderItem.getQuantity(); i++) {
//                if (orderItem.getMaterial() instanceof Wood) {
//                    Wood wood = (Wood) orderItem.getMaterial();
//                    boolean addedToExistingSpot = false;
//
//                    Iterator<Spot> spotIterator = spots.iterator();
//                    while (spotIterator.hasNext()) {
//                        Spot spot = spotIterator.next();
//                        List<Wood> spotWoods = spot.getWoods();
//
//                        // calculate length of spot
//                        double spotLength = 0;
//                        for (Wood spotWood : spotWoods) {
//                            spotLength += spotWood.getLength();
//                        }
//
//                        if (spotLength + wood.getLength() < maxSpotLength) {
//                            spot.addWood(wood);
//                            addedToExistingSpot = true;
//                            break; // Exit the loop since wood was added to an existing spot
//                        }
//                    }
//
//                    if (!addedToExistingSpot) {
//                        Spot newSpot = new Spot(maxSpotLength, wood.getWidth(), wood);
//                        spots.add(newSpot);
//                    }
//                }
//            }
//        }
//
//        JavaCSG csg = JavaCSGFactory.createDefault();
//        var shape = csg.box3D(0,0, 0, false); // var must be initialized but cannot be null: solution = create empty box
//
//        double horizontalGap = 0;
//
//        for (int i = 0; i < spots.size(); i++) {
//            List<Wood> woods = spots.get(i).getWoods();
//            double verticalGap = 0;
//            for (int j = 0; j < woods.size(); j++) {
//                Wood wood = woods.get(j);
//                double spotLength = (wood.getLength())/2 + (maxSpotLength/2)/maxSpotLength;
//                Geometry3D box = csg.box3D(wood.getWidth(), wood.getLength(), wood.getHeight(), false);
//                var square = csg.translate3D(horizontalGap, spotLength + verticalGap, 0).transform(box);
//                shape = csg.union3D(shape, square);
//                verticalGap += wood.getLength() + 5;
//            }
//            horizontalGap += spots.get(i).getWidth() + 10;
//        }
//        double scaleFactor = 0.18;
//        shape = csg.scale3D(scaleFactor, scaleFactor, scaleFactor).transform(shape);
//        csg.view(shape);
//
//    }

}
