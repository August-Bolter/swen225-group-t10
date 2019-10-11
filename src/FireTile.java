import Maze.Tile;

public class FireTile extends Tile {
    public FireTile(int row, int col) {
        super(row, col);
    }

    public boolean isWalkable() {
        return true;
        //return this.main.getPlayer().isInInventory(new Boots(0, 0));
    }

    public void interact() {
    }
}
