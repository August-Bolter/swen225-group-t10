package Maze;

import java.awt.*;

public class DoorTile extends Tile {
    Color color;
    boolean isLocked = true;
    public DoorTile(int row, int col, Color color, boolean isLocked) {
        super(row, col);
        this.color = color;
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }
}
