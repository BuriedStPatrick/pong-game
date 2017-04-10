package pong.abstracts;

import pong.interfaces.InputHandler;
import pong.handlers.InputMapper;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public abstract class ControllableObject implements InputHandler{
    protected InputMapper inputMapper;

    protected ControllableObject(InputMapper inputMapper){
        this.inputMapper = inputMapper;
    }
}
