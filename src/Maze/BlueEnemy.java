package Maze;

/**
 * Class for blueEnemy - this is a type of enemy that moves and the player must avoid.
 * It can't be 'defeated'
 */
public class BlueEnemy extends Enemy {
    /**
     * Constructor for blueenemy
     * @param row where blueenemy is located
     * @param col where blueenemy is located
     * @param direction blueenemy is moving
     */
    public BlueEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    /**
     * Method that gets a random direction exlcusively for the blueEnemy to move in
     * @return random direction
     */
    private LevelBoard.Direction getRandomDirection(){
        int x = main.getRandomInt() % 4;
        if (x == 1){
            return LevelBoard.Direction.LEFT;
        } else if (x == 2){
            return LevelBoard.Direction.RIGHT;
        } else if (x == 3){
            return LevelBoard.Direction.UP;
        } else {
            return LevelBoard.Direction.DOWN;
        }
    }

    /**
     * Method that moves the blueEnemy in the direction
     *
     */
    public void moveEnemy(){
        //tile moving to
        Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);

        //check to make sure that blueEnemy can move onto the tile
        while (!(desiredTile instanceof FreeTile) && (!(desiredTile instanceof InfoTile))) {
            setDirection(getRandomDirection());     //fetches a new direction to move in
            desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        }
        doMove(desiredTile);
        //if player is already there then they need to interact/kill the player
        if (currentPos.getItems().contains(main.getPlayer())) {
            interact();
        }
    }

    /**
     * Method used to 'interact' (when the player steps on a square the blueEnemy is on
     * Note: the level will restart if this occurs
     */
    public void interact() {
        main.getFrame().displayInfo("Watch out for moving Blastoise!");
        main.restartLevel();
    }

    /**
     * Setter method: sets the direction which the blueEnemy is going to be moving in
     * @param d direction blueEnemy is moving in
     */
    private void setDirection(LevelBoard.Direction d) {
        if (d == LevelBoard.Direction.LEFT) {
            direction = LevelBoard.Direction.LEFT;
        }
        else if (d == LevelBoard.Direction.RIGHT) {
            direction = LevelBoard.Direction.RIGHT;
        }
        else if (d == LevelBoard.Direction.UP) {
            direction = LevelBoard.Direction.UP;
        }
        else if (d == LevelBoard.Direction.DOWN) {
            direction = LevelBoard.Direction.DOWN;
        }
    }
}
