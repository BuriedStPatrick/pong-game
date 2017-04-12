package pong.abstracts;

import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.interfaces.Updateable;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class MovableObject extends GameObject implements Updateable {

    protected float maxVelocity;
    protected Vector velocity;

    protected MovableObject(Vector position, Bounds bounds, float maxVelocity) {
        super(position, bounds);
        this.maxVelocity = maxVelocity;
        velocity = new Vector(maxVelocity, maxVelocity);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity){
        this.velocity = velocity;
    }
}
