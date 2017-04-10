package pong.models;

import pong.abstracts.GameObject;
import pong.abstracts.MoveableObject;
import pong.enums.RacquetState;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Racquet extends MoveableObject {

    private final static int WIDTH = 20;
    private final static int HEIGHT = 100;
    private final static float MAX_VELOCITY = 15;

    private RacquetState racquetState;

    public Racquet(int posX, int posY){
        super(posX, posY, WIDTH, HEIGHT, MAX_VELOCITY);
        racquetState = RacquetState.IDLE;
    }

    public Racquet(int posX, int posY, int width, int height){
        super(posX, posY, width, height, MAX_VELOCITY);
        racquetState = RacquetState.IDLE;
    }

    private void moveUp(){
        posY -= MAX_VELOCITY;
    }

    private void moveDown(){
        posY += MAX_VELOCITY;
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
}
