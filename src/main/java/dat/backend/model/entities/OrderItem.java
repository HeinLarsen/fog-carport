package dat.backend.model.entities;

public class OrderItem {
    private int ID;
    private int quantity;
    private int price;
    private String description;
    private AMaterial material;

    public OrderItem(int ID, int quantity, int price, String description) {
        this.ID = ID;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public int getQuantity() {
        return quantity;
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

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "ID=" + ID +
                ", amount=" + quantity +
                ", price=" + price +
                ", material=" + material +
                '}';
    }

    public void setMaterial(AMaterial material) {
        this.material = material;
    }
}
