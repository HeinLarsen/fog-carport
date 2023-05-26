package dat.backend.model.entities;

public abstract class ExtraDimensionMaterial extends AMaterial{
    private double width;
    private double height;

    public ExtraDimensionMaterial(int id, String name, int length, double price, String unit, double width, double height) {
        super(id, name, length, price, unit);
        this.width = width;
        this.height = height;
    }

    public ExtraDimensionMaterial(int id, String name, int length, double price, String unit, int width) {
        super(id, name, length, price, unit);
        this.width = width;
    }



    public double getWidthDouble() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getWidthInt() {
        return (int) width;
    }

    @Override
    public String toString() {
        return super.toString() + "ExtraDimensionMaterial{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
