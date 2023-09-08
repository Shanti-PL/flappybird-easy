package PipeElements;

import Game.ShadowFlap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PipeList {

    private List<Pipe> pipes;


    public PipeList() {
        pipes = new ArrayList<>();
    }

    public void createLevel0Pipes(int framePipe) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int type = rand.nextInt(3);
            if (type == 0 && framePipe % 100 == 0) {
                pipes.add(new PipeLevel0(ShadowFlap.WIDTH + 300 + i * 600, 100));
            } else if (type == 1 && framePipe % 100 == 0) {
                pipes.add(new PipeLevel0(ShadowFlap.WIDTH + 300 + i * 600, 300));
            } else if (type == 2 && framePipe % 100 == 0) {
                pipes.add(new PipeLevel0(ShadowFlap.WIDTH + 300 + i * 600, 500));
            }
        }
    }

    public void createLevel1Pipes(int framePipe) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int topY = rand.nextInt(401) + 100;
            int chooseType = rand.nextInt(2);
            if (framePipe % 100 == 0 && chooseType == 0) {
                pipes.add(new PipeLevel0(ShadowFlap.WIDTH + 7000 + i * 600, topY));
            } else if( framePipe % 100 == 0 && chooseType == 1) {
                pipes.add(new PipeLevel1(ShadowFlap.WIDTH + 7000 + i * 600, topY));
            }
        }
    }

    public List<Pipe> getPipe() {
        return pipes;
    }

    public void drawOnePipe(Pipe pipe) {
        pipe.spawnTop();
        pipe.spawnBottom();
        pipe.moveToLeft();
    }

    public void drawAllPipes() {
        int i = 0;
        while (i < 30) {
            drawOnePipe(pipes.get(i));
            i++;
        }
    }

}
