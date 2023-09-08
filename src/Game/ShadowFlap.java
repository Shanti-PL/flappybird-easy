package Game;

import PipeElements.*;
import GameElements.*;
import BirdElements.*;

import bagel.*;
import bagel.util.Rectangle;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A small flappybird game which has two levels
 * <p>
 *
 * @author: Junyi(Shanti) Fan
 */
public class ShadowFlap extends AbstractGame {

    /**
     * Game level 0 or 1
     */
    public static int gameLevel = 0;
    public static final int Level0 = 0;
    public static final int Level1 = 1;

    /**
     * Screen size
     */
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    /**
     * Font and corrseponding properties
     */
    private final Font font;
    private static final int fontSize = 48;

    /**
     * The score counter
     */
    private ScoreCounter counter;

    /**
     * Count the frame of bird fly from 0
     */
    private static int frame = 0;
    private static final int Multiple = 10;

    /**
     * Count the frame of pipe move from 0
     */
    private static int framePipe = 0;

    /**
     * The frame to count LEVEL_UP screen from 0
     */
    private static int pauseFrame = 0;

    /**
     * Count the frame of weapon operation from 1
     */
    private static int weaponframe = 1;

    /**
     * Game background
     */
    private Background background;

    /**
     * Bird object
     */
    private final Bird bird;
    private static final int start_Y = 350; // the start position of bird in coordinate-y is 350 pixels

    /**
     * Pipe object
     */
    private PipeList pipes;

    /**
     * Life Bar object
     */
    private LifeBar lifeBar;

    /**
     * Timescale object
     */
    private Timescale timescale;


    /**
     * The game's state
     */
    private int gameState;
    public static final int GAME_NOT_START = 0; // the game has not start yet
    public static final int GAME_START = 1; // the game has start
    public static final int GAME_OVER = 2; // the game is over
    public static final int GAME_WIN = 3; // player win the game
    public static final int GAME_LEVEL_UP = 4; // the state to show Level Up screen

    /**
     * The constructor of class ShadowFlap to initialise it
     */
    public ShadowFlap() {
        this.background = new Background(gameLevel);
        this.bird = new Bird(start_Y);
        Path pathFont = Paths.get("res/font/slkscr.ttf");
        this.font = new Font(pathFont.toString(), fontSize);
        this.counter = new ScoreCounter(bird);
        this.pipes = new PipeList();
        this.lifeBar = new LifeBar(bird);
        this.timescale = new Timescale();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        int frameFlame = 0;
        // draw background
        background.drawBackground();


        // if player press ESCAPE, the game will exit
        if (input.wasPressed(Keys.ESCAPE)) {
            System.exit(0);
        }

        if (input.wasPressed(Keys.L)) {
            timescale.increaseTimescale();
        } else if (input.wasPressed(Keys.K)) {
            timescale.decreaseTimescale();
        }


        if (gameState == GAME_NOT_START) {
            if (input.wasPressed(Keys.SPACE)) {
                // game start when user press SPACE
                gameState = GAME_START;
            }
            double textWidth = font.getWidth("PRESS SPACE TO START");
            font.drawString("PRESS SPACE TO START", (WIDTH - textWidth) / 2, HEIGHT / 2);
        } else if (gameState == GAME_OVER) {
            double textWidth = font.getWidth("GAME OVER");
            font.drawString("GAME OVER", (WIDTH - textWidth) / 2, HEIGHT / 2);
            textWidth = font.getWidth("FINAL SCORE: " + counter.getScore());
            font.drawString("FINAL SCORE " + counter.getScore(), (WIDTH - textWidth) / 2, (HEIGHT / 2) + 75);
        } else if (gameState == GAME_WIN) {
            gameLevel = Level0;
            double textWidth = font.getWidth("CONGRATULATIONS!");
            font.drawString("CONGRATULATIONS!", (WIDTH - textWidth) / 2, HEIGHT / 2);
        } else if (gameState == GAME_LEVEL_UP) {
            if (input.wasPressed(Keys.SPACE)) {
                // game start when user press SPACE
                bird.setCorYDefault();
                gameState = GAME_START;
            }
            double textWidth = font.getWidth("PRESS SPACE TO START");
            font.drawString("PRESS SPACE TO START", (WIDTH - textWidth) / 2, HEIGHT / 2);
            textWidth = font.getWidth("PRESS S TO SHOOT");
            font.drawString("PRESS S TO SHOOT", (WIDTH - textWidth) / 2, HEIGHT / 2 + 68);
        } else if (gameState == GAME_START) {

            // draw score counter
            counter.drawScore();

            // draw lifeBar
            lifeBar.drawAllLifeBar();

            // bird operation
            if (input.isDown(Keys.SPACE)) {
                bird.setBirdState(Bird.BIRD_FLY);
                frame = 0;
            } else {
                bird.setBirdState(Bird.BIRD_FALL);
            }
            bird.move(frame);

            if (frame % Multiple == 0) {
                bird.spawnUp();
                bird.setBirdUpRect(bird.getCorX(), bird.getCorY());
            } else {
                bird.spawnDown();
                bird.setBirdDownRect(bird.getCorX(), bird.getCorY());
            }

            // draw pipes and bird
            pipes.createLevel0Pipes(framePipe);
            pipes.createLevel1Pipes(framePipe);
            pipes.drawAllPipes();

            // counter operation
            // as I set three frame in update(), so I set pauseFrame as 20*3(60)
            if (counter.getScore() == 10 && pauseFrame < 20) {
                drawLevelUpBackground();
                pauseFrame++;
            } else if (counter.getScore() == 10 && pauseFrame == 20) {
                gameLevel = Level1;
                lifeBar.setLife();
                this.gameState = GAME_LEVEL_UP;
                pauseFrame++;
            } else if (counter.getScore() == 30) {
                this.gameState = GAME_WIN;
            } else if (lifeBar.getLifeRemain() == 0) {
                this.gameState = GAME_OVER;
            } else {
                framePipe++;
            }

            if( gameLevel == Level1 && frameFlame % 20 == 0 ) {

            }

            // level 0 game
            for (int i = 0; i < 30; i++) {
                Pipe curPipe = pipes.getPipe().get(i);
                Rectangle birdRect = bird.getBirdRect();
                Rectangle topPipeRect = curPipe.getTopPipeRect(curPipe.getPipeImage());
                Rectangle bottomPipeRect = curPipe.getBottomPipeRect(curPipe.getPipeImage());
                int curPipeSpeed = curPipe.getRealSpeed();
                // level 0 game
                if( i < 10 ) {
                    // Out-of-Bound Detection
                    if (birdOutOfBound(bird)) {
                        bird.setBirdState(Bird.BIRD_OUT);
                        bird.setCorYDefault();
                        lifeBar.interactLifeOut();
                    }
                    // collision detection
                    else if (detectCollisionLevel0(birdRect, topPipeRect, bottomPipeRect)) {
                        bird.setBirdState(Bird.BIRD_KNOCK);
                        int birdCorY = (int) topPipeRect.bottomLeft().y + Pipe.GAP / 2;
                        lifeBar.interactLifeKnock(birdCorY);

                    }
                    // score detection
                    else if (scoreDetectionLevel0(bird, topPipeRect, curPipeSpeed)) {
                        counter.addScore();
                        counter.drawScore();
                    }
                }
                // level 1 game
                else if( i >= 10 ) {
                    Flame curFlame = new Flame((int) topPipeRect.centre().x, (int) topPipeRect.bottom());
                    if (curPipe.getClass().getName().equals("PipeElements.PipeLevel1") && curFlame.getFlameState() == Flame.LIGHT) {
                        curFlame.drawFlame();
                    }
                }
            }

        }
        frame++;
    }



    public boolean detectCollisionLevel0(Rectangle birdRect, Rectangle topPipeRect, Rectangle bottomPipeRect) {
        return birdRect.intersects(topPipeRect) ||
                birdRect.intersects(bottomPipeRect);
    }

    public boolean scoreDetectionLevel0(Bird bird, Rectangle topPipeRect, int curPipeSpeed) {
        return bird.getCenterX(bird.getBirdRect()) - topPipeRect.right() <= curPipeSpeed &&
                bird.getCenterX(bird.getBirdRect()) - topPipeRect.right() >= 0;
    }

    public boolean birdOutOfBound(Bird bird) {
        return bird.getCorY() + bird.getBirdHeight() / 2 <= 0.0
                || bird.getCorY() + bird.getBirdHeight() / 2 >= 768.0;
    }


    public void drawLevelUpBackground() {
        background.drawBackground();
        double textWidth = font.getWidth("LEVEL-UP!");
        font.drawString("LEVEL-UP!", (WIDTH - textWidth) / 2, HEIGHT / 2);
        textWidth = font.getWidth("FINAL SCORE: " + counter.getScore());
        font.drawString("FINAL SCORE " + counter.getScore(), (WIDTH - textWidth) / 2, (HEIGHT / 2) + 75);
    }


}
