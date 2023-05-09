package dat.backend.model.entities;

public class ScrewPack extends ExtraDiameterMaterial{
    private int quantity;

    public ScrewPack(int diameter, int quantity) {
        super(diameter);
        this.quantity = quantity;
    }
}
