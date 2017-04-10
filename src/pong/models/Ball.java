package pong.models;

import pong.abstracts.GameObject;
import pong.abstracts.MoveableObject;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Ball extends MoveableObject {

    private static final float MAX_VELOCITY = 5;

    public Ball(float posX, float posY, float width, float height) {
        super(posX, posY, width, height, MAX_VELOCITY);
    }

    @Override
    public void update() {
        posX += MAX_VELOCITY;
        posY += MAX_VELOCITY;
    }
}
