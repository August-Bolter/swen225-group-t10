package Maze;

import java.util.Objects;

public abstract class Tile {

    private int row;
    private int col;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return getRow() == tile.getRow() &&
                getCol() == tile.getCol();
    }

    private boolean isWalkable(){
        if (this instanceof WallTile){
            return false;
        }
        if (this instanceof DoorTile){
            DoorTile door = (DoorTile) this;
            if (door.isLocked()){
                return false;
            } else {
                return true;
            }
        }
        //TODO: implement logic for other tiles that cannot be walked on
        return true;
    }

}
