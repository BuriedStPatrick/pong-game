package pong.abstracts;

import pong.interfaces.Updateable;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class MoveableObject extends GameObject implements Updateable {

    protected float maxVelocity;

    protected MoveableObject(float posX, float posY, float width, float height, float maxVelocity) {
        super(posX, posY, width, height);
        this.maxVelocity = maxVelocity;
    }

    public float getMaxVelocity() {
        return maxVelocity;
    }
}
