package dat.backend.model.entities;

import org.abstractica.javacsg.Geometry3D;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Spot {
    private double height;
    private double width;
    private Geometry3D shape;

    public Spot(double height, double width, Geometry3D shape) {
        this.height = height;
        this.width = width;
        this.shape = shape;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Geometry3D getShape() {
        return shape;
    }

    public void setWidth(double maxWidth) {
        this.width = maxWidth;
    }
}
