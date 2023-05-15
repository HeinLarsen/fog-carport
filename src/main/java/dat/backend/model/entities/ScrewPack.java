package dat.backend.model.entities;

public class ScrewPack extends ExtraDiameterMaterial{
    private int quantity;

    public ScrewPack(String name, int length, double price, String packaging, String description, int diameter, int quantity) {
        super(name, length, price, packaging, description, diameter);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
