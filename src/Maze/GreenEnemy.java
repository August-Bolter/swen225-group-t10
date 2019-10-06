package Maze;

public class GreenEnemy extends Enemy{

    public GreenEnemy(int row, int col) {
        super(row, col);
    }

    public void interact() {
        getTile().removeItem(this);
        doMove(main.getLevelBoard().getTileAtPosition(currentPos, main.getPlayer().getDirection()));
    }

}
