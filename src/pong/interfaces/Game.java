package pong.interfaces;

import pong.models.gameobjects.Goal;

/**
 * Created by patrick.christensen on 4/11/2017.
 */
public interface Game {
    void pause();
    void exit();
    void resume();
    void gameOver();
    void registerGoal(Goal goal);
}
