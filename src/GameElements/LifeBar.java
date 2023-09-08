package GameElements;

import BirdElements.Bird;
import Game.ShadowFlap;

import bagel.Image;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LifeBar {

    private int life;
    private Bird bird;
    private Image fullLifeImage;
    private Image noLifeImage;
    private static int corX = 100;
    private static int corY = 15;

    public LifeBar(Bird bird) {
        this.bird = bird;
        Path pathFullLifeImage = Paths.get("res/level/fullLife.png");
        Path pathNolLifeImage = Paths.get("res/level/noLife.png");
        this.fullLifeImage = new Image(pathFullLifeImage.toString());
        this.noLifeImage = new Image(pathNolLifeImage.toString());
        setLife();
    }

    public void setLife() {
        if(ShadowFlap.gameLevel == 0) {
            this.life = 3;
        } else if(ShadowFlap.gameLevel == 1) {
            this.life = 6;
        }
    }

    public void drawAllLifeBar() {
        if( ShadowFlap.gameLevel == 0 ) {
            int noLife = 3 - life;
            drawOneLifeBar(life, noLife);
        } else if( ShadowFlap.gameLevel == 1 ) {
            int noLife = 6 - life;
            drawOneLifeBar(life, noLife);
        }
    }

    public void drawOneLifeBar(int fullLife, int noLife) {
        for (int i = 0; i < fullLife; i++) {
            fullLifeImage.draw(corX + i * 50, corY );
        }
        while( noLife > 0 ) {
            noLifeImage.draw(corX + (life - 1 + noLife) * 50, corY );
            noLife--;
        }
    }

    public void interactLifeOut() {
        life--;
    }

    public void interactLifeKnock(int birdCorY) {
        life--;
        bird.setCorY(birdCorY);
    }

    public int getLifeRemain() {
        return life;
    }
}
