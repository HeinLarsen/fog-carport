package dat.backend.model.entities;

public class OrderItem {
    private int ID;
    private int quantity;
    private double price;
    private String description;
    private AMaterial material;
    private int length;
    private int width;

    public OrderItem(int ID, int quantity, double price, String description) {
        this.ID = ID;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public OrderItem(int quantity, double price, String description) {
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public OrderItem(int quantity, double price, String description, int length, int width){
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.length = length;
        this.width = width;
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
        return description;
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
