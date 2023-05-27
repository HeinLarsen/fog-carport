package dat.backend.model.entities;

public class Wood extends ExtraDimensionMaterial{
    private String category;
    private boolean isPressureTreated;

    public Wood(int id, String name, double length, double price, String unit, String category, double width, double height, boolean isPressureTreated) {
        super(id, name, length, price, unit, width, height);
        this.category = category;
        this.isPressureTreated = isPressureTreated;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPressureTreated() {
        return isPressureTreated;
    }

    @Override
    public String toString() {
        return super.toString() + "Wood{" +
                "category='" + category + '\'' +
                ", isPressureTreated=" + isPressureTreated +
                '}';
    }
}
