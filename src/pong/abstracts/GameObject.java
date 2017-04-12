package pong.abstracts;

import pong.geometry.Bounds;
import pong.geometry.Vector;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class GameObject {

    protected Bounds bounds;
    protected Vector position;

    protected GameObject(Vector position, Bounds bounds){
        this.position = position;
        this.bounds = bounds;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }
}
