package minesweeper.core;

import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {

    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;

    /**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }

    /**
     * Returns chosen tile
     *
     * @param row    row number
     * @param column column number
     * @return chosen tile
     */
    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    /**
     * Returns row count
     *
     * @return row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Returns column count
     *
     * @return column count
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Returns mine count
     *
     * @return mine count
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * Returns state of game
     *
     * @return state of game
     */
    public GameState getState() {
        return state;
    }

    /**
     * Opens tile at specified indexes.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        final Tile tile = tiles[row][column];

        if (tile.getState() == Tile.State.CLOSED) {
            tile.setState(Tile.State.OPEN);

            if (tile instanceof Mine) {
                state = GameState.FAILED;
                return;
            }

            else if (countAdjacentMines(row, column) == 0) {
                openAdjacentTiles(row, column);
            }

            if (isSolved()) {
                state = GameState.SOLVED;
            }
        }
    }

    /**
     * Marks tile at specified indexes.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {
        final Tile tile = tiles[row][column];

        if (tile.getState() == Tile.State.CLOSED)
            tile.setState(Tile.State.MARKED);

        else if (tile.getState() == Tile.State.MARKED)
            tile.setState(Tile.State.CLOSED);
    }

    /**
     * Returns count of remaining mines in field
     *
     * @return  remaining mine count
     */
    public int getRemainingMineCount() {
        int markedTilesCount = getNumberOf(Tile.State.MARKED);

        return mineCount - markedTilesCount;
    }

    /**
     * Returns number of tiles in the selected state
     *
     * @param state state of tile
     * @return number of tiles in the selected state
     */
    public int getNumberOf(Tile.State state) {
        int num = 0;

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                if (tiles[i][j].getState() == state) {
                    ++num;
                }
            }
        }

        return num;
    }

    /**
     * Generates playing field.
     */
    private void generate() {
        generateMines();
        fillWithClues();
    }

    /**
     * Generates random mines on field.
     */
    private void generateMines() {
        Random rand = new Random();
        int actualMineCount = 0;

        int row, column;
        int min = 0;
        int rowMax = rowCount - 1;
        int columnMax = columnCount - 1;

        while (actualMineCount < mineCount) {
            row = rand.nextInt((rowMax - min) + 1) + min;
            column = rand.nextInt((columnMax - min) + 1) + min;

            if (tiles[row][column] == null) {
                tiles[row][column] = new Mine();
                ++actualMineCount;
            }
        }
    }

    /**
     * Fill tiles with clues
     */
    private void fillWithClues() {
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Clue(countAdjacentMines(i, j));
                }
            }
        }
    }

    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
        int allTilesCount = rowCount * columnCount;
        int remainingTilesCount = allTilesCount - getNumberOf(Tile.State.OPEN);

        return (remainingTilesCount == mineCount);
    }

    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    /**
     * Open adjacent tiles for a tile at specified position in the field
     *
     * @param row row number
     * @param column column number
     */
    private void openAdjacentTiles(int row, int column) {
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        openTile(actRow, actColumn);
                    }
                }
            }
        }
    }
}
