import Application.Main;
import Maze.Tile;
import java.util.Optional;

public class Fireblast extends RedEnemy {
    public Fireblast(int row, int col, String direction) {
        super(row, col, direction);
    }

    public void moveBlast() {
        Tile newTile = this.main.getLevelBoard().getTileAtPosition(this.currentPos, this.direction);
        if (newTile.isWalkable()) {
            this.doMove(newTile);
            if (this.currentPos.getItems().contains(this.main.getPlayer())) {
                this.interact();
            }
        } else {
            this.currentPos.removeItem(this);
        }

    }

    public void onTick() {
        this.moveBlast();
    }

    public void interact() {
        super.main.getFrame().displayInfo("Watch out for Fire Blasts!");
        super.main.restartLevel(Optional.empty());
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
