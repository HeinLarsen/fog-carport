package dat.backend.model.entities;

public class Wood extends ExtraDimensionMaterial{
    private String category;
    private boolean isPressureTreated;

    public Wood(String name, int length, double price, String unit, String category, int width, int height, boolean isPressureTreated) {
        super(name, length, price, unit, width, height);
        this.category = category;
        this.isPressureTreated = isPressureTreated;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPressureTreated() {
        return isPressureTreated;
    }
}
