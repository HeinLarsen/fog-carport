package dat.backend.model.entities;

public abstract class ExtraDiameterMaterial {
    private int diameter;

    public ExtraDiameterMaterial(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
