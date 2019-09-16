package Maze;

import java.awt.*;

/** Represents a door in the game. Doors can be unlocked using a key with the same color as the door. By unlocking the door
 * the player can access other parts of the map. */

public class DoorTile extends Tile {
    private Color color; //The color of the door. The color can be blue, green, red or yellow
    private boolean isLocked; //Whether the door is locked
    /** Creates a door tile with a pre-defined color.
     * @param row The row (in regards to the board) of the door
     * @param col The column (in regards to the board) of the door
     * @param color The pre-defined color of the board
     * */
    public DoorTile(int row, int col, Color color) {
        super(row, col);
        this.color = color;
        this.isLocked = true; //The door by default (i.e. when created) is locked.
    }

    /** Returns whether the door is locked.
     * @return a boolean representing if the door is locked
     * */
    public boolean isLocked() {
        return isLocked;
    }

    /** Locks/unlocks the door.
     * @param locked a boolean representing if the door is getting locked
     * */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean isWalkable() {
        return !isLocked; //Players can walk through the door if it isn't locked.
    }
}
