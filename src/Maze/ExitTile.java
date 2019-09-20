package Maze;

/** Represents an exit tile in the game. When a player steps on an exit tile the level ends (as they have completed the level)
 *  and the new level is loaded in. */
public class ExitTile extends Tile {
    /** Creates an exit tile
     * @param row The row (in regards to the board) of the exit tile
     * @param col The column (in regards to the board) of the exit tile
     * */
    public ExitTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void interact() {
        //TODO: need to implement exit tile being able to interact with Player
    }
}
