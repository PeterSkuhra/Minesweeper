package minesweeper;

import java.io.*;

/**
 * Settings class
 */
public class Settings implements Serializable {

    /**
     * Setting file path
     */
    private static final String SETTING_FILE =
            System.getProperty("user.home") +
            System.getProperty("file.separator") +
            "minesweeper.settings";

    /**
     * BEGINNER Settings object
     */
    public static final Settings BEGINNER =
            new Settings(9, 9, 10);

    /**
     * INTERMEDIATE Settings object
     */
    public static final Settings INTERMEDIATE =
            new Settings(16, 16, 40);

    /**
     * EXPERT Settings object
     */
    public static final Settings EXPERT =
            new Settings(16, 30, 99);

    /**
     * Row count.
     */
    private final int rowCount;

    /**
     * Column count.
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Settings(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
    }

    /**
     * Returns row count.
     *
     * @return row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Returns column count.
     *
     * @return column count
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Returns mine count.
     *
     * @return mine count
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * Returns saved setting from file, if it possible.
     * If not, returns BEGINNER setting.
     *
     * @return saved setting
     */
    public static Settings load() {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(SETTING_FILE);

            ObjectInputStream objectInputStream =
                    new ObjectInputStream(fileInputStream);

            return (Settings) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());

            return BEGINNER;
        }
    }

    /**
     * Saves setting into the file in user home directory.
     */
    public void save() {
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(SETTING_FILE);

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns hash code od settings.
     *
     * @return hash code of settings
     */
    @Override
    public int hashCode() {
        return (rowCount * columnCount * mineCount);
    }

    /**
     * Compares objects.
     *
     * @param o Object to compare
     * @return true if objects are equals, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Settings)) {
            throw new ClassCastException("Object can not to by compare!");
        }

        Settings settings = (Settings) o;

        return ((settings.getRowCount() == rowCount) &&
                (settings.getColumnCount() == columnCount) &&
                (settings.getMineCount() == mineCount));
    }
}
