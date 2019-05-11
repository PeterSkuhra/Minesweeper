package minesweeper;

import minesweeper.core.Field;

public interface IUserInterface {

    /**
     * Starts the game.
     *
     * @param field field of mines and clues
     */
    void newGameStarted(Field field);

    /**
     * Updates user interface
     */
    void update();
}
