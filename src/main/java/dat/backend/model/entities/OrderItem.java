package dat.backend.model.entities;

public class OrderItem {
    private int ID;
    private int quantity;
    private double price;
    private OrderItemTask description;
    private AMaterial material;

    public OrderItem(int ID, int quantity, double price, OrderItemTask description) {
        this.ID = ID;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public OrderItem(int quantity, double price, OrderItemTask description) {
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

    public double getPrice() {
        return price;
    }

    public void addMaterial(AMaterial material) {
        this.material = material;
    }

    public AMaterial getMaterial() {
        return material;
    }

    public String getDescription() {
        return description.getTask();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "ID=" + ID +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", material=" + material +
                '}';
    }

    public void setMaterial(AMaterial material) {
        this.material = material;
    }
}
