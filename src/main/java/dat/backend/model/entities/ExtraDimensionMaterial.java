package dat.backend.model.entities;

public abstract class ExtraDimensionMaterial extends AMaterial{
    private int width;
    private int height;

    public ExtraDimensionMaterial(int id, String name, int length, double price, String unit, int width, int height) {
        super(id, name, length, price, unit);
        this.width = width;
        this.height = height;
    }

    public ExtraDimensionMaterial(int id, String name, int length, double price, String unit, int width) {
        super(id, name, length, price, unit);
        this.width = width;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
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
