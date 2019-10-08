package Maze;

public class BlueEnemy extends Enemy {
    public BlueEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    public LevelBoard.Direction getRandomDirection(){
        int x = (int)(Math.random()*((4-1)+1))+1;
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
        while (!(desiredTile instanceof FreeTile) && (!(desiredTile instanceof InfoTile))) {
            setDirection(getRandomDirection());
            desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        }
        doMove(desiredTile);
        if (currentPos.getItems().contains(main.getPlayer())) {
            interact();
        }
    }

    public void interact() {
        main.getFrame().displayInfo("Game over you lil bitch ass");
    }
}
