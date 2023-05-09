package dat.backend.model.entities;

public class OrderItem {
    private int ID;
    private int amount;
    private int price;
    private Material material;

    public OrderItem(int ID, int amount, int price) {
        this.ID = ID;
        this.amount = amount;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void addMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }


}
