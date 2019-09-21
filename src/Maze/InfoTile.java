package Maze;

import java.awt.*;

/** Represents an info tile. An info tile is similar to a free tile but when a player steps onto it helpful information (help
 * text) will be displayed. */
public class InfoTile extends Tile {
    private String info; //The helpful text

    /** Creates an info tile with a pre-determined helpful text
     * @param row The row (in regards to the board) of the info tile
     * @param col The column (in regards to the board) of the info tile
     * @param info The helpful text
     * */
    public InfoTile(int row, int col, String info) {
        super(row, col);
        this.info = info;
    }

    /** Gets the helpful text
     * @return The helpful text */
    public String getInfo() {
        return info;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void interact() {
        //TODO: need to implement info tile being able to interact with Player
    }

    @Override
    public void paint(Graphics g) {
        //TODO
    }
}
