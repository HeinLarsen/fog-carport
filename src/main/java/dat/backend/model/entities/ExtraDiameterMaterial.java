package dat.backend.model.entities;

public abstract class ExtraDiameterMaterial extends AMaterial {
    private int diameter;

    public ExtraDiameterMaterial(int id, String name, int length, double price, String unit, int diameter) {
        super(id, name, length, price, unit);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
