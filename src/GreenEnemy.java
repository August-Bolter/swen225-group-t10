import Maze.Enemy;
import Maze.LevelBoard;
import Maze.Tile;
import Maze.LevelBoard.Direction;

public class GreenEnemy extends Enemy {
    private boolean moved;

    public GreenEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    public void interact() {
        this.moved = false;
        if (this.main.getPlayer().isInInventory(new Gloves(0, 0))) {
            Direction playerDir = this.main.getPlayer().getDirection();
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

    public void onTick() {
    }

    public boolean hasMoved() {
        return this.moved;
    }
}
