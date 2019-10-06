package Maze;

public class BlueEnemy extends Enemy {
    public BlueEnemy(int row, int col) {
        super(row, col);
    }

    public LevelBoard.Direction getNextClockwiseDirection(LevelBoard.Direction d){
        if (d == LevelBoard.Direction.LEFT) {
            direction = LevelBoard.Direction.UP;
        }
        else if (d == LevelBoard.Direction.RIGHT) {
            direction = LevelBoard.Direction.DOWN;
        }
        else if (d == LevelBoard.Direction.UP) {
            direction = LevelBoard.Direction.RIGHT;
        }
        else if (d == LevelBoard.Direction.DOWN) {
            direction = LevelBoard.Direction.LEFT;
        }
        return direction;
    }

    public void setDirection(LevelBoard.Direction d) {
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

    public void moveEnemy(){
        Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        if (!(desiredTile instanceof FreeTile)) {
            setDirection(getNextClockwiseDirection(direction));
        }
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        doMove(newTile);
    }

    public void interact() {
        main.getFrame().displayInfo("Game over you lil bitch ass");
    }
}
