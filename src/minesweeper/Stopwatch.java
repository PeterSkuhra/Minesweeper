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
    public long elapsedTimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
