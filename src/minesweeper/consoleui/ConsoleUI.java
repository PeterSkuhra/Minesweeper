package minesweeper.consoleui;

import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console user interface.
 */
public class ConsoleUI implements minesweeper.IUserInterface {
    /**
     * Playing field.
     */
    private Field field;

    /**
     * Input reader.
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads line of text from the reader.
     *
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Starts the game.
     *
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {
        this.field = field;

        do {
            update();
            processInput();
            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while (true);
    }

    /**
     * Updates user interface - prints the field.
     */
    @Override
    public void update() {

        // Print num of columns
        System.out.printf("\t");
        for (int i = 0; i < field.getColumnCount(); ++i) {
            System.out.printf("%d3", i);
        }

        // Print field
        for (int i = 0; i < field.getRowCount(); ++i) {
            System.out.printf("%C3", i + 65);

            for (int j = 0; j < field.getRowCount(); ++j) {
                Tile tile = field.getTile(i, j);
                Tile.State state = tile.getState();

                switch (state) {
                    case CLOSED:
                        System.out.printf("%C3", '-');
                        break;

                    case MARKED:
                        System.out.printf("%C3", 'M');
                        break;

                    case OPEN:
                        if (tile instanceof Mine) {
                            System.out.printf("%C3", 'X');
                            break;
                        }
                        else if (tile instanceof Clue){
                            System.out.printf("%d3", ((Clue) tile).getValue());
                        }
                }

            }
        }

        printSelection();
    }

    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        throw new UnsupportedOperationException("Method processInput not yet implemented");
    }

    /**
     * Print possible selections for user
     */
    private void printSelection() {
        System.out.print("\nPlease enter your selection ");
        System.out.print("(X) EXIT ");
        System.out.print("(MA1) MARK ");
        System.out.print("(OB4) OPEN ");
        System.out.println(":");
    }
}
