package pong.abstracts;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class GameObject {

    protected float posX;
    protected float posY;
    protected float width;
    protected float height;

    protected GameObject(float posX, float posY, float width, float height){
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
