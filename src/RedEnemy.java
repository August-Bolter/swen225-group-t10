import Maze.Enemy;
import java.util.Optional;

public class RedEnemy extends Enemy {
    public RedEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    public void interact() {
        this.main.getFrame().displayInfo("Watch out for Charizards!");
        this.main.restartLevel(Optional.empty());
    }

    public void onTick() {
        if (this.main.getTimeRemaining() % 3 == 0) {
            this.main.addEnemy(this.shoot());
        }

    }

    public Fireblast shoot() {
        Fireblast fb = new Fireblast(this.getRow(), this.getCol(), this.direction.toString());
        fb.setMain(this.main);
        fb.setCurrentPos(this.main.getLevelBoard().getBoard()[this.getRow()][this.getCol()]);
        fb.moveBlast();
        return fb;
    }
}