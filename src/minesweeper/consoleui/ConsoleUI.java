package minesweeper.consoleui;

import minesweeper.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            if (field.getState() == GameState.SOLVED) {
                System.out.println("\n\t\t!!!VICTORY!!!\n");
                break;
            }

            else if (field.getState() == GameState.FAILED) {
                System.out.println("\n\t\tDEFEAT!\n");
                break;
            }
        } while (true);

        update();
    }

    /**
     * Updates user interface - prints the field.
     */
    @Override
    public void update() {

        // Print num of columns
        System.out.printf("%3C", ' ');
        for (int i = 0; i < field.getColumnCount(); ++i) {
            System.out.printf("%3d", i);
        }
        System.out.println();

        // Print field
        for (int i = 0; i < field.getRowCount(); ++i) {
            System.out.printf("%3C", i + 65);

            for (int j = 0; j < field.getRowCount(); ++j) {
                Tile tile = field.getTile(i, j);
                Tile.State state = tile.getState();

                printStateChar(tile, state);
            }
            System.out.println();
        }
    }

    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {

        printMenu();
        String userRequest = readLine();
        Pattern pattern = Pattern.compile("([oO]|[mM])([a-iA-I])([0-8])");
        Matcher matcher = pattern.matcher(userRequest);

        while (!matcher.matches()) {
            System.out.println("Wrong input! Try again...");
            userRequest = readLine();
            matcher = pattern.matcher(userRequest);
        }

        String operation = matcher.group(1).toUpperCase();
        int row = ((int) (matcher.group(2).toUpperCase().charAt(0))) - 65;
        int column = Integer.parseInt(matcher.group(3));

        switch (operation) {
            case "O":
                field.openTile(row, column);
                break;

            case "M":
                field.markTile(row, column);
                break;

            case "X":
                System.exit(0);
        }
    }

    /**
     * Prints the selected tile character according to its state
     *
     * @param tile  selected tile
     * @param state state of tile
     */
    private void printStateChar(Tile tile, Tile.State state) {
        switch (state) {
            case CLOSED:
                System.out.printf("%3C", '-');
                break;

            case MARKED:
                System.out.printf("%3C", 'M');
                break;

            case OPEN:
                if (tile instanceof Mine) {
                    System.out.printf("%3C", 'X');
                    break;
                }
                else if (tile instanceof Clue) {
                    System.out.printf("%3d", ((Clue) tile).getValue());
                }
        }
    }

    /**
     * Print possible selections for user
     */
    private void printMenu() {
        System.out.print("\nPlease enter your selection ");
        System.out.print("(X) - EXIT, ");
        System.out.print("(MA1) - MARK, ");
        System.out.print("(OB4) - OPEN ");
        System.out.print(":\t");
    }
}

