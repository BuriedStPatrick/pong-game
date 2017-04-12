package pong.geometry;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Bounds {

    private int width;
    private int height;

    public Bounds(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
