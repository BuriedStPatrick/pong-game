package pong.interfaces;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public interface InputHandler {
    void handlePress(int keyCode);
    void handleRelease(int keyCode);
}
