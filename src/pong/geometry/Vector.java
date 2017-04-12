package pong.geometry;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Vector {

    private float x;
    private float y;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector add(Vector otherVector){
        this.x += otherVector.getX();
        this.y += otherVector.getY();
        return this;
    }

    public void subtract(Vector otherVector){
        this.x -= otherVector.getX();
        this.y -= otherVector.getY();
    }

    public void multiply(Vector otherVector){
        this.x *= otherVector.getX();
        this.y *= otherVector.getY();
    }

    public float dist(Vector otherVector){
        return
                ((float)Math.sqrt(Math.pow(this.x - otherVector.getX(), 2)
            +   Math.pow(this.y - otherVector.getY(), 2)));
    }

    public void rotate(int degrees){
        double radians = degrees*Math.PI/180;
        double ca = Math.cos(radians);
        double sa = Math.sin(radians);
        this.x = (float)(ca*this.x - sa*this.y);
        this.y = (float)(sa*this.x + sa*this.y);
    }

    public float getLength(){
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void setLength(float length){
        float difference = getLength()/length;
        if(this.x == 0) this.x = 1;
        if(this.y == 0) this.y = 1;
        this.x /= difference;
        this.y /= difference;
    }
}
