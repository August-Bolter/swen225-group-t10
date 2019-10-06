package Maze;

public class Enemy extends Item {
    protected Tile currentPos;
    protected Maze.LevelBoard.Direction direction;
    private int row, col;

    public Enemy(int row, int col){
        super(row, col);
        this.row = row;
        this.col = col;
        direction = LevelBoard.Direction.DOWN;
    }

    @Override
    public void interact() {
//        //Kill player, game over
//        if (this instanceof RedEnemy || this instanceof BlueEnemy) {
//            main.getFrame().displayInfo("Game over you lil bitch ass");
//        } else if (this instanceof GreenEnemy){
//            getTile().removeItem(this);
//            doMove(main.getLevelBoard().getTileAtPosition(currentPos, main.getPlayer().getDirection()));
//        }
    }

    public void doMove(Tile toMoveTo){
        currentPos.removeItem(this);
        toMoveTo.addItem(this);
        setCurrentPos(toMoveTo);
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
