package GameElements;

import BirdElements.Bird;

import bagel.Font;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreCounter {

    /**
     * The bird in the game
     */
    private Bird bird;

    /**
     * The game score of player
     */
    private int score;

    /**
     * Font and corrseponding properties
     */
    private final Font font;
    private static final int fontSize = 48;

    /**
     * The constructor of ScoreCounter
     *
     * @param bird the bird object in the game
     */
    public ScoreCounter(Bird bird) {
        this.bird = bird;
        this.score = 0;
        Path pathFont = Paths.get("res/font/slkscr.ttf");
        this.font = new Font(pathFont.toString(), fontSize);
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public void drawScore() {
        font.drawString("SCORE " + score, 100, 100);
    }


}
