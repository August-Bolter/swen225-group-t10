package Maze;

public class GreenEnemy extends Enemy{

    public GreenEnemy(int row, int col) {
        super(row, col);
    }

    public void interact() {
        Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, main.getPlayer().getDirection());
        if (desiredTile.isWalkable()) {
            getTile().removeItem(this);
            doMove(main.getLevelBoard().getTileAtPosition(currentPos, main.getPlayer().getDirection()));
        }
    }

}
