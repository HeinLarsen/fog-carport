package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Spot {
    private double height;
    private double width;
    private List<Wood> woods = new ArrayList<>();

    public Spot(double height, double width, Wood wood) {
        this.height = height;
        this.width = width;
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
        calculateMaxWidth();
    }

    private void calculateMaxWidth() {
        double maxWidth = 0;
        for (Wood wood : woods) {
            if (wood.getWidth() > maxWidth) {
                maxWidth = wood.getWidth();
            }
        }
        if (maxWidth > this.width) {
            this.width = maxWidth;
        }
    }
}
