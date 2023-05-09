package dat.backend.model.entities;

public abstract class AMaterial {
    private String name;
    private int length;
    private double price;
    private String packaging;
    private String description;



    public AMaterial(String name, int length, double price, String packaging, String description){
        this.name = name;
        this.length = length;
        this.price = price;
        this.packaging = packaging;
        this.description = description;


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

    public String getPackaging(){
        return packaging;
    }

    public String getDescription(){
        return description;
    }





}






