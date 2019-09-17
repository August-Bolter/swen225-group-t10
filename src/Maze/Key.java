package Maze;
import java.awt.*;

/** Represents a key in the game. The key has a color. The player can pick the key up and use it to unlock doors with the
 * same color. */
public class Key extends Item {
    String color; //The key can be blue, red, green or yellow

    /** Creates a key.
     * @param row The row (in regards to the board) of the key.
     * @param col The column (in regards to the board) of the key.
     * @param color The color of the key. */
    public Key(int row, int col, String color) {
        super(row, col);
        this.color = color;
    }

    /**
     * @return the key's color
     */
    public String getColor() {
        return color;
    }
}
