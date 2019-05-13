package minesweeper;

/**
 * Stopwatch class
 */
public class Stopwatch {

    /**
     * Start time in ms
     */
    private long startTime;

    /**
     * Start stopwatch.
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Reset stopwatch.
     */
    public void reset() {
        start();
    }

    /**
     * Returns elapsed time in secdonds.
     *
     * @return elapsed time in secdonds
     */
    public int elapsedTimeSeconds() {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }
}
