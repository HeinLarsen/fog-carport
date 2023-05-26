package dat.backend.model.entities;

public class Shed {
    private int length;
    private int width;

    public Shed(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
