package pong.models.gameobjects;

import pong.abstracts.CollidableObject;
import pong.enums.CollisionType;
import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.interfaces.Collidable;
import pong.models.Player;

/**
 * Created by patrick.christensen on 4/12/2017.
 */
public class Goal extends CollidableObject {

    private Player keeper;

    public Goal(Vector position, Bounds bounds, Player keeper) {
        super(position, bounds, 0);
        this.keeper = keeper;
    }

    @Override
    public boolean isWithinBounds(Bounds bounds) {
        return false;
    }

    @Override
    public boolean collidesWith(Collidable other) {
        return false;
    }

    @Override
    public void onCollide(Collidable other) {

    }

    @Override
    public void onCollide(Bounds bounds) {

    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.TRIGGER;
    }

    public Player getKeeper(){
        return keeper;
    }

    @Override
    public void update() {

    }
}
