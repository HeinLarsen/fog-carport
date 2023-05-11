package dat.backend.model.entities;

public class OrderItem {
    private int ID;
    private int amount;
    private int price;
    private int materialID;
    private AMaterial material;

    public OrderItem(int ID, int amount, int price, int materialID) {
        this.ID = ID;
        this.amount = amount;
        this.price = price;
        this.materialID = materialID;
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

    public void addMaterial(AMaterial material) {
        this.material = material;
    }

    public AMaterial getMaterial() {
        return material;
    }


}
