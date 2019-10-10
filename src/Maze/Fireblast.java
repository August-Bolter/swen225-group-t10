package Maze;

import Application.Main;

import java.util.Optional;

/**
 * Fireblast class.
 * Fireblasts are shot by the red enemies.
 */
public class Fireblast extends RedEnemy {
    /**
     * Creates a new Fireblast.
     * @param row the starting row in reference to the level board
     * @param col the starting col in reference to the level board
     * @param direction the direction the Fireblast is travelling
     */
    public Fireblast(int row, int col, String direction) {
        super(row, col, direction);
    }

    /**
     * Moves the blast in one specific direction
     */
    public void moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        if (newTile.isWalkable()) {
            doMove(newTile);
            if (currentPos.getItems().contains(main.getPlayer())) {
                interact();
            }
        } else {
            currentPos.removeItem(this);
        }
    }

    /**
     * Method for interacting with the fireblast - this occurs when the player walks into the same tile as the fireblast
     * When this occurs, the game starts again
     */
    public void interact() {
        super.main.getFrame().displayInfo("Watch out for Fire Blasts!");
        super.main.restartLevel(Optional.empty());
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
