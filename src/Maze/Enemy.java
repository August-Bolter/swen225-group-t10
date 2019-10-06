package Maze;

public class Enemy extends Item {
    private Tile currentPos;
    private Maze.LevelBoard.Direction direction;
    private int row, col;

    public Enemy(int row, int col){
        super(row, col);
        this.row = row;
        this.col = col;
        direction = LevelBoard.Direction.UP;
    }

    @Override
    public void interact() {
        //Kill player, game over
        main.getFrame().displayInfo("Game over you lil bitch ass");
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
        currentPos.removeItem(this);
        newTile.addItem(this);
        setCurrentPos(newTile);

    }

    public Tile getCurrentPos() {
        return currentPos;
    }

    public LevelBoard.Direction getDirection() {
        return direction;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public void setCurrentPos(Tile currentPos) {
        this.currentPos = currentPos;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setCol(int col) {
        this.col = col;
    }

    //straight = move in direction currently facing
    //always move straight
    //if you cannot move straight, then turn clockwise
}
