package dat.backend.model.services;

import dat.backend.model.entities.OrderItem;
import dat.backend.model.entities.Spot;
import dat.backend.model.entities.Wood;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Modelling {


    public static void generate3D(List<OrderItem> orderItems) {
        // get max length;
        int maxSpotLength = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getMaterial().getLength() > maxSpotLength) {
                maxSpotLength = orderItem.getMaterial().getLength() * 10;
            }
        }
//        // create spot list
//        List<Spot> spots = new ArrayList<>();
//        for (OrderItem orderItem : orderItems) {
//            for (int i = 0; i < orderItem.getQuantity(); i++) {
//                Wood wood = (Wood) orderItem.getMaterial();
//                for (Spot spot : spots) {
//                   List<Wood> spotWoods = spot.getWoods();
//                   // calculate length of spot
//                     double spotLength = 0;
//                        for (Wood spotWood : spotWoods) {
//                            spotLength += spotWood.getLength();
//                        }
//                        if (spotLength + wood.getLength() < maxSpotLength) {
//                            spot.addWood(wood);
//                        } else {
//                            // create new spot
//                            Spot newSpot = new Spot(maxSpotLength, wood.getWidth(), wood);
//                            spots.add(newSpot);
//                        }
//
//                }
//                Spot spot = new Spot(maxSpotLength, wood.getWidth(), wood);
//                spots.add(spot);
//            }
//        }

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
                        spotLength += spotWood.getLength() * 10;
                    }

                    if (spotLength + (wood.getLength() * 10) < maxSpotLength) {
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
        var shape = csg.box3D(0,0, 0, true); // var must be initialized but cannot be null: solution = create empty box

        double horizontalGap = 0;

        for (int i = 0; i < spots.size(); i++) {
            horizontalGap += spots.get(i).getWidth();

            double verticalGap = 0;
            List<Wood> woods = spots.get(i).getWoods();
            if (woods.size() > 1) {
                for (int j = 0; j < woods.size(); j++) {
                    Wood wood = woods.get(j);
                    double spotLength = ((wood.getLength()*10)/maxSpotLength) * maxSpotLength;
                    Geometry3D box = csg.box3D(wood.getWidth(), wood.getLength() * 10, wood.getHeight(), false);
                    var square = csg.translate3D(horizontalGap, (wood.getLength()*10/2) + (maxSpotLength/2) /maxSpotLength + verticalGap, 0).transform(box);
                    verticalGap -= (woods.get(j).getLength() * 10) + 5;
                    shape = csg.union3D(shape, square);
                }
            } else {
                Wood wood = spots.get(i).getWoods().get(0);
                Geometry3D box = csg.box3D(wood.getWidth(), wood.getLength() * 10, wood.getHeight(), false);
                var square = csg.translate3D(horizontalGap, 0, 0).transform(box);
                shape = csg.union3D(shape, square);
            }




        }

//        double horizontalGap = 0;
//        for (int i = 0; i < spots.size(); i++) {
//            horizontalGap += spots.get(i).getWidth();
//            for (int j = 0; j < spots.get(i).getWoods().size(); j++) {
//                Wood wood = spots.get(i).getWoods().get(j);
//                double materialLength = 0;
//                for (Wood wood2 : spots.get(i).getWoods()) {
//                    materialLength += wood2.getLength() * 10;
//                }
//                double verticalGap = materialLength + 2.5;
//                Geometry3D box = csg.box3D(wood.getLength() * 10, wood.getWidth(), wood.getHeight(), true);
//                if ((wood.getLength() * 10 + materialLength) > maxSpotLength) {
//                    var square = csg.translate3D(0, verticalGap, 0).transform(box);
//                }
//                var square = csg.translate3D(0, horizontalGap, 0).transform(box);
//                shape = csg.union3D(shape, square);
////                Geometry3D box = csg.box3D(wood.getLength(), wood.getWidth(), wood.getHeight(), true);
////                var square = csg.translate3D(200, 0, 0).transform(box);
////                shape = csg.union3D(shape, square);
//            }
//
//
//        }
//        csg.view(shape);
//        Geometry3D box = csg.box3D(10, 10, 10, true);
//        var shape = box;
//        double x = 2.5;
//        for (int i = 0; i < 10; i++) {
//            var square = csg.translate3D(x, 0, 0).transform(box);
//            shape = csg.union3D(shape, square);
//            x += 20;
//        }
//
//
//
//
//
        csg.view(shape);
    }
}


