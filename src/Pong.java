import pong.abstracts.CollidableObject;
import pong.enums.GameState;
import pong.enums.InputType;
import pong.geometry.Bounds;
import pong.geometry.Vector;
import pong.handlers.InputMapper;
import pong.helpers.MathHelper;
import pong.interfaces.Game;
import pong.managers.GameManager;
import pong.models.Player;
import pong.models.gameobjects.Ball;
import pong.models.gameobjects.Goal;
import pong.models.gameobjects.Racquet;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by patrick.christensen on 4/10/2017.
 */
public class Pong extends PApplet implements Game{

    private static final int PADDING = 20;
    private static final boolean DEBUG = false;
    private Player player1, player2;
    private Goal goal1, goal2;
    private Ball ball;
    private ArrayList<CollidableObject> collidables;
    private ArrayList<Player> players;
    private Bounds windowBounds;
    private GameManager gameManager;

    public static void main(String[] args) {
        PApplet.main("Pong");
    }

    @Override
    public void setup() {
        super.setup();
        gameManager = new GameManager(this,
                new InputMapper()
                    .map(ESC, InputType.GAME_EXIT)
                    .map(BACKSPACE, InputType.GAME_PAUSE)
                    .map(81, InputType.GAME_END)
        );
        windowBounds = new Bounds(width, height);
        collidables = new ArrayList<>();
        setupPlayers();
        setupGoals();
        ball = new Ball(
                new Vector(width/2, height/2),
                new Bounds(25, 25),
                this
        );
        collidables.add(ball);
    }

    @Override
    public void draw() {
        if(gameManager.getGameState() != GameState.PAUSED){
            update();
            background(255, 204, 0);
            Racquet racquet1 = player1.getRacquet();
            Racquet racquet2 = player2.getRacquet();
            drawRacquet(racquet1);
            drawRacquet(racquet2);
            drawBall(ball);
            drawScore();
            if(DEBUG){
                drawDebug();
            }
        }else if(gameManager.getGameState() == GameState.GAME_OVER){
            drawGameOver();
        }
    }

    private void drawDebug(){
        rect(
                goal1.getPosition().getX(),
                goal1.getPosition().getY(),
                goal1.getBounds().getWidth(),
                goal1.getBounds().getHeight()
        );
        rect(
                goal2.getPosition().getX(),
                goal2.getPosition().getY(),
                goal2.getBounds().getWidth(),
                goal2.getBounds().getHeight()
        );
    }

    private void drawScore(){
        int scorePadding = 10;
        Bounds scoreBounds = new Bounds(windowBounds.getWidth()/3, windowBounds.getHeight()/3);
        Vector scorePos = new Vector(width/2-(scoreBounds.getWidth()/2), height-(scoreBounds.getHeight()/2));
        textSize(32f);

        fill(255, 255, 255);
        text("Score", scorePos.getX() + scorePadding, scorePos.getY() + 32 + scorePadding);

        text("Player 1: " + player1.getScore(), scorePos.getX() + scorePadding, scorePos.getY() + 64 + scorePadding);

        fill(0, 0, 0, 75f);
        rect(scorePos.getX(), scorePos.getY(), scoreBounds.getWidth(), scoreBounds.getHeight());
    }

    private void drawBall(Ball ball){
        fill(150);
        ellipseMode(CENTER);
        noStroke();
        smooth();
        ellipse(
                ball.getPosition().getX(),
                ball.getPosition().getY(),
                ball.getBounds().getWidth(),
                ball.getBounds().getHeight()
        );
    }

    private void drawRacquet(Racquet racquet){
        fill(150, 50, 240);
        noStroke();
        rect(
            racquet.getPosition().getX(),
            racquet.getPosition().getY(),
            racquet.getBounds().getWidth(),
            racquet.getBounds().getHeight()
        );
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        gameManager.handlePress(keyCode);
        player1.handlePress(keyCode);
        player2.handlePress(keyCode);
    }

    @Override
    public void keyReleased() {
        super.keyReleased();
        gameManager.handleRelease(keyCode);
        player1.handleRelease(keyCode);
        player2.handleRelease(keyCode);
    }

    private void update(){
        gameManager.update();
        checkWindowBounds();
        checkCollisions();
        ball.update();
        player1.getRacquet().update();
        player2.getRacquet().update();
    }

    private void checkWindowBounds(){
        for(CollidableObject collidable : collidables){
            if(!collidable.isWithinBounds(windowBounds)){
                collidable.onCollide(windowBounds);
            }
        }
    }

    private void checkCollisions(){
        for(CollidableObject collidable : collidables){
            for(CollidableObject other : collidables){
                if(collidable != other && collidable.collidesWith(other)){
                    collidable.onCollide(other);
                }
            }
        }
    }

    private void setupPlayers() {
        players = new ArrayList<>();
        Racquet racquet1 = new Racquet(new Vector(PADDING, height/2));
        Racquet racquet2 = new Racquet(new Vector(width - (PADDING + Racquet.WIDTH), height/2));
        collidables.add(racquet1);
        collidables.add(racquet2);

        InputMapper player1Input = new InputMapper()
            .map('w', InputType.PLAYER_UP)
            .map('W', InputType.PLAYER_UP)
            .map('s', InputType.PLAYER_DOWN)
            .map('S', InputType.PLAYER_DOWN);
        InputMapper player2Input = new InputMapper()
            .map(UP, InputType.PLAYER_UP)
            .map(DOWN, InputType.PLAYER_DOWN);
        player1 = new Player(racquet1, player1Input);
        player2 = new Player(racquet2, player2Input);

        players.add(player1);
        players.add(player2);
    }

    private void setupGoals(){
        goal1 = new Goal(new Vector(0, 0), new Bounds(PADDING, height), player1);
        goal2 = new Goal(new Vector(width-PADDING, 0), new Bounds(PADDING, height), player2);
        collidables.add(goal1);
        collidables.add(goal2);
    }

    @Override
    public void settings() {
        super.settings();
        size(1280, 720);
    }

    @Override
    public void pause() {
        super.pause();
        System.out.println("Pausing");
    }

    @Override
    public void resume() {
        super.resume();
        System.out.println("Resuming");
    }

    @Override
    public void gameOver() {
        System.out.println("Game over!");
        drawGameOver();
    }

    @Override
    public void registerGoal(Goal goal) {
        Player keeper = goal.getKeeper();
        for(Player player : players){
            if(player != keeper){
                player.addScore();
                System.out.println("adding score, total: " + player.getScore());
                resetBall();
            }
        }
    }

    private void resetBall(){
        int randomDegrees =
                MathHelper.limitValue(
                    new Random().nextInt(330),
                    30,
                    330
                );
        ball.setPosition(new Vector(width/2, height/2));
        Vector resetVelocity = new Vector(1, 1);
        resetVelocity.setLength(ball.MAX_VELOCITY);
        resetVelocity.rotate(randomDegrees);
        ball.setVelocity(resetVelocity);
    }

    private void drawGameOver(){
        textSize(32);
        fill(0, 102, 153, 51);
        text("Game is over", width/2, height/2);
        textSize(16);
        fill(0, 102, 153);
        text("Score: " + player1.getScore() + " to " + player2.getScore(), width/2, (height/2) + 100);
    }
}
