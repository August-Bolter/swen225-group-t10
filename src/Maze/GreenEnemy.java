package Maze;

/**
 * Represents a green enemy.
 * This enemy blocks the player.
 * Gloves are needed to push it out of the way.
 */
public class GreenEnemy extends Enemy{
    private boolean moved;

    /**
     * Creates a new GreenEnemy
     * @param row the starting row in reference to the level board
     * @param col the starting col in reference to the level board
     * @param direction the starting direction, can be UP, DOWN, LEFT, OR RIGHT
     */
    public GreenEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    @Override
    public void interact() {
        moved = false;
        if (main.getPlayer().isInInventory(new Gloves(0,0))) {
            LevelBoard.Direction playerDir = main.getPlayer().getDirection();
            Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, playerDir);
            if (desiredTile.isWalkable()) {
                getTile().removeItem(this);
                doMove(desiredTile);
                moved = true;
            }
        }
    }

    /**
     * @return true if the enemy has been moved, false if not
     */
    public boolean hasMoved() {
        return moved;
    }
}
