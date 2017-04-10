import pong.enums.InputType;
import pong.handlers.InputMapper;
import pong.models.*;
import processing.core.PApplet;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Pong extends PApplet{

    private static final int PADDING = 10;
    private Player player1, player2;
    private Ball ball;

    public static void main(String[] args) {
        PApplet.main("Pong");
    }

    @Override
    public void draw() {
        update();
        fill(150, 50, 240);
        background(255, 204, 0);
        Racquet racquet1 = player1.getRacquet();
        Racquet racquet2 = player2.getRacquet();
        rect(racquet1.getPosX(), racquet1.getPosY(), racquet1.getWidth(), racquet1.getHeight());
        rect(racquet2.getPosX(), racquet2.getPosY(), racquet2.getWidth(), racquet2.getHeight());
        ellipse(ball.getPosX(), ball.getPosY(), ball.getWidth(), ball.getHeight());
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        player1.handlePress(keyCode);
        player2.handlePress(keyCode);
    }

    @Override
    public void keyReleased() {
        super.keyReleased();
        player1.handleRelease(keyCode);
        player2.handleRelease(keyCode);
    }

    private void update(){
        ball.update();
        player1.getRacquet().update();
        player2.getRacquet().update();
    }

    @Override
    public void setup() {
        super.setup();
        mapInputs();
        ball = new Ball(width/2, height/2, 25, 25);
    }

    private void mapInputs(){
        Racquet racquet1 = new Racquet(PADDING, height/2);
        Racquet racquet2 = new Racquet(width - PADDING, height/2);
        InputMapper player1Input = new InputMapper()
                .map(UP, InputType.PLAYER_UP)
                .map(DOWN, InputType.PLAYER_DOWN);
        InputMapper player2Input = new InputMapper()
                .map('w', InputType.PLAYER_UP)
                .map('W', InputType.PLAYER_UP)
                .map('s', InputType.PLAYER_DOWN)
                .map('S', InputType.PLAYER_DOWN);
        player1 = new Player(racquet1, player1Input);
        player2 = new Player(racquet2, player2Input);
    }

    @Override
    public void settings() {
        super.settings();
        size(1280, 720);
    }
}
