package Maze;

/** Represents a free space (free tile) in the game. All free tiles can be moved onto by the player. Objects can also be pushed
 * onto/off free tiles. They may hold items as well. */
public class FreeTile extends Tile {
    /** Creates a free tile
     * @param row The row (in regards to the board) of the free tile
     * @param col The column (in regards to the board) of the free tile
     * */
    public FreeTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
