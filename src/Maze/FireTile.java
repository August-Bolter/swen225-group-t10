package Maze;

public class FireTile extends Tile {

    public FireTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        return main.getPlayer().isInInventory(new Boots(0,0));
    }

    @Override
    public void interact() {

    }
}
