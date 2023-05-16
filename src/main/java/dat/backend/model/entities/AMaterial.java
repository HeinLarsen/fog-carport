package dat.backend.model.entities;

public abstract class AMaterial {
    private String name;
    private int length;
    private double price;
    private String unit;

    public AMaterial(String name, int length, double price, String unit){
        this.name = name;
        this.length = length;
        this.price = price;
        this.unit = unit;


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

}






