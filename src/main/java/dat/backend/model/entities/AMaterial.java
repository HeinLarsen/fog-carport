package dat.backend.model.entities;

public abstract class AMaterial {
    private int id;
    private String name;
    private int length;
    private double price;
    private String unit;

    public AMaterial(int id, String name, int length, double price, String unit){
        this.id = id;
        this.name = name;
        this.length = length;
        this.price = price;
        this.unit = unit;


    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getLength(){
        return length;
    }

    public double getPrice(){
        return price;
    }

    public String getUnit(){
        return unit;
    }

    @Override
    public String toString() {
        return "AMaterial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}






