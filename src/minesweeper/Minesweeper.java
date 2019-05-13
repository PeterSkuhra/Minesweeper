package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
    /**
     * User interface.
     */
    private IUserInterface userInterface;

    /**
     * Playing field.
     */
    private Field field;

    /**
     * Constructor.
     */
    private Minesweeper() {
        userInterface = new ConsoleUI();

        field = new Field(9, 9, 10);
    }

    private void run() {
        userInterface.newGameStarted(field);
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
