package PipeElements;


import GameElements.Timescale;

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Pipe {

    /**
     * The coordinate-x of pipe
     */
    public double corX;

    /**
     * The coordinate-y of pipeTop
     */
    public int topY;

    /**
     * The coordinate-y of pipeBottom
     */
    public int bottomY;

    /**
     * The gap between top and bottom pipe is 168 pixels
     */
    public static final int GAP = 168;

    /**
     * The moving speed of a pipe
     */
    public static double iniSpeed = 3.0;
    public int realSpeed;
    public static Timescale timescale = new Timescale();

    /**
     * The width of a pipe
     */
    public double pipeWidth;

    /**
     * The height of a pipe
     */
    public double pipeHeight;

    /**
     * The constructor of Pipe
     */
    public Pipe(double corX) {
        this.realSpeed = (int) iniSpeed;
        this.corX = corX;
    }


    /**
     * Get the collision rectangle of pipe.
     *
     * @return the collision rectangle of top pipe
     */
    public Rectangle getTopPipeRect(Image pipeImage) {
        return pipeImage.getBoundingBoxAt(new Point(corX, topY));
    }

    /**
     * Get the collision rectangle of pipe.
     *
     * @return the collision rectangle of bottom pipe
     */
    public Rectangle getBottomPipeRect(Image pipeImage) {
        return pipeImage.getBoundingBoxAt(new Point(corX, bottomY));
    }

    /**
     * Draw the top pipe
     */
    public void spawnTop() {
    }

    /**
     * Draw the bottom pipe
     */
    public void spawnBottom() {
    }

    /**
     * Get the pipe Image
     *
     * @return the image of pipe
     */
    public Image getPipeImage() {
        return null;
    }

    /**
     * Make the pipe move on screen n pixels per frame
     */
    public void moveToLeft() {
        realSpeed = checkRealSpeed(iniSpeed * Math.pow(1.5, (timescale.getTimescale() - 1.0)));
        this.corX -= realSpeed;
    }

    /**
     * The method to check the real speed of pipe moving,
     * the real speed should be the nearest integer of iniSpeed after timescale controlling
     *
     * @param curSpeed the speed which is iniSpeed after timescale controlling
     * @return
     */
    public int checkRealSpeed(double curSpeed) {
        int curSpeedPre = (int) curSpeed;
        if (curSpeed - curSpeedPre >= 0.5) {
            return curSpeedPre + 1;
        }
        return curSpeedPre;
    }

    /**
     * Get the current speed of pipe moving.
     *
     * @return
     */
    public int getRealSpeed() {
        return realSpeed;
    }
}

