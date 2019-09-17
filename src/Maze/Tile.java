package Maze;

import java.util.Objects;

/** An abstract class representing a tile in the game. This abstract class is extended by concrete classes like DoorTile,
 * ExitTile etc. The board is made up of tiles (with items on some of the tiles). */
public abstract class Tile {

    private int row;
    private int col;

    /** Creates a tile
     * @param row The row (in regards to the board) of the tile
     * @param col The column (in regards to the board) of the tile */
    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    /** Gets the row of the tile */
    public int getRow() {
        return row;
    }

    /** Gets the column of the tile */
    public int getCol() {
        return col;
    }

    /** Checks if an Object is the same as (equals) this tile.
     * @param o The object that the tile is being compared to
     * @return Whether the object is the same as the tile
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        /* If the tile is in the same location as 'this' (this tile) then they are the same tile. */
        return getRow() == tile.getRow() &&
                getCol() == tile.getCol();
    }

    /** An abstract class which determines if a tile is walkable.
     * @return Whether the tile is walkable */
    public abstract boolean isWalkable();

}
