package dat.backend.model.entities;

public abstract class ExtraDiameterMaterial extends AMaterial {
    private int diameter;

    public ExtraDiameterMaterial(String name, int length, double price, String packaging, String description, int diameter) {
        super(name, length, price, packaging, description);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
