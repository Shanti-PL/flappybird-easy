package BirdElements;

import Game.ShadowFlap;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Bird {

    /**
     * The coordinate-x number of bird's position
     */
    private static final int corX = 200;

    /**
     * The coordinate-y number of bird's position
     */
    private int corY;

    /**
     * The image of bird wing
     */
    private Image wingDownLevel0;
    private Image wingUpLevel0;
    private Image wingDownLevel1;
    private Image wingUpLevel1;

    /**
     * The width of a bird
     */
    public static int birdWidth;

    /**
     * The height of a bird
     */
    public static int birdHeight;

    /**
     * Gravity is 0.4 pixel per frame-squared
     */
    private static final double Gravity = 0.4;

    /**
     * The max velocity is 10 pixels per frame
     */
    private static final int MAX_Velocity = 10;

    /**
     * The velocity of bird's fall
     */
    public int velocity = 0;

    /**
     * The state of the bird
     */
    public int birdState;
    public static final int BIRD_FLY = 1; // the bird is flying
    public static final int BIRD_FALL = 2; // the bird is falling
    public static final int BIRD_KNOCK = 3; // the bird is knocked on the pipe
    public static final int BIRD_OUT = 4; // the bird is out of bound

    /**
     * The collision rectangle of a bird
     */
    private Rectangle birdRect;

    /**
     * The constructor of bird
     *
     * @param y the coordinate-y number of bird's position
     */
    public Bird(int y) {
        Path pathWingDownLevel0 = Paths.get("res/level-0/birdWingDown.png");
        Path pathWingDownLevel1 = Paths.get("res/level-1/birdWingDown.png");
        Path pathWingUpLevel0 = Paths.get("res/level-0/birdWingUp.png");
        Path pathWingUpLevel1 = Paths.get("res/level-1/birdWingUp.png");
        this.wingDownLevel0 = new Image(pathWingDownLevel0.toString());
        this.wingUpLevel0 = new Image(pathWingUpLevel0.toString());
        this.wingDownLevel1 = new Image(pathWingDownLevel1.toString());
        this.wingUpLevel1 = new Image(pathWingUpLevel1.toString());
        this.corY = y;
        this.birdWidth = (int) wingDownLevel0.getWidth();
        this.birdHeight = (int) wingDownLevel0.getHeight();
        this.birdRect = wingDownLevel0.getBoundingBoxAt(new Point(corX, corY));
    }

    /**
     * Draw the BirdWingDown.png on Game window depending on the gamelevel
     */
    public void spawnDown() {
        if (ShadowFlap.gameLevel == 0) {
            wingDownLevel0.draw(corX, corY);
        } else if (ShadowFlap.gameLevel == 1) {
            wingDownLevel1.draw(corX, corY);
        }
    }

    /**
     * Draw the birdWingUp.png on Game window depending on the gamelevel
     */
    public void spawnUp() {
        if (ShadowFlap.gameLevel == 0) {
            wingUpLevel0.draw(corX, corY);
        } else if (ShadowFlap.gameLevel == 1) {
            wingUpLevel1.draw(corX, corY);
        }
    }

    /**
     * The action of bird fling
     */
    public void fly() {
        this.corY -= 6;
    }


    /**
     * The action of bird falling
     *
     * @param frame the frame has pass when the game is running
     */
    public void fall(int frame) {
        velocity += Gravity * frame;
        if (velocity < MAX_Velocity) {
            this.corY += velocity + (0.5 * Gravity * frame);
        } else {
            this.corY += MAX_Velocity + (0.5 * Gravity * frame);
        }
    }

    /**
     * The movement of bird which contains fly() and fall()
     *
     * @param frame the frame has pass when the game is running
     */
    public void move(int frame) {
        velocity = 0;
        if (birdState == BIRD_FALL) {
            fall(frame);
        } else if (birdState == BIRD_FLY) {
            fly();
        }
    }

    public void setCorYDefault() {
        this.corY = 350;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public void setBirdUpRect(int x, int y) {
        if (ShadowFlap.gameLevel == 0) {
            birdRect = wingUpLevel0.getBoundingBoxAt(new Point(x, y));
        } else if (ShadowFlap.gameLevel == 1) {
            birdRect = wingUpLevel1.getBoundingBoxAt(new Point(x, y));
        }
    }

    public void setBirdDownRect(int x, int y) {
        if (ShadowFlap.gameLevel == 0) {
            birdRect = wingDownLevel0.getBoundingBoxAt(new Point(x, y));
        } else if (ShadowFlap.gameLevel == 1) {
            birdRect = wingDownLevel1.getBoundingBoxAt(new Point(x, y));
        }
    }


    /**
     * Set the bird state when plager is playing
     *
     * @param n the integer number represents different bird states
     */
    public void setBirdState(int n) {
        birdState = n;
    }

    /**
     * Get the rectangle of the bird
     *
     * @return the rectangle of the bird
     */
    public Rectangle getBirdRect() {
        return birdRect;
    }

    public double getCenterX(Rectangle birdRect) {
        return (birdRect.left() + birdRect.right()) / 2;
    }

    public int getCorX() {
        return corX;
    }

    public int getCorY() {
        return corY;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public int getBirdState() {
        return this.birdState;
    }
}
