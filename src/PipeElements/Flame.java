package PipeElements;

import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Flame {

    private int corX;
    private int topY;
    private int bottomY;
    private Image flameImage;
    private double flameWidth;
    private double flameHeight;

    public int flameState;
    public static int LIGHT = 0;
    public static int DARK = 1;

    public Flame(int corX, int topY) {
        this.corX = corX + 5;
        this.topY = topY + (int)flameHeight + 10;
        this.bottomY = topY + Pipe.GAP - ((int) flameHeight + 10);
        Path pathFlameImage = Paths.get("res/level-1/flame.png");
        this.flameImage = new Image(pathFlameImage.toString());
        this.flameWidth = flameImage.getWidth();
        this.flameHeight = flameImage.getHeight();
        this.flameState = LIGHT;
    }

    private Rectangle getTopFlame(Image flameImage) {
        return flameImage.getBoundingBoxAt(new Point(corX, topY));
    }

    private Rectangle getBottomFlame(Image flameImage) {
        return flameImage.getBoundingBoxAt(new Point(corX, bottomY));
    }

    private void spawnTop() {
        flameImage.draw(corX, topY);
    }

    private void spawnBottom() {
        flameImage.draw(corX, bottomY, new DrawOptions().setRotation(Math.PI));
    }

    public void setFlameState() {
        if( flameState == LIGHT ) {
            flameState = DARK;
        } else if( flameState == DARK ) {
            flameState = LIGHT;
        }
    }

    public void drawFlame() {
        spawnTop();
        spawnBottom();
    }

    public int getFlameState() {
        return flameState;
    }
}
