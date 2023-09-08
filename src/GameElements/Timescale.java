package GameElements;

import bagel.AbstractGame;

public class Timescale {

    private static int timescale = 1;

    public static int MAX_TIMESCALE = 5;
    public static int MIN_TIMESCALE = 1;

    public Timescale() {
    }

    public int getTimescale() {
        return timescale;
    }

    public void increaseTimescale() {
        if (getTimescale() < MAX_TIMESCALE) {
            timescale += 1;
        } else {
            timescale = MAX_TIMESCALE;
        }
    }

    public void decreaseTimescale() {
        if (getTimescale() > MIN_TIMESCALE) {
            timescale -= 1;
        } else {
            timescale = MIN_TIMESCALE;
        }

    }

}
