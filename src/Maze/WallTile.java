package Maze;

/** Represents a wall tile in the game. Players can never move onto/pass through wall tiles. Items can't be pushed onto wall
 * tiles either. Wall tiles are static */
public class WallTile extends Tile {
    /** Creates a wall tile
     * @param row The row (in regards to the board) of the wall tile
     * @param col The column (in regards to the board) of the wall tile
     * */
    public WallTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        return false; //Players can't walk onto walls
    }

}
