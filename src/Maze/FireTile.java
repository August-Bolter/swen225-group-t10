package Maze;

public class FireTile extends Tile {
    private boolean walkable = false;

    public FireTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        if (!walkable)
            for (int i = 0; i < main.getPlayer().getInventory().length; i++)
                if (main.getPlayer().getInventory()[i] instanceof Boots)
                    return true;

        return walkable;
    }

    @Override
    public void interact() {
        // If the player doesn't have boots 'kill them' and restart the level
    }
}
