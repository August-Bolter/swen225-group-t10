package Maze;

import Application.Main;

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
     * @param main the main class, i.e. the current game
     */
    public Fireblast(int row, int col, String direction, Main main) {
        super(row, col, direction);
        this.main = main;
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
        main.getFrame().displayInfo("Watch out for Fire Blasts!");
        main.restartLevel();
    }
}
