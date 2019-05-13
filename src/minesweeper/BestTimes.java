package minesweeper;

import java.util.*;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {

    /**
     * List of best player times.
     */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    /**
     * Returns an iterator over a set of  best times.
     *
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    public String toString() {
        String s = "";
        Formatter formatter = new Formatter();

        int i = 0;
        for (PlayerTime playerTime : playerTimes) {
            // s += playerTime.toString() + "\n";
            formatter.format("%3s:\t%s\n", ++i, playerTime.toString());
        }

        return formatter.toString();
    }

    /**
     * Adds player time to best times.
     *
     * @param name name of the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
        playerTimes.add(new PlayerTime(name, time));

        Collections.sort(playerTimes);
    }

    /**
     * Reset all records of times.
     */
    public void reset() {
        playerTimes.clear();
    }

    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime> {

        /** Player name. */
        private final String name;

        /** Playing time in seconds. */
        private final int time;

        /**
         * Constructor.
         *
         * @param name player name
         * @param time playing game time in seconds
         */
        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

        /**
         * Returns a string representation of the object.
         *
         * @return a string representation of the object
         */
        @Override
        public String toString() {
            return getName() + ": " + getTime() + "s";
        }

        /**
         * Returns a negative integer, zero, or a positive integer as this
         * object is less than, equal to, or greater than the specified object.
         *
         * @param o the object to be compared.
         * @return  -1, 0, 1
         */
        public int compareTo(PlayerTime o) {
            if (o == null) {
                throw new NullPointerException("No player to compare");
            }

            return Integer.compare(o.getTime(), time);
        }

        /**
         * Returns player name
         *
         * @return player name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns player time
         *
         * @return player time
         */
        public int getTime() {
            return time;
        }
    }
}
