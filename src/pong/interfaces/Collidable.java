package pong.interfaces;

import pong.enums.CollisionType;
import pong.geometry.Bounds;

/**
 * Created by patrick.christensen on 4/11/2017.
 */
public interface Collidable {
    boolean isWithinBounds(Bounds bounds);
    boolean collidesWith(Collidable other);
    void onCollide(Collidable other);
    void onCollide(Bounds bounds);
    CollisionType getCollisionType();
}
