package dat.backend.model.entities;

public class Carport {
    private int length;
    private int width;
    private Shed shed;

    public Carport(int length, int width, Shed shed) {
        this.length = length;
        this.width = width;
        this.shed = shed;
    }

    public Carport(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Shed getShed() {
        return shed;
    }

    public boolean hasShed() {
        return shed != null;
    }
}
