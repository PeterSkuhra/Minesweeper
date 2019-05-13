package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;
import minesweeper.swingui.SwingUI;

/**
 * Main application class.
 */
public class Minesweeper {

    /**
     * Single instance of Minesweeper
     */
    private static Minesweeper instance;

    /**
     * Default user interface
     */
    private static final String DEFAULT_UI = "console";
    // private static final String DEFAULT_UI = "swing";

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
    private Settings setting;

    /**
     * Constructor.
     */
    private Minesweeper() {
        instance = this;

        //setting = Settings.load();
        setting = Settings.BEGINNER; //TODO: fix load !!!

        userInterface = create(DEFAULT_UI);
        bestTimes = new BestTimes();
        stopwatch = new Stopwatch();
    }

    /**
     * Create user interface to specified name
     *
     * @param name name of user interface
     * @return user interface
     */
    private IUserInterface create(String name) {
        switch(name) {
            case "swing":
                return new SwingUI();

            case "console":
                return new ConsoleUI();

            default:
                throw new RuntimeException("No valid UI specified");
        }
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
     * Starts new game
     */
    public void newGame() {
        field = new Field(setting.getRowCount(),
                setting.getColumnCount(),
                setting.getMineCount());

        stopwatch.start();
        userInterface.newGameStarted(field);
    }

    /**
     * Return playing seconds
     *
     * @return playing seconds
     */
    public int getPlayingSeconds() {
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
    public Settings getSetting() {
        return setting;
    }

    /**
     * Set object of Settings class to specified setting.
     *
     * @param setting specified setting
     */
    public void setSetting(Settings setting) {
        this.setting = setting;
        setting.save();
    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
       Minesweeper.getInstance().newGame();
    }
}
