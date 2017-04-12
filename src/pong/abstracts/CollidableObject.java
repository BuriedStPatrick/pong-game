package pong.abstracts;

import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.interfaces.Collidable;

/**
 * Created by patrick.christensen on 4/11/2017.
 */
public abstract class CollidableObject extends MovableObject implements Collidable {

    protected CollidableObject(Vector position, Bounds bounds, float maxVelocity) {
        super(position, bounds, maxVelocity);
    }
}
