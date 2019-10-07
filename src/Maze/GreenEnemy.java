package Maze;

public class GreenEnemy extends Enemy{
    private boolean moved;

    public GreenEnemy(int row, int col) {
        super(row, col);
    }

    public void interact() {
        //Needs to check if it can be pushed into a wall in that direction
        LevelBoard.Direction playerDir = main.getPlayer().getDirection();
        moved = false;
        Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, playerDir);
        if (desiredTile.isWalkable()) {
            getTile().removeItem(this);
            doMove(desiredTile);
            moved = true;
        }
    }

    public boolean hasMoved() {
        return moved;
    }
}
