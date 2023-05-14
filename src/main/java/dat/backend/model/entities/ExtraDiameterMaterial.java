package dat.backend.model.entities;

public abstract class ExtraDiameterMaterial extends AMaterial {
    private int diameter;

    public ExtraDiameterMaterial(String name, int length, double price, String unit, int diameter) {
        super(name, length, price, unit);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
