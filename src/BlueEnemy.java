import Maze.Enemy;
import Maze.FreeTile;
import Maze.InfoTile;
import Maze.Tile;
import Maze.LevelBoard.Direction;
import java.util.Optional;

public class BlueEnemy extends Enemy {
    public BlueEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    private Direction getRandomDirection() {
        int x = this.main.getRandomInt() % 4;
        if (x == 1) {
            return Direction.LEFT;
        } else if (x == 2) {
            return Direction.RIGHT;
        } else {
            return x == 3 ? Direction.UP : Direction.DOWN;
        }
    }

    public void moveEnemy() {
        Tile desiredTile;
        for(desiredTile = this.main.getLevelBoard().getTileAtPosition(this.currentPos, this.direction); !(desiredTile instanceof FreeTile) && !(desiredTile instanceof InfoTile); desiredTile = this.main.getLevelBoard().getTileAtPosition(this.currentPos, this.direction)) {
            this.setDirection(this.getRandomDirection());
        }

        this.doMove(desiredTile);
        if (this.currentPos.getItems().contains(this.main.getPlayer())) {
            this.interact();
        }

    }

    public void interact() {
        this.main.getFrame().displayInfo("Watch out for moving Blastoise!");
        this.main.restartLevel(Optional.empty());
    }

    public void onTick() {
        this.moveEnemy();
    }

    private void setDirection(Direction d) {
        if (d == Direction.LEFT) {
            this.direction = Direction.LEFT;
        } else if (d == Direction.RIGHT) {
            this.direction = Direction.RIGHT;
        } else if (d == Direction.UP) {
            this.direction = Direction.UP;
        } else if (d == Direction.DOWN) {
            this.direction = Direction.DOWN;
        }

    }
}