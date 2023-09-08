package GameElements;

import Game.ShadowFlap;
import bagel.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class of game background
 */
public class Background {

    /**
     * The image of game background
     */
    private Image level0Background;
    private Image level1Background;

    public Background(int gameLevel) {
        Path pathLevel0Background = Paths.get("res/level-0/background.png");
        Path pathLevel1Background = Paths.get("res/level-1/background.png");
        this.level0Background = new Image(pathLevel0Background.toString());
        this.level1Background = new Image(pathLevel1Background.toString());
    }


    public void drawBackground() {
        if(ShadowFlap.gameLevel == 0) {
            level0Background.drawFromTopLeft(0, 0);
        } else if( ShadowFlap.gameLevel == 1) {
            level1Background.drawFromTopLeft(0,0);
        }
    }


}
