package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.swingui.SwingUI;
import minesweeper.core.Field;


/**
 * Main application class.
 *
 * @author      Peter Skuhra
 * @version     1.0
 * @since       20.5.2019
 */
public class Minesweeper {

    /**
     * Single instance of Minesweeper
     */
    private static Minesweeper instance;

    /**
     * Default user interface
     */
    //private static String DEFAULT_UI = "console";
    private static String DEFAULT_UI = "swing";

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
     * Settings object
     */
    private Settings setting;

    /**
     * Constructor.
     */
    private Minesweeper() {
        instance = this;

        setting = Settings.load();

        userInterface = create(DEFAULT_UI);
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
        if (instance == null) {
            instance = new Minesweeper();
        }

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
