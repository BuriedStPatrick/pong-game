package pong.handlers;

import pong.enums.InputType;

import java.util.HashMap;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class InputMapper {

    private HashMap<Integer, InputType> map; //keyCode, Input-type

    public InputMapper(){
        map = new HashMap<>();
    }

    public InputMapper map(int keyCode, InputType input){
        map.put(keyCode, input);
        return this;
    }

    public InputType getInput(int keyCode){
        try{
            return map.get(keyCode);
        }catch(NullPointerException npe){
            return null;
        }
    }
}
