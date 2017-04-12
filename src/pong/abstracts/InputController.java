package pong.abstracts;

import pong.interfaces.InputHandler;
import pong.handlers.InputMapper;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class InputController implements InputHandler{
    protected InputMapper inputMapper;
    protected InputController(InputMapper inputMapper){
        this.inputMapper = inputMapper;
    }
}
