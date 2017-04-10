package pong.models;

import pong.abstracts.ControllableObject;
import pong.enums.InputType;
import pong.enums.RacquetState;
import pong.handlers.InputMapper;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Player extends ControllableObject {

    private Racquet racquet;
    private int score;

    public Player(Racquet racquet, InputMapper inputMapper){
        super(inputMapper);
        this.racquet = racquet;
    }

    public Racquet getRacquet() {
        return racquet;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void handlePress(int keyCode) {
        InputType input = inputMapper.getInput(keyCode);
        if(input == null) return;
        switch(inputMapper.getInput(keyCode)){
            case PLAYER_UP:
                racquet.setState(RacquetState.MOVING_UP);
                break;
            case PLAYER_DOWN:
                racquet.setState(RacquetState.MOVING_DOWN);
                break;
        }
    }

    @Override
    public void handleRelease(int keyCode) {
        InputType input = inputMapper.getInput(keyCode);
        if(input == null) return;
        switch(inputMapper.getInput(keyCode)){
            case PLAYER_UP:
                racquet.setState(RacquetState.IDLE);
                break;
            case PLAYER_DOWN:
                racquet.setState(RacquetState.IDLE);
                break;
        }
    }
}
