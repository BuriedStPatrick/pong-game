package pong.models.gameobjects;

import pong.abstracts.CollidableObject;
import pong.enums.CollisionType;
import pong.enums.RacquetState;
import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.interfaces.Collidable;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Racquet extends CollidableObject {

    public final static int WIDTH = 20;
    public final static int HEIGHT = 100;
    private final static float MAX_VELOCITY = 15;

    private RacquetState racquetState;

    public Racquet(Vector position){
        super(position, new Bounds(WIDTH, HEIGHT), MAX_VELOCITY);
        racquetState = RacquetState.IDLE;
    }

    public Racquet(Vector position, Bounds bounds){
        super(position, bounds, MAX_VELOCITY);
        racquetState = RacquetState.IDLE;
    }

    private void moveUp(){
        position.subtract(new Vector(0, MAX_VELOCITY));
    }

    private void moveDown(){
        position.add(new Vector(0, MAX_VELOCITY));
    }

    public void setState(RacquetState racquetState){
        this.racquetState = racquetState;
    }

    public RacquetState getState(){
        return racquetState;
    }

    @Override
    public void update() {
        switch(racquetState){
            case MOVING_UP:
                moveUp();
                break;
            case MOVING_DOWN:
                moveDown();
                break;
        }
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
        return CollisionType.SOLID;
    }
}
