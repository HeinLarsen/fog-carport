package dat.backend.model.entities;

public abstract class ExtraDimensionMaterial extends AMaterial{
    private double width;
    private double height;

    public ExtraDimensionMaterial(int id, String name, double length, double price, String unit, double width, double height) {
        super(id, name, length, price, unit);
        this.width = width;
        this.height = height;
    }

    public ExtraDimensionMaterial(int id, String name, double length, double price, String unit, double width) {
        super(id, name, length, price, unit);
        this.width = width;
    }



    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() + "ExtraDimensionMaterial{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
