import Maze.Enemy;

import java.util.Optional;

/**
 * Type of enemy.
 * Shoots fireblasts every 3 seconds.
 */
public class RedEnemy extends Enemy {
    /**
     * Creates a new red enemy.
     * @param row the starting row in reference to the level board
     * @param col the starting col in reference to the level board
     * @param direction the starting direction, UP, DOWN, LEFT, or RIGHT
     */
    public RedEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    @Override
    public void interact() {
        main.getFrame().displayInfo("Watch out for Charizards!");
        main.restartLevel(Optional.empty());
    }

    @Override
    public void onTick() {
        if (main.getTimeRemaining() % 3 == 0) {
            this.main.addEnemy(shoot());
        }
    }

    /**
     * @return a new fireball that has been shot
     */
    public Fireblast shoot(){
        Fireblast fb = new Fireblast(getRow(), getCol(), direction.toString());
        fb.setMain(main);
        fb.setCurrentPos(main.getLevelBoard().getBoard()[getRow()][getCol()]);
        fb.moveBlast();
        return fb;
    }


}
