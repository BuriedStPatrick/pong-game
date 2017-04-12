package pong.managers;

import pong.abstracts.InputController;
import pong.enums.GameState;
import pong.enums.InputType;
import pong.handlers.InputMapper;
import pong.interfaces.Game;
import pong.interfaces.Updateable;

/**
 * Created by patrick.christensen on 4/11/2017.
 */
public class GameManager extends InputController implements Updateable {

    private GameState gameState;
    private Game game;

    public GameManager(Game game, InputMapper inputMapper) {
        super(inputMapper);
        this.game = game;
        gameState = GameState.PLAYING;
    }

    @Override
    public void handlePress(int keyCode) {
        InputType input = inputMapper.getInput(keyCode);
        if(input == null) return;
        switch(input){
            case GAME_PAUSE:
                handlePause();
                break;
            case GAME_EXIT:
                gameState = GameState.GAME_EXITING;
                break;
            case GAME_END:
                gameState = GameState.GAME_OVER;
                break;
        }
    }

    private void handlePause(){
        switch (gameState) {
            case PLAYING:
                gameState = GameState.PAUSING;
                break;
            case PAUSED:
                gameState = GameState.RESUMING;
                break;
        }
    }

    @Override
    public void handleRelease(int keyCode) {

    }

    @Override
    public void update() {
        switch (gameState){
            case RESUMING:
                game.resume();
                gameState = GameState.PLAYING;
                break;
            case PAUSING:
                game.pause();
                gameState = GameState.PAUSED;
                break;
            case GAME_OVER:
                game.gameOver();
                game.pause();
                gameState = GameState.PAUSED;
                break;
            case GAME_EXITING:
                game.exit();
                break;
        }
    }

    public GameState getGameState(){
        return gameState;
    }
}
