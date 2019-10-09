package Maze;

public class GreenEnemy extends Enemy{
    private boolean moved;

    public GreenEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    public void interact() {
        //Needs to check if it can be pushed into a wall in that direction
        //return main.getPlayer().isInInventory(new Boots(0,0));
        moved = false;
        if (main.getPlayer().isInInventory(new Gloves(0,0))) {
            LevelBoard.Direction playerDir = main.getPlayer().getDirection();
            Tile desiredTile = main.getLevelBoard().getTileAtPosition(currentPos, playerDir);
            if (desiredTile.isWalkable()) {
                getTile().removeItem(this);
                doMove(desiredTile);
                moved = true;
            }
        }
    }

    @Override
    public void onTick() {
        // Do nothing
    }

    public boolean hasMoved() {
        return moved;
    }
}
