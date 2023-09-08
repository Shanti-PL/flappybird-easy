package PipeElements;

import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;

import java.nio.file.Path;
import java.nio.file.Paths;


public class PipeLevel0 extends Pipe {

    private Image plasticPipeImage;

    public PipeLevel0(double corX, int topY) {
        super(corX);
        Path pathPlasticPipeImage = Paths.get("res/level/plasticPipe.png");
        this.plasticPipeImage = new Image(pathPlasticPipeImage.toString());
        this.pipeWidth = plasticPipeImage.getWidth();
        this.pipeHeight = plasticPipeImage.getHeight();
        this.topY = (topY - 300) - GAP / 2;
        this.bottomY = Window.getHeight() + GAP / 2 + (topY - 300);
    }

    @Override
    public void spawnTop() {
        plasticPipeImage.draw(corX, topY);
    }

    @Override
    public void spawnBottom() {
        plasticPipeImage.draw(corX, bottomY, new DrawOptions().setRotation(Math.PI));
    }

    @Override
    public Image getPipeImage() {
        return plasticPipeImage;
    }
}
