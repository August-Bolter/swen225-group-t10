import Maze.Enemy;
import Maze.LevelBoard;
import Maze.Tile;

/**
 * Represents a green enemy.
 * This enemy blocks the player.
 * Gloves are needed to push it out of the way.
 */
public class GreenEnemy extends Enemy {
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
        this.moved = false;
        if (this.main.getPlayer().isInInventory(new Gloves(0, 0))) {
            LevelBoard.Direction playerDir = this.main.getPlayer().getDirection();
            Tile desiredTile = this.main.getLevelBoard().getTileAtPosition(this.currentPos, playerDir);
            if (desiredTile.isWalkable()) {
                this.getTile().removeItem(this);
                this.doMove(desiredTile);
                this.moved = true;
            } else {
                this.main.getPlayer().move(this.main.getLevelBoard().getTileAtPosition(this.currentPos, LevelBoard.directionInverse(playerDir)));
            }
        } else {
            this.main.getPlayer().move(this.main.getLevelBoard().getTileAtPosition(this.currentPos, LevelBoard.directionInverse(this.main.getPlayer().getDirection())));
        }

    }

    @Override
    public void onTick() {}

    /**
     * @return true if the enemy has been moved, false if not
     */
    public boolean hasMoved() {
        return moved;
    }
}
