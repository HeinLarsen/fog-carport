package dat.backend.model.entities;

public abstract class ExtraDimensionMaterial extends AMaterial{
    private int width;
    private int height;

    public ExtraDimensionMaterial(String name, int length, double price, String packaging, String description, int width, int height) {
        super(name, length, price, packaging, description);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
