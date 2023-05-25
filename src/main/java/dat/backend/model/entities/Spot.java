package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Spot {
    private double height;
    private double width;
    private List<Wood> woods = new ArrayList<>();

    public Spot(double height, double width, Wood wood) {
        double gap = 50;
        this.height = height + (gap * 2);
        this.width = width + (gap * 2);
        this.woods.add(wood);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public List<Wood> getWoods() {
        return woods;
    }

    public void addWood(Wood wood) {
        this.woods.add(wood);
    }
}
