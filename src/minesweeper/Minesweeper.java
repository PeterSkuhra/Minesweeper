package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {

    /**
     * Single instance of Minesweeper
     */
    private static Minesweeper instance;

    /**
     * User interface.
     */
    private IUserInterface userInterface;

    /**
     * Playing field.
     */
    private Field field;

    /**
     * Stopwatch
     */
    private Stopwatch stopwatch;

    /**
     * Best times
     */
    private BestTimes bestTimes;

    /**
     * Settings object
     */
    private Settings settings;

    /**
     * Constructor.
     */
    private Minesweeper() {
        instance = this;

        userInterface = new ConsoleUI();
        settings = Settings.load();

        field = new Field(settings.getRowCount(),
                settings.getColumnCount(),
                settings.getMineCount());

        stopwatch = new Stopwatch();
        bestTimes = new BestTimes();
    }

    /**
     * Returns single instance of Minesweeper
     *
     * @return single instance of Minesweeper
     */
    public static Minesweeper getInstance() {
        return instance;
    }

    /**
     * Run method.
     */
    private void run() {
        userInterface.newGameStarted(field);
        stopwatch.start();
    }

    /**
     * Return playing seconds
     *
     * @return playing seconds
     */
    public long getPlayingSeconds() {
        return stopwatch.elapsedTimeSeconds();
    }

    /**
     * Returns bestTimes object
     *
     * @return best times
     */
    public BestTimes getBestTimes() {
        return bestTimes;
    }

    /**
     * Returns object of Settings class.
     *
     * @return object of Settings class
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Set object of Settings class to specified setting.
     *
     * @param settings specified setting
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
        settings.save();
    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper().run();
    }
}
