package PipeElements;

import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;

import java.nio.file.Path;
import java.nio.file.Paths;


public class PipeLevel1 extends Pipe {

    private Image steelPipeImage;

    public PipeLevel1(double corX, int topY) {
        super(corX);
        Path pathSteelPipeImage = Paths.get("res/level-1/steelPipe.png");
        this.steelPipeImage = new Image(pathSteelPipeImage.toString());
        this.pipeWidth = steelPipeImage.getWidth();
        this.pipeHeight = steelPipeImage.getHeight();
        this.topY = (topY - 300) - GAP / 2;
        this.bottomY = Window.getHeight() + GAP / 2 + (topY - 300);
    }

    @Override
    public void spawnTop() {
        steelPipeImage.draw(corX, topY);
    }

    @Override
    public void spawnBottom() {
        steelPipeImage.draw(corX, bottomY, new DrawOptions().setRotation(Math.PI));
    }

    @Override
    public Image getPipeImage() {
        return steelPipeImage;
    }
}
