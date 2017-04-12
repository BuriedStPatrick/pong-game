package pong.models.gameobjects;

import pong.abstracts.CollidableObject;
import pong.enums.CollisionType;
import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.interfaces.Collidable;
import pong.interfaces.Game;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Ball extends CollidableObject {

    public static final float MAX_VELOCITY = 12;
    private float radius;
    private Game game;

    public Ball(Vector position, Bounds bounds, Game game) {
        super(position, bounds, MAX_VELOCITY);
        radius = bounds.getHeight()/2;
        this.game = game;
    }

    @Override
    public void update() {
        velocity.setLength(MAX_VELOCITY);
        position.add(velocity);
    }

    @Override
    public boolean isWithinBounds(Bounds bounds) {
        if (position.getX() + radius >= bounds.getWidth() || position.getX() - radius <= 0) {
            velocity.multiply(new Vector(-1, 1));
            if(position.getX() + radius > bounds.getWidth()) position.setX(bounds.getWidth() - radius);
            if(position.getX() - radius < 0) position.setX(0 + radius);
            return false;
        } else if (position.getY() - radius <= 0 || position.getY() + radius >= bounds.getHeight()) {
            velocity.multiply(new Vector(1, -1));
            if(position.getY() - radius < 0) position.setY(0 + radius);
            if(position.getY() + radius > bounds.getHeight()) position.setY(bounds.getHeight() - radius);
            return false;
        }
        return true;
    }

    @Override
    public boolean collidesWith(Collidable other) {
        CollidableObject object = ((CollidableObject) other);
        if(object == null) return false;
        Vector otherPosition = object.getPosition();
        Bounds otherBounds = object.getBounds();
        Vector topLeftCorner = otherPosition;
        Vector bottomRightCorner =
                new Vector(otherPosition.getX() + otherBounds.getWidth(), otherPosition.getY() + otherBounds.getHeight());
        if(
            position.getX() >= topLeftCorner.getX()
            && position.getY() >= topLeftCorner.getY()
            && position.getX() <= bottomRightCorner.getX()
            && position.getY() <= bottomRightCorner.getY()){
            return true;
        }
        return false;
    }

    @Override
    public void onCollide(Collidable other) {
        switch(other.getCollisionType()){
            case SOLID:
                collide((CollidableObject) other);
                break;
            case TRIGGER:
                collide((Goal) other);
                break;
        }
    }

    private void collide(CollidableObject collidableObject){
        float y = position.getY();
        int racquetHeight = collidableObject.getBounds().getHeight();
        float racquetTop = collidableObject.getPosition().getY();

        float halfHeight = racquetHeight/2;
        float racquetMiddle = racquetTop + halfHeight;
        float difference = racquetMiddle - y;
        float factor = -(difference/halfHeight);
        factor = factor >= 1.0f ? 0.7f : factor;
        factor = factor <= -1.0f ? -0.7f : factor;
        float xVel = velocity.getX();
        float yVel = velocity.getY();
//        if(xVel < 0){
//            xVel = Math.min(xVel, -MIN_VELOCITY);
//        }else{
//            xVel = Math.max(xVel, MIN_VELOCITY);
//        }
        velocity = new Vector(xVel*-1, factor*MAX_VELOCITY);
    }

    private void collide(Goal goal){
        game.registerGoal(goal);
    }

    @Override
    public void onCollide(Bounds bounds) {

    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SOLID;
    }
}
